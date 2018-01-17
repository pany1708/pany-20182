package com.kingthy.platform.dto.moments;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * UpdateMomentReq(描述其作用)
 * <p>
 * 赵生辉 2017年08月09日 15:03
 *
 * @version 1.0.0
 */
@Data
@ToString
public class UpdateMomentReq implements Serializable
{
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(name = "业务主键")
    private String uuid;

    @ApiModelProperty(name = "审核状态")
    private Integer review;

    @ApiModelProperty(name = "原因")
    private String reason;
}
