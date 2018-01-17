package com.kingthy.transaction;

import com.kingthy.dto.EverydayRegisterNumDto;
import com.kingthy.entity.ReportUserRegister;
import com.kingthy.mapper.ReportUserRegisterMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @author chenz
 * @version 1.0.0
 * @class_name ReportUserRegisterTransaction
 * @description 每日注册用户数表事务
 * @create 2017/8/29
 */
@Component
public class ReportUserRegisterTransaction {
    @Autowired
    private ReportUserRegisterMapper reportUserRegisterMapper;

    @Transactional(rollbackFor = Exception.class)
    public void add(List<EverydayRegisterNumDto> list) {
        //删除旧记录
        reportUserRegisterMapper.deleteAll();
        Date date = new Date();
        for (EverydayRegisterNumDto everydayRegisterNumDto : list) {
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
}
