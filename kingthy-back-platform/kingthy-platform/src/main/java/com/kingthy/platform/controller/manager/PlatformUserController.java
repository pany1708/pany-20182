package com.kingthy.platform.controller.manager;

import com.github.pagehelper.PageInfo;
import com.kingthy.platform.dto.manager.*;
import com.kingthy.platform.response.WebApiResponse;
import com.kingthy.platform.service.manager.PlatformUserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * 
 *
 * PlatformUserController(平台用户控制层)
 * 
 * 陈钊 2017年5月4日 上午9:34:42
 * 
 * @version 1.0.0
 *
 */
@RestController
@RequestMapping("/platformUser")
public class PlatformUserController
{
    private static final Logger LOG = LoggerFactory.getLogger(PlatformUserController.class);
    
    @Autowired
    private PlatformUserService platformUserService;
    
    @ApiOperation(value = "分页查询用户信息")
    @RequestMapping(value = "/findByPage", method = RequestMethod.POST)
    public WebApiResponse<?> findUserByPage(
        @RequestBody @ApiParam(name = "platformUserPageReq", value = "查询条件", required = true) PlatformUserPageReq platformUserPageReq)
    {
        PageInfo<PlatformUserInfoDto> page;
        try
        {
            page = platformUserService.findUserByPage(platformUserPageReq);
        }
        catch (Exception e)
        {
            LOG.error("/platformUser/findByPage:" + e);
            return WebApiResponse.error(WebApiResponse.ResponseMsg.EXCEPTION.getValue());
        }
        
        return WebApiResponse.success(page, WebApiResponse.ResponseMsg.SUCCESS.getValue());
    }
    
    @ApiOperation(value = "添加用户检查用户名是否重复")
    @RequestMapping(value = "/checkRepeat/{name}", method = RequestMethod.GET)
    public WebApiResponse<?> checkRepeat(
        @PathVariable @ApiParam(name = "name", value = "用户名", required = true) String name)
    {
        try
        {
            if (platformUserService.findCountByName(name) > 0)
            {
                return WebApiResponse.error("用户名已经存在");
            }
        }
        catch (Exception e)
        {
            LOG.error("/platformUser/addCheckRepeat/{name}:" + e);
            return WebApiResponse.error(WebApiResponse.ResponseMsg.EXCEPTION.getValue());
        }
        return WebApiResponse.success(null, WebApiResponse.ResponseMsg.SUCCESS.getValue());
    }

    @ApiOperation(value = "编辑用户检查用户名是否重复")
    @RequestMapping(value = "/editCheckRepeat/{name}/{uuid}", method = RequestMethod.GET)
    public WebApiResponse<?> editCheckRepeat(
            @PathVariable @ApiParam(name = "name", value = "用户名", required = true) String name,
            @PathVariable @ApiParam(name = "uuid", value = "用户uuid", required = true) String uuid){
        try
        {
            if(platformUserService.isNameChange(name,uuid)){
                if (platformUserService.findCountByName(name) > 0)
                {
                    return WebApiResponse.error("用户名已经存在");
                }
            }
        }
        catch (Exception e)
        {
            LOG.error("/platformUser/editCheckRepeat/{name}/{uuid}:" + e);
            return WebApiResponse.error(WebApiResponse.ResponseMsg.EXCEPTION.getValue());
        }
        return WebApiResponse.success(null, WebApiResponse.ResponseMsg.SUCCESS.getValue());
    }
    
    @ApiOperation(value = "创建用户")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public WebApiResponse<?> add(
        @RequestBody @ApiParam(name = "platformUserReq", value = "用户信息入参", required = true) @Validated PlatformUserReq platformUserReq,
        BindingResult bindingResult)
    {
        if (bindingResult.hasErrors())
        {
            return WebApiResponse.error(bindingResult.getFieldError().getDefaultMessage());
        }
        int result;
        try
        {
            result = platformUserService.add(platformUserReq);
        }
        catch (Exception e)
        {
            LOG.error("/platformUser/add:" + e);
            return WebApiResponse.error(WebApiResponse.ResponseMsg.EXCEPTION.getValue());
        }
        if (result > 0)
        {
            return WebApiResponse.success(null, WebApiResponse.ResponseMsg.SUCCESS.getValue());
        }
        return WebApiResponse.error(WebApiResponse.ResponseMsg.FAIL.getValue());
        
    }
    
