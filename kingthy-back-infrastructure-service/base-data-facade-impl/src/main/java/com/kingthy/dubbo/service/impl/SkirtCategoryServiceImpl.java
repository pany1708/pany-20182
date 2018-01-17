package com.kingthy.dubbo.service.impl;


import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kingthy.dubbo.basedata.service.SkirtCategoryDubboService;
import com.kingthy.entity.PantsCategory;
import com.kingthy.entity.SkirtCategory;
import com.kingthy.mapper.SkirtCategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

/**
 * SkirtCategoryServiceImpl(描述其作用)
 * @author zhaochen 2017年07月10日 10:46
 *
 * @version 1.0.0
 */
@Service(version = "1.0.0",timeout = 3000)
public class SkirtCategoryServiceImpl implements SkirtCategoryDubboService
{
    @Autowired
    private SkirtCategoryMapper skirtCategoryMapper;

    @Override
    public int insert(SkirtCategory skirtCategory) {
        Example example = new Example(SkirtCategory.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("className", skirtCategory.getClassName());
        criteria.andEqualTo("delFlag", false);
        /* 判断是否已存在相同名称的数据 */
        if (skirtCategoryMapper.selectCountByExample(example) > 0)
        {
            return -1;
        }
        Date currentDate = new Date();
        skirtCategory.setCreateDate(currentDate);
        skirtCategory.setModifyDate(currentDate);
        skirtCategory.setCreator("Creator");
        skirtCategory.setModifier("Modifier");
        skirtCategory.setDelFlag(false);
        skirtCategory.setVersion(1);
        int result = skirtCategoryMapper.insertSelective(skirtCategory);
        return result;
    }

    @Override
    public int updateByUuid(SkirtCategory skirtCategory) {
        SkirtCategory skirtCategoryResult = selectByUuid(skirtCategory.getUuid());
        Integer currentVersion = skirtCategoryResult.getVersion();
        Example example = new Example(SkirtCategory.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("uuid",skirtCategory.getUuid());
        criteria.andEqualTo("delFlag",false);
        criteria.andEqualTo("version",currentVersion);
        skirtCategory.setVersion(currentVersion+1);
        return  skirtCategoryMapper.updateByExampleSelective(skirtCategory,example);
    }

    @Override
    public List<SkirtCategory> selectAll() {
        return skirtCategoryMapper.selectAll();
    }

    @Override
    public SkirtCategory selectByUuid(String uuid) {
        return skirtCategoryMapper.selectByUuid(uuid);
    }

    @Override
    public int selectCount(SkirtCategory skirtCategory) {
        return 0;
    }

    @Override
    public List<SkirtCategory> select(SkirtCategory var1) {
        return null;
    }

    @Override
    public SkirtCategory selectOne(SkirtCategory var1) {
        return null;
    }

    @Override
    public PageInfo<SkirtCategory> queryPage(Integer pageNum, Integer pageSize, SkirtCategory skirtCategory) {
        PageHelper.startPage(pageNum,pageSize);
        List<SkirtCategory> result = skirtCategoryMapper.findByPage(skirtCategory);
        PageInfo<SkirtCategory> pantsCategoryPageInfo = new PageInfo<>(result);
        return pantsCategoryPageInfo;
    }

    @Override
    public int deleteSkirtCategory(String uuid) {
        Example example = new Example(PantsCategory.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("uuid",uuid);
        return skirtCategoryMapper.deleteByExample(example);
    }
}
