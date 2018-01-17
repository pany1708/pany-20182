package com.kingthy.platform.service.report;

import com.kingthy.platform.dto.report.ReportUserDataDto;
import com.kingthy.platform.dto.report.ReportUserDataReq;
import com.kingthy.platform.entity.report.ReportUserData;

import java.util.List;

/**
 * @author chenz
 * @version 1.0.0
 * @class_name ReportUserDataService
 * @description 全站注册用户接口
 * @create 2017/7/24
 */
public interface ReportUserDataService {

    /**
     * 添加注册用户记录
     * @param reportUserData
     */
    void add(ReportUserData reportUserData);

    /**
     * 查询注册用户数量
     * @param reportUserDataReq
     * @return List<ReportUserDataDto>
     */
    List<ReportUserDataDto> findRegisterNumByTime(ReportUserDataReq reportUserDataReq);
}
