package com.kingthy.mapper;


import com.kingthy.dto.AgeGroupBuyDto;
import com.kingthy.entity.ReportAgeGroupBuy;
import com.kingthy.util.MyMapper;

import java.util.List;

/**
 * ReportAgeGroupBuyMapper(按年龄段统计交易额报表映射接口)
 * @author 陈钊
 * @date 2017年7月24日 上午9:49:34
 * @version 1.0.0
 */
public interface ReportAgeGroupBuyMapper extends MyMapper<ReportAgeGroupBuy> {

    /**
     * 按年龄段查询交易金额
     *
     * @return List<AgeGroupBuyDto>
     */
    List<AgeGroupBuyDto> findReportByAge();

    /**
     * 删除原来记录
     *
     * @return int
     */
    int deleteAll();
}