package com.kingthy.dubbo.basedata.service;


import com.kingthy.base.BaseService;
import com.kingthy.entity.TagCategory;

import java.util.List;

/**
 * 
 *
 * tagCategoryService(标签分类接口)
 * 
 * @author zhaochen 2017年3月29日 下午1:56:17
 * 
 * @version 1.0.0
 *
 */
public interface TagCategoryDubboService extends BaseService<TagCategory>
{
    /**
     * 删除标签
     * @param uuid
     * @return
     */
    int deleteByUuid(String uuid);

    /**
     * 更新标签
     * @param uuid
     * @param status
     * @return
     */
    int updateShowOrHide(String uuid, boolean status);

    /**
     * 根据名称查询标签
     * @param tagName
     * @return
     */
    List<TagCategory> findTags(String tagName);

    /**
     * 查询标签
     * @param tagCategoryName
     * @return
     */
    Boolean findTagCategoryName(String tagCategoryName);

}
