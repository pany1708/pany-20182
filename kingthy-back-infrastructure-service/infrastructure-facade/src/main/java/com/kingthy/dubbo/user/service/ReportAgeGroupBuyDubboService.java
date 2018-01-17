package com.kingthy.dubbo.user.service;


import com.kingthy.dto.AgeBuyDataDto;
import com.kingthy.dto.AgeGroupBuyDto;

import java.util.List;

/**
 * @author chenz
 * @version 1.0.0
 * @class_name ReportAgeGroupBuyDubboService
 * @description 不同年龄段购买情况接口
 * @create 2017/7/24
 */
public interface ReportAgeGroupBuyDubboService {

    /**
     * 添加
     * @param  list
     *
     */
    void add(List<AgeBuyDataDto> list);

    /**
     * 按年龄段查询交易金额
     * @return List<AgeGroupBuyDto>
     */
    List<AgeGroupBuyDto> findReportByAge();
}
