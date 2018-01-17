package com.kingthy.platform.controller.manager;

import com.github.pagehelper.PageInfo;
import com.kingthy.platform.dto.manager.MenuAssignedReq;
import com.kingthy.platform.dto.manager.PlatformRolePageReq;
import com.kingthy.platform.dto.manager.PlatformRoleReq;
import com.kingthy.platform.entity.manager.PlatformRole;
import com.kingthy.platform.response.WebApiResponse;
import com.kingthy.platform.service.manager.PlatformRoleService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 
 *
 * PlatformRoleController(平台角色控制层)
 * 
 * 陈钊 2017年5月4日 下午8:16:28
 * 
 * @version 1.0.0
 *
 */
@RestController
@RequestMapping("/platformRole")
public class PlatformRoleController
{
    private static final Logger LOG = LoggerFactory.getLogger(PlatformRoleController.class);
    
    @Autowired
    private PlatformRoleService platformRoleService;
    
    @ApiOperation(value = "分页查询角色")
    @RequestMapping(value = "/findByPage", method = RequestMethod.POST)
    public WebApiResponse<?> findByPage(
        @RequestBody @ApiParam(name = "platformRolePageReq", value = "查询条件", required = true) PlatformRolePageReq platformRolePageReq)
    {
        PageInfo<PlatformRole> page;
        try
        {
            page = platformRoleService.findByPage(platformRolePageReq);
        }
        catch (Exception e)
        {
            LOG.error("/platformRole/findByPage:" + e);
            return WebApiResponse.error(WebApiResponse.ResponseMsg.EXCEPTION.getValue());
        }
        return WebApiResponse.success(page, WebApiResponse.ResponseMsg.SUCCESS.getValue());
    }
    
    @ApiOperation(value = "查询所有角色")
    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    public WebApiResponse<?> findAll()
    {
        List<PlatformRole> roleList;
        try
        {
            roleList = platformRoleService.findAll();
        }
        catch (Exception e)
        {
            LOG.error("/platformRole/findAll:" + e);
            return WebApiResponse.error(WebApiResponse.ResponseMsg.EXCEPTION.getValue());
        }
        return WebApiResponse.success(roleList, WebApiResponse.ResponseMsg.SUCCESS.getValue());
    }
    
    @ApiOperation(value = "添加角色检查角色名是否重复")
    @RequestMapping(value = "/addCheckRepeat/{name}", method = RequestMethod.GET)
    public WebApiResponse<?> addCheckRepeat(
        @PathVariable @ApiParam(name = "name", value = "角色名", required = true) String name)
    {
        try
        {
            if (platformRoleService.findCountByName(name) > 0)
            {
                return WebApiResponse.error("角色名已经存在");
            }
        }
        catch (Exception e)
        {
            LOG.error("/platformRole/addCheckRepeat/{name}:" + e);
            return WebApiResponse.error(WebApiResponse.ResponseMsg.EXCEPTION.getValue());
        }
        return WebApiResponse.success(null, WebApiResponse.ResponseMsg.SUCCESS.getValue());
    }

    @ApiOperation(value = "修改角色检查角色名是否重复")
    @RequestMapping(value = "/editCheckRepeat/{name}/{uuid}", method = RequestMethod.GET)
    public WebApiResponse<?> checkRepeat(
            @PathVariable @ApiParam(name = "name", value = "角色名", required = true) String name,
            @PathVariable @ApiParam(name = "uuid", value = "角色uuid", required = true) String uuid)
    {
        try
        {
            if (platformRoleService.isNameChanged(name, uuid)){
                if (platformRoleService.findCountByName(name) > 0)
                {
                    return WebApiResponse.error("角色名已经存在");
                }
            }
        }
        catch (Exception e)
        {
            LOG.error("/platformRole/editCheckRepeat/{name}/{uuid}:" + e);
            return WebApiResponse.error(WebApiResponse.ResponseMsg.EXCEPTION.getValue());
        }
        return WebApiResponse.success(null, WebApiResponse.ResponseMsg.SUCCESS.getValue());
    }

    @ApiOperation(value = "添加角色")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public WebApiResponse<?> addRole(
        @RequestBody @ApiParam(name = "platformRoleReq", value = "添加角色", required = true) @Validated PlatformRoleReq platformRoleReq,
        BindingResult bindingResult)
    {
        if (bindingResult.hasErrors())
        {
            return WebApiResponse.error(bindingResult.getFieldError().getDefaultMessage());
        }
        int result;
        try
        {
            result = platformRoleService.add(platformRoleReq);
        }
        catch (Exception e)
        {
            LOG.error("/platformRole/add:" + e);
            return WebApiResponse.error(WebApiResponse.ResponseMsg.EXCEPTION.getValue());
        }
        if (result > 0)
        {
            return WebApiResponse.success(null, WebApiResponse.ResponseMsg.SUCCESS.getValue());
        }
        return WebApiResponse.error(WebApiResponse.ResponseMsg.FAIL.getValue());
    }
    
    @ApiOperation(value = "查询角色基本信息")
    @RequestMapping(value = "/findByUuid/{uuid}", method = RequestMethod.GET)
    public WebApiResponse<?> findByUuid(
        @PathVariable @ApiParam(name = "uuid", value = "角色uuid", required = true) String uuid)
    {
        PlatformRole platformRole;
        try
        {
            platformRole = platformRoleService.findByUuid(uuid);
        }
        catch (Exception e)
        {
            LOG.error("/platformRole/findByUuid/{uuid}:" + e);
            return WebApiResponse.error(WebApiResponse.ResponseMsg.EXCEPTION.getValue());
        }
        if (platformRole != null)
        {
            return WebApiResponse.success(platformRole);
        }
        return WebApiResponse.error(WebApiResponse.ResponseMsg.FAIL.getValue());
    }
    
    @ApiOperation(value = "修改角色")
    @RequestMapping(value = "/edit", method = RequestMethod.PUT)
    public WebApiResponse<?> editRole(
        @RequestBody @ApiParam(name = "platformRoleReq", value = "修改角色", required = true) @Validated PlatformRoleReq platformRoleReq,
        BindingResult bindingResult)
    {
        if (bindingResult.hasErrors())
        {
            return WebApiResponse.error(bindingResult.getFieldError().getDefaultMessage());
        }
        int result;
        try
        {
            if (platformRoleService.isSuperAdmin(platformRoleReq.getUuid()))
            {
                return WebApiResponse.error("超级管理员角色信息不能修改");
            }
            result = platformRoleService.edit(platformRoleReq);
        }
        catch (Exception e)
        {
            LOG.error("/platformRole/edit:" + e);
            return WebApiResponse.error(WebApiResponse.ResponseMsg.EXCEPTION.getValue());
        }
        if (result > 0)
        {
            return WebApiResponse.success(null, WebApiResponse.ResponseMsg.SUCCESS.getValue());
        }
        return WebApiResponse.error(WebApiResponse.ResponseMsg.FAIL.getValue());
    }
    
    @ApiOperation(value = "删除角色")
    @RequestMapping(value = "/delete/{uuid}", method = RequestMethod.PUT)
    public WebApiResponse<?> deleteRole(
        @PathVariable @ApiParam(name = "uuid", value = "角色uuid", required = true) String uuid)
    {
        Integer result;
        try
        {
            if (platformRoleService.isSuperAdmin(uuid))
            {
                return WebApiResponse.error("超级管理员角色不能删除");
            }
            result = platformRoleService.delete(uuid);
        }
        catch (Exception e)
        {
            LOG.error("/platformRole/delete/{uuid}:" + e);
            return WebApiResponse.error(WebApiResponse.ResponseMsg.EXCEPTION.getValue());
        }
        if (result == null)
        {
            return WebApiResponse.error("此角色下有用户存在，不能删除");
        }
        else
        {
            if (result > 0)
            {
                return WebApiResponse.success(null, WebApiResponse.ResponseMsg.SUCCESS.getValue());
            }
            else
            {
                return WebApiResponse.error(WebApiResponse.ResponseMsg.FAIL.getValue());
            }
        }
    }
    
    @ApiOperation(value = "权限分配")
    @RequestMapping(value = "/assigned", method = RequestMethod.POST)
    public WebApiResponse<?> assigned(
        @RequestBody @ApiParam(name = "menuAssignedReq", value = "权限分配", required = true) @Validated MenuAssignedReq menuAssignedReq,
        BindingResult bindingResult)
    {
        if (bindingResult.hasErrors())
        {
            return WebApiResponse.error(bindingResult.getFieldError().getDefaultMessage());
        }
        int result;
        try
        {
            if (platformRoleService.isSuperAdmin(menuAssignedReq.getRoleUuid()))
            {
                return WebApiResponse.error("超级管理员角色权限不能更改");
            }
            result = platformRoleService.assignedMenu(menuAssignedReq);
        }
        catch (Exception e)
        {
            LOG.error("/platformRole/assigned:" + e);
            return WebApiResponse.error(WebApiResponse.ResponseMsg.EXCEPTION.getValue());
        }
        if (result > 0)
        {
            return WebApiResponse.success(null, WebApiResponse.ResponseMsg.SUCCESS.getValue());
        }
        return WebApiResponse.error(WebApiResponse.ResponseMsg.FAIL.getValue());
        
    }
    
    @ApiOperation(value = "根据角色查询权限")
    @RequestMapping(value = "/findMenuByRole/{uuid}", method = RequestMethod.GET)
    public WebApiResponse<?> findMenuByRoleId(@PathVariable String uuid)
    {
        List<String> urlList;
        try
        {
            urlList = platformRoleService.findMenuByRoleUuid(uuid);
        }
        catch (Exception e)
        {
            LOG.error("/platformRole/findMenuByRole/{uuid}:" + e);
            return WebApiResponse.error(WebApiResponse.ResponseMsg.EXCEPTION.getValue());
        }
        if (urlList != null)
        {
            return WebApiResponse.success(urlList, WebApiResponse.ResponseMsg.SUCCESS.getValue());
        }
        return WebApiResponse.error(WebApiResponse.ResponseMsg.FAIL.getValue());
    }
}
