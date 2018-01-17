package com.kingthy.platform.service.basedata;

import com.github.pagehelper.PageInfo;
import com.kingthy.platform.entity.basedata.TagCategory;

import java.util.List;

/**
 * 
 *
 * tagCategoryService(标签分类接口)
 * 
 * 赵生辉 2017年3月29日 下午1:56:17
 * 
 * @version 1.0.0
 *
 */
public interface TagCategoryService
{
    
    /**
     * 
     * create(创建一个新的标签) (添加新的标签)
     * 
     * @param tagCategory
     * @return 赵生辉 int
     * @exception @since 1.0.0
     */
    int create(TagCategory tagCategory);
    
    /**
     * 
     * deleteById(删除一个标签) (逻辑删除一个标签)
     * 
     * @param uuid
     * @return 赵生辉 int
     * @exception @since 1.0.0
     */
    int deleteByUuid(String uuid);
    
    /**
     * 
     * updateSelective(选择性修改标签信息) (可以修改部分信息)
     * 
     * @param tagCategory
     * @return 赵生辉 int
     * @exception @since 1.0.0
     */
    int updateSelective(TagCategory tagCategory);
    
    /**
     * 
     * findAllTagCategory(查询所有的标签) (用于其他模块调用标签数据)
     * 
     * @param tagCategory
     * @return 赵生辉 PageInfo<Member>
     * @exception @since 1.0.0
     */
    List<TagCategory> findAllTagCategory(TagCategory tagCategory);
    
    /**
     * 
     * findPageAllTagCategory(分页查询所有的标签) (用于首页分页展示数据信息)
     * 
     * @param pageNo
     * @param pageSize
     * @param tagCategory
     * @return 赵生辉 PageInfo<Member>
     * @exception @since 1.0.0
     */
    PageInfo<TagCategory> findAllTagCategoryPage(int pageNo, int pageSize, TagCategory tagCategory);
    
    /**
     * 
     * updateShowOrHide(修改某一标签 是否显示) (需要修改某一部件显示隐藏)
     * 
     * @param uuid
     * @param status
     * @return 赵生辉 int
     * @exception @since 1.0.0
     */
    int updateShowOrHide(String uuid, boolean status);
    
    /**
     * 查询标签 (这里描述这个方法适用条件 – 可选)
     *
     * yuanml
     * 
     * @param tagName
     * @return List<TagCategory>
     * @exception @since 1.0.0
     */
    List<TagCategory> findTags(String tagName);
    /**
     * findTagCategoryName(检查分类名称是否重复) (这里描述这个方法适用条件 – 可选)
     *
     * yuanml
     * 
     * @param tagCategoryName
     * @return Boolean
     * @exception @since 1.0.0
     */
    Boolean findTagCategoryName(String tagCategoryName);
    
}
