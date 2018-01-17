package com.kingthy.platform.mapper.report;


import com.kingthy.platform.dto.report.AgeGroupBuyDto;
import com.kingthy.platform.entity.report.ReportAgeGroupBuy;
import com.kingthy.platform.util.MyMapper;

import java.util.List;

/**
 *
 *
 * ReportAgeGroupBuyMapper(按年龄段统计交易额报表映射接口)
 *
 * 陈钊 2017年7月24日 上午9:49:34
 *
 * @version 1.0.0
 *
 */
public interface ReportAgeGroupBuyMapper extends MyMapper<ReportAgeGroupBuy>{

    /**
     * 按年龄段查询交易金额
     * @return List<AgeGroupBuyDto>
     */
   List<AgeGroupBuyDto> findReportByAge();

    /**
     * 删除原来记录
     * @return int
     */
    int deleteAll();
}