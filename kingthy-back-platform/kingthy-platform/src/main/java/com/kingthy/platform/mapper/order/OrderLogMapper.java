package com.kingthy.platform.mapper.order;

import com.kingthy.platform.dto.order.OrderLogDto;
import com.kingthy.platform.entity.order.OrderLog;
import com.kingthy.platform.util.MyMapper;

import java.util.List;

/**
 * 
 *
 * OrderLogMapper(订单记录映射接口)
 * 
 * 赵生辉 2017年4月14日 上午9:49:28
 * 
 * @version 1.0.0
 *
 */
public interface OrderLogMapper extends MyMapper<OrderLog>
{

    List<OrderLogDto> selectOrderLogList(String orderSn);
    
}