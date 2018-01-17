package com.kingthy.platform.service.impl.goods;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.StringUtil;
import com.google.gson.Gson;
import com.kingthy.platform.dto.goods.*;
import com.kingthy.platform.dto.goods.GoodsUuidArrayReq.GoodsOperation;
import com.kingthy.platform.entity.goods.Goods;
import com.kingthy.platform.entity.goods.GoodsOfficially;
import com.kingthy.platform.entity.goodsPutonScanner.GoodsPutonScanner;
import com.kingthy.platform.mapper.goods.GoodsMapper;
import com.kingthy.platform.mapper.goods.GoodsOfficiallyMapper;
import com.kingthy.platform.mapper.goodsPutonScanner.GoodsPutonScannerMapper;
import com.kingthy.platform.response.WebApiResponse;
import com.kingthy.platform.service.goods.GoodsService;
import com.kingthy.platform.util.DateUtil;
import com.kingthy.platform.util.SnFeignClient;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 *
 * GoodsServiceImpl(商品业务层实现类)
 * 
 * 陈钊 2017年4月5日 下午7:59:58
 * 
 * @version 1.0.0
 *
 */
@Service(value = "goodsService")
public class GoodsServiceImpl implements GoodsService
{
    
    @Autowired
    private GoodsMapper goodsMapper;

    @Autowired
    private GoodsOfficiallyMapper goodsOfficiallyMapper;

    @Autowired
    private GoodsPutonScannerMapper goodsPutonScannerMapper;

    @Autowired
    private SnFeignClient snFeignClient;
    
    @Autowired
    private HttpServletRequest request;
    
    /**
     * 暂定初始版本为1
     */
    private static final int version = 1;
    
    /**
     * 
     * getCurrentUser(得到当前操作用户的uuid)
     * 
     * @return <b>创建人：</b>陈钊<br/>
     *         String
     * @exception @since 1.0.0
     */
    private String getCurrentUser()
    {
        String currentUser = request.getHeader("uuid");
        if (currentUser == null)
        {
            return "";
        }
        return currentUser;
    }

    @Transactional(readOnly = true)
    @Override
    public PageInfo<GoodsPageDto> findByPage(GoodsPageReq goodsPageReq)
    {
        PageHelper.startPage(goodsPageReq.getPageNum(), goodsPageReq.getPageSize());

        goodsPageReq.setEndTime(DateUtil.putEndTime(goodsPageReq.getEndTime()));

        List<GoodsPageDto> goodslist = goodsMapper.findByPage(goodsPageReq);
        PageInfo<GoodsPageDto> pageInfo = new PageInfo<>(goodslist);
        return pageInfo;
    }

    @Transactional(readOnly = true)
    @Override
    public WebApiResponse<?> officiallyList(GoodsPageReq goodsPageReq) {

        PageHelper.startPage(goodsPageReq.getPageNum(), goodsPageReq.getPageSize());

        goodsPageReq.setEndTime(DateUtil.putEndTime(goodsPageReq.getEndTime()));

        PageInfo<GoodsPageDto> pageInfo = new PageInfo<>(goodsOfficiallyMapper.findByPage(goodsPageReq));
        return WebApiResponse.success(pageInfo);
    }

    @Transactional
    @Override
    public int changeDelFlagBatch(String[] uuidArray, boolean delFlag)
    {
        Date currentDate = new Date();
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("uuidArray", uuidArray);
        paramMap.put("delFlag", delFlag);
        paramMap.put("modifyDate", currentDate);
        paramMap.put("modifier", getCurrentUser());
        // 如果是将商品放入回收站，那就同时删除商品扫描表的对应记录，并且给更新参数中加上商品下架状态
        if (delFlag)
        {
            goodsPutonScannerMapper.deleteBatch(paramMap);
            paramMap.put("status", GoodsReq.GoodsStatus.goodsDown.getValue());
        }
        // 更新商品表的记录状态
        int result = goodsMapper.changeDelFlagBatch(paramMap);
        return result;
    }
    
    @Transactional
    @Override
    public String add(GoodsReq goodsAddParam)
    {
        int result = 0;
        // 如果操作是下架，则直接改变商品基础信息及状态，否则进入下一步处理
        if (goodsAddParam.getGoodsOperation().getValue() == GoodsReq.GoodsOperation.down.getValue())
        {
            goodsAddParam.setStatus(GoodsReq.GoodsStatus.goodsDown.getValue());
            result = addBaseInfo(goodsAddParam);
        }
        else
        { // 如果是上架
          // 判断上架方式，如果是立即上架，就立即上架，改变状态，不添加上架扫描表任务；
            if (goodsAddParam.getGoodsPutOnMethod().getValue() == GoodsReq.GoodsPutOnMethod.immediately.getValue())
            {
                goodsAddParam.setStatus(GoodsReq.GoodsStatus.goodsOn.getValue());
                result = addBaseInfo(goodsAddParam);
            }
            else
            {
                // 如果是延迟上架，改变商品状态为待上架，并在扫描表中添加一条待扫描任务；
                goodsAddParam.setStatus(GoodsReq.GoodsStatus.goodsWait.getValue());
                result = addBaseInfo(goodsAddParam);
                // 添加扫描任务
                result = addScannerTask(goodsAddParam);
            }
        }
        if (result > 0)
        {
            return goodsAddParam.getUuid().toString();
        }
        return String.valueOf(result);
        
    }
    
    @Transactional
    @Override
    public int edit(GoodsReq goodsEditParam)
    {
        int result = 0;
        // 如果操作是下架，则直接改变商品基础信息及状态
        if (goodsEditParam.getGoodsOperation().getValue() == GoodsReq.GoodsOperation.down.getValue())
        {
            // 删除扫描表中对应记录
            goodsPutonScannerMapper.deleteByGoodsUuid(goodsEditParam.getUuid());
            // 更改商品上架状态和基本信息
            goodsEditParam.setStatus(GoodsReq.GoodsStatus.goodsDown.getValue());
            result = editBaseInfo(goodsEditParam);
        }
        else
        {
            // 如果操作是上架，则根据商品上架状态进行判断
            if (goodsEditParam.getStatus() == GoodsReq.GoodsStatus.goodsOn.getValue())// 如果商品已经上架，直接修改基本信息
            {
                result = editBaseInfo(goodsEditParam);
            }
            else
            {// 如果商品是待上架或者下架状态，则先删除扫描表中的对应记录
             // 删除扫描表中对应记录
                goodsPutonScannerMapper.deleteByGoodsUuid(goodsEditParam.getUuid());
                // 如果是立即上架，则直接上架，不用添加扫描任务
                if (goodsEditParam.getGoodsPutOnMethod().getValue() == GoodsReq.GoodsPutOnMethod.immediately.getValue())
                {
                    // 修改商品基本信息
                    goodsEditParam.setStatus(GoodsReq.GoodsStatus.goodsOn.getValue());
                    result = editBaseInfo(goodsEditParam);
                }
                else
                {
                    // 延迟上架，添加扫描任务
                    result = addScannerTask(goodsEditParam);
                    // 修改商品基本信息
                    goodsEditParam.setStatus(GoodsReq.GoodsStatus.goodsWait.getValue());
                    result = editBaseInfo(goodsEditParam);
                }
            }
            
        }
        return result;
    }

    /**
     * addScannerTask(添加扫描任务到商品上架扫描表中)
     * 
     * @param goodsEditParam
     * @return <b>创建人：</b>陈钊<br/>
     *         int
     * @exception @since 1.0.0
     */
    private int addScannerTask(GoodsReq goodsEditParam)
    {
        int result;
        GoodsPutonScanner goodsPutonScanner = new GoodsPutonScanner();
        goodsPutonScanner.setCreateDate(new Date());
        goodsPutonScanner.setCreator(getCurrentUser());
        goodsPutonScanner.setGoodsPutOnTime(goodsEditParam.getPutOnTime());
        goodsPutonScanner.setGoodsUuid(goodsEditParam.getUuid());
        goodsPutonScanner.setStatus(false);// 未上架
        goodsPutonScanner.setDelFlag(false);
        goodsPutonScanner.setVersion(1);
        result = goodsPutonScannerMapper.insert(goodsPutonScanner);
        return result;
    }
    
