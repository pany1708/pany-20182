package com.kingthy.dubbo.basedata.service;


import com.kingthy.base.BaseService;
import com.kingthy.entity.SuitCategory;

/**
 * SuitCategoryDubboService(描述其作用)
 * @author zhaochen 2017年07月10日 10:46
 *
 * @version 1.0.0
 */
public interface SuitCategoryDubboService extends BaseService<SuitCategory>
{
    /**
     * 删除外套
     * @param uuid
     * @return
     */
    int deleteSuitCategory(String uuid);
}
