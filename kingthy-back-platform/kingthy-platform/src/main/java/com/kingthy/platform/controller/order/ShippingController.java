package com.kingthy.platform.controller.order;

import com.kingthy.platform.dto.order.ShippingReq;
import com.kingthy.platform.response.WebApiResponse;
import com.kingthy.platform.service.order.ShippingService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @AUTHORS xumin
 * @Description: 发货管理
 * @DATE Created by 13:46 on 2017/7/12.
 * @Modified by:
 */
@RestController
@RequestMapping("shipping")
public class ShippingController {

    private static final Logger LOG = LoggerFactory.getLogger(ShippingController.class);

    @Autowired
    private ShippingService shippingService;

    @ApiOperation(value = "发货列表", notes = "发货管理")
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public WebApiResponse<?> shippingList(@RequestBody @ApiParam(name = "AfterSaleServiceReq", value = "查询条件", required = true) ShippingReq req){

        WebApiResponse<?> result = null;

        try {

            result = shippingService.shippingList(req);

        }catch (Exception e){
            LOG.error("/shipping/list 发货列表出错,异常信息" + e);
            return WebApiResponse.error(WebApiResponse.ResponseMsg.EXCEPTION.getValue());
        }

        return result;
    }

    @ApiOperation(value = "发货", notes = "发货管理")
    @RequestMapping(value = "/delivery", method = RequestMethod.PUT)
    public WebApiResponse<?> delivery(@RequestBody @ApiParam(name = "ShippingReq.DeliveryReq") ShippingReq.DeliveryReq req){

        WebApiResponse<?> result = null;

        try{

            result = shippingService.delivery(req);

        }catch (Exception e){
            LOG.error("/shipping/delivery 发货出错,异常信息" + e);
            return WebApiResponse.error(WebApiResponse.ResponseMsg.EXCEPTION.getValue());
        }

        return result;
    }

    @ApiOperation(value = "修改收货地址", notes = "发货管理")
    @RequestMapping(value = "/edit/address", method = RequestMethod.PUT)
    public WebApiResponse<?> editAddress(@RequestBody @ApiParam(name = "ShippingReq.DeliveryReq") ShippingReq.EditAddress req){

        WebApiResponse<?> result = null;

        try {

            result = shippingService.editAddress(req);

        }catch (Exception e){
            LOG.error("/shipping/edit/address 发货出错,异常信息" + e);
            return WebApiResponse.error(WebApiResponse.ResponseMsg.EXCEPTION.getValue());
        }

        return result;
    }

    /**
     * 发货详情
     * @param orderSn
     * @return
     */
    @ApiOperation(value = "发货详情", notes = "发货管理")
    @RequestMapping(value = "/show/{orderSn}", method = RequestMethod.GET)
    public WebApiResponse<?> showOrderShipping(@PathVariable @ApiParam(name = "orderSn", value = "查询条件", required = true) String orderSn){

        WebApiResponse<?> result = null;

        try {

            result = shippingService.showOrderShipping(orderSn);

        }catch (Exception e){
            LOG.error("/shipping/show/{orderSn} 发货详情出错,异常信息" + e);
            return WebApiResponse.error(WebApiResponse.ResponseMsg.EXCEPTION.getValue());
        }

        return result;
    }
}
