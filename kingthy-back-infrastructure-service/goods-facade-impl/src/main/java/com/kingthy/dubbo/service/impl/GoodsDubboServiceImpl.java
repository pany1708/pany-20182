package com.kingthy.dubbo.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kingthy.dto.GoodsDTO;
import com.kingthy.dto.GoodsIncrement;
import com.kingthy.dto.GoodsOrderDTO;
import com.kingthy.dto.GoodsPageDto;
import com.kingthy.dubbo.goods.service.GoodsDubboService;
import com.kingthy.entity.Goods;
import com.kingthy.exception.BizException;
import com.kingthy.mapper.GoodsMapper;
import com.kingthy.request.DelGoodsPageReq;
import com.kingthy.request.EsGoodsReq;
import com.kingthy.request.GoodsPageReq;

import tk.mybatis.mapper.entity.Example;

/**
 * @author xumin
 * @Description:
 * @DATE Created by 16:02 on 2017/8/7.
 * @Modified by:
 */
@Service(version = "1.0.0", timeout = 10000)
public class GoodsDubboServiceImpl implements GoodsDubboService
{

    @Autowired
    private GoodsMapper goodsMapper;

    @Override
    public int insert(Goods goods)
    {
        return goodsMapper.insert(goods);
    }

    @Override
    public Goods queryMaterielInfoByUUID(String uuid) {
        return goodsMapper.queryMaterielInfoByUUID(uuid);
    }

    @Override
    public String queryDesingerByUUID(String uuid) {
        return goodsMapper.queryDesingerByUUID(uuid);
    }

    @Override
    public Goods queryGoodsByUUID(String uuid) {
        return goodsMapper.queryGoodsByUUID(uuid);
    }

    @Override
    public String insertReturn(Goods goods)
    {
        try
        {
            goodsMapper.insertSelective(goods);
        }
        catch (Exception e)
        {
            throw new BizException("添加作品失败");
        }
        return goods.getUuid();
    }

    @Override
    public int updateByUuid(Goods var)
    {

        Example example = new Example(Goods.class);
        example.createCriteria().andEqualTo("uuid", var.getUuid());

        return goodsMapper.updateByExampleSelective(var, example);
    }

    @Override
    public List<Goods> selectAll()
    {
        return goodsMapper.selectAll();
    }

    @Override
    public Goods selectByUuid(String uuid)
    {
        Goods var1 = new Goods();
        var1.setUuid(uuid);
        return selectOne(var1);
    }

    @Override
    public int selectCount(Goods goods)
    {
        return goodsMapper.selectCount(goods);
    }

    @Override
    public List<Goods> select(Goods var1)
    {
        return goodsMapper.select(var1);
    }

    @Override
    public Goods selectOne(Goods var1)
    {
        return goodsMapper.selectOne(var1);
    }

    @Override
    public PageInfo<Goods> queryPage(Integer pageNum, Integer pageSize, Goods goods)
    {
        PageHelper.startPage(pageNum, pageSize);
        return new PageInfo<>(goodsMapper.findGoodsByPage(goods));
    }

    @Override
    public List<Goods> getGoodsListByIds(List<String> productUuids)
    {
        return goodsMapper.getGoodsListByIds(productUuids);
    }

    @Override
    public List<GoodsDTO.CoverInfo> getGoodsCoverListByIds(List<String> productUuids)
    {
        return goodsMapper.getGoodsCoverListByIds(productUuids);
    }

    @Override
    public List<Goods> selectVersionByGoodsUuid(List<String> listGoodsUuid)
    {
        return goodsMapper.selectVersionByGoodsUuid(listGoodsUuid);
    }

    @Override
    public List<Goods> selectGoodsUuid(List<String> listGoodsUuid)
    {
        System.out.println("-------------------------------" + listGoodsUuid.size());
        return goodsMapper.selectGoodsUuid(listGoodsUuid);
    }

    @Override
    public int updateGoodsClicks(Goods goods)
    {
        return goodsMapper.updateGoodsClicks(goods);
    }

    @Override
    public List<GoodsOrderDTO> selectGoodsListByGoodsIds(List<String> listGoodsUuid)
    {
        return goodsMapper.selectGoodsListByGoodsIds(listGoodsUuid);
    }

    /**
     *
     * findByPage(分页查询商品信息)
     *
     * @param goodsPageReq
     * @return <b>创建人：</b>陈钊<br/>
     *         PageInfo<GoodsPageDto>
     * @exception @since 1.0.0
     */
    @Override
    public PageInfo<GoodsPageDto> findByPage(GoodsPageReq goodsPageReq)
    {

        PageHelper.startPage(goodsPageReq.getPageNum(), goodsPageReq.getPageSize());
        List<GoodsPageDto> goodslist = goodsMapper.findByPage(goodsPageReq);
        return new PageInfo<>(goodslist);
    }

    @Override
    public int changeDelFlagBatch(Map<String, Object> paramMap)
    {
        return goodsMapper.changeDelFlagBatch(paramMap);
    }

    @Override
    public List<GoodsPageDto> findDelByPage(DelGoodsPageReq delGoodsPageReq)
    {
        PageHelper.startPage(delGoodsPageReq.getPageNum(), delGoodsPageReq.getPageSize());
        return goodsMapper.findDelByPage(delGoodsPageReq);
    }

    @Override
    public int deleteBatch(Map<String, Object> paramMap)
    {
        return goodsMapper.deleteBatch(paramMap);
    }

    @Override
    public int upOrDownBatch(Map map)
    {
        return goodsMapper.upOrDownBatch(map);
    }

    @Override
    public List<String> selectGoodsUuidByDesinger(List<String> memberUuids)
    {
        return goodsMapper.selectGoodsUuidByDesinger(memberUuids);
    }

    @Override
    public List<String> queryUUidByEsGoods(EsGoodsReq esGoodsReq)
    {
        return goodsMapper.queryUUidByEsGoods(esGoodsReq);
    }

    @Override
    public GoodsIncrement queryPageIncrement(int pageNum, int pageSize, Long referenceTime)
    {
        PageHelper.startPage(pageNum, pageSize);
        List<Goods> goods = goodsMapper.queryIncrementGoods(referenceTime);
        PageInfo pageInfo = new PageInfo(goods);
        GoodsIncrement goodsIncrement = new GoodsIncrement();
        BeanUtils.copyProperties(pageInfo, goodsIncrement);
        Goods good = goods.get(goods.size() - 1);
        goodsIncrement.setReferenceTime(good.getModifyDate());
        return goodsIncrement;
    }

    @Override
    public List<Goods> selectGoodsByName(String name)
    {
        Example example = new Example(Goods.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andLike("goods_name", name);
        return goodsMapper.selectByExample(example);
    }

}
