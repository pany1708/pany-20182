package com.kingthy.platform.entity.report;


import com.kingthy.platform.util.BaseTableFileds;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.math.BigDecimal;

@ToString
@Data
@EqualsAndHashCode(callSuper = true)
public class ReportRepeatBuyRate extends BaseTableFileds {


    private Boolean status;
    //重复购买率
    private BigDecimal rate;
}