package com.kingthy.dubbo.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.kingthy.dto.AgeBuyDataDto;
import com.kingthy.dto.AgeGroupBuyDto;
import com.kingthy.dubbo.user.service.ReportAgeGroupBuyDubboService;
import com.kingthy.mapper.ReportAgeGroupBuyMapper;
import com.kingthy.transaction.ReportAgeGroupBuyTransaction;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author chenz
 * @version 1.0.0
 * @class_name ReportAgeGroupBuyDubboServiceImpl
 * @description 根据年龄分段查询金额接口实现类
 * @create 2017/8/29
 */
@Service(version = "1.0.0", timeout = 10000)
public class ReportAgeGroupBuyDubboServiceImpl implements ReportAgeGroupBuyDubboService {

    @Autowired
    private ReportAgeGroupBuyMapper reportAgeGroupBuyMapper;

    @Autowired
    private ReportAgeGroupBuyTransaction reportAgeGroupBuyTransaction;

    @Override
    public List<AgeGroupBuyDto> findReportByAge() {
        return reportAgeGroupBuyMapper.findReportByAge();
    }

    @Override
    public void add(List<AgeBuyDataDto> list) {
        reportAgeGroupBuyTransaction.add(list);
    }
}
