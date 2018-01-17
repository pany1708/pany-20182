package com.kingthy.mapper;


import com.kingthy.entity.SkirtCategory;
import com.kingthy.util.MyMapper;

import java.util.List;

/**
 * SkirtCategory(描述其作用)
 * @author zhaochen 2017年07月10日 10:33
 *
 * @version 1.0.0
 */
public interface SkirtCategoryMapper extends MyMapper<SkirtCategory>
{
    /**
     * 分页查询衬衫分类信息
     * @param skirtCategory
     * @return
     */
    List<SkirtCategory> findByPage(SkirtCategory skirtCategory);

    /**
     * 根据uuid查询衬衫分类信息
     * @param uuid
     * @return
     */
    SkirtCategory selectByUuid(String uuid);
}
