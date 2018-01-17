package com.kingthy.platform.dto.moments;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * QueryMomentPageReq(描述其作用)
 * <p>
 * 赵生辉 2017年08月08日 17:36
 *
 * @version 1.0.0
 */
@Data
@ToString
public class QueryMomentPageReq implements Serializable
{
    @ApiModelProperty(name = "当前页数")
    private Integer pageNo;

    @ApiModelProperty(name = "每页数量")
    private Integer pageSize;

    @ApiModelProperty(name = "用户手机")
    private String phone;

    @ApiModelProperty(name = "审核状态")
    private Integer review;

    @ApiModelProperty(name = "用户昵称")
    private String memberNick;

    @ApiModelProperty(name = "开始时间")
    private Date beginDate;

    @ApiModelProperty(name = "结束时间")
    private Date endDate;

    private static final long serialVersionUID = 1L;
}
