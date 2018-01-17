package com.kingthy.dubbo.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kingthy.dto.GoodsParameterCountDTO;
import com.kingthy.dubbo.goods.service.GoodsParameterDubboService;
import com.kingthy.entity.GoodsParameter;
import com.kingthy.mapper.GoodsParameterMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author  likejie on 2017/8/22.
 */
@Service(version = "1.0.0",timeout = 10000)
public class GoodsParameterDubboServiceImpl  implements GoodsParameterDubboService{

    @Autowired
    private  GoodsParameterMapper mapper;
    @Override
    public int insert(GoodsParameter goodsParameter) {
        return mapper.insert(goodsParameter);
    }

    @Override
    public int updateByUuid(GoodsParameter goodsParameter) {

        return -1;
    }

    @Override
    public List<GoodsParameter> selectAll() {
        return mapper.selectAll();
    }

    @Override
    public GoodsParameter selectByUuid(String uuid) {
        return null;
    }

    @Override
    public int selectCount(GoodsParameter goodsParameter) {
        return mapper.selectCount(goodsParameter);
    }

    @Override
    public List<GoodsParameter> select(GoodsParameter var1) {
        return mapper.select(var1);
    }

    @Override
    public GoodsParameter selectOne(GoodsParameter var1) {
        return mapper.selectOne(var1);
    }

    @Override
    public PageInfo<GoodsParameter> queryPage(Integer pageNum, Integer pageSize, GoodsParameter goodsParameter) {
        PageHelper.startPage(pageNum, pageSize);
        return new PageInfo<>(mapper.select(goodsParameter));
    }

    @Override
    public List<GoodsParameterCountDTO> selectCountByParameterIds(List<String> list) {
        return mapper.selectCountByParameterIds(list);
    }
}
