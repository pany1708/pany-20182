package com.kingthy.dubbo.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kingthy.dto.GoodsPageDto;
import com.kingthy.dubbo.goods.service.GoodsOfficiallyDubboService;
import com.kingthy.entity.Goods;
import com.kingthy.entity.GoodsOfficially;
import com.kingthy.mapper.GoodsOfficiallyMapper;
import com.kingthy.request.GoodsOfficiallyReq;
import com.kingthy.request.GoodsPageReq;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @author  xumin
 * @Description:
 * @DATE Created by 15:44 on 2017/8/7.
 * @Modified by:
 */

@Service(version = "1.0.0", timeout = 10000)
public class GoodsOfficiallyDubboServiceImpl implements GoodsOfficiallyDubboService {

    @Autowired
    private GoodsOfficiallyMapper goodsOfficiallyMapper;

    @Override
    public int insert(Goods var) {
        return goodsOfficiallyMapper.insert(var);
    }

    @Override
    public int updateByUuid(Goods var) {

        Example example = new Example(GoodsOfficially.class);
        example.createCriteria().andEqualTo("uuid", var.getUuid());

        return goodsOfficiallyMapper.updateByExampleSelective(var, example);
    }

    @Override
    public List<Goods> selectAll() {
        return goodsOfficiallyMapper.selectAll();
    }

    @Override
    public Goods selectByUuid(String uuid) {
        Goods var1 = new Goods();
        var1.setUuid(uuid);
        return selectOne(var1);
    }

    @Override
    public int selectCount(Goods var) {
        return goodsOfficiallyMapper.selectCount(var);
    }

    @Override
    public List<Goods> select(Goods var1) {
        return goodsOfficiallyMapper.select(var1);
    }

    @Override
    public Goods selectOne(Goods var1) {
        return goodsOfficiallyMapper.selectOne(var1);
    }

    @Override
    public PageInfo<Goods> queryPage(Integer pageNum, Integer pageSize, Goods var) {
        PageHelper.startPage(pageNum, pageSize);
        return new PageInfo<>(goodsOfficiallyMapper.select(var));
    }

    @Override
    public PageInfo<GoodsPageDto> findByPage(GoodsPageReq goodsParam) {
        PageHelper.startPage(goodsParam.getPageNum(), goodsParam.getPageSize());
        return new PageInfo<>(goodsOfficiallyMapper.findByPage(goodsParam));
    }

    @Override
    public int upOrDownBatch(GoodsOfficiallyReq.UpOrDownBatch req) {
        return goodsOfficiallyMapper.upOrDownBatch(req);
    }

    @Override
    public GoodsPageDto.GoodsDetailDto selectOfficiallyGoodsInfoByUuid(String goodsUuid) {
        return goodsOfficiallyMapper.selectOfficiallyGoodsInfoByUuid(goodsUuid);
    }

}
