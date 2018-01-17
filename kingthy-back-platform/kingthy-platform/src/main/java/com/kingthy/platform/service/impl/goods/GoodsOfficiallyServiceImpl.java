package com.kingthy.platform.service.impl.goods;

import com.google.gson.Gson;
import com.kingthy.platform.dto.order.GoodsOfficiallyReq;
import com.kingthy.platform.entity.goods.GoodsOfficially;
import com.kingthy.platform.mapper.goods.GoodsOfficiallyMapper;
import com.kingthy.platform.response.WebApiResponse;
import com.kingthy.platform.service.goods.GoodsOfficiallyService;
import com.kingthy.platform.util.SnFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Random;

/**
 * @AUTHORS xumin
 * @Description:
 * @DATE Created by 15:56 on 2017/7/26.
 * @Modified by:
 */

@Service
public class GoodsOfficiallyServiceImpl implements GoodsOfficiallyService
{

    @Autowired
    private GoodsOfficiallyMapper goodsOfficiallyMapper;

    @Autowired
    private SnFeignClient snFeignClient;

    /**
     * 官方商品详情
     * @param goodsUuid
     * @return
     * @throws Exception
     */
    @Override
    public WebApiResponse<?> showOfficiallyDetail(String goodsUuid) throws Exception {
        return WebApiResponse.success(goodsOfficiallyMapper.selectOfficiallyGoodsInfoByUuid(goodsUuid));
    }

    /**
     * 创建官方商品
     * @param req
     * @return
     * @throws Exception
     */
    @Override
    public WebApiResponse<?> createGoods(GoodsOfficiallyReq req) throws Exception {

        String snType = null;
        switch (req.getGoodsCategoryType()){

            case 1:
                snType = "coat";
                break;
            case 2:
                snType = "trousers";
                break;
            case 3:
                snType = "dress";
                break;
            case 4:
                snType = "suit";
                break;
        }

        if (snType == null){
            return WebApiResponse.error(WebApiResponse.ResponseMsg.PARAMETER_ERROR.getValue());
        }

        String sn = null;

        WebApiResponse<String> response = snFeignClient.generateSn(snType);

        if (WebApiResponse.SUCCESS_CODE == response.getCode()){
            sn = response.getData();
        }

        if(sn == null){
            return WebApiResponse.error("创建产品编号失败");
        }

        GoodsOfficially goodsOfficially = new GoodsOfficially();
        Gson gson = new Gson();

        goodsOfficially.setSn(sn);
        goodsOfficially.setCreator(req.getMemberUuid());
        goodsOfficially.setCreateDate(new Date());
        goodsOfficially.setCover(req.getCover());
        goodsOfficially.setDesinger(req.getDesigner());
        goodsOfficially.setGoodsCategoryUuid(req.getGoodsCategoryUuid());
        goodsOfficially.setGoodsDetails(req.getGoodsDetails());
        goodsOfficially.setGoodsImage(gson.toJson(req.getGoodsImages()));
        goodsOfficially.setGoodsName(req.getGoodsName());
        goodsOfficially.setGoodsCategoryType(req.getGoodsCategoryType());
        goodsOfficially.setVersion(0);
        goodsOfficially.setDelFlag(false);
        goodsOfficially.setStatus(req.getStatus());

        //计算价格...等待产品提供算法. 目前先填一个随机数
        Random random = new Random();

        Integer standardPrice = random.nextInt(1500) + 500;

        goodsOfficially.setStandardPrice(new BigDecimal(standardPrice));
        goodsOfficially.setOpusUuid(req.getOpusUuid());

        //解析模型文件 获取面料,物料信息...


        int result = goodsOfficiallyMapper.insert(goodsOfficially);

        return result > 0 ? WebApiResponse.success(WebApiResponse.ResponseMsg.SUCCESS.getValue()) : WebApiResponse.error(WebApiResponse.ResponseMsg.FAIL.getValue());
    }

    /**
     * 商品上下架
     * @param req
     * @return
     * @throws Exception
     */
    @Override
    public WebApiResponse<?> upOrDownBatch(GoodsOfficiallyReq.UpOrDownBatch req) throws Exception {

        int result = goodsOfficiallyMapper.upOrDownBatch(req);

        return result > 0 ? WebApiResponse.success(WebApiResponse.ResponseMsg.SUCCESS.getValue()) : WebApiResponse.error(WebApiResponse.ResponseMsg.FAIL.getValue());
    }
}
