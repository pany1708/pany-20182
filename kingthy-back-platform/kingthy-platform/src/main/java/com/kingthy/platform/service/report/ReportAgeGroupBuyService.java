package com.kingthy.platform.service.report;

import com.kingthy.platform.dto.report.AgeBuyDataDto;
import com.kingthy.platform.dto.report.AgeGroupBuyDto;

import java.util.List;

/**
 * @author chenz
 * @version 1.0.0
 * @class_name ReportAgeGroupBuyService
 * @description 不同年龄段购买情况接口
 * @create 2017/7/24
 */
public interface ReportAgeGroupBuyService {

    /**
     * 添加
     */
    void add(List<AgeBuyDataDto> list);

    /**
     * 按年龄段查询交易金额
     * @return List<AgeGroupBuyDto>
     */
    List<AgeGroupBuyDto> findReportByAge();
}