    /**
     * 
     * addBaseInfo(添加商品基本信息)
     * 
     * @param goodsAddParam
     * @return <b>创建人：</b>陈钊<br/>
     *         int
     * @exception @since 1.0.0
     */
    private int addBaseInfo(GoodsReq goodsAddParam)
    {
        Goods goods = new Goods();
        BeanUtils.copyProperties(goodsAddParam, goods);
        Map<String, String> resultMap = imageDeal(goods.getGoodsImage());
        goods.setGoodsImage(resultMap.get("imgStr"));
        goods.setCover(resultMap.get("cover"));
        Date currentDate = new Date();
        goods.setPutOnMethod(goodsAddParam.getGoodsPutOnMethod().getValue());
        goods.setCreateDate(currentDate);
        goods.setModifyDate(currentDate);
        goods.setVersion(version);
        goods.setCreator(getCurrentUser());
        goods.setDelFlag(false);
        int result = goodsMapper.insert(goods);
        goodsAddParam.setUuid(goods.getUuid());
        return result;
    }
    
    /**
     * editBaseInfo(修改商品基本信息)
     * 
     * @param goodsEditParam
     * @return <b>创建人：</b>陈钊<br/>
     *         int
     * @exception @since 1.0.0
     */
    private int editBaseInfo(GoodsReq goodsEditParam)
    {
        Example example = new Example(Goods.class);
        Criteria criteria = example.createCriteria();
        criteria.andEqualTo("uuid", goodsEditParam.getUuid());
        List<Goods> oldGoodsList = goodsMapper.selectByExample(example);
        int result = 0;
        if (oldGoodsList != null && oldGoodsList.size() > 0)
        {
            Goods oldGoods = oldGoodsList.get(0);
            oldGoods.setModifyDate(new Date());
            oldGoods.setGoodsName(goodsEditParam.getGoodsName());
            oldGoods.setGoodsFeature(goodsEditParam.getGoodsFeature());
            oldGoods.setStandardPrice(goodsEditParam.getStandardPrice());
            oldGoods.setReturnPoint(goodsEditParam.getReturnPoint());
            oldGoods.setPutOnMethod(goodsEditParam.getGoodsPutOnMethod().getValue());
            oldGoods.setPutOnTime(goodsEditParam.getPutOnTime());
            oldGoods.setGoodsTags(goodsEditParam.getGoodsTags());
            oldGoods.setGoodsDetails(goodsEditParam.getGoodsDetails());
            Map<String, String> resultMap = imageDeal(goodsEditParam.getGoodsImage());
            oldGoods.setGoodsImage(resultMap.get("imgStr"));
            oldGoods.setCover(resultMap.get("cover"));
            oldGoods.setStatus(goodsEditParam.getStatus());
            oldGoods.setModifier(getCurrentUser());
            result = goodsMapper.updateByExample(oldGoods, example);
        }
        return result;
    }
    
    @Override
    public PageInfo<GoodsPageDto> findDelByPage(DelGoodsPageReq delGoodsPageReq)
    {
        PageHelper.startPage(delGoodsPageReq.getPageNum(), delGoodsPageReq.getPageSize());
        List<GoodsPageDto> goodsDelList = goodsMapper.findDelByPage(delGoodsPageReq);
        PageInfo<GoodsPageDto> pageInfo = new PageInfo<GoodsPageDto>(goodsDelList);
        return pageInfo;
    }
    
    @Override
    public int delete(String[] uuidArray)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("uuidArray", uuidArray);
        // 删除商品表的记录
        int result = goodsMapper.deleteBatch(paramMap);
        return result;
    }
    
    @Transactional
    @Override
    public int upOrDownBatch(String[] uuidArray, GoodsOperation opration)
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("uuidArray", uuidArray);
        paramMap.put("status", opration.getValue());
        paramMap.put("modifyDate", new Date());
        paramMap.put("modifier", getCurrentUser());
        // 不论是立即上架还是立即下架，都先删除扫描表对应商品的信息，取消对其的扫描
        int result = goodsPutonScannerMapper.deleteBatch(paramMap);
        // 更新商品信息
        result = goodsMapper.upOrDownBatch(paramMap);
        return result;
    }
    
    @Override
    public GoodsDto findGoodsByUuid(String uuid)
    {
        GoodsDto goodsDto = goodsMapper.findGoodsByUuid(uuid);
        return goodsDto;
    }

    @Override
    public WebApiResponse<?> showDetail(String goodsUuid) throws Exception {

        return WebApiResponse.success(goodsMapper.selectGoodsInfoByUuid(goodsUuid));
    }

    /**
     * 个人商品 申请 成为官方商品
     * @param goodsUuid
     * @param memberUuid
     * @return
     * @throws Exception
     */
    @Override
    public WebApiResponse<String> applyOfficially(String goodsUuid, String memberUuid) throws Exception {

        Gson gson = new Gson();

        Goods goods = goodsMapper.findGoodsInfoByUuid(goodsUuid);

        if (goods == null){
            return WebApiResponse.error(WebApiResponse.ResponseMsg.PARAMETER_ERROR.getValue());
        }

        String sn = getSn("coat");

        if (StringUtil.isEmpty(sn)){
            return WebApiResponse.error("生成SN失败");
        }

        GoodsOfficially officially = gson.fromJson(gson.toJson(goods), GoodsOfficially.class);

        officially.setUuid(null);
        officially.setSn(sn);
        officially.setCreateDate(new Date());
        officially.setCreator(memberUuid);
        officially.setModifier(memberUuid);
        officially.setModifyDate(new Date());

        return goodsOfficiallyMapper.insert(officially) > 0 ? WebApiResponse.success(WebApiResponse.ResponseMsg.SUCCESS.getValue())
                : WebApiResponse.error(WebApiResponse.ResponseMsg.FAIL.getValue());

    }

    /**
     * imageDeal(图片信息处理，生成缩略图，大图等)
     * 
     * @param img
     * @return <b>创建人：</b>陈钊<br/>
     *         String
     * @exception @since 1.0.0
     */
    private Map<String, String> imageDeal(String img)
    {
        Map<String, String> resultMap = new HashMap<String, String>();
        String cover = "";// 封面
        JSONArray jsonOldImages = JSONArray.parseArray(img);
        JSONArray jsonNewImages = new JSONArray();
        
        if (jsonOldImages != null && jsonOldImages.size() > 0)
        {
            Map<String, Object> imageMap = null;
            for (Object object : jsonOldImages)
            {
                imageMap = new HashMap<String, Object>();
                JSONObject imgJson = JSONObject.parseObject(object.toString());
                String opusImage = imgJson.getString("opusImage");
                if (opusImage.contains("."))
                {
                    String imgUrl = opusImage.substring(0, opusImage.lastIndexOf("."));
                    String suffix = opusImage.substring(opusImage.lastIndexOf("."));
                    imageMap.put("opusImage", opusImage);
                    imageMap.put("maxImg", imgUrl + "_500x500" + suffix);
                    imageMap.put("minImg", imgUrl + "_50x50" + suffix);
                    imageMap.put("midImg", imgUrl + "_200x200" + suffix);
                    imageMap.put("isCover", imgJson.getBoolean("isCover"));
                    jsonNewImages.add(imageMap);
                    if (imgJson.getBoolean("isCover"))
                    {
                        cover = imgUrl + "_50x50" + suffix;
                    }
                }
            }
        }

        resultMap.put("cover", cover);
        resultMap.put("imgStr", jsonNewImages.toJSONString());
        return resultMap;
    }

    private String getSn(String goodsType){

        WebApiResponse<String> webApiResponse = snFeignClient.generateSn(goodsType);

        return webApiResponse.getCode() != WebApiResponse.SUCCESS_CODE ? null : webApiResponse.getData();

    }
}
