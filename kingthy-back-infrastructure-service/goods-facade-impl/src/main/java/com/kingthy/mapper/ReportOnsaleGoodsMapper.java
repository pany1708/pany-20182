package com.kingthy.mapper;


import com.kingthy.entity.ReportOnsaleGoods;
import com.kingthy.util.MyMapper;

public interface ReportOnsaleGoodsMapper extends MyMapper<ReportOnsaleGoods> {

    /**
     * 全部删除
     * @return int
     */
    int deleteAll();
}