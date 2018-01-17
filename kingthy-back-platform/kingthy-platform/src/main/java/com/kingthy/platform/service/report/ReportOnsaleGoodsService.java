package com.kingthy.platform.service.report;

import com.kingthy.platform.dto.report.GoodsPriceRangeNumDto;
import com.kingthy.platform.entity.report.ReportOnsaleGoods;

import java.util.List;

/**
 * @author chenz
 * @version 1.0.0
 * @class_name ReportOnsaleGoodsService
 * @description 上架商品价格分布接口
 * @create 2017/7/31
 */
public interface ReportOnsaleGoodsService {
    /**
     * 添加商品价格分布信息
     * @param list
     */
    void add(List<GoodsPriceRangeNumDto> list);

    /**
     * 查询已上架商品价格分布
     * @return List<ReportOnsaleGoods>
     */
    List<ReportOnsaleGoods> findAll();
}
