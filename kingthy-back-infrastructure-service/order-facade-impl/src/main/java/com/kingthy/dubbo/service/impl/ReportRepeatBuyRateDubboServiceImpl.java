package com.kingthy.dubbo.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.kingthy.dto.RepeatBuyRateReportDto;
import com.kingthy.dubbo.order.service.ReportRepeatBuyRateDubboService;
import com.kingthy.entity.ReportRepeatBuyRate;
import com.kingthy.mapper.ReportRepeatBuyRateMapper;
import com.kingthy.transaction.ReportRepeatBuyRateTransaction;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author chenz
 * @version 1.0.0
 * @class_name ReportRepeatBuyRateDubboServiceImpl
 * @description 重复购买率接口实现类
 * @create 2017/8/29
 */
@Service(version = "1.0.0",timeout = 10000)
public class ReportRepeatBuyRateDubboServiceImpl implements ReportRepeatBuyRateDubboService {

    @Autowired
    private ReportRepeatBuyRateMapper reportRepeatBuyRateMapper;

    @Autowired
    private ReportRepeatBuyRateTransaction reportRepeatBuyRateTransaction;

    @Override
    public void add(ReportRepeatBuyRate reportRepeatBuyRate) {
        reportRepeatBuyRateTransaction.add(reportRepeatBuyRate);
    }

    @Override
    public RepeatBuyRateReportDto repeatBuyRateReport() {
        return reportRepeatBuyRateMapper.repeatBuyRateReport();
    }
}
