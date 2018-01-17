package com.kingthy.platform.service.report;

import com.kingthy.platform.dto.report.OrderAreaDto;
import com.kingthy.platform.dto.report.OrderAreaReportDto;

import java.util.List;

/**
 * @author chenz
 * @version 1.0.0
 * @class_name ReportOrderAreaService
 * @description 区域订单数量接口
 * @create 2017/7/24
 */
public interface ReportOrderAreaService {
    void add(List<OrderAreaDto> list);
    /**
     * 地区订单分布统计结果
     * @return List<OrderAreaReportDto>
     */
    List<OrderAreaReportDto> findOrderAreaReport();
}
