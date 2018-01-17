package com.kingthy.platform.controller.manager;

import com.kingthy.platform.dto.manager.CheckPermissionReq;
import com.kingthy.platform.dto.manager.PlatformUserLoginReq;
import com.kingthy.platform.entity.manager.PlatformMenu;
import com.kingthy.platform.entity.manager.PlatformRole;
import com.kingthy.platform.entity.manager.PlatformUser;
import com.kingthy.platform.response.WebApiResponse;
import com.kingthy.platform.service.manager.PlatformMenuService;
import com.kingthy.platform.service.manager.PlatformRoleService;
import com.kingthy.platform.service.manager.PlatformUserService;
import com.kingthy.platform.util.MD5Util;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/")
public class LoginController {
    private static final Logger LOG = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private PlatformUserService platformUserService;

    @Autowired
    private PlatformRoleService platformRoleService;

    @Autowired
    private PlatformMenuService platformMenuService;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @ApiOperation(value = "登录")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public WebApiResponse<?> login(
            @RequestBody @ApiParam(name = "platformUserReq", value = "用户信息入参", required = true) @Validated PlatformUserLoginReq platformUserLoginReq,
            BindingResult bindingResult, HttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            return WebApiResponse.error(bindingResult.getFieldError().getDefaultMessage());
        }
        PlatformUser platformUser;
        try {
            platformUser = platformUserService.findByUserName(platformUserLoginReq.getUserName());
        } catch (Exception e) {
            LOG.error("/login:" + e);
            return WebApiResponse.error(WebApiResponse.ResponseMsg.EXCEPTION.getValue());
        }
        if (platformUser != null) {
            String salt = platformUser.getSalt();
            String password = MD5Util.MD5Encode(platformUserLoginReq.getPassword() + salt);
            if (password.equals(platformUser.getPassword())) {
                saveMenu(platformUser);
                return WebApiResponse.success(platformUser.getUuid(), WebApiResponse.ResponseMsg.SUCCESS.getValue());
            } else {
                return WebApiResponse.error("用户名或密码错误");
            }
        } else {
            return WebApiResponse.error("用户名或密码错误");
        }
    }

    /**
     * saveMenu(将用户所有权限放入redis)
     *
     * @param platformUser
     * @param platformUser <b>创建人：</b>陈钊<br/>
     * @throws @since 1.0.0
     */
    private void saveMenu(PlatformUser platformUser) {
        try {
            List<PlatformRole> roleList = platformRoleService.findRolesByUserName(platformUser.getUserName());
            if (roleList != null) {
                for (PlatformRole role : roleList) {
                    List<PlatformMenu> menuList = null;
                    menuList = platformMenuService.findMenuByRoleUuid(role.getUuid());
                    if (menuList != null) {
                        String key = "platform:" + platformUser.getUuid();
                        if (stringRedisTemplate.hasKey(key)) {
                            stringRedisTemplate.delete(key);
                        }
                        for (PlatformMenu platformMenu : menuList) {
                            stringRedisTemplate.opsForSet().add(key, platformMenu.getUrl());
                            stringRedisTemplate.expire(key,7,TimeUnit.DAYS);
                        }
                    }
                }
            }
        } catch (Exception e) {
            LOG.error("saveMenu(将用户所有权限放入redis):" + e);
        }

    }

    /**
     * checkUrl(检查用户是否有菜单权限)
     *
     * @param checkPermissionReq
     * @return <b>创建人：</b>陈钊<br/>
     * WebApiResponse<?>
     * @throws @since 1.0.0
     */
    @RequestMapping(value = "/checkUrl", method = RequestMethod.POST)
    public WebApiResponse<?> checkUrl(@RequestBody CheckPermissionReq checkPermissionReq) {
        String uuid = checkPermissionReq.getUuid();
        boolean hasPermission;
        try {
            String pageName = checkPermissionReq.getPageName();
            int i = pageName.lastIndexOf("/");
            pageName = i > 0 ? pageName.substring(++i) : pageName;
            hasPermission = stringRedisTemplate.opsForSet().isMember("platform:" + uuid, pageName);
        } catch (Exception e) {
            LOG.error("/login/checkUrl:" + e);
            return WebApiResponse.error(WebApiResponse.ResponseMsg.EXCEPTION.getValue());
        }
        if (hasPermission) {
            return WebApiResponse.success(null, "0");
        }
        return WebApiResponse.success(null, "1");
    }

}
