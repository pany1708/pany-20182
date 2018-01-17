package com.kingthy.dubbo.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kingthy.dubbo.basedata.service.SparePartDubboService;
import com.kingthy.entity.SparePart;
import com.kingthy.mapper.SparePartMapper;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @author zhaochen
 * @Description: 零件表
 * @DATE Created by 18:40 on 2017/9/13.
 * @Modified by:
 */
@Service(version = "1.0.0",timeout = 3000)
public class SparePartDubboServiceImpl implements SparePartDubboService
{
    @Autowired
    private SparePartMapper sparePartMapper;

    @Override
    public int insert(SparePart sparePart) {
        return sparePartMapper.insert(sparePart);
    }

    @Override
    public int updateByUuid(SparePart var) {
        Example example = new Example(SparePart.class);
        example.createCriteria().andEqualTo("uuid",var.getUuid());
        return sparePartMapper.updateByExample(var,example);
    }

    @Override
    public List<SparePart> selectAll() {
        return sparePartMapper.selectAll();
    }

    @Override
    public SparePart selectByUuid(String uuid) {
        SparePart var1 = new SparePart();
        var1.setUuid(uuid);
        return selectOne(var1);
    }

    @Override
    public int selectCount(SparePart sparePart) {
        return sparePartMapper.selectCount(sparePart);
    }

    @Override
    public List<SparePart> select(SparePart var1) {
        return sparePartMapper.select(var1);
    }

    @Override
    public SparePart selectOne(SparePart var1) {
        return sparePartMapper.selectOne(var1);
    }

    @Override
    public PageInfo<SparePart> queryPage(Integer pageNum, Integer pageSize, SparePart var) {
        PageHelper.startPage(pageNum, pageSize);
        return new PageInfo<>(sparePartMapper.select(var));
    }

    @Override
    public int updateBySn(SparePart var) {
        Example example = new Example(SparePart.class);
        example.createCriteria().andEqualTo("sn", var.getSn()).andEqualTo("delFlag", false);
        return sparePartMapper.updateByExampleSelective(var, example);
    }

    @Override
    public int deleteBySn(SparePart var) {
        return updateBySn(var);
    }
}
