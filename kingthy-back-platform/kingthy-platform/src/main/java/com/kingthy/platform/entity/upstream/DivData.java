package com.kingthy.platform.entity.upstream;

import lombok.Data;
import lombok.ToString;

/**
 * @author chenz
 * @version 1.0.0
 * @class_name DivData
 * @description 策略信息
 * @create 2017/6/8
 */
@Data
@ToString
public class DivData {

    private Range range;
    private String upstream;
}
