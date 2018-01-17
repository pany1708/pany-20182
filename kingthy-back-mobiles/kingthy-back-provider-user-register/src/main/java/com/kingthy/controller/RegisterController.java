package com.kingthy.controller;


import com.kingthy.cache.RedisManager;
import com.kingthy.common.ValidMobile;
import com.kingthy.constant.CacheKeysConstants;
import com.kingthy.constant.RegularConstants;
import com.kingthy.dto.UserRegDTO;
import com.kingthy.response.WebApiResponse;
import com.kingthy.service.RegisterService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;

@RestController()
@RequestMapping("/member")
/**
 * 注册
 * @author pany
 */
public class RegisterController
{
    private static final Logger LOG = LoggerFactory.getLogger(RegisterController.class);

    @Autowired
    private RegisterService registerService;
    @Autowired
    private RedisManager redisManager;

    @ApiOperation(value = "用户注册接口", notes = "根据手机号进行用户注册")
    @PostMapping(value = "/createUser")
    public WebApiResponse<?> createUser(
        @RequestBody @ApiParam(name = "userDTO", value = "用户注册接口", required = true) UserRegDTO userRegDTO)
        throws IOException {
        try {
            //验证账号
            WebApiResponse<String> validMobile = validateUserTelphone(userRegDTO);
            if (validMobile != null) {
                return validMobile;
            }
            //验证密码
            if (!RegularConstants.isPassword(userRegDTO.getPwd())) {
                return WebApiResponse.error("密码无效,请输入8-20位字母数字特殊字符任意两种组合");
            }
            //创建用户
            return registerService.createUser(userRegDTO);

        } catch (Exception ex) {
            LOG.error("createUser error:" + ex.toString());
            return WebApiResponse.error(WebApiResponse.ResponseMsg.EXCEPTION.getValue());
        }
    }

    @ApiOperation(value = "忘记密码", notes = "根据手机号重置密码")
    @RequestMapping(value = "/restPwd", method = RequestMethod.POST)
    public WebApiResponse<String> restPwd(
        @RequestBody @ApiParam(name = "UserRegDTO", value = "用户名,密码,验证码必填", required = true) UserRegDTO userRegDTO)
    {
        try {
            WebApiResponse<String> validateResult = validateUserTelphone(userRegDTO);
            if (validateResult == null) {
                int res=registerService.updateUserPwd(userRegDTO);
                if(res==1){
                    return WebApiResponse.success("密码更改成功");
                }else{
                    return WebApiResponse.error("密码重置失败，用户不存在。");
                }
            }
            return validateResult;
        }catch (Exception ex){
            LOG.error("restPwd error:"+ex.toString());
            return WebApiResponse.error(WebApiResponse.ResponseMsg.EXCEPTION.getValue());
        }
    }

    @ApiOperation(value = "获取验证码", notes = "根据手机号获取验证码")
    @GetMapping("/getVerificationCode/{phoneNum}")
    public WebApiResponse<?> getVerificationCode(
        @PathVariable @ApiParam(name = "phoneNum", value = "手机号", required = true) String phoneNum)
    {
        try {
            if (StringUtils.isBlank(phoneNum)) {
                return WebApiResponse.error("手机号不能为空");
            }
            if (!ValidMobile.isMobileNO(phoneNum)) {
                return WebApiResponse.error("非法的手机号");
            }
            return WebApiResponse.success(registerService.getVerificationCode(phoneNum));
        }catch (Exception ex){
            LOG.error("getVerificationCode error:"+ex.toString());
            return WebApiResponse.error(WebApiResponse.ResponseMsg.EXCEPTION.getValue());
        }
    }
    private WebApiResponse<String> validateUserTelphone(UserRegDTO userDTO)
    {
        String key = CacheKeysConstants.PHONE_VERIFY_PREFIX + userDTO.getPhone();
        String verificationCode = redisManager.get(key);
        if (StringUtils.isBlank(userDTO.getPhone()) || StringUtils.isBlank(userDTO.getPwd()))
        {
            return WebApiResponse.error("手机号或密码不能为空");
        }
        if (!ValidMobile.isMobileNO(userDTO.getPhone()))
        {
            return WebApiResponse.error("非法的手机号");
        }
        if (StringUtils.isBlank(verificationCode))
        {
            return WebApiResponse.error("验证码已过期，请重新获取");
        }
        if (!verificationCode.equals(userDTO.getVerificationCode()))
        {
            return WebApiResponse.error("验证码输入错误，请输入：" + verificationCode);
        }

        return null;
    }
}
