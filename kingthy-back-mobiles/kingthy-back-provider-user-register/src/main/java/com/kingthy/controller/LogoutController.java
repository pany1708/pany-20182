package com.kingthy.controller;

import com.kingthy.cache.RedisManager;
import com.kingthy.common.CommonUtils;
import com.kingthy.constant.CacheKeysConstants;
import com.kingthy.entity.Member;
import com.kingthy.response.WebApiResponse;
import com.kingthy.service.LoginService;
import com.kingthy.service.RegisterService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.TimeUnit;


/**
 * 登出
 * @author pany
 */
@Api
@RestController
@RequestMapping("/member")
public class LogoutController
{
    private static final Logger LOG = LoggerFactory.getLogger(RegisterController.class);

    @Autowired
    private RedisManager redisManager;

    @Autowired
    private LoginService loginService;

    @ApiOperation(value = "用户注销接口", notes = "用户注销接口")
    @GetMapping("/logout")
    public WebApiResponse<?> logout(@RequestHeader(CommonUtils.REQUEST_HEADER_PARAMETER) String token) {
        try {
            if (StringUtils.isBlank(token)) {
                return WebApiResponse.error(WebApiResponse.ResponseMsg.PARAMETER_ERROR.getValue());
            }
            String memberUuid = redisManager.get(token);
            if (StringUtils.isBlank(memberUuid)) {
                return WebApiResponse.error(WebApiResponse.ResponseMsg.NOT_LOGIN.getValue());
            }
            return loginService.toLogout(token);
        } catch (Exception ex) {
            LOG.error("logout error:" + ex.toString());
            return WebApiResponse.error(WebApiResponse.ResponseMsg.EXCEPTION.getValue());
        }

    }
}
