package com.kingthy.transaction;

import com.kingthy.dto.OrderAreaDto;
import com.kingthy.entity.ReportOrderArea;
import com.kingthy.mapper.ReportOrderAreaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @author chenz
 * @version 1.0.0
 * @class_name ReportOrderAreaTransaction
 * @description 区域订单数量事务
 * @create 2017/8/29
 */
@Component
public class ReportOrderAreaTransaction {
    @Autowired
    private ReportOrderAreaMapper reportOrderAreaMapper;

    @Transactional
    public void add(List<OrderAreaDto> list) {
        reportOrderAreaMapper.deleteAll();
        Date date = new Date();
        for (OrderAreaDto orderAreaDto : list){
            ReportOrderArea reportOrderArea = new ReportOrderArea();
            reportOrderArea.setNum(orderAreaDto.getNum());
            reportOrderArea.setProvince(orderAreaDto.getProvince());
            reportOrderArea.setStatus(true);
            reportOrderArea.setCreateDate(date);
            reportOrderArea.setDelFlag(false);
            reportOrderArea.setVersion(1);
            reportOrderAreaMapper.insert(reportOrderArea);
        }
    }
}
