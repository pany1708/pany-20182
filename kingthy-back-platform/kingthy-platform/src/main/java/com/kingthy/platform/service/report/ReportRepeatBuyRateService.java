package com.kingthy.platform.service.report;

import com.kingthy.platform.dto.report.RepeatBuyRateReportDto;
import com.kingthy.platform.entity.report.ReportRepeatBuyRate;


/**
 * @author chenz
 * @version 1.0.0
 * @class_name ReportRepeatBuyRateService
 * @description 全站重复购买率接口
 * @create 2017/7/24
 */
public interface ReportRepeatBuyRateService {
    void add(ReportRepeatBuyRate reportRepeatBuyRate);
    /**
     * 重复购买率统计结果
     * @return RepeatBuyRateReportDto
     */
    RepeatBuyRateReportDto repeatBuyRateReport();
}
