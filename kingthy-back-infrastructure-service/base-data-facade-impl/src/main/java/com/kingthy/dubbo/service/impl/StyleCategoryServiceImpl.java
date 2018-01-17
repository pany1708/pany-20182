package com.kingthy.dubbo.service.impl;


import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kingthy.dubbo.basedata.service.StyleCategoryDubboService;
import com.kingthy.entity.StyleCategory;
import com.kingthy.mapper.StyleCategoryMapper;
import com.kingthy.response.ClassCategoryResp;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

/**
 * @author zhaochen
 */
@Service(version = "1.0.0",timeout = 3000)
public class StyleCategoryServiceImpl implements StyleCategoryDubboService
{
    @Autowired
    private StyleCategoryMapper styleCategoryMapper;

    @Override
    public int insert(StyleCategory styleCategory) {
        Date currentDate = new Date();
        styleCategory.setCreateDate(currentDate);
        styleCategory.setModifyDate(currentDate);
        styleCategory.setCreator("Creator");
        styleCategory.setModifier("Modifier");
        styleCategory.setDelFlag(false);
        styleCategory.setVersion(1);
        return styleCategoryMapper.insertSelective(styleCategory);
    }

    @Override
    public int updateByUuid(StyleCategory styleCategory) {
        StyleCategory   styleCategoryResult = selectByUuid(styleCategory.getUuid());
        Integer currentVersion = styleCategoryResult.getVersion();
        Example example = new Example(StyleCategory.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("uuid",styleCategory.getUuid());
        criteria.andEqualTo("delFlag",false);
        criteria.andEqualTo("version",currentVersion);
        styleCategory.setVersion(currentVersion+1);
        return styleCategoryMapper.updateByExampleSelective(styleCategory,example);
    }

    @Override
    public List<StyleCategory> selectAll() {
        Example example = new Example(StyleCategory.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("delFlag",false);
        return styleCategoryMapper.selectByExample(example);
    }

    @Override
    public StyleCategory selectByUuid(String uuid) {
        return styleCategoryMapper.selectByUuid(uuid);
    }

    @Override
    public int selectCount(StyleCategory var) {
        return styleCategoryMapper.selectCount(var);
    }

    @Override
    public List<StyleCategory> select(StyleCategory var1) {
        return styleCategoryMapper.selectUuidAndClassName(var1);
    }

    @Override
    public StyleCategory selectOne(StyleCategory var1) {
        return styleCategoryMapper.selectNameByUuid(var1.getUuid());
    }

    @Override
    public PageInfo<StyleCategory> queryPage(Integer pageNum, Integer pageSize, StyleCategory styleCategory) {
        PageHelper.startPage(pageNum,pageSize);
        List<StyleCategory> styleCategories = styleCategoryMapper.findByPage(styleCategory);
        return new PageInfo<>(styleCategories);
    }

    @Override
    public int deleteByUuid(String uuid) {
        Example example = new Example(StyleCategory.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("uuid",uuid);
        return styleCategoryMapper.deleteByExample(example);
    }

    @Override
    public List<ClassCategoryResp> queryStyleClassCategory() {
        return styleCategoryMapper.queryStyleClassCategory();
    }

    @Override
    public String selectClassNameByUUID(String uuid) {
        return styleCategoryMapper.selectClassNameByUUID(uuid);
    }
}
