package com.kingthy.platform.controller.order;

import com.github.pagehelper.PageInfo;
import com.kingthy.platform.dto.order.OrderItemReq;
import com.kingthy.platform.entity.order.OrderItem;
import com.kingthy.platform.response.WebApiResponse;
import com.kingthy.platform.service.order.OrderItemService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 
 *
 * OrderItemController(订单明细模块控制层)
 * 
 * 赵生辉 2017年4月12日 下午8:17:37
 * 
 * @version 1.0.0
 *
 */
@RestController
@RequestMapping("orderItem")
public class OrderItemController
{
    @Autowired
    private OrderItemService orderItemService;
    
    @Autowired
    private final static Logger LOG = LoggerFactory.getLogger(OrderItemController.class);
    
    @ApiOperation(value = "分页查询订单明细", notes = "分页查询订单明细")
    @RequestMapping(value = "/{pageNo}/{pageSize}/{orderUuid}", method = RequestMethod.GET)
    public WebApiResponse<PageInfo<OrderItem>> findOrderItem(@PathVariable String pageNo, @PathVariable String pageSize,
        @PathVariable String orderUuid)
    {
        WebApiResponse<PageInfo<OrderItem>> webApiResponse = new WebApiResponse<PageInfo<OrderItem>>();
        OrderItem orderItem = new OrderItem();
        orderItem.setOrderUuid(orderUuid);
        PageInfo<OrderItem> pageInfo;
        try
        {
            pageInfo =
                orderItemService.findOrderItemPage(Integer.parseInt(pageNo), Integer.parseInt(pageSize), orderItem);
        }
        catch (NumberFormatException e)
        {
            LOG.error("/orderItem/{pageNo}/{pageSize}/{orderUuid}出错，异常信息" + e);
            return WebApiResponse.error(WebApiResponse.ResponseMsg.EXCEPTION.getValue());
        }
        return webApiResponse.success(pageInfo);
    }

    @ApiOperation(value = "订单列表", notes = "订单管理")
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public WebApiResponse<?> list(@RequestBody @ApiParam(name = "OrderItemReq", value = "订单列表", required = true) OrderItemReq req){

        WebApiResponse<?> result = null;

        try {

            result = orderItemService.list(req);

        }catch (Exception e){
            LOG.error("/orderItem/list 订单列表出错,异常信息" + e);
            return WebApiResponse.error(WebApiResponse.ResponseMsg.EXCEPTION.getValue());
        }

        return result;
    }

    @ApiOperation(value = "订单详情", notes = "订单管理")
    @RequestMapping(value = "/show/{orderSn}", method = RequestMethod.GET)
    public WebApiResponse<?> showOrderInfo(@PathVariable @ApiParam(name = "orderSn", value = "订单详情", required = true) String orderSn){

        WebApiResponse<?> result = null;

        try {

            result = orderItemService.showOrderInfo(orderSn);

        }catch (Exception e){
            LOG.error("/orderItem/show/{orderSn} 订单详情出错,异常信息" + e);
            return WebApiResponse.error(WebApiResponse.ResponseMsg.EXCEPTION.getValue());
        }

        return result;
    }

    @ApiOperation(value = "取消订单", notes = "订单管理")
    @RequestMapping(value = "/cancel/{orderSn}/{memberUuid}", method = RequestMethod.PUT)
    public WebApiResponse<?> cancelOrder(@PathVariable @ApiParam(name = "orderSn", value = "订单号", required = true) String orderSn,
                            @PathVariable @ApiParam(name = "memberUuid", value = "用户", required = true) String memberUuid)
    {
        WebApiResponse<?> result = null;

        try{

            result = orderItemService.showOrderInfo(orderSn);

        }catch (Exception e){

        }

        return result;
    }
    
}
