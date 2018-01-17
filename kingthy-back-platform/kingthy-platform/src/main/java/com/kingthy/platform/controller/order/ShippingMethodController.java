package com.kingthy.platform.controller.order;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.kingthy.platform.dto.order.CreateShippingMethodReq;
import com.kingthy.platform.dto.order.QueryShippingMethodPage;
import com.kingthy.platform.dto.order.UpdateShippingMethodReq;
import com.kingthy.platform.entity.order.DeliveryCorp;
import com.kingthy.platform.entity.order.ShippingMethod;
import com.kingthy.platform.response.WebApiResponse;
import com.kingthy.platform.service.order.DeliveryCorpService;
import com.kingthy.platform.service.order.ShippingMethodService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 
 *
 * ShippingMethodController(配送方式模块控制层)
 * 
 * 赵生辉 2017年4月12日 下午8:17:14
 * 
 * @version 1.0.0
 *
 */
@RestController
@RequestMapping("shippingMethod")
public class ShippingMethodController
{
    @Autowired
    private ShippingMethodService shippingMethodService;

    @Autowired
    private DeliveryCorpService deliveryCorpService;
    
    private static final Logger LOG = LoggerFactory.getLogger(ShippingMethodController.class);
    
    @ApiOperation(value = "查询所有的物流", notes = "查询所有的物流")
    @GetMapping(value = "/queryShippingMethodAll")
    public WebApiResponse<?> queryAll()
    {
        WebApiResponse<List<ShippingMethod>> webApiResponse = new WebApiResponse<>();
        List<ShippingMethod> returnResult;
        try
        {
            returnResult = shippingMethodService.findAllShippingMethod();
        }
        catch (Exception e)
        {
            LOG.error("/shippingMethod/queryShippingMethodAll，异常信息:" + e);
            return WebApiResponse.error(WebApiResponse.ResponseMsg.EXCEPTION.getValue());
        }
        return webApiResponse.success(returnResult);
    }

    @ApiOperation(value = "查询所有的快递公司", notes = "查询所有的快递公司")
    @GetMapping(value = "/queryDeliveryCorpAll")
    public WebApiResponse<?> queryDeliveryCorpAll()
    {
        WebApiResponse<List<ShippingMethod>> webApiResponse = new WebApiResponse<>();
        List<DeliveryCorp> returnResult;
        try
        {
            returnResult = deliveryCorpService.queryAll();
        }
        catch (Exception e)
        {
            LOG.error("/shippingMethod/queryDeliveryCorpAll，异常信息:" + e);
            return WebApiResponse.error(WebApiResponse.ResponseMsg.EXCEPTION.getValue());
        }
        return webApiResponse.success(returnResult);
    }

    @ApiOperation(value = "分页查询物流", notes = "分页查询查询所有的物流")
    @PostMapping(value = "/querShippingMethodPage")
    public WebApiResponse<?> queryShippingMethodPage(@RequestBody QueryShippingMethodPage queryShippingMethodPage)
    {
        ShippingMethod shippingMethod = JSONObject.parseObject(JSON.toJSONString(queryShippingMethodPage),ShippingMethod.class);
        WebApiResponse<List<ShippingMethod>> webApiResponse = new WebApiResponse<>();
        PageInfo<ShippingMethod> returnResult;
        try
        {
            returnResult = shippingMethodService.findAllShippingMethodPage(queryShippingMethodPage.getPageNum(),
                queryShippingMethodPage.getPageSize(),shippingMethod);
        }
        catch (Exception e)
        {
            LOG.error("/shippingMethod/querShippingMethodPage，异常信息:" + e);
            return WebApiResponse.error(WebApiResponse.ResponseMsg.EXCEPTION.getValue());
        }
        return webApiResponse.success(returnResult);
    }

    @ApiOperation(value = "添加物流", notes = "添加物流")
    @PostMapping(value = "/create")
    public WebApiResponse<?> create(@RequestBody CreateShippingMethodReq createShippingMethodReq)
    {
        ShippingMethod shippingMethod = JSONObject.parseObject(JSON.toJSONString(createShippingMethodReq),ShippingMethod.class);
        WebApiResponse<List<ShippingMethod>> webApiResponse = new WebApiResponse<>();
        int returnResult;
        try
        {
            returnResult = shippingMethodService.crate(shippingMethod);
        }
        catch (Exception e)
        {
            LOG.error("/shippingMethod/create，异常信息:" + e);
            return WebApiResponse.error(WebApiResponse.ResponseMsg.EXCEPTION.getValue());
        }
        return webApiResponse.success(returnResult);
    }

    @ApiOperation(value = "修改物流", notes = "修改物流")
    @PutMapping(value = "/update")
    public WebApiResponse<?> update(@RequestBody UpdateShippingMethodReq updateShippingMethodReq)
    {
        ShippingMethod shippingMethod = JSONObject.parseObject(JSON.toJSONString(updateShippingMethodReq),ShippingMethod.class);
        WebApiResponse<List<ShippingMethod>> webApiResponse = new WebApiResponse<>();
        int returnResult;
        try
        {
            returnResult = shippingMethodService.update(shippingMethod);
        }
        catch (Exception e)
        {
            LOG.error("/shippingMethod/create，异常信息:" + e);
            return WebApiResponse.error(WebApiResponse.ResponseMsg.EXCEPTION.getValue());
        }
        return webApiResponse.success(returnResult);
    }


}
