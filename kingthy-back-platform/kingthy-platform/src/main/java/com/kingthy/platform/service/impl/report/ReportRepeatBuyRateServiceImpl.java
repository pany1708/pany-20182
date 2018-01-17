package com.kingthy.platform.service.impl.report;

import com.kingthy.platform.dto.report.RepeatBuyRateReportDto;
import com.kingthy.platform.entity.report.ReportRepeatBuyRate;
import com.kingthy.platform.mapper.report.ReportRepeatBuyRateMapper;
import com.kingthy.platform.service.report.ReportRepeatBuyRateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * @author chenz
 * @version 1.0.0
 * @class_name ReportRepeatBuyRateServiceImpl
 * @description 重复购买率实现类
 * @create 2017/7/24
 */
@Service(value = "reportRepeatBuyRateService")
public class ReportRepeatBuyRateServiceImpl implements ReportRepeatBuyRateService {

    @Autowired
    private ReportRepeatBuyRateMapper reportRepeatBuyRateMapper;

    @Override
    @Transactional
    public void add(ReportRepeatBuyRate reportRepeatBuyRate) {
        reportRepeatBuyRateMapper.deleteAll();
        reportRepeatBuyRateMapper.insert(reportRepeatBuyRate);
    }

    @Override
    public RepeatBuyRateReportDto repeatBuyRateReport() {
        return reportRepeatBuyRateMapper.repeatBuyRateReport();
    }
}
