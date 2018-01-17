package com.kingthy.platform.controller.order;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.kingthy.exception.OrderStatus;
import com.kingthy.platform.dto.order.AuditingOrderStatusReq;
import com.kingthy.platform.dto.order.UpdateOrdersReq;
import com.kingthy.platform.entity.order.OrderLog;
import com.kingthy.platform.entity.order.Orders;
import com.kingthy.platform.entity.order.dto.CreateOrderParam;
import com.kingthy.platform.entity.order.dto.FindOrderPageReq;
import com.kingthy.platform.entity.order.dto.OrderInfoDto;
import com.kingthy.platform.response.WebApiResponse;
import com.kingthy.platform.service.order.OrdersService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 
 *
 * OrdersController(订单模块控制层)
 * 
 * 赵生辉 2017年4月12日 下午8:17:23
 * 
 * @version 1.0.0
 *
 */
@RestController
@RequestMapping("orders")
public class OrdersController
{
    @Autowired
    private OrdersService ordersService;

    private static final Logger LOG = LoggerFactory.getLogger(OrdersController.class);
    
    @ApiOperation(value = "创建订单", notes = "创建生成一个新的订单")
    @RequestMapping(value = "", method = RequestMethod.POST)
    public WebApiResponse<Integer> createOrder(
        @RequestBody @ApiParam(name = "createOrderParam", value = "订单详细信息", required = true) @Validated CreateOrderParam createOrderParam,
        BindingResult results)
    {
        WebApiResponse<Integer> webApiResponse = new WebApiResponse<>();
        if (results.hasErrors())
        {
            return webApiResponse.error(results.getFieldError().getDefaultMessage());
        }
        if (createOrderParam.getOrderItemList().isEmpty() || createOrderParam.getOrderItemList().size() == 0)
        {
            return webApiResponse.error("订单明细不能为空");
        }
        int result;
        try
        {
            result = ordersService.create(createOrderParam.getOrder(), createOrderParam.getOrderItemList());
        }
        catch (Exception e)
        {
            LOG.error("/orders创建订单出错，异常信息" + e);
            return webApiResponse.error(WebApiResponse.ResponseMsg.EXCEPTION.getValue());
        }
        if (result < 1)
        {
            return webApiResponse.error("创建订单失败");
        }
        return webApiResponse.success(result);
    }
    
    @ApiOperation(value = "查询订单详情", notes = "查询订单的详细信息包括操作记录和订单明细")
    @RequestMapping(value = "/{uuid}", method = RequestMethod.GET)
    public WebApiResponse<OrderInfoDto> findOrder(@PathVariable String uuid)
    {
        WebApiResponse<Integer> webApiResponse = new WebApiResponse<>();
        OrderInfoDto orderInfoDto;
        try
        {
            Orders order = new Orders();
            order.setUuid(uuid);
            orderInfoDto = ordersService.findOrder(order);
        }
        catch (Exception e)
        {
            LOG.error("/orders/{uuid}查询订单详情出错，异常信息" + e);
            return webApiResponse.error(WebApiResponse.ResponseMsg.EXCEPTION.getValue());
        }
        if (orderInfoDto == null)
        {
            return webApiResponse.error("查询订单详情失败");
        }
        return webApiResponse.success(orderInfoDto);
    }
    
    @ApiOperation(value = "分页查询列表详情", notes = "查询订单的详细信息包括操作记录和订单明细")
    @RequestMapping(value = "/page", method = RequestMethod.POST)
    public WebApiResponse<PageInfo<Orders>> findOrderPage(@RequestBody @Validated FindOrderPageReq findOrderPageReq,
        BindingResult results)
    {
        WebApiResponse<PageInfo<Orders>> webApiResponse = new WebApiResponse<>();
        if (results.hasErrors())
        {
            return webApiResponse.error(results.getFieldError().getDefaultMessage());
        }
        PageInfo<Orders> pageInfo;
        try
        {
            Orders order;
            order = JSON.parseObject(JSON.toJSONString(findOrderPageReq), Orders.class);
            pageInfo =
                ordersService.findAllOrderPage(findOrderPageReq.getPageNum(), findOrderPageReq.getPageSize(), order);
        }
        catch (Exception e)
        {
            LOG.error("/orders/page分页查询订单详情出错，异常信息" + e);
            return webApiResponse.error(WebApiResponse.ResponseMsg.EXCEPTION.getValue());
        }
        if (pageInfo == null)
        {
            return webApiResponse.error("查询订单信息失败");
        }
        return webApiResponse.success(pageInfo);
    }
    
    @ApiOperation(value = "审核订单", notes = "审核用户对退单等操作")
    @RequestMapping(value = "/audition", method = RequestMethod.PUT)
    public WebApiResponse<Integer> AuditingOrder(@RequestBody @Validated AuditingOrderStatusReq auditingOrderStatusReq,
        BindingResult results)
    {
        WebApiResponse<Integer> webApiResponse = new WebApiResponse<>();
        if (results.hasErrors())
        {
            return webApiResponse.error(results.getFieldError().getDefaultMessage());
        }
        OrderLog orderLog;
        orderLog = JSON.parseObject(JSON.toJSONBytes(auditingOrderStatusReq), OrderLog.class);
        int result;
        try
        {
            result = ordersService.updateStatus(orderLog);
        }
        catch (Exception e)
        {
            LOG.error("/oders/audition:" + e);
            return WebApiResponse.error(WebApiResponse.ResponseMsg.EXCEPTION.getValue());
        }
        if (result < 1)
        {
            return webApiResponse.error("审核订单失败");
        }
        return webApiResponse.success(result);
    }
    
    @ApiOperation(value = "修改订单信息", notes = "修改订单的部分信息")
    @RequestMapping(value = "/", method = RequestMethod.PUT)
    public WebApiResponse<Integer> updateOrders(@RequestBody @Validated UpdateOrdersReq updateOrdersReq,
        BindingResult results)
    {
        WebApiResponse<Integer> webApiResponse = new WebApiResponse<Integer>();
        if (results.hasErrors())
        {
            return webApiResponse.error(results.getFieldError().getDefaultMessage());
        }
        if (updateOrdersReq.getStatus() != OrderStatus.ORDER_CREATE.getCode()
            && updateOrdersReq.getStatus() != OrderStatus.ORDER_PRODUCTION.getCode())
        {
            return webApiResponse.error("订单不能修改");
        }
        
        if (updateOrdersReq.getIsInvoice() == true)
        {
            if (updateOrdersReq.getInvoiceTitle() == null)
            {
                return webApiResponse.error("发票抬头不能为空");
            }
            if (updateOrdersReq.getMemo() == null)
            {
                return webApiResponse.error("客户备注不能为空");
            }
        }
        int result;
        try
        {
            Orders orders;
            orders = JSON.parseObject(JSON.toJSONBytes(updateOrdersReq), Orders.class);
            result = ordersService.update(orders);
        }
        catch (Exception e)
        {
            LOG.error("/oders/，异常：" + e);
            return WebApiResponse.error(WebApiResponse.ResponseMsg.EXCEPTION.getValue());
        }
        if (result < 1)
        {
            return webApiResponse.error("修改订单失败");
        }
        return webApiResponse.success(result);
    }
    
}
