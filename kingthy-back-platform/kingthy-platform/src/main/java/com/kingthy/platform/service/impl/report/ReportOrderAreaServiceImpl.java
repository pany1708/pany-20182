package com.kingthy.platform.service.impl.report;

import com.kingthy.platform.dto.report.OrderAreaDto;
import com.kingthy.platform.dto.report.OrderAreaReportDto;
import com.kingthy.platform.entity.report.ReportOrderArea;
import com.kingthy.platform.mapper.report.ReportOrderAreaMapper;
import com.kingthy.platform.service.report.ReportOrderAreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @author chenz
 * @version 1.0.0
 * @class_name ReportOrderAreaServiceImpl
 * @description 区域订单数量实现类
 * @create 2017/7/24
 */
@Service(value = "ReportOrderAreaService")
public class ReportOrderAreaServiceImpl implements ReportOrderAreaService {

    @Autowired
    private ReportOrderAreaMapper reportOrderAreaMapper;

    @Override
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

    @Override
    public List<OrderAreaReportDto> findOrderAreaReport() {
        return reportOrderAreaMapper.findOrderAreaReport();
    }
}
