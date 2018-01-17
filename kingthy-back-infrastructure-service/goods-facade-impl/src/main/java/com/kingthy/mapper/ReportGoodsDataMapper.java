package com.kingthy.mapper;



import com.kingthy.entity.ReportGoodsData;
import com.kingthy.util.MyMapper;

import java.util.List;

/**
 * @author zhaochen
 */
public interface ReportGoodsDataMapper extends MyMapper<ReportGoodsData> {

    /**
     * 根据数据、时间类型删除数据
     * @param reportGoodsData
     * @return int
     */
    int deleteByDataTimeType(ReportGoodsData reportGoodsData);

    /**
     * 查询所有数据
     * @param timeType
     * @return List<ReportGoodsData>
     */
    List<ReportGoodsData> findByTimeType(int timeType);
}