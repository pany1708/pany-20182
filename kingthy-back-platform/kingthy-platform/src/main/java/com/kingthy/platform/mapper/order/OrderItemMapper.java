package com.kingthy.platform.mapper.order;

import com.kingthy.platform.dto.order.OrderItemDto;
import com.kingthy.platform.dto.order.OrderItemReq;
import com.kingthy.platform.dto.report.UserDataReq;
import com.kingthy.platform.entity.order.OrderItem;
import com.kingthy.platform.util.MyMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 
 *
 * OrderItemMapper(订单明细映射接口)
 * 
 * 赵生辉 2017年4月14日 上午9:49:21
 * 
 * @version 1.0.0
 *
 */
public interface OrderItemMapper extends MyMapper<OrderItem>
{
    List<OrderItemDto> findOrderItemListByPage(OrderItemReq req);

    OrderItemDto.OrderDetail selectOrderDetail(String orderSn);

    OrderItemDto.InvoiceInfo selectShowOrderInfo(String orderSn);

    String selectDesingerByGoodsUuid(String goodsUuid);

    int cancelOrderBySn( @Param("orderSn") String orderSn, @Param("memberUuid")String memberUuid);

    String selectOrderUuidBySn(String orderSn);

}