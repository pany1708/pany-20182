package com.kingthy.dubbo.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.kingthy.dto.EverydayRegisterNumDto;
import com.kingthy.dto.UserRegisterReportDto;
import com.kingthy.dubbo.user.service.ReportUserRegisterDubboService;
import com.kingthy.mapper.ReportUserRegisterMapper;
import com.kingthy.transaction.ReportUserRegisterTransaction;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author chenz
 * @version 1.0.0
 * @class_name ReportUserRegisterDubboServiceImpl
 * @description 每日注册用户数接口实现类
 * @create 2017/8/29
 */
@Service(version = "1.0.0", timeout = 10000)
public class ReportUserRegisterDubboServiceImpl implements ReportUserRegisterDubboService {
    @Autowired
    private ReportUserRegisterMapper reportUserRegisterMapper;

    @Autowired
    private ReportUserRegisterTransaction reportUserRegisterTransaction;

    @Override
    public void add(List<EverydayRegisterNumDto> list) {
        reportUserRegisterTransaction.add(list);
    }

    @Override
    public List<UserRegisterReportDto> userRegisterReport() {
        return reportUserRegisterMapper.userRegisterReport();
    }
}
