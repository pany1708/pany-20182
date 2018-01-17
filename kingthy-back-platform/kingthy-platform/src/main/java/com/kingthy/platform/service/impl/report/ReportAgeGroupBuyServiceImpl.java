package com.kingthy.platform.service.impl.report;

import com.kingthy.platform.dto.report.AgeBuyDataDto;
import com.kingthy.platform.dto.report.AgeGroupBuyDto;
import com.kingthy.platform.entity.report.ReportAgeGroupBuy;
import com.kingthy.platform.mapper.report.ReportAgeGroupBuyMapper;
import com.kingthy.platform.service.report.ReportAgeGroupBuyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author chenz
 * @version 1.0.0
 * @class_name ReportAgeGroupBuyServiceImpl
 * @description 不同年龄段购买情况实现类
 * @create 2017/7/24
 */
@Service(value = "reportAgeGroupBuyService")
public class ReportAgeGroupBuyServiceImpl implements ReportAgeGroupBuyService {

    @Autowired
    private ReportAgeGroupBuyMapper reportAgeGroupBuyMapper;

    @Override
    public List<AgeGroupBuyDto> findReportByAge() {
        return reportAgeGroupBuyMapper.findReportByAge();
    }

    @Override
    @Transactional
    public void add(List<AgeBuyDataDto> list) {
        reportAgeGroupBuyMapper.deleteAll();
        Date date = new Date();
        for (AgeBuyDataDto ageBuyDataDto : list){
            ReportAgeGroupBuy reportAgeGroupBuy = new ReportAgeGroupBuy();
            reportAgeGroupBuy.setAgeGroup(ageBuyDataDto.getAgeGroup());
            reportAgeGroupBuy.setMoney(ageBuyDataDto.getMoney());
            reportAgeGroupBuy.setStatus(true);
            reportAgeGroupBuy.setCreateDate(date);
            reportAgeGroupBuy.setDelFlag(false);
            reportAgeGroupBuy.setVersion(1);
            reportAgeGroupBuyMapper.insert(reportAgeGroupBuy);
        }
    }
}
