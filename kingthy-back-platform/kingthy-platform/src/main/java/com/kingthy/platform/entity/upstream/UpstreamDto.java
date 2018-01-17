package com.kingthy.platform.entity.upstream;

import lombok.Data;
import lombok.ToString;

/**
 * @author chenz
 * @version 1.0.0
 * @class_name UpstreamDto
 * @description 分流接口响应类
 * @create 2017/6/8
 */
@Data
@ToString
public class UpstreamDto {

    private int code;
    private String desc;
}