    @ApiOperation(value = "查询用户基本信息")
    @RequestMapping(value = "/findByUuid/{uuid}", method = RequestMethod.GET)
    public WebApiResponse<?> findByUuid(
        @PathVariable @ApiParam(name = "uuid", value = "用户uuid", required = true) String uuid)
    {
        PlatformUserInfoDto platformUserInfoDto;
        try
        {
            platformUserInfoDto = platformUserService.findBaseInfoByUuid(uuid);
        }
        catch (Exception e)
        {
            LOG.error("/platformUser/findByUuid/{uuid}:" + e);
            return WebApiResponse.error(WebApiResponse.ResponseMsg.EXCEPTION.getValue());
        }
        if (platformUserInfoDto != null)
        {
            return WebApiResponse.success(platformUserInfoDto);
        }
        return WebApiResponse.error(WebApiResponse.ResponseMsg.FAIL.getValue());
        
    }
    
    @ApiOperation(value = "修改用户基本信息")
    @RequestMapping(value = "/editBaseInfo", method = RequestMethod.PUT)
    public WebApiResponse<?> editBaseInfo(
        @RequestBody @ApiParam(name = "platformUserEditReq", value = "用户信息入参", required = true) @Validated PlatformUserEditReq platformUserEditReq,
        BindingResult bindingResult)
    {
        if (bindingResult.hasErrors())
        {
            return WebApiResponse.error(bindingResult.getFieldError().getDefaultMessage());
        }
        if (StringUtils.isEmpty(platformUserEditReq.getUuid()))
        {
            return WebApiResponse.error("用户uuid不能为空");
        }
        int result;
        try
        {
            result = platformUserService.editBaseInfo(platformUserEditReq);
        }
        catch (Exception e)
        {
            LOG.error("/platformUser/editBaseInfo:" + e);
            return WebApiResponse.error(WebApiResponse.ResponseMsg.EXCEPTION.getValue());
        }
        if (result > 0)
        {
            return WebApiResponse.success(null, WebApiResponse.ResponseMsg.SUCCESS.getValue());
        }
        return WebApiResponse.error(WebApiResponse.ResponseMsg.FAIL.getValue());
    }
    
    @ApiOperation(value = "删除用户")
    @RequestMapping(value = "/delete/{uuid}", method = RequestMethod.PUT)
    public WebApiResponse<?> delete(
        @PathVariable @ApiParam(name = "uuid", value = "用户uuid", required = true) String uuid,
        HttpServletRequest request)
    {
        int result;
        try
        {
            if (uuid.equals(request.getHeader("uuid")))
            {
                return WebApiResponse.error("用户不能删除自己");
            }
            if (!platformUserService.checkInitAdmin(uuid))
            {
                return WebApiResponse.error("您没有权限操作系统内置管理员");
            }
            result = platformUserService.delete(uuid);
        }
        catch (Exception e)
        {
            LOG.error("/platformUser/delete/{uuid}:" + e);
            return WebApiResponse.error(WebApiResponse.ResponseMsg.EXCEPTION.getValue());
        }
        if (result > 0)
        {
            return WebApiResponse.success(null, WebApiResponse.ResponseMsg.SUCCESS.getValue());
        }
        return WebApiResponse.error(WebApiResponse.ResponseMsg.FAIL.getValue());
    }
    
    @ApiOperation(value = "重置密码")
    @RequestMapping(value = "/resetPassword", method = RequestMethod.PUT)
    public WebApiResponse<?> resetPassword(
        @RequestBody @ApiParam(name = "resetPasswordReq", value = "重置密码", required = true) @Validated ResetPasswordReq resetPasswordReq,
        BindingResult bindingResult)
    {
        if (bindingResult.hasErrors())
        {
            return WebApiResponse.error(bindingResult.getFieldError().getDefaultMessage());
        }
        String result;
        try
        {
            if (!platformUserService.checkInitAdmin(resetPasswordReq.getUuid()))
            {
                return WebApiResponse.error("您没有权限操作系统内置管理员");
            }
            result = platformUserService.resetPassword(resetPasswordReq);
        }
        catch (Exception e)
        {
            LOG.error("/platformUser/resetPassword:" + e);
            return WebApiResponse.error(WebApiResponse.ResponseMsg.EXCEPTION.getValue());
        }
        if (result != null)
        {
            return WebApiResponse.success(result, WebApiResponse.ResponseMsg.SUCCESS.getValue());
        }
        return WebApiResponse.error(WebApiResponse.ResponseMsg.FAIL.getValue());
    }
    
    @ApiOperation(value = "修改密码")
    @RequestMapping(value = "/editPassword", method = RequestMethod.PUT)
    public WebApiResponse<?> editPassword(
        @RequestBody @ApiParam(name = "editPasswordReq", value = "修改密码", required = true) @Validated EditPasswordReq editPasswordReq,
        BindingResult bindingResult)
    {
        if (bindingResult.hasErrors())
        {
            return WebApiResponse.error(bindingResult.getFieldError().getDefaultMessage());
        }
        boolean correctPassword;
        try
        {
            correctPassword =
                platformUserService.checkPassword(editPasswordReq.getOldPassword(), editPasswordReq.getUuid());
        }
        catch (Exception e)
        {
            LOG.error("/platformUser/editPassword:" + e);
            return WebApiResponse.error(WebApiResponse.ResponseMsg.EXCEPTION.getValue());
        }
        if (correctPassword)
        {
            int result = platformUserService.editPassword(editPasswordReq);
            if (result > 0)
            {
                return WebApiResponse.success(null, WebApiResponse.ResponseMsg.SUCCESS.getValue());
            }
        }
        else
        {
            return WebApiResponse.error("密码不正确");
        }
        return WebApiResponse.error(WebApiResponse.ResponseMsg.FAIL.getValue());
    }
    
    @ApiOperation(value = "超级管理员修改用户基本信息")
    @RequestMapping(value = "/adminEditUser", method = RequestMethod.PUT)
    public WebApiResponse<?> adminEditUser(
        @RequestBody @ApiParam(name = "platformUserReq", value = "用户信息入参", required = true) @Validated PlatformUserEditReq platformUserEditReq,
        BindingResult bindingResult)
    {
        if (bindingResult.hasErrors())
        {
            return WebApiResponse.error(bindingResult.getFieldError().getDefaultMessage());
        }
        if (StringUtils.isEmpty(platformUserEditReq.getUuid()))
        {
            return WebApiResponse.error("用户uuid不能为空");
        }
        int result;
        try
        {
            if (!platformUserService.checkInitAdmin(platformUserEditReq.getUuid()))
            {
                return WebApiResponse.error("您没有权限操作系统内置管理员");
            }
            if (platformUserService.beforeAssigned(platformUserEditReq))
            {
                return WebApiResponse.error("内置管理员的角色不能修改");
            }
            result = platformUserService.adminEditUser(platformUserEditReq);
        }
        catch (Exception e)
        {
            LOG.error("/platformUser/adminEditUser:" + e);
            return WebApiResponse.error(WebApiResponse.ResponseMsg.EXCEPTION.getValue());
        }
        if (result > 0)
        {
            return WebApiResponse.success(null, WebApiResponse.ResponseMsg.SUCCESS.getValue());
        }
        return WebApiResponse.error(WebApiResponse.ResponseMsg.FAIL.getValue());
    }
}
