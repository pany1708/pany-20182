package com.kingthy.dubbo.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.kingthy.dto.ReportUserDataDto;
import com.kingthy.dubbo.user.service.ReportUserDataDubboService;
import com.kingthy.entity.ReportUserData;
import com.kingthy.mapper.ReportUserDataMapper;
import com.kingthy.request.ReportUserDataReq;
import com.kingthy.transaction.ReportUserDataTransaction;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author chenz
 * @version 1.0.0
 * @class_name ReportUserDataDubboServiceImpl
 * @description 全站注册用户接口实现类
 * @create 2017/8/29
 */
@Service(version = "1.0.0", timeout = 10000)
public class ReportUserDataDubboServiceImpl implements ReportUserDataDubboService {

    @Autowired
    private ReportUserDataMapper reportUserDataMapper;

    @Autowired
    private ReportUserDataTransaction reportUserDataTransaction;

    @Override
    public void add(ReportUserData reportUserData) {
        reportUserDataTransaction.add(reportUserData);
    }

    @Override
    public List<ReportUserDataDto> findRegisterNumByTime(ReportUserDataReq reportUserDataReq) {
        return reportUserDataMapper.findUserDataReport(reportUserDataReq);
    }
}
