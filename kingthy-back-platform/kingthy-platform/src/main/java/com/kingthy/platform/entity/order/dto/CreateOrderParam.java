package com.kingthy.platform.entity.order.dto;

import com.kingthy.platform.entity.order.OrderItem;
import com.kingthy.platform.entity.order.Orders;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 
 *
 * CreateOrderParam()
 * 
 * 赵生辉 2017年4月13日 下午8:27:01
 * 
 * @version 1.0.0
 *
 */
@Component
@Setter
@Getter
public class CreateOrderParam
{
    /**
     * 订单详情
     */
    private Orders order;
    
    /**
     * 订单商品明细集合
     */
    @NotEmpty(message = "订单明细不能为空")
    private List<OrderItem> orderItemList;
}
