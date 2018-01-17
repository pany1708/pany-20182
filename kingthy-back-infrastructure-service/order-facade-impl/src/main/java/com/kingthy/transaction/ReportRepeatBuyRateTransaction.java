package com.kingthy.transaction;

import com.kingthy.entity.ReportRepeatBuyRate;
import com.kingthy.mapper.ReportRepeatBuyRateMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author chenz
 * @version 1.0.0
 * @class_name ReportRepeatBuyRateTransaction
 * @description 重复购买率事务
 * @create 2017/8/29
 */
@Component
public class ReportRepeatBuyRateTransaction {
    @Autowired
    private ReportRepeatBuyRateMapper reportRepeatBuyRateMapper;

    @Transactional
    public void add(ReportRepeatBuyRate reportRepeatBuyRate) {
        reportRepeatBuyRateMapper.deleteAll();
        reportRepeatBuyRateMapper.insert(reportRepeatBuyRate);
    }
}
