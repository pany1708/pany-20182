package com.kingthy.dubbo.order.service;


import com.kingthy.dto.OrderAreaDto;
import com.kingthy.dto.OrderAreaReportDto;

import java.util.List;

/**
 * @author chenz
 * @version 1.0.0
 * @class_name ReportOrderAreaDubboService
 * @description 区域订单数量接口
 * @create 2017/7/24
 */
public interface ReportOrderAreaDubboService {
    void add(List<OrderAreaDto> list);
    /**
     * 地区订单分布统计结果
     * @return List<OrderAreaReportDto>
     */
    List<OrderAreaReportDto> findOrderAreaReport();
}
