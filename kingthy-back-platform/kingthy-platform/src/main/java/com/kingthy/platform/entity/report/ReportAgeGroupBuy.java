package com.kingthy.platform.entity.report;

import com.kingthy.platform.util.BaseTableFileds;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.math.BigDecimal;

@ToString
@Data
@EqualsAndHashCode(callSuper = true)
public class ReportAgeGroupBuy extends BaseTableFileds {

    private Boolean status;
    //金额
    private BigDecimal money;
    //年龄段
    private String ageGroup;

}