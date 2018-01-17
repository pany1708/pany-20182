package com.kingthy.platform.controller.member;

import com.github.pagehelper.PageInfo;
import com.kingthy.platform.dto.member.IntegralReq;
import com.kingthy.platform.entity.member.Integral;
import com.kingthy.platform.response.WebApiResponse;
import com.kingthy.platform.service.member.IntegralService;
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
 * IntegralController(积分设置控制层)
 * 
 * 陈钊 2017年4月18日 上午9:54:11
 * 
 * @version 1.0.0
 *
 */
@RestController
@RequestMapping("/integral")
public class IntegralController
{
    private static final Logger LOG = LoggerFactory.getLogger(IntegralController.class);
    
    @Autowired
    private IntegralService integralService;
    
    @ApiOperation(value = "分页查询积分设置信息")
    @RequestMapping(value = "/integralList", method = RequestMethod.POST)
    public WebApiResponse<?> findByPage(
        @RequestBody @ApiParam(name = "integralReq", value = "积分分页查询") IntegralReq integralReq)
    {
        PageInfo<Integral> page;
        try
        {
            page = integralService.findByPage(integralReq.getPageNum(), integralReq.getPageSize());
        }
        catch (Exception e)
        {
            LOG.error("/integral/integralList:" + e);
            return WebApiResponse.error(WebApiResponse.ResponseMsg.EXCEPTION.getValue());
        }
        if (page != null && page.getList() != null)
        {
            return WebApiResponse.success(page);
        }
        return WebApiResponse.error(WebApiResponse.ResponseMsg.FAIL.getValue());
    }
    
    @ApiOperation(value = "新增积分设置信息")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public WebApiResponse<String> addIntegral(
        @RequestBody @ApiParam(name = "integralReq", value = "新增积分设置", required = true) @Validated IntegralReq integralReq,
        BindingResult bindingResult)
    {
        if (bindingResult.hasErrors())
        {
            return WebApiResponse.error(bindingResult.getFieldError().getDefaultMessage());
        }
        int result;
        try
        {
            result = integralService.addIntegral(integralReq);
        }
        catch (Exception e)
        {
            LOG.error("/integral/add:" + e);
            return WebApiResponse.error(WebApiResponse.ResponseMsg.EXCEPTION.getValue());
        }
        if (result > 0)
        {
            return WebApiResponse.success(null, WebApiResponse.ResponseMsg.SUCCESS.getValue());
        }
        return WebApiResponse.error(WebApiResponse.ResponseMsg.FAIL.getValue());
        
    }
    
    @ApiOperation(value = "查询积分设置信息")
    @RequestMapping(value = "/findOne/{uuid}", method = RequestMethod.PUT)
    public WebApiResponse<Integral> findOne(@PathVariable String uuid)
    {
        Integral integral;
        try
        {
            integral = integralService.findByUuid(uuid);
        }
        catch (Exception e)
        {
            LOG.error("/integral/findOne/{uuid}:" + e);
            return WebApiResponse.error(WebApiResponse.ResponseMsg.EXCEPTION.getValue());
        }
        if (integral != null)
        {
            return WebApiResponse.success(integral, WebApiResponse.ResponseMsg.SUCCESS.getValue());
        }
        return WebApiResponse.error(WebApiResponse.ResponseMsg.FAIL.getValue());
    }
    
    @ApiOperation(value = "修改积分设置信息")
    @RequestMapping(value = "/edit", method = RequestMethod.PUT)
    public WebApiResponse<String> editIntegral(
        @RequestBody @ApiParam(name = "integralReq", value = "修改积分设置", required = true) @Validated IntegralReq integralReq,
        BindingResult bindingResult)
    {
        if (bindingResult.hasErrors())
        {
            return WebApiResponse.error(bindingResult.getFieldError().getDefaultMessage());
        }
        int result;
        try
        {
            result = integralService.editIntegral(integralReq);
        }
        catch (Exception e)
        {
            LOG.error("/integral/edit:" + e);
            return WebApiResponse.error(WebApiResponse.ResponseMsg.EXCEPTION.getValue());
        }
        if (result > 0)
        {
            return WebApiResponse.success(null, WebApiResponse.ResponseMsg.SUCCESS.getValue());
        }
        return WebApiResponse.error(WebApiResponse.ResponseMsg.FAIL.getValue());
        
    }
    
    @ApiOperation(value = "删除积分设置信息")
    @RequestMapping(value = "/delete/{uuid}", method = RequestMethod.PUT)
    public WebApiResponse<String> deleteIntegral(@PathVariable String uuid)
    {
        int result;
        try
        {
            result = integralService.deleteIntegral(uuid);
        }
        catch (Exception e)
        {
            LOG.error("/integral/delete/{uuid}:" + e);
            return WebApiResponse.error(WebApiResponse.ResponseMsg.EXCEPTION.getValue());
        }
        if (result > 0)
        {
            return WebApiResponse.success(null, WebApiResponse.ResponseMsg.SUCCESS.getValue());
        }
        return WebApiResponse.error(WebApiResponse.ResponseMsg.FAIL.getValue());
    }
    
}
