package com.kingthy.platform.service.order;

import com.github.pagehelper.PageInfo;
import com.kingthy.platform.entity.order.OrderLog;

import java.util.List;

/**
 * 
 *
 * OrderLogService(订单记录接口)
 * 
 * 赵生辉 2017年4月14日 上午9:50:22
 * 
 * @version 1.0.0
 *
 */
public interface OrderLogService
{
    /**
     * 
     * create(创建一条订单日志) (创建退订单，驳回等操作的日志记录)
     * 
     * @param orderLog
     * @return 赵生辉 int
     * @exception @since 1.0.0
     */
    int create(OrderLog orderLog);
    
    /**
     * 
     * update(修改订单日志) (修改退订单，驳回等操作的日志记录)
     * 
     * @param orderLog
     * @return 赵生辉 int
     * @exception @since 1.0.0
     */
    int update(OrderLog orderLog);
    
    /**
     * 
     * findAllOrderItem(查询订单的所有的操作记录) (查询订单的所有的操作记录)
     * 
     * @param orderLog
     * @return 赵生辉 List<OrderLog>
     * @exception @since 1.0.0
     */
    List<OrderLog> findAllOrderLog(OrderLog orderLog);
    
    /**
     * 
     * findOrderLogPage(分页查询订单记录) (分页查询订单记录)
     * 
     * @param pageNo
     * @param pageSize
     * @param orderLog
     * @return 赵生辉 PageInfo<OrderLog>
     * @exception @since 1.0.0
     */
    PageInfo<OrderLog> findOrderLogPage(Integer pageNo, Integer pageSize, OrderLog orderLog);
}
