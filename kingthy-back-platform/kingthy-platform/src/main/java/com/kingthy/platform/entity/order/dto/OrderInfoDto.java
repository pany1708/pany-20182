package com.kingthy.platform.entity.order.dto;

import com.kingthy.platform.entity.order.OrderItem;
import com.kingthy.platform.entity.order.OrderLog;
import com.kingthy.platform.entity.order.Orders;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 
 *
 * OrderInfoDto()
 * 
 * 赵生辉 2017年4月13日 下午8:27:08
 * 
 * @version 1.0.0
 *
 */
@Component
@Setter
@Getter
public class OrderInfoDto extends Orders
{
    /**
     * 订单明细集合
     */
    private List<OrderItem> orderItems;
    
    /**
     * 订单记录集合
     */
    private List<OrderLog> orderLogs;
}
