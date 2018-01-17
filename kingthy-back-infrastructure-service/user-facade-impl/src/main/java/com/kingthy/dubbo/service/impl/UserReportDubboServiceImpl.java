package com.kingthy.dubbo.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.kingthy.dto.EverydayRegisterNumDto;
import com.kingthy.dto.ReportUserUuidByAgeDto;
import com.kingthy.dubbo.user.service.UserReportDubboService;
import com.kingthy.mapper.UserReportMapper;
import com.kingthy.request.UserDataReq;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chenz
 * @version 1.0.0
 * @class_name UserReportDubboServiceImpl
 * @description 用户数据统计接口实现类
 * @create 2017/8/28
 */
@Service(version = "1.0.0", timeout = 10000)
public class UserReportDubboServiceImpl implements UserReportDubboService {

    @Autowired
    private UserReportMapper userReportMapper;

    @Override
    public int findRegisterUserNum(UserDataReq userDataReq) {
        return userReportMapper.findRegisterUserNum(userDataReq);
    }

    @Override
    public ArrayList<EverydayRegisterNumDto> lastWeekNewUserNum(UserDataReq userDataReq) {
        List<EverydayRegisterNumDto> list = userReportMapper.lastWeekNewUserNum(userDataReq);
        ArrayList<EverydayRegisterNumDto> arrayList = new ArrayList<>();
        if (list!=null){
            arrayList = (ArrayList<EverydayRegisterNumDto>) list;
        }
        return arrayList;
    }

    @Override
    public ArrayList<ReportUserUuidByAgeDto> findUuidByAge() {
        List<ReportUserUuidByAgeDto> list = userReportMapper.findUuidByAge();
        ArrayList<ReportUserUuidByAgeDto> arrayList = new ArrayList<>();
        if (list!=null){
            arrayList = (ArrayList<ReportUserUuidByAgeDto>)list;
        }
        return arrayList;
    }
}
