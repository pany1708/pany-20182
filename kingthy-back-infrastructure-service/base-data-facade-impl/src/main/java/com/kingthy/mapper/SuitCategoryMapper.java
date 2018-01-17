package com.kingthy.mapper;


import com.kingthy.entity.SuitCategory;
import com.kingthy.util.MyMapper;

import java.util.List;

/**
 * Suit(描述其作用)
 * @author zhaochen 2017年07月10日 10:37
 *
 * @version 1.0.0
 */
public interface SuitCategoryMapper extends MyMapper<SuitCategory>
{
    /**
     * 分页查询外套信息
     * @param suitCategory
     * @return
     */
    List<SuitCategory> findByPage(SuitCategory suitCategory);

    /**
     * 根据uuid查询信息
     * @param uuid
     * @return
     */
    SuitCategory selectByUuid(String uuid);
}
