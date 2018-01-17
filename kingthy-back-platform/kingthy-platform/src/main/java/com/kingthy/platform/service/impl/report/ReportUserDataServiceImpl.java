package com.kingthy.platform.service.impl.report;

import com.kingthy.platform.dto.report.ReportUserDataDto;
import com.kingthy.platform.dto.report.ReportUserDataReq;
import com.kingthy.platform.entity.report.ReportUserData;
import com.kingthy.platform.mapper.report.ReportUserDataMapper;
import com.kingthy.platform.service.report.ReportUserDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author chenz
 * @version 1.0.0
 * @class_name ReportUserDataServiceImpl
 * @description 全站用户注册统计实现类
 * @create 2017/7/24
 */
@Service(value = "reportUserDataService")
public class ReportUserDataServiceImpl implements ReportUserDataService {

    @Autowired
    private ReportUserDataMapper reportUserDataMapper;


    @Override
    @Transactional
    public void add(ReportUserData reportUserData) {
        //删除原来的记录
        reportUserDataMapper.deleteByDataType(reportUserData);
        //添加新记录
        reportUserDataMapper.insert(reportUserData);
    }

    @Override
    public List<ReportUserDataDto> findRegisterNumByTime(ReportUserDataReq reportUserDataReq) {
        return reportUserDataMapper.findUserDataReport(reportUserDataReq);
    }
}
