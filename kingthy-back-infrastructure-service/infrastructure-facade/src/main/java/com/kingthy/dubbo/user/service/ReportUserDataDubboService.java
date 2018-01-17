package com.kingthy.dubbo.user.service;



import com.kingthy.dto.ReportUserDataDto;
import com.kingthy.entity.ReportUserData;
import com.kingthy.request.ReportUserDataReq;

import java.util.List;

/**
 * @author chenz
 * @version 1.0.0
 * @class_name ReportUserDataDubboService
 * @description 全站注册用户接口
 * @create 2017/7/24
 */
public interface ReportUserDataDubboService {

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
