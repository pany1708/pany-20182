package com.kingthy.dubbo.goods.service;


import com.kingthy.entity.ReportGoodsData;

import java.util.List;

/**
 * @author chenz
 * @version 1.0.0
 * @class_name ReportGoodsDataService
 * @description 商品数据统计结果接口
 * @create 2017/7/28
 */
public interface ReportGoodsDataDubboService {

    /**
     * 添加商品统计结果
     * @param reportGoodsData
     */
    void add(ReportGoodsData reportGoodsData);

    /**
     * 查询所有数据
     * @return
     */
    List<ReportGoodsData> findByTimeType(int timeType);
}
