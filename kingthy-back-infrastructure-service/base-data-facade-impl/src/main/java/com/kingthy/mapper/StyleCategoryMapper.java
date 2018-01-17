package com.kingthy.mapper;


import com.kingthy.entity.StyleCategory;
import com.kingthy.response.ClassCategoryResp;
import com.kingthy.util.MyMapper;

import java.util.List;

/**
 * 
 *
 * StyleCategoryMapper(风格分类映射接口)
 * 
 * @author zhaochen 2017年4月1日 下午4:49:13
 * 
 * @version 1.0.0
 *
 */
public interface StyleCategoryMapper extends MyMapper<StyleCategory>
{

    /**
     * 查询款式信息
     * @return
     */
    List<ClassCategoryResp> queryStyleClassCategory();

    /**
     * 分页查询款式信息
     * @param styleCategory
     * @return
     */
    List<StyleCategory> findByPage(StyleCategory styleCategory);

    String selectClassNameByUUID(String uuid);

    /**
     * 根据uuid查询名称
     * @param uuid
     * @return
     */
    StyleCategory selectNameByUuid(String uuid);

    /**
     * 查询款式uuid和名称
     * @param var1
     * @return
     */
    List<StyleCategory> selectUuidAndClassName(StyleCategory var1);

    /**
     * 根据uuid查询信息
     * @param uuid
     * @return
     */
    StyleCategory selectByUuid(String uuid);
}