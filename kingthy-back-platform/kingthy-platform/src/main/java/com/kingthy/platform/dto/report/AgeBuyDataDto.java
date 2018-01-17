package com.kingthy.platform.dto.report;

import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;

/**
 * @author chenz
 * @version 1.0.0
 * @class_name AgeBuyDataDto
 * @description 不同年龄段购买情况出参封装类
 * @create 2017/7/24
 */
@Data
@ToString
public class AgeBuyDataDto {
    private String ageGroup;
    private BigDecimal money;
}
