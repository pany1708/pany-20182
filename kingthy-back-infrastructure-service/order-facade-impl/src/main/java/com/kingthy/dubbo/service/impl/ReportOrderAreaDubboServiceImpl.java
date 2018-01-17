package com.kingthy.dubbo.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.kingthy.dto.OrderAreaDto;
import com.kingthy.dto.OrderAreaReportDto;
import com.kingthy.dubbo.order.service.ReportOrderAreaDubboService;
import com.kingthy.mapper.ReportOrderAreaMapper;
import com.kingthy.transaction.ReportOrderAreaTransaction;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author chenz
 * @version 1.0.0
 * @class_name ReportOrderAreaDubboServiceImpl
 * @description 区域订单数量接口实现类
 * @create 2017/8/29
 */
@Service(version = "1.0.0",timeout = 10000)
public class ReportOrderAreaDubboServiceImpl implements ReportOrderAreaDubboService {

    @Autowired
    private ReportOrderAreaMapper reportOrderAreaMapper;

    @Autowired
    private ReportOrderAreaTransaction reportOrderAreaTransaction;

    @Override
    public void add(List<OrderAreaDto> list) {
        reportOrderAreaTransaction.add(list);
    }

    @Override
    public List<OrderAreaReportDto> findOrderAreaReport() {
        return reportOrderAreaMapper.findOrderAreaReport();
    }
}
