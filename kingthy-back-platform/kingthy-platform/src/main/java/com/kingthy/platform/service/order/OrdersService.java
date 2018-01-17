package com.kingthy.platform.service.order;

import com.github.pagehelper.PageInfo;
import com.kingthy.platform.entity.order.OrderItem;
import com.kingthy.platform.entity.order.OrderLog;
import com.kingthy.platform.entity.order.Orders;
import com.kingthy.platform.entity.order.dto.OrderInfoDto;
import com.kingthy.platform.response.WebApiResponse;

import java.util.List;

/**
 * 
 *
 * OrdersService(订单接口)
 * 
 * 赵生辉 2017年4月14日 上午9:50:27
 * 
 * @version 1.0.0
 *
 */
public interface OrdersService
{

    WebApiResponse<?> InvoiceList();

    /**
     * 
     * create(创建一个订单) (下单生成新的订单)
     * 
     * @param order
     * @return 赵生辉 int
     * @exception @since 1.0.0
     */
    int create(Orders order, List<OrderItem> orderItems);
    
    /**
     * 
     * update(修改订单的详细信息) (修改订单的详细信息 – 可选)
     * 
     * @param order
     * @return 赵生辉 int
     * @exception @since 1.0.0
     */
    int update(Orders orders);
    
    /**
     * 
     * updateStatus(审核订单操作) (这里描述这个方法适用条件 – 可选)
     * 
     * @param orderLog
     * @return 赵生辉 int
     * @exception @since 1.0.0
     */
    int updateStatus(OrderLog orderLog);
    
    /**
     * 
     * findOrder(查询订单的详细信息) (用于查询订单的详细信息 – 可选)
     * 
     * @param order
     * @return 赵生辉 Order
     * @exception @since 1.0.0
     */
    OrderInfoDto findOrder(Orders order);
    
    /**
     * 
     * findAllOrderItem(查询订单的列表) (查询订单的列表 – 可选)
     * 
     * @param order
     * @return 赵生辉 List<Order>
     * @exception @since 1.0.0
     */
    List<Orders> findAllOrder(Orders order);
    
    /**
     * 
     * findAllOrderItemPage(分页查询订单的列表) (分页查询订单的列表 – 可选)
     * 
     * @param order
     * @return 赵生辉 PageInfo<Order>
     * @exception @since 1.0.0
     */
    PageInfo<Orders> findAllOrderPage(int pageNo, int pageSize, Orders order);
}
