package com.kingthy.platform.mapper.report;


import com.kingthy.platform.dto.report.OrderAreaReportDto;
import com.kingthy.platform.entity.report.ReportOrderArea;
import com.kingthy.platform.util.MyMapper;

import java.util.List;

/**
 *
 *
 * ReportOrderAreaMapper(用户购买区域分布数据统计报表结果映射接口)
 *
 * 陈钊 2017年7月24日 上午9:49:34
 *
 * @version 1.0.0
 *
 */
public interface ReportOrderAreaMapper extends MyMapper<ReportOrderArea>{

    /**
     * 删除原来记录
     * @return int
     */
    int deleteAll();

    /**
     * 地区订单分布统计结果
     * @return List<OrderAreaReportDto>
     */
    List<OrderAreaReportDto> findOrderAreaReport();

}