package com.kingthy.dubbo.goods.service;


import com.kingthy.dto.GoodsPriceRangeNumDto;
import com.kingthy.request.ReportGoodsDataReq;

import java.util.ArrayList;

/**
 * @author chenz
 * @version 1.0.0
 * @class_name GoodsReportDubboService
 * @description 商品统计数据接口
 * @create 2017/7/27
 */
public interface GoodsReportDubboService {

    /**
     * 查询商品数据
     *
     * @param reportGoodsDataReq
     * @return int
     */
    int findGoodsDataByDataType(ReportGoodsDataReq reportGoodsDataReq);

    /**
     * 根据价格区间查询商品数
     *
     * @return List<GoodsPriceRangeNumDto>
     */
    ArrayList<GoodsPriceRangeNumDto> findNumByPriceRange();
}
