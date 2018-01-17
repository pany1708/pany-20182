package com.kingthy.dubbo.order.service;


import com.kingthy.dto.RepeatBuyRateReportDto;
import com.kingthy.entity.ReportRepeatBuyRate;

/**
 * @author chenz
 * @version 1.0.0
 * @class_name ReportRepeatBuyRateDubboService
 * @description 全站重复购买率接口
 * @create 2017/7/24
 */
public interface ReportRepeatBuyRateDubboService {
    void add(ReportRepeatBuyRate reportRepeatBuyRate);
    /**
     * 重复购买率统计结果
     * @return RepeatBuyRateReportDto
     */
    RepeatBuyRateReportDto repeatBuyRateReport();
}
