package com.kingthy.platform.entity.report;

import com.kingthy.platform.util.BaseTableFileds;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@ToString
@Data
@EqualsAndHashCode(callSuper = true)
public class ReportUserData extends BaseTableFileds {


    private Boolean status;
    //用户量
    private Integer number;
    //数据类型
    private Integer dataType;
    //时间段类型
    private Integer timeType;


}