package com.kingthy.transaction;

import com.kingthy.dto.AgeBuyDataDto;
import com.kingthy.entity.ReportAgeGroupBuy;
import com.kingthy.mapper.ReportAgeGroupBuyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @author chenz
 * @version 1.0.0
 * @class_name ReportAgeGroupBuyTransaction
 * @description 根据年龄分段查询金额表事务
 * @create 2017/8/29
 */
@Component
public class ReportAgeGroupBuyTransaction {

    @Autowired
    private ReportAgeGroupBuyMapper reportAgeGroupBuyMapper;

    @Transactional(rollbackFor = Exception.class)
    public void add(List<AgeBuyDataDto> list) {
        reportAgeGroupBuyMapper.deleteAll();
        Date date = new Date();
        for (AgeBuyDataDto ageBuyDataDto : list) {
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
