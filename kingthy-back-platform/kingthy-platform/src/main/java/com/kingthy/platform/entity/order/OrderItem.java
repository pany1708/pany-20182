package com.kingthy.platform.entity.order;

import com.kingthy.platform.util.BaseTableFileds;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * 
 *
 * OrderItem(订单明细实体bean)
 * 
 * 赵生辉 2017年4月13日 下午8:25:29
 * 
 * @version 1.0.0
 *
 */
@Component
@Getter
@Setter
public class OrderItem extends BaseTableFileds
{
    
    /**
     * 是否需要物流
     */
    private Boolean isDelivery;
    
    /**
     * 商品名称
     */
    private String name;
    
    /**
     * 商品价格
     */
    private BigDecimal price;
    
    /**
     * 商品数量
     */
    private Integer quantity;
    
    /**
     * 子订单编号
     */
    private String sn;
    
    /**
     * 缩略图地址
     */
    private String thumbnail;
    
    /**
     * 类型
     */
    private Integer type;
    
    /**
     * 订单业务主键
     */
    private String orderUuid;
    
    /**
     * 商品业务主键
     */
    private String productUuid;
    
    /**
     * 商品定制信息
     */
    private String specifications;
    
}