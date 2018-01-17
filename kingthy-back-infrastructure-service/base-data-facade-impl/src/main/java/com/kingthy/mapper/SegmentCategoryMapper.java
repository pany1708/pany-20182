package com.kingthy.mapper;


import com.kingthy.entity.SegmentCategory;
import com.kingthy.util.MyMapper;

import java.util.List;

/**
 * SegmentCategoryMapper(描述其作用)
 * @author zhaochen 2017年07月11日 11:05
 *
 * @version 1.0.0
 */
public interface SegmentCategoryMapper extends MyMapper<SegmentCategory> {

    /**
     * 分页查询
     * @param segmentCategory
     * @return
     */
    List<SegmentCategory> findByPage(SegmentCategory segmentCategory);

    /**
     * 根据uuid查询
     * @param uuid
     * @return
     */
    SegmentCategory selectByUuid(String uuid);
}
