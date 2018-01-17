package com.kingthy.dubbo.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kingthy.dubbo.basedata.service.MeasureUnitDubboService;
import com.kingthy.entity.MeasureUnit;
import com.kingthy.mapper.MeasureUnitMapper;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @author zhaochen
 * @Description:
 * @DATE Created by 9:35 on 2017/9/27.
 * @Modified by:
 */
@Service(version = "1.0.0", timeout = 3000)
public class MeasureUnitDubboServiceImpl implements MeasureUnitDubboService
{

    @Autowired
    private MeasureUnitMapper measureUnitMapper;

    @Override
    public int insert(MeasureUnit measureUnit) {
        return measureUnitMapper.insert(measureUnit);
    }

    @Override
    public int updateByUuid(MeasureUnit var) {

        Example example = new Example(MeasureUnit.class);
        example.createCriteria().andEqualTo("uuid", var.getUuid());

        return measureUnitMapper.updateByExampleSelective(var, example);
    }

    @Override
    public List<MeasureUnit> selectAll() {
        return measureUnitMapper.selectAll();
    }

    @Override
    public MeasureUnit selectByUuid(String uuid) {
        MeasureUnit var1 = new MeasureUnit();
        var1.setUuid(uuid);
        return selectOne(var1);
    }

    @Override
    public int selectCount(MeasureUnit measureUnit) {
        return measureUnitMapper.selectCount(measureUnit);
    }

    @Override
    public List<MeasureUnit> select(MeasureUnit var1) {
        return measureUnitMapper.select(var1);
    }

    @Override
    public MeasureUnit selectOne(MeasureUnit var1) {
        return measureUnitMapper.selectOne(var1);
    }

    @Override
    public PageInfo<MeasureUnit> queryPage(Integer pageNum, Integer pageSize, MeasureUnit measureUnit) {
        PageHelper.startPage(pageNum, pageSize);
        return new PageInfo<>(select(measureUnit));
    }

    @Override
    public int updateByCode(MeasureUnit var) {
        Example example = new Example(MeasureUnit.class);
        example.createCriteria().andEqualTo("code", var.getCode()).andEqualTo("delFlag", false);
        return measureUnitMapper.updateByExampleSelective(var, example);
    }

    @Override
    public int deleteByCode(MeasureUnit var) {
        return updateByCode(var);
    }
}
