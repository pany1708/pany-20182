package com.kingthy.platform.entity.upstream;

import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * @author chenz
 * @version 1.0.0
 * @class_name Policy
 * @description 分流策略
 * @create 2017/6/8
 */
@ToString
@Data
public class Policy {

    private String divtype;
    private List<DivData> divdata;
}
