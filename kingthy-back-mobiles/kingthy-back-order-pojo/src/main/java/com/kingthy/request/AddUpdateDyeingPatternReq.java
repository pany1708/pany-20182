package com.kingthy.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * author:fmq
 */
@Data
@ToString
public class AddUpdateDyeingPatternReq implements Serializable
{
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "花样编码")
    private String code;

    @ApiModelProperty(value = "花样名称")
    private String name;

    @ApiModelProperty(value = "花样效果图")
    private String imageUrl;

    @ApiModelProperty(value = "效果图长")
    private Integer imageLength;

    @ApiModelProperty(value = "效果图宽")
    private Integer imageWidth;

    @ApiModelProperty(value = "生产文件")
    private String productFile;

    @ApiModelProperty(value = "花样分类")
    private String type;

    @ApiModelProperty(value = "价格")
    private BigDecimal price;

    @ApiModelProperty(value = "备注说明")
    private String remark;

    @ApiModelProperty(value = "创建开始时间")
    private String beginTime;

    @ApiModelProperty(value = "创建结束时间")
    private String endTime;

    @ApiModelProperty(value = "UUID")
    private String uuid;

    @ApiModelProperty(value = "当前页数")
    private int pageNum;

    @ApiModelProperty(value = "每页显示条数")
    private int pageSize;

    @ApiModelProperty(value = "状态")
    private String status;
}
