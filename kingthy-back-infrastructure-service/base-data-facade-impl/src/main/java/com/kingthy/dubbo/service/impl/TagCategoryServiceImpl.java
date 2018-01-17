package com.kingthy.dubbo.service.impl;


import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kingthy.dubbo.basedata.service.TagCategoryDubboService;
import com.kingthy.entity.TagCategory;
import com.kingthy.mapper.TagCategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

/**
 * 
 *
 * TagCategoryServiceImpl
 * 
 * @author zhaochen 2017年4月1日 上午9:20:53
 * 
 * @version 1.0.0
 *
 */
@Service(version = "1.0.0",timeout = 3000)
public class TagCategoryServiceImpl implements TagCategoryDubboService
{
    @Autowired
    private transient TagCategoryMapper tagCategoryMapper;

    @Override
    public int insert(TagCategory tagCategory) {
        Example example = new Example(TagCategory.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("className", tagCategory.getClassName());
        criteria.andEqualTo("delFlag", false);
        /* 判断是否已存在相同名称的标签 */
        if (tagCategoryMapper.selectCountByExample(example) > 0)
        {
            return 0;
        }
        Date currentDate = new Date();
        tagCategory.setCreateDate(currentDate);
        tagCategory.setModifyDate(currentDate);
        tagCategory.setVersion(1);
        int result = tagCategoryMapper.insertSelective(tagCategory);
        return result;
    }

    @Override
    public int updateByUuid(TagCategory tagCategory) {
        TagCategory tagCategoryResult = selectByUuid(tagCategory.getUuid());
        Integer currentVersion = tagCategoryResult.getVersion();
        Date currentDate = new Date();
        tagCategory.setModifyDate(currentDate);
        Example example = new Example(TagCategory.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("delFlag", false);
        criteria.andEqualTo("uuid", tagCategory.getUuid());
        criteria.andEqualTo("version", currentVersion);
        tagCategory.setVersion(currentVersion+1);
        return tagCategoryMapper.updateByExampleSelective(tagCategory, example);
    }

    @Override
    public List<TagCategory> selectAll() {
        return tagCategoryMapper.selectAllTags();
    }

    @Override
    public TagCategory selectByUuid(String uuid) {
        Example example = new Example(TagCategory.class);
        example.createCriteria().andEqualTo("uuid",uuid);
        List<TagCategory> tagCategoryList = tagCategoryMapper.selectByExample(example);
        if (tagCategoryList!=null && tagCategoryList.size()>0){
            return tagCategoryList.get(0);
        }
        return new TagCategory();
    }

    @Override
    public int selectCount(TagCategory tagCategory) {
        return 0;
    }

    @Override
    public List<TagCategory> select(TagCategory var1) {
        return null;
    }

    @Override
    public TagCategory selectOne(TagCategory var1) {
        return null;
    }

    @Override
    public PageInfo<TagCategory> queryPage(Integer pageNum, Integer pageSize, TagCategory tagCategory) {
        PageHelper.startPage(pageNum, pageSize);
        List<TagCategory> countries = tagCategoryMapper.findByPage(tagCategory);
        return new PageInfo<>(countries);
    }

    @Override
    public int deleteByUuid(String uuid) {
        TagCategory tagCategory = new TagCategory();
        Date currentDate = new Date();
        tagCategory.setDelFlag(true);
        tagCategory.setModifyDate(currentDate);
        tagCategory.setModifier("user");
        Example example = new Example(TagCategory.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("uuid", uuid);
        criteria.andEqualTo("delFlag", false);
        return tagCategoryMapper.updateByExampleSelective(tagCategory, example);
    }

    @Override
    public int updateShowOrHide(String uuid, boolean status) {
        Example example = new Example(TagCategory.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("uuid", uuid);
        criteria.andEqualTo("delFlag", false);
        TagCategory tagCategory = new TagCategory();
        tagCategory.setStatus(status);
        tagCategory.setModifyDate(new Date());
        return tagCategoryMapper.updateByExampleSelective(tagCategory, example);
    }

    @Override
    public List<TagCategory> findTags(String tagName) {
        return tagCategoryMapper.findTagsByName(tagName);
    }

    @Override
    public Boolean findTagCategoryName(String tagCategoryName) {
        return tagCategoryMapper.findTagCategoryName(tagCategoryName) > 0;
    }
}
