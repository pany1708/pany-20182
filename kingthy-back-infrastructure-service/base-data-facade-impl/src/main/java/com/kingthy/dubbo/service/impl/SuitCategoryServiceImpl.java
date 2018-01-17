package com.kingthy.dubbo.service.impl;


import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kingthy.dubbo.basedata.service.SuitCategoryDubboService;
import com.kingthy.entity.SuitCategory;
import com.kingthy.mapper.SuitCategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

/**
 * SuitCategoryServiceImpl(描述其作用)
 * @author zhaochen 2017年07月10日 10:46
 *
 * @version 1.0.0
 */
@Service(version = "1.0.0",timeout = 3000)
public class SuitCategoryServiceImpl implements SuitCategoryDubboService
{
    @Autowired
    private SuitCategoryMapper suitCategoryMapper;

    @Override
    public int insert(SuitCategory suitCategory) {
        Example example = new Example(SuitCategory.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("className", suitCategory.getClassName());
        criteria.andEqualTo("delFlag", false);
        /* 判断是否已存在相同名称的数据 */
        if (suitCategoryMapper.selectCountByExample(example) > 0)
        {
            return -1;
        }
        Date currentDate = new Date();
        suitCategory.setCreateDate(currentDate);
        suitCategory.setModifyDate(currentDate);
        suitCategory.setCreator("Creator");
        suitCategory.setModifier("Modifier");
        suitCategory.setDelFlag(false);
        suitCategory.setVersion(1);
        return suitCategoryMapper.insertSelective(suitCategory);
    }

    @Override
    public int updateByUuid(SuitCategory suitCategory) {
        SuitCategory suitCategoryResult = selectByUuid(suitCategory.getUuid());
        Integer currentVersion = suitCategoryResult.getVersion();
        Example example = new Example(SuitCategory.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("uuid",suitCategory.getUuid());
        criteria.andEqualTo("delFlag",false);
        criteria.andEqualTo("version",currentVersion);
        suitCategory.setVersion(currentVersion+1);
        return suitCategoryMapper.updateByExampleSelective(suitCategory,example);
    }

    @Override
    public List<SuitCategory> selectAll() {
        Example example = new Example(SuitCategory.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("delFlag",false);
        return suitCategoryMapper.selectByExample(example);
    }

    @Override
    public SuitCategory selectByUuid(String uuid) {
        return suitCategoryMapper.selectByUuid(uuid);
    }

    @Override
    public int selectCount(SuitCategory suitCategory) {
        return 0;
    }

    @Override
    public List<SuitCategory> select(SuitCategory var1) {
        return null;
    }

    @Override
    public SuitCategory selectOne(SuitCategory var1) {
        return null;
    }

    @Override
    public PageInfo<SuitCategory> queryPage(Integer pageNum, Integer pageSize, SuitCategory suitCategory) {
        PageHelper.startPage(pageNum,pageSize);
        List<SuitCategory> result = suitCategoryMapper.findByPage(suitCategory);
        return new PageInfo<>(result);
    }

    @Override
    public int deleteSuitCategory(String uuid) {
        Example example = new Example(SuitCategory.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("uuid",uuid);
        return suitCategoryMapper.deleteByExample(example);
    }
}
