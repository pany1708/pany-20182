package com.kingthy.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author : 潘勇
 * @Description: 查询资源列表的请求参数封装（采样工具使用）
 * @Date: 2018/1/8 14:45
 */
@Data
@ToString
public class QueryResourceInfoReq implements Serializable
{
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "物料编码")
    private String materialCode;

    @ApiModelProperty(value = "物料名称")
    private String materialName;

    @ApiModelProperty(value = "子分类")
    private String sourceUuid;

    @ApiModelProperty(value = "分类类型 1 面料 2 辅料")
    private String sourceType;
}
