package com.kingthy.controller;

import com.kingthy.cache.RedisManager;
import com.kingthy.constant.RegularConstants;
import com.kingthy.dto.UserDTO;
import com.kingthy.dto.UserDTO.LoginModel;
import com.kingthy.response.WebApiResponse;
import com.kingthy.service.LoginService;
import com.netflix.hystrix.exception.HystrixRuntimeException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Optional;



/**
 *  登录
 * @author pany
 */
@Api
@RestController()
@RequestMapping("/member")
public class LoginController

{
    private static final Logger LOGGER= LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private RedisManager redisManager;
    @Autowired
    private LoginService loginService;
    @ApiOperation(value = "用户登录接口", notes = "用户登录接口")
    @PostMapping("/login")
    public WebApiResponse<?> submit(
        @RequestBody @ApiParam(name = "userDTO", value = "用户对象", required = true) UserDTO userDTO)
    {

        try {
            if (StringUtils.isBlank(userDTO.getPhone())) {
                return WebApiResponse.error("手机号不能为空");
            }
            if (!RegularConstants.isPhone(userDTO.getPhone())) {
                return WebApiResponse.error("无法识别的手机号");
            }
            if(StringUtils.isBlank(userDTO.getClientId())){
                return WebApiResponse.error("参数clientId不能为空");
            }
            if(Optional.ofNullable(userDTO.getLoginMode()).orElse(0).equals(LoginModel.PHONE_VERIFY_CODE.getValue())){
                if(StringUtils.isBlank(userDTO.getCode())){
                    return WebApiResponse.error("验证码不能为空");
                }
            }else{
                if(StringUtils.isBlank(userDTO.getPwd())){
                    return WebApiResponse.error("密码不能为空");
                }
            }
            return loginService.toLogin(userDTO);

        }catch (HystrixRuntimeException e){
            LOGGER.error("submit error:"+e.toString());
            if (e.getFailureType()== HystrixRuntimeException.FailureType.TIMEOUT ) {
                return WebApiResponse.error(WebApiResponse.ResponseMsg.TIMEOUT.getValue());
            }
            return WebApiResponse.error(WebApiResponse.ResponseMsg.HYSTRIX_EXCEPTION.getValue());
        } catch (Exception e){
            LOGGER.error("submit error:"+e.toString());
            return WebApiResponse.error(WebApiResponse.ResponseMsg.EXCEPTION.getValue());
        }
    }
}
