package com.kingthy.mapper;


import com.kingthy.entity.TechnicsCategory;
import com.kingthy.util.MyMapper;

import java.util.List;

/**
 * TechnicsCategoryMapper(描述其作用)
 * @author zhaochen 2017年07月28日 17:47
 *
 * @version 1.0.0
 */
public interface TechnicsCategoryMapper extends MyMapper<TechnicsCategory>
{
    /**
     * 分页查询技术信息
     * @param technicsCategory
     * @return
     */
    List<TechnicsCategory> findByPage(TechnicsCategory technicsCategory);

    /**
     * 根据名称查询数量
     * @param name
     * @return
     */
    int selectCountByName(String name);

    /**
     * 根据uuid查询
     * @param uuid
     * @return
     */
    TechnicsCategory selectByUuid(String uuid);

    /**
     * 全部查询
     * @return
     */
    List<TechnicsCategory> selectAllTechnicsCategory();
}
