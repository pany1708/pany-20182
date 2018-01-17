package com.kingthy.platform.entity.upstream;

import lombok.Data;
import lombok.ToString;

/**
 * @author chenz
 * @version 1.0.0
 * @class_name PolicyDto
 * @description 策略出参封装类
 * @create 2017/6/9
 */
@Data
@ToString
public class PolicyDto {


    private String description;
    private boolean isRunTime;
}
