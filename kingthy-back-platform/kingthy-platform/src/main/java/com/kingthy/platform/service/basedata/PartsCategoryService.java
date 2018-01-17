package com.kingthy.platform.service.basedata;

import com.github.pagehelper.PageInfo;
import com.kingthy.platform.entity.basedata.PartsCategory;

import java.util.List;

/**
 * 
 *
 * PartsCategoryService(部件分类接口)
 * 
 * 赵生辉 2017年3月29日 下午1:56:17
 * 
 * @version 1.0.0
 *
 */
public interface PartsCategoryService
{
    int create(PartsCategory partsCategory);

    int deleteById(String uuid);

    int updateSelective(PartsCategory partsCategory);

    PageInfo<PartsCategory> findAllPartsCategoryPage(int pageNo, int pageSize, PartsCategory partsCategory);

    PartsCategory findPartsCategory(String parentUuid);

    List<PartsCategory> findAllPartsCategory();
}
