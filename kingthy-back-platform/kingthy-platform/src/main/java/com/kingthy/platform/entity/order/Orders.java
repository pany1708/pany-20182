package com.kingthy.platform.entity.order;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kingthy.platform.util.BaseTableFileds;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 
 *
 * Orders(订单实体bean)
 * 
 * 赵生辉 2017年4月13日 下午8:25:48
 * 
 * @version 1.0.0
 *
 */
@Component
@Getter
@Setter
public class Orders extends BaseTableFileds
{
    /**
     * 支付时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm",timezone = "GMT+8")
    private Date paymentDate;
    
    /**
     * 发货时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm",timezone = "GMT+8")
    private Date deliveryDate;
    
    /**
     * 收货地址
     */
    private String address;
    
    /**
     * 订单金额
     */
    private BigDecimal amount;
    
    /**
     * 地区名称
     */
    private String areaName;
    
    /**
     * 完成日期
     */
    private Date completeDate;
    
    /**
     * 收货人
     */
    private String consignee;
    
    /**
     * 优惠券折扣
     */
    private BigDecimal couponDiscount;
    
    /**
     * 兑换积分
     */
    private Integer exchangePoint;
    
    /**
     * 过期时间
     */
    private Date expire;
    
    /**
     * 支付手续费
     */
    private BigDecimal fee;
    
    /**
     * 运费
     */
    private BigDecimal freight;
    
    /**
     * 是否需要发票
     */
    private Boolean isInvoice;
    
    /**
     * 发票内容
     */
    private String invoiceContent;
    
    /**
     * 发票抬头
     */
    private String invoiceTitle;
    
    /**
     * 是否已兑换积分
     */
    private Boolean isExchangePoint;
    
    /**
     * 是否使用优惠码
     */
    private Boolean isUseCouponCode;
    
    /**
     * 附言
     */
    private String memo;
    
    /**
     * 调整金额
     */
    private BigDecimal offsetAmount;
    
    /**
     * 支付方式类型 0:支付宝 1:微信 2:银联
     */
    private Integer paymentMethodType;
    
    /**
     * 电话
     */
    private String phone;
    
    /**
     * 商品价格
     */
    private BigDecimal price;
    
    /**
     * 商品数量
     */
    private Integer quantity;
    
    /**
     * 退款金额
     */
    private BigDecimal refundAmount;
    
    /**
     * 已退货数量
     */
    private Integer returnedQuantity;
    
    /**
     * 已发货数量
     */
    private Integer shippedQuantity;
    
    /**
     * 配送方式名称
     */
    private String shippingMethodName;
    
    /**
     * 运单号
     */
    private String shippingNumber;
    
    /**
     * 订单序列号
     */
    private String sn;
    
    /**
     * 订单状态 0:代付款 1:生产中 2:待发货 3:待签收 4:待评价 5:已取消 6:待退单
     */
    private Integer status;
    
    /**
     * 税金
     */
    private BigDecimal tax;
    
    /**
     * 订单类型 0:普通订单 1:积分兑换订单
     */
    private Integer type;
    
    /**
     * 邮编
     */
    private String zipCode;
    
    /**
     * 地区业务主键
     */
    private String areaUuid;
    
    /**
     * 优惠券业务主键
     */
    private String couponUuid;
    
    /**
     * 会员业务主键
     */
    private String memberUuid;
    
    /**
     * 会员名称
     */
    private String memberName;
    
    /**
     * 支付方式业务主键
     */
    private String paymentMethodUuid;
    
    /**
     * 配送方式业务主键
     */
    private String shippingMethodUuid;
    
    /**
     * 体型表的业务主键
     */
    private String figureUuid;
    
    /**
     * 省的业务主键
     */
    private String provinceUuid;
    
    /**
     * 省的名称
     */
    private String provinceName;
    
    /**
     * 市的业务主键
     */
    private String cityUuid;
    
    /**
     * 市的名称
     */
    private String cityName;
    
}