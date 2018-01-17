package com.kingthy.platform.mapper.report;

import com.kingthy.platform.dto.report.RepeatBuyRateReportDto;
import com.kingthy.platform.entity.report.ReportRepeatBuyRate;
import com.kingthy.platform.util.MyMapper;

import java.util.List;

/**
 *
 *
 * ReportRepeatBuyRateMapper(用户重复购买率数据统计报表结果映射接口)
 *
 * 陈钊 2017年7月24日 上午9:49:34
 *
 * @version 1.0.0
 *
 */
public interface ReportRepeatBuyRateMapper extends MyMapper<ReportRepeatBuyRate>{

    /**
     * 删除原来记录
     * @return int
     */
    int deleteAll();

    /**
     * 重复购买率统计结果
     * @return RepeatBuyRateReportDto
     */
   RepeatBuyRateReportDto repeatBuyRateReport();
}