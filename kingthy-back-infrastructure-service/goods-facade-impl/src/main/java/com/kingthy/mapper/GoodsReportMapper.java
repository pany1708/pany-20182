package com.kingthy.mapper;


import com.kingthy.dto.GoodsPriceRangeNumDto;
import com.kingthy.request.ReportGoodsDataReq;

import java.util.List;

/**
 * @author chenz
 * @version 1.0.0
 * @class_name GoodsReportMapper
 * @description 商品统计数据接口映射
 * @create 2017/7/27
 */
public interface GoodsReportMapper {

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
    List<GoodsPriceRangeNumDto> findNumByPriceRange();
}
