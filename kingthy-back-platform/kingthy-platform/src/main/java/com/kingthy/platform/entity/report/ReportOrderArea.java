package com.kingthy.platform.entity.report;

import com.kingthy.platform.util.BaseTableFileds;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString
@Data
@EqualsAndHashCode(callSuper = true)
public class ReportOrderArea extends BaseTableFileds {

    private Boolean status;
    //数量
    private Integer num;
    //省份代号
    private String province;


}