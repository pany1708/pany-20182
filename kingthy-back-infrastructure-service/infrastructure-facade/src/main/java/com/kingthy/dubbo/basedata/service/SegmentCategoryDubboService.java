package com.kingthy.dubbo.basedata.service;


import com.kingthy.base.BaseService;
import com.kingthy.entity.SegmentCategory;

/**
 * SegmentCategoryDubboService(描述其作用)
 * @author zhaochen 2017年07月11日 10:57
 *
 * @version 1.0.0
 */
public interface SegmentCategoryDubboService extends BaseService<SegmentCategory>
{
    /**
     * 删除
     * @param uuid
     * @return
     */
    int deleteSegmentCategory(String uuid);
}
