package com.kingthy.dubbo.basedata.service;


import com.kingthy.base.BaseService;
import com.kingthy.entity.StyleCategory;
import com.kingthy.response.ClassCategoryResp;

import java.util.List;

/**
 * 
 *
 * StyleCategoryDubboService(风格分类接口)
 * 
 * @author zhaochen 2017年3月29日 下午1:56:11
 * 
 * @version 1.0.0
 *
 */
public interface StyleCategoryDubboService extends BaseService<StyleCategory>
{
    /**
     * 删除
     * @param uuid
     * @return
     */
    int deleteByUuid(String uuid);

    /**
     * 查询款式分类
     * @return
     */
    List<ClassCategoryResp> queryStyleClassCategory();

    /**
     * 查询风格名称
     * @param uuid
     * @return
     */
    String selectClassNameByUUID(String uuid);
}
