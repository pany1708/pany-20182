package com.kingthy.platform.mapper.report;

import com.kingthy.platform.dto.report.UserRegisterReportDto;
import com.kingthy.platform.entity.report.ReportUserRegister;
import com.kingthy.platform.util.MyMapper;

import java.util.List;

/**
 *
 *
 * ReportUserRegisterMapper(用户注册数据统计报表结果映射接口)
 *
 * 陈钊 2017年7月24日 上午9:49:34
 *
 * @version 1.0.0
 *
 */
public interface ReportUserRegisterMapper extends MyMapper<ReportUserRegister>{

    /**
     * 删除原来记录
     * @return int
     */
    int deleteAll();

    /**
     * 过去一周每日用户注册量统计结果
     * @return List<UserRegisterReportDto>
     */
    List<UserRegisterReportDto> userRegisterReport();
}