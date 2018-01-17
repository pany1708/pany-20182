package com.kingthy.platform.dto.order;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.io.Serializable;



/**
 * 
 *
 * UpdateOrdersReq
 * 
 * 赵生辉 2017年4月14日 下午2:11:28
 * 
 * @version 1.0.0
 *
 */
@Setter
@Getter
@Component
public class UpdateOrdersReq implements Serializable
{
    
    private static final long serialVersionUID = 1L;
    
    /**
     * 业务主键
     */
    @NotEmpty(message = "业务主键不能为空")
    private String uuid;
    
    /**
     * 收货地址
     */
    @NotEmpty(message = "收货地址不能为空")
    private String address;
    
    /**
     * 地区名称
     */
    @NotEmpty(message = "地区名称不能为空")
    private String areaName;
    
    /**
     * 收货人
     */
    @NotEmpty(message = "收货人不能为空")
    private String consignee;
    
    /**
     * 是否需要发票
     */
    @NotNull(message = "是否需要发票不能为空")
    private Boolean isInvoice;
    
    /**
     * 发票抬头
     */
    private String invoiceTitle;
    
    /**
     * 附言
     */
    private String memo;
    
    /**
     * 电话
     */
    @NotEmpty(message = "电话不能为空")
    private String phone;
    
    /**
     * 配送方式名称
     */
    @NotEmpty(message = "配送方式名称不能为空")
    private String shippingMethodName;
    
    /**
     * 地区业务主键
     */
    @NotEmpty(message = "地区名称不能为空")
    private String areaUuid;
    
    /**
     * 配送方式业务主键
     */
    @NotEmpty(message = "配送方式名称不能为空")
    private String shippingMethodUuid;
    
    /**
     * 省的业务主键
     */
    @NotEmpty(message = "省的名称不能为空")
    private String provinceUuid;
    
    /**
     * 省的名称
     */
    @NotEmpty(message = "省的名称不能为空")
    private String provinceName;
    
    /**
     * 市的业务主键
     */
    @NotEmpty(message = "市的名称不能为空")
    private String cityUuid;
    
    /**
     * 市的名称
     */
    @NotEmpty(message = "市的名称不能为空")
    private String cityName;
    
    /**
     * 订单状态 0:代付款 1:生产中 2:待发货 3:待签收 4:待评价 5:已取消 6:待退单
     */
    private Integer status;
}
