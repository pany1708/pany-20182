package com.kingthy.platform.service.order;

import com.github.pagehelper.PageInfo;
import com.kingthy.platform.dto.order.OrderItemReq;
import com.kingthy.platform.entity.order.OrderItem;
import com.kingthy.platform.response.WebApiResponse;

import java.util.List;

/**
 * 
 *
 * OrderItemService(订单明细接口)
 * 
 * 赵生辉 2017年4月14日 上午9:50:16
 * 
 * @version 1.0.0
 *
 */
public interface OrderItemService
{
    /**
     * 
     * crate(创建一个订单) (用户下单生成一个订单)
     * 
     * @param orderItem
     * @return 赵生辉 int
     * @exception @since 1.0.0
     */
    int crate(OrderItem orderItem);
    
    /**
     * 
     * update(修改订单信息) (修改订单的详细信息)
     * 
     * @param orderItem
     * @return 赵生辉 int
     * @exception @since 1.0.0
     */
    int update(OrderItem orderItem);
    
    /**
     * 
     * findAllOrderItemPage(查询订单明细) (查询所有的订单明细)
     * 
     * @param orderItem
     * @return 赵生辉 List<OrderItem>
     * @exception @since 1.0.0
     */
    List<OrderItem> findAllOrderItem(OrderItem orderItem);
    
    /**
     * 
     * findAllOrderItem(分页查询订单明细) (订单详情页面展示订单的详细商品信息)
     * 
     * @param pageNo
     * @param pageSize
     * @param orderItem
     * @return 赵生辉 Package<OrderItem>
     * @exception @since 1.0.0
     */
    PageInfo<OrderItem> findOrderItemPage(Integer pageNo, Integer pageSize, OrderItem orderItem);


    WebApiResponse<?> list(OrderItemReq req) throws Exception;

    WebApiResponse<?> showOrderInfo(String orderSn) throws Exception;

    WebApiResponse<?> cancelOrder(String orderSn, String memberUuid) throws Exception;
}
