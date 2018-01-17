package com.kingthy.mapper;


import com.kingthy.entity.BaseData;
import com.kingthy.util.MyMapper;

import java.util.List;

/**
 * BaseDataMapper(描述其作用)
 * @author zhaochen 2017年07月05日 10:25
 *
 * @version 1.0.0
 */
public interface BaseDataMapper extends MyMapper<BaseData>
{
    /**
     * 查询换货原因
     * @return
     */
    List<String> queryExchangeReason();

    /**
     * 分页查询
     * @param baseData
     * @return
     */
    List<BaseData> findByPage(BaseData baseData);

    /**
     * 根据uuid查询
     * @param uuid
     * @return
     */
    BaseData selectByUuid(String uuid);
}
