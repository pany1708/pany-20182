package com.kingthy.platform.controller.order;

import com.kingthy.platform.dto.order.InvoiceInfoReq;
import com.kingthy.platform.response.WebApiResponse;
import com.kingthy.platform.service.order.InvoiceInfoService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @AUTHORS xumin
 * @Description:
 * @DATE Created by 15:30 on 2017/7/11.
 * @Modified by:
 */

@RestController
@RequestMapping("invoice")
public class InvoiceInfoController {

    private static final Logger LOG = LoggerFactory.getLogger(InvoiceInfoController.class);

    @Autowired
    private InvoiceInfoService invoiceInfoService;

    @ApiOperation(value = "发票列表", notes = "发票管理")
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public WebApiResponse<?> invoiceList(@RequestBody @ApiParam(name = "InvoiceInfoReq", value = "查询条件", required = true) InvoiceInfoReq req){

        WebApiResponse<?> result = null;

        try{

            result = invoiceInfoService.invoiceList(req);

        }catch (Exception e){
            LOG.error("/invoice/list 发票列表出错,异常信息" + e);
            return WebApiResponse.error(WebApiResponse.ResponseMsg.EXCEPTION.getValue());
        }

        return result;
    }

    @ApiOperation(value = "发票详情", notes = "发票管理")
    @RequestMapping(value = "/show/{orderSn}", method = RequestMethod.GET)
    public WebApiResponse<?> showInvoiceInfo(@PathVariable @ApiParam(name = "orderSn", value = "查询条件", required = true) String orderSn){

        WebApiResponse<?> result = null;

        try {

            result = invoiceInfoService.showInvoiceInfo(orderSn);

        }catch (Exception e){
            LOG.error("/invoice/show/{orderSn} 发票列表出错,异常信息" + e);
            return WebApiResponse.error(WebApiResponse.ResponseMsg.EXCEPTION.getValue());
        }

        return result;
    }
}
