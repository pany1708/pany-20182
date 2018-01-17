package com.kingthy.mapper;

import com.kingthy.entity.Collocation;
import com.kingthy.util.MyMapper;

import java.util.List;

/**
 * @author zhaochen 2017/8/29.
 */
public interface CollocationMapper extends MyMapper<Collocation> {
    /**
     * 分页查询
     * @param collocation
     * @return
     */
    List<Collocation> findByPage(Collocation collocation);
}
