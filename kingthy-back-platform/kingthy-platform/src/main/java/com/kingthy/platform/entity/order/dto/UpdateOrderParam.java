package com.kingthy.platform.entity.order.dto;

import com.kingthy.platform.entity.order.OrderLog;
import com.kingthy.platform.entity.order.Orders;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

/**
 * 
 *
 * UpdateOrderParam()
 * 
 * 赵生辉 2017年4月13日 下午8:27:20
 * 
 * @version 1.0.0
 *
 */
@Component
@Setter
@Getter
public class UpdateOrderParam
{
    private Orders orders;
    
    private OrderLog orderLog;
}
