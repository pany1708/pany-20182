package com.kingthy.platform.entity.report;

import com.kingthy.platform.util.BaseTableFileds;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Date;


@ToString
@Data
@EqualsAndHashCode(callSuper = true)
public class ReportUserRegister extends BaseTableFileds {


    private Boolean status;
    //注册用户数
    private Integer number;
    //注册日期
    private Date registerDate;


}