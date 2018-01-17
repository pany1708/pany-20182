package com.kingthy.platform.service.impl.report;

import com.kingthy.platform.dto.report.EverydayRegisterNumDto;
import com.kingthy.platform.dto.report.UserRegisterReportDto;
import com.kingthy.platform.entity.report.ReportUserRegister;
import com.kingthy.platform.mapper.report.ReportUserRegisterMapper;
import com.kingthy.platform.service.report.ReportUserRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @author chenz
 * @version 1.0.0
 * @class_name ReportUserRegisterServiceImpl
 * @description 过去一周每日注册用户数实现类
 * @create 2017/7/24
 */
@Service(value = "reportUserRegisterService")
public class ReportUserRegisterServiceImpl implements ReportUserRegisterService {

    @Autowired
    private ReportUserRegisterMapper reportUserRegisterMapper;

    @Override
    @Transactional
    public void add(List<EverydayRegisterNumDto> list) {
        //删除旧记录
        reportUserRegisterMapper.deleteAll();
        Date date = new Date();
        for (EverydayRegisterNumDto everydayRegisterNumDto :list){
            ReportUserRegister reportUserRegister = new ReportUserRegister();
            reportUserRegister.setNumber(everydayRegisterNumDto.getNumber());
            reportUserRegister.setRegisterDate(everydayRegisterNumDto.getCreateDate());
            reportUserRegister.setStatus(true);
            reportUserRegister.setCreateDate(date);
            reportUserRegister.setDelFlag(false);
            reportUserRegister.setVersion(1);
            //增加新记录
            reportUserRegisterMapper.insert(reportUserRegister);
        }
    }

    @Override
    public List<UserRegisterReportDto> userRegisterReport() {
        return reportUserRegisterMapper.userRegisterReport();
    }
}
