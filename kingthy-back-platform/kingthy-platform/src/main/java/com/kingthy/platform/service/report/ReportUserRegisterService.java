package com.kingthy.platform.service.report;

import com.kingthy.platform.dto.report.EverydayRegisterNumDto;
import com.kingthy.platform.dto.report.UserRegisterReportDto;

import java.util.List;

/**
 * @author chenz
 * @version 1.0.0
 * @class_name ReportUserRegisterService
 * @description 过去一周每日注册用户数接口
 * @create 2017/7/24
 */
public interface ReportUserRegisterService {
    //添加
    void add(List<EverydayRegisterNumDto> list);
    /**
     * 过去一周每日用户注册量统计结果
     * @return List<UserRegisterReportDto>
     */
    List<UserRegisterReportDto> userRegisterReport();
}
