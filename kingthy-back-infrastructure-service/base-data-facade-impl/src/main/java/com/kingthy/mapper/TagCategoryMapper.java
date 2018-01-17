package com.kingthy.mapper;


import com.kingthy.entity.TagCategory;
import com.kingthy.util.MyMapper;

import java.util.List;

/**
 * 
 *
 * TagCategoryMapper(标签分类映射接口)
 * 
 * @author zhaochen 2017年4月13日 下午8:28:45
 * 
 * @version 1.0.0
 *
 */
public interface TagCategoryMapper extends MyMapper<TagCategory>
{
    /**
     * 分页查询标签信息
     * @param tagCategory
     * @return
     */
    List<TagCategory> findByPage(TagCategory tagCategory);

    /**
     * 查询所有标签
     * @return
     */
    List<TagCategory> selectAllTags();

    /**
     * 根据名称查询标签
     * @param tagName
     * @return
     */
    List<TagCategory> findTagsByName(String tagName);

    /**
     * 根据名称查询标签数量
     * @param tagCategoryName
     * @return
     */
    int findTagCategoryName(String tagCategoryName);
}
