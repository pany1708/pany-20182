package com.kingthy.platform.controller.manager;

import com.github.pagehelper.PageInfo;
import com.kingthy.platform.dto.manager.PlatformMenuPageReq;
import com.kingthy.platform.dto.manager.PlatformMenuReq;
import com.kingthy.platform.entity.manager.PlatformMenu;
import com.kingthy.platform.response.WebApiResponse;
import com.kingthy.platform.service.manager.PlatformMenuService;
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
 * PlatformMenuController(菜单控制层)
 * 
 * 陈钊 2017年5月17日 上午11:35:24
 * 
 * @version 1.0.0
 *
 */
@RestController
@RequestMapping("platformMenu")
public class PlatformMenuController
{
    private static final Logger LOG = LoggerFactory.getLogger(PlatformMenuController.class);
    
    @Autowired
    private PlatformMenuService platformMenuService;
    
    @ApiOperation(value = "分页查询菜单")
    @RequestMapping(value = "/findByPage", method = RequestMethod.POST)
    public WebApiResponse<?> findByPage(
        @RequestBody @ApiParam(name = "platformMenuPageReq", value = "查询条件", required = true) PlatformMenuPageReq platformMenuPageReq)
    {
        PageInfo<PlatformMenu> page = platformMenuService.findByPage(platformMenuPageReq);
        return WebApiResponse.success(page, WebApiResponse.ResponseMsg.SUCCESS.getValue());
    }
    
    @ApiOperation(value = "新增菜单")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public WebApiResponse<?> add(
        @RequestBody @ApiParam(name = "platformMenuReq", value = "菜单信息", required = true) @Validated PlatformMenuReq platformMenuReq,
        BindingResult bindingResult)
    {
        if (bindingResult.hasErrors())
        {
            return WebApiResponse.error(bindingResult.getFieldError().getDefaultMessage());
        }
        int result;
        try
        {
            result = platformMenuService.add(platformMenuReq);
        }
        catch (Exception e)
        {
            LOG.error("/platformMenu/add:" + e);
            return WebApiResponse.error(WebApiResponse.ResponseMsg.EXCEPTION.getValue());
        }
        if (result > 0)
        {
            return WebApiResponse.success(null, WebApiResponse.ResponseMsg.SUCCESS.getValue());
        }
        return WebApiResponse.error(WebApiResponse.ResponseMsg.FAIL.getValue());
    }
    
    @ApiOperation(value = "查询菜单信息")
    @RequestMapping(value = "/findByUuid/{uuid}", method = RequestMethod.GET)
    public WebApiResponse<?> findByUuid(
        @PathVariable @ApiParam(name = "uuid", value = "菜单uuid", required = true) String uuid)
    {
        PlatformMenu platformMenu;
        try
        {
            platformMenu = platformMenuService.findByUuid(uuid);
        }
        catch (Exception e)
        {
            LOG.error("/platformMenu/findByUuid/{uuid}:" + e);
            return WebApiResponse.error(WebApiResponse.ResponseMsg.EXCEPTION.getValue());
        }
        if (platformMenu != null)
        {
            return WebApiResponse.success(platformMenu);
        }
        return WebApiResponse.error(WebApiResponse.ResponseMsg.FAIL.getValue());
    }
    
    @ApiOperation(value = "修改菜单")
    @RequestMapping(value = "/edit", method = RequestMethod.PUT)
    public WebApiResponse<?> edit(
        @RequestBody @ApiParam(name = "platformMenuReq", value = "菜单信息", required = true) @Validated PlatformMenuReq platformMenuReq,
        BindingResult bindingResult)
    {
        if (bindingResult.hasErrors())
        {
            return WebApiResponse.error(bindingResult.getFieldError().getDefaultMessage());
        }
        int result;
        try
        {
            result = platformMenuService.edit(platformMenuReq);
        }
        catch (Exception e)
        {
            LOG.error("/platformMenu/findByUuid/{uuid}:" + e);
            return WebApiResponse.error(WebApiResponse.ResponseMsg.EXCEPTION.getValue());
        }
        if (result > 0)
        {
            return WebApiResponse.success(null, WebApiResponse.ResponseMsg.SUCCESS.getValue());
        }
        return WebApiResponse.error(WebApiResponse.ResponseMsg.FAIL.getValue());
    }
    
    @ApiOperation(value = "删除菜单")
    @RequestMapping(value = "/delete/{uuid}", method = RequestMethod.PUT)
    public WebApiResponse<?> delete(@PathVariable @ApiParam(name = "uuid", value = "uuid", required = true) String uuid)
    {
        int result;
        try
        {
            result = platformMenuService.delete(uuid);
        }
        catch (Exception e)
        {
            LOG.error("/platformMenu/findByUuid/{uuid}:" + e);
            return WebApiResponse.error(WebApiResponse.ResponseMsg.EXCEPTION.getValue());
        }
        if (result > 0)
        {
            return WebApiResponse.success(null, WebApiResponse.ResponseMsg.SUCCESS.getValue());
        }
        return WebApiResponse.error(WebApiResponse.ResponseMsg.FAIL.getValue());
    }
}
