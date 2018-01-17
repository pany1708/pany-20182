package com.kingthy.platform.service.report;

import com.kingthy.platform.dto.report.GoodsPriceRangeNumDto;
import com.kingthy.platform.dto.report.ReportGoodsDataReq;

import java.util.List;

/**
 * @author chenz
 * @version 1.0.0
 * @class_name GoodsReportService
 * @description 商品统计数据接口
 * @create 2017/7/27
 */
public interface GoodsReportService {

    /**
     * 查询商品数据
     * @param reportGoodsDataReq
     * @return int
     */
    int findGoodsDataByDataType(ReportGoodsDataReq reportGoodsDataReq);

    /**
     * 根据价格区间查询商品数
     * @return List<GoodsPriceRangeNumDto>
     */
    List<GoodsPriceRangeNumDto> findNumByPriceRange();
}
