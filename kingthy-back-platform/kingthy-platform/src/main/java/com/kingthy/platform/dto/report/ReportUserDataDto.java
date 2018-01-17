package com.kingthy.platform.dto.report;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

/**
 * @author chenz
 * @version 1.0.0
 * @class_name ReportUserDataDto
 * @description 用户数据模块出参封装类
 * @create 2017/7/24
 */
@Data
@ToString
public class ReportUserDataDto {
    private int number;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date refreshTime;
    private int dataType;
}
