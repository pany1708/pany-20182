package com.kingthy.platform.mapper.report;


import com.kingthy.platform.entity.report.ReportOnsaleGoods;
import com.kingthy.platform.util.MyMapper;

public interface ReportOnsaleGoodsMapper extends MyMapper<ReportOnsaleGoods> {

    /**
     * 全部删除
     * @return int
     */
    int deleteAll();
}