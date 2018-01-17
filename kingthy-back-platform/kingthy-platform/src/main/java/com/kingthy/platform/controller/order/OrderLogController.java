package com.kingthy.platform.controller.order;

import com.github.pagehelper.PageInfo;
import com.kingthy.platform.entity.order.OrderLog;
import com.kingthy.platform.response.WebApiResponse;
import com.kingthy.platform.service.order.OrderLogService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 
 *
 * OrderLogController(订单记录控制层)
 * 
 * 赵生辉 2017年4月12日 下午8:17:31
 * 
 * @version 1.0.0
 *
 */
@RestController
@RequestMapping("orderLog")
public class OrderLogController
{
    @Autowired
    private OrderLogService orderLogService;

    @Autowired
    private final static Logger LOGGER = LoggerFactory.getLogger(OrderLogController.class);
    
    @ApiOperation(value = "分页查询订单记录", notes = "分页查询订单记录")
    @RequestMapping(value = "/{pageNo}/{pageSize}/{orderUuid}", method = RequestMethod.GET)
    public WebApiResponse<PageInfo<OrderLog>> findOrderItem(@PathVariable String pageNo, @PathVariable String pageSize,
        @PathVariable String orderUuid)
    {
        WebApiResponse<PageInfo<OrderLog>> webApiResponse = new WebApiResponse<PageInfo<OrderLog>>();
        PageInfo<OrderLog> pageInfo ;
        try
        {
            OrderLog orderLog = new OrderLog();
            orderLog.setOrderUuid(orderUuid);
            pageInfo = orderLogService.findOrderLogPage(Integer.parseInt(pageNo), Integer.parseInt(pageSize), orderLog);
        }
        catch (NumberFormatException e)
        {
            LOGGER.error("/orderLog/{pageNo}/{pageSize}/{orderUuid}出错，异常信息"+e);
            return webApiResponse.error(WebApiResponse.ResponseMsg.EXCEPTION.getValue());
        }
        return webApiResponse.success(pageInfo);
    }
}
