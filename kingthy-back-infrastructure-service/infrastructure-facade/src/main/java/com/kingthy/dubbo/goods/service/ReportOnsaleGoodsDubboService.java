package com.kingthy.dubbo.goods.service;


import com.kingthy.dto.GoodsPriceRangeNumDto;
import com.kingthy.entity.ReportOnsaleGoods;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chenz
 * @version 1.0.0
 * @class_name ReportOnsaleGoodsService
 * @description 上架商品价格分布接口
 * @create 2017/7/31
 */
public interface ReportOnsaleGoodsDubboService {
    /**
     * 添加商品价格分布信息
     *
     * @param list
     */
    void add(List<GoodsPriceRangeNumDto> list);

    /**
     * 查询已上架商品价格分布
     *
     * @return List<ReportOnsaleGoods>
     */
    ArrayList<ReportOnsaleGoods> findAll();
}
