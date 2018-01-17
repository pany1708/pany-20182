package com.kingthy.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author 潘勇
 * @Data 2017/12/25 10:20.
 */
@Data
@AllArgsConstructor
public class Resource4SamplingDTO implements Serializable
{
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "当前页数")
    private int pageNum;

    @ApiModelProperty(value = "每页显示条数")
    private int pageSize;

    @ApiModelProperty(value = "物料编码")
    private String materialCode;

    @ApiModelProperty(value = "物料名称")
    private String materialName;

    @ApiModelProperty(value = "子分类")
    private int subCategroy;
}
