package com.kingthy.platform.controller.material;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.kingthy.platform.dto.material.AddUpdateAccessoriesReq;
import com.kingthy.platform.dto.material.FindPage;
import com.kingthy.platform.dto.material.UpdateAccessoriesReq;
import com.kingthy.platform.entity.material.Accessories;
import com.kingthy.platform.response.WebApiResponse;
import com.kingthy.platform.service.material.AccessoriesService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "accessories")
public class AccessoriesController
{
    @Autowired
    private AccessoriesService accessoriesService;
    
    private static final Logger LOG = LoggerFactory.getLogger(AccessoriesController.class);
    
    @ApiOperation(value = "创建一个辅料", notes = "创建一个辅料")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public WebApiResponse<?> createAccessories(
        @RequestBody @Validated AddUpdateAccessoriesReq addUpdateAccessoriesReq, BindingResult bindingResult)
    {
        if (bindingResult.hasErrors())
        {
            return WebApiResponse.error(bindingResult.getFieldError().getDefaultMessage());
        }
        Accessories accessories = JSONObject.parseObject(JSON.toJSONString(addUpdateAccessoriesReq),Accessories.class);
        int result;
        try
        {
            result = accessoriesService.create(accessories);
        }
        catch (Exception e)
        {
            LOG.error("创建辅料出错，异常信息" + e);
            return WebApiResponse.error(WebApiResponse.ResponseMsg.EXCEPTION.getValue());
        }
        if (result < 1)
        {
            return WebApiResponse.error(WebApiResponse.ResponseMsg.FAIL.getValue());
        }
        return WebApiResponse.success(result, WebApiResponse.ResponseMsg.SUCCESS.getValue());
    }
    
    @ApiOperation(value = "查询辅料详情", notes = "查询辅料详情")
    @RequestMapping(value = "/{accessoriesUuid}", method = RequestMethod.GET)
    public WebApiResponse<?> findAccessories(@PathVariable String accessoriesUuid)
    {
        Accessories accessories;
        try
        {
            accessories = accessoriesService.findAccessories(accessoriesUuid);
        }
        catch (Exception e)
        {
            LOG.error("查询辅料详情出错，异常信息" + e);
            return WebApiResponse.error(e.getMessage());
        }
        if (accessories == null)
        {
            return WebApiResponse.error(WebApiResponse.ResponseMsg.FAIL.getValue());
        }
        return WebApiResponse.success(accessories);
    }
    
    @ApiOperation(value = "查询辅料列表详情", notes = "查询辅料详情")
    @RequestMapping(value = "/page", method = RequestMethod.POST)
    public WebApiResponse<?> findAccessoriesPage(@RequestBody FindPage findPage)
    {
        Accessories accessories = JSONObject.parseObject(JSON.toJSONString(findPage),Accessories.class);
        PageInfo<Accessories> accessoriesPage;
        try
        {
            accessoriesPage = accessoriesService.findAccessoriesPage(findPage.getPageNum(),findPage.getPageSize(),accessories);
        }
        catch (Exception e)
        {
            LOG.error("查询辅料列表详情出错，异常信息" + e);
            return WebApiResponse.error(e.getMessage());
        }
        if (accessoriesPage == null)
        {
            return WebApiResponse.error(WebApiResponse.ResponseMsg.FAIL.getValue());
        }
        return WebApiResponse.success(accessoriesPage);
    }
    
    @ApiOperation(value = "删除辅料", notes = "根据UUID删除辅料")
    @RequestMapping(value = "/deleteAccessories/{accessoriesUuid}", method = RequestMethod.DELETE)
    public WebApiResponse<?> deleteAccessories(@PathVariable String accessoriesUuid)
    {
        int result;
        try
        {
            result = accessoriesService.deleteAccessories(accessoriesUuid);
        }
        catch (Exception e)
        {
            LOG.error("删除辅料出错，异常信息" + e);
            return WebApiResponse.error(WebApiResponse.ResponseMsg.EXCEPTION.getValue());
        }
        if (result > 0)
        {
            return WebApiResponse.success(result, WebApiResponse.ResponseMsg.SUCCESS.getValue());
        }
        else
        {
            return WebApiResponse.error(WebApiResponse.ResponseMsg.FAIL.getValue());
        }
    }
    
    @ApiOperation(value = "更新辅料", notes = "根据UUID更新辅料")
    @PutMapping("/updateAccessories")
    public WebApiResponse<?> updateMaterial(
        @RequestBody @ApiParam(name = "addUpdateAccessories") @Validated UpdateAccessoriesReq updateAccessoriesReq,
        BindingResult bindingResult)
    {
        if (bindingResult.hasErrors())
        {
            return WebApiResponse.error(bindingResult.getFieldError().getDefaultMessage());
        }
        Accessories accessories = JSONObject.parseObject(JSON.toJSONString(updateAccessoriesReq),Accessories.class);
        int result ;
        try
        {
            result = accessoriesService.updateAccessories(accessories);
        }
        catch (Exception e)
        {
            LOG.error("更新辅料出错，异常信息" + e);
            return WebApiResponse.error(e.getMessage());
        }
        if (result > 0)
        {
            return WebApiResponse.success(result, WebApiResponse.ResponseMsg.SUCCESS.getValue());
        }
        else
        {
            return WebApiResponse.error(WebApiResponse.ResponseMsg.FAIL.getValue());
        }
    }
}