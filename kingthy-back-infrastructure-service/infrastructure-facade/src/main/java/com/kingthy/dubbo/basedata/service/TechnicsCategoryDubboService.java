package com.kingthy.dubbo.basedata.service;


import com.kingthy.base.BaseService;
import com.kingthy.entity.TechnicsCategory;

/**
 * TechnicsService(描述其作用)
 * @author zhaochen 2017年07月28日 17:26
 *
 * @version 1.0.0
 */
public interface TechnicsCategoryDubboService extends BaseService<TechnicsCategory>
{
    /**
     * 删除
     * @param uuid
     * @return
     */
    int delete(String uuid);

    /**
     * 查询
     * @param technicsCategory
     * @return
     */
    int selectCountByExample(TechnicsCategory technicsCategory);
}
