package com.kingthy.platform.service.impl.report;

import com.kingthy.platform.dto.report.GoodsPriceRangeNumDto;
import com.kingthy.platform.dto.report.ReportGoodsDataReq;
import com.kingthy.platform.mapper.report.GoodsReportMapper;
import com.kingthy.platform.service.report.GoodsReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author chenz
 * @version 1.0.0
 * @class_name GoodsReportServiceImpl
 * @description 商品统计数据接口实现类
 * @create 2017/7/27
 */
@Service(value = "goodsReportService")
public class GoodsReportServiceImpl implements GoodsReportService {
    @Autowired
    private GoodsReportMapper goodsReportMapper;

    @Override
    public int findGoodsDataByDataType(ReportGoodsDataReq reportGoodsDataReq) {
        return goodsReportMapper.findGoodsDataByDataType(reportGoodsDataReq);
    }

    @Override
    public List<GoodsPriceRangeNumDto> findNumByPriceRange() {
        return goodsReportMapper.findNumByPriceRange();
    }
}
