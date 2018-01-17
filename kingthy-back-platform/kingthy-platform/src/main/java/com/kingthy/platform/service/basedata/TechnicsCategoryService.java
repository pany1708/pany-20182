package com.kingthy.platform.service.basedata;

import com.github.pagehelper.PageInfo;
import com.kingthy.platform.entity.basedata.TechnicsCategory;

import java.util.List;

/**
 * TechnicsService(描述其作用)
 * <p>
 * 赵生辉 2017年07月28日 17:26
 *
 * @version 1.0.0
 */
public interface TechnicsCategoryService
{

    int create(TechnicsCategory technicsCategory);

    int delete(String uuid);

    int update(TechnicsCategory technicsCategory);

    TechnicsCategory queryInfo(String uuid);

    PageInfo<TechnicsCategory> queryPage(int pageNum,int pageSize,TechnicsCategory technicsCategory);

    List<TechnicsCategory> queryAll();
}
