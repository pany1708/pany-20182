package com.kingthy.platform.mapper.report;


import com.kingthy.platform.dto.report.ReportUserDataDto;
import com.kingthy.platform.dto.report.ReportUserDataReq;
import com.kingthy.platform.entity.report.ReportUserData;
import com.kingthy.platform.util.MyMapper;

import java.util.List;

/**
 *
 *
 * ReportUserDataMapper(用户数据统计报表结果映射接口)
 *
 * 陈钊 2017年7月24日 上午9:49:34
 *
 * @version 1.0.0
 *
 */
public interface ReportUserDataMapper extends MyMapper<ReportUserData>{

     /**
      * 用户数据统计报表结果
      * @param reportUserDataReq
      * @return List<ReportUserDataDto>
      */
     List<ReportUserDataDto> findUserDataReport(ReportUserDataReq reportUserDataReq);

     /**
      * 根据数据类型删除
      * @param reportUserData
      * @return int
      */
     int deleteByDataType(ReportUserData reportUserData);
}