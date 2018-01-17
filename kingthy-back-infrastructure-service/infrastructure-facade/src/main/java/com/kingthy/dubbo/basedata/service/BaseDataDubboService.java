package com.kingthy.dubbo.basedata.service;


import com.kingthy.base.BaseService;
import com.kingthy.entity.BaseData;

import java.util.List;

/**
 * BaseDataDubboService(描述其作用)
 * @author zhaochen 2017年07月05日 10:17
 *
 * @version 1.0.0
 */
public interface BaseDataDubboService extends BaseService<BaseData>
{
    /**
     * 查询换货原因
     * @return
     */
    List<String> queryExchangeReason();

    /**
     * 查询基础数据
     * @param baseData
     * @return
     */
    List<BaseData> queryBaseData(BaseData baseData);

    /**
     * 添加一个基础数据
     * @param baseData
     * @return
     */
    int createBaseData(BaseData baseData);

    /**
     * 删除基础信息
     * @param uuid
     * @return
     */
    int deleteBaseData(String uuid);

}
