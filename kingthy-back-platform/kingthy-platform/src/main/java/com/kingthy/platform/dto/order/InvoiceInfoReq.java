package com.kingthy.platform.dto.order;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @AUTHORS xumin
 * @Description:
 * @DATE Created by 17:04 on 2017/7/10.
 * @Modified by:
 */
@Data
@ToString
public class InvoiceInfoReq implements Serializable {

    /**
     * 当前页数
     */
    @NotNull(message = "当前页数不能为空")
    private Integer pageNum;

    /**
     * 每页展示的数量
     */
    @NotNull(message = "每页数量不能为空")
    private Integer pageSize;

    /**
     * 订单状态
     */
    private Integer status;

    @ApiModelProperty("订单号")
    private String orderSn;
    @ApiModelProperty("发票编号")
    private String invoiceNo;
    @ApiModelProperty("手机号")
    private String phone;
    @ApiModelProperty("发票title")
    private String invoiceTitle;
    @ApiModelProperty("发票类型")
    private Integer invoiceType;

    @ApiModelProperty("金额")
    private Double amountMin;
    private Double amountMax;

    @ApiModelProperty("开通时间")
    private Long billingTimeBegin;
    private Long billingTimeEnd;

    @JsonIgnore
    private String uuid;
}
