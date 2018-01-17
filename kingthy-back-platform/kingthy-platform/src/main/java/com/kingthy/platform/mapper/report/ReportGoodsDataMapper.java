package com.kingthy.platform.mapper.report;


import com.kingthy.platform.entity.report.ReportGoodsData;
import com.kingthy.platform.util.MyMapper;

import java.util.List;

public interface ReportGoodsDataMapper extends MyMapper<ReportGoodsData>{

    /**
     * 根据数据、时间类型删除数据
     * @param reportGoodsData
     * @return int
     */
    int deleteByDataTimeType(ReportGoodsData reportGoodsData);

    /**
     * 查询所有数据
     * @return List<ReportGoodsData>
     */
    List<ReportGoodsData> findByTimeType(int timeType);
}