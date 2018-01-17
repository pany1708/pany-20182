package com.kingthy.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.kingthy.cache.RedisManager;
import com.kingthy.constant.CacheKeysConstants;
import com.kingthy.dto.UpdateLoginInfoDTO;
import com.kingthy.dto.UserDTO;
import com.kingthy.dto.UserDTO.LoginModel;
import com.kingthy.dubbo.user.service.MemberDubboService;
import com.kingthy.entity.Member;
import com.kingthy.response.WebApiResponse;
import com.kingthy.security.Audience;
import com.kingthy.service.LoginService;
import com.kingthy.util.JwtUtil;
import com.kingthy.util.MD5Util;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service(value = "userService")

/**
 * 登录
 * @author py
 */
public class LoginServiceImpl implements LoginService
{
    private static final Logger LOG= LoggerFactory.getLogger(LoginServiceImpl.class);

    @Reference(version = "1.0.0")
    private MemberDubboService memberDubboService;
    @Autowired
    private RedisManager redisManager;
    @Autowired
    private Audience audience;

    @HystrixCommand(fallbackMethod = "toLoginHystrixCommand")
    @Override
    public WebApiResponse toLogin(UserDTO userDTO) {

        if ((userDTO.getClientId().compareTo(audience.getClientId()) != 0)) {
            return WebApiResponse.error(WebApiResponse.ResponseMsg.INVALID_CLIENT_ID.getValue());
        }
        /**1.校验账号*/
        String loginName=userDTO.getPhone();
        //根据账号查询用户信息
        Member loginUser = findUserByLoginName(loginName);
        if(loginUser!=null){
            WebApiResponse<?> result=isValidateAccount(loginUser);
            if(result.getCode()!=WebApiResponse.SUCCESS_CODE){
                return result;
            }
        }
        if(Optional.ofNullable(userDTO.getLoginMode()).orElse(0).equals(LoginModel.PHONE_VERIFY_CODE.getValue())){
            /**2.手机验证码登录*/
            WebApiResponse<Member> result=verifyCodeLogin(userDTO,loginUser);
            if(result.getCode()!=WebApiResponse.SUCCESS_CODE){
                return result;
            }
            loginUser=result.getData();
        }else{
            /**2.账号密码登录*/
            WebApiResponse<?> result=passwordLogin(userDTO,loginUser);
            if(result.getCode()!=WebApiResponse.SUCCESS_CODE){
                return result;
            }
        }
        /**3.登录成功处理****/
        String tokenKey= CacheKeysConstants.LONGIN_PREFIX + loginName;
        String accessToken="";
        if (redisManager.hasKey(tokenKey)) {
            //从缓存获取
            accessToken = redisManager.get(tokenKey);
        }
        if(StringUtils.isBlank(accessToken)){
            //生成token
            accessToken = JwtUtil.createJWT(loginUser, audience);
            // 写入缓存
            redisManager.set(tokenKey, accessToken,7, TimeUnit.DAYS);
            redisManager.set(accessToken, loginUser.getUuid(),7,TimeUnit.DAYS);
        }
        //登录成功
        onLoginSuccess(loginName,accessToken);
        return WebApiResponse.success(accessToken);
    }
    private WebApiResponse toLoginHystrixCommand(UserDTO userDTO,Throwable e){
        LOG.error("toLogin 发生异常，进入熔断，异常信息：" + e.toString());
        throw new RuntimeException(e);
    }
    @HystrixCommand(fallbackMethod = "toLogoutHystrixCommand")
    @Override
    public WebApiResponse toLogout(String token) {

        Member member = memberDubboService.selectByUuid(redisManager.get(token));
        if (null == member) {
            return WebApiResponse.error(WebApiResponse.ResponseMsg.UNEXIST_USER.getValue());
        }
        String phone = member.getPhone();
        String tokenKey = CacheKeysConstants.LONGIN_PREFIX+ phone;
        //让登陆标识失效1
        boolean existLogout = redisManager.get(tokenKey) == null;
        if (existLogout) {
            return WebApiResponse.error(WebApiResponse.ResponseMsg.LOGOUT_ERROR.getValue());
        }
        boolean phoneFlag = redisManager.expire(tokenKey, -1, TimeUnit.SECONDS);
        boolean tokenFlag = redisManager.expire(token, -1, TimeUnit.SECONDS);
        LOG.debug("phoneFlag>>>" + phoneFlag);
        LOG.debug("tokenFlag>>>" + tokenFlag);
        if (phoneFlag == Boolean.TRUE && tokenFlag == Boolean.TRUE) {
            return WebApiResponse.success();
        } else {
            return WebApiResponse.error(WebApiResponse.ResponseMsg.LOGOUT_ERROR.getValue());
        }
    }
    private WebApiResponse toLogoutHystrixCommand(String token,Throwable e){
        LOG.error("toLogout 发生异常，进入熔断，异常信息：" + e.toString());
        throw new RuntimeException(e);
    }
    @Override
    public Member  findUserByLoginName(String loginName) {

        Member member = new Member();
        member.setPhone(loginName);
        member=memberDubboService.selectOne(member);
        return member;
    }
    /**
     * 手机号验证码登录
     */
    private WebApiResponse verifyCodeLogin(UserDTO userDTO, Member member){
        String key = CacheKeysConstants.PHONE_VERIFY_PREFIX + userDTO.getPhone();
        //从缓存中获取验证码
        String cacheCode = redisManager.get(key);
        if (StringUtils.isBlank(cacheCode))
        {
            return WebApiResponse.error("验证码已过期，请重新获取");
        }
        if (!cacheCode.equals(userDTO.getCode()))
        {
            return WebApiResponse.error("验证码输入错误");
        }
        if(null==member){
            /**用户不存在，注册新用户*/
            Member newUser = new Member();
            //注册手机号
            newUser.setPhone(userDTO.getPhone());
            Date currentDate = new Date();
            newUser.setUserName(userDTO.getPhone());
            newUser.setCreateDate(currentDate);
            newUser.setLastLoginDate(currentDate);
            newUser.setIsLocked(false);
            newUser.setIsEnabled(false);
            newUser.setLoginFailureCount(0);
            newUser.setLockedDate(null);
            newUser.setModifyDate(currentDate);
            newUser.setDelFlag(false);
            newUser.setCreator("system");
            newUser.setIntegral(1000);
            newUser.setCreateDate(currentDate);
            memberDubboService.insert(newUser);
            /**注册成功，从新查询新用户信息*/
            member = findUserByLoginName(userDTO.getPhone());
            if(member==null){
                return WebApiResponse.error("登录失败，用户注册不成功！");
            }
        }
        return WebApiResponse.success(member);
    }

    /**
     * 账号密码登录
     */
    private WebApiResponse passwordLogin(UserDTO userDTO, Member loginUser){
        /**1. 校验账号***/
        if (null == loginUser) {
            return WebApiResponse.error("该账号未注册");
        }
        if(StringUtils.isBlank(loginUser.getPassWord())){
            return WebApiResponse.error("该账号未设置密码，请使用其他登录方式");
        }
        /**2. 校验密码***/
        // 比对用户名密码 暂时未对密码进行加密 上线前需对密码以及手机号进行加密处理
        String pwdFromWeb = MD5Util.MD5Encode(userDTO.getPwd() + loginUser.getSalt());
        if (!pwdFromWeb.equals(loginUser.getPassWord())) {
            // 失败后，失败次数+1
            int loginFailureCount = Optional.ofNullable(loginUser.getLoginFailureCount()).orElse(0)+ 1;
            if (loginFailureCount >= 1) {
                //登录失败
                onLoginFailure(loginUser.getPhone(),loginFailureCount);

                return WebApiResponse.error("密码错误，若连续  5 次密码错误账号将被锁定,剩余重试次数:" + (5 - loginFailureCount));
            }
            return WebApiResponse.error("用户名或密码错误");
        }else{
            return WebApiResponse.success("登录成功");
        }
    }



    /**
     * 用户账户验证
     */
    private WebApiResponse<Member> isValidateAccount(Member member) {
        if (Optional.ofNullable(member.getIsEnabled()).orElse(false)) {
            return WebApiResponse.error("该账号" + member.getPhone() + "已被冻结");
        }
        if (Optional.ofNullable(member.getIsLocked()).orElse(false)) {

            Date lockedDate = member.getLockedDate();
            Date unlockDate = DateUtils.addMinutes(lockedDate, 30);
            if (new Date().after(unlockDate)) {
                //解锁用户
                unlockUser(member.getPhone());
            }
            else {
                return WebApiResponse.error("该账号" + member.getPhone() + "已被锁定");
            }
        }
        return WebApiResponse.success();
    }
    /**
     * 解锁用户
     */
    @Override
    public int unlockUser(String phone){
        UpdateLoginInfoDTO dto=new UpdateLoginInfoDTO();
        dto.setPhone(phone);
        dto.setLoginFailureCount(0);
        dto.setIsLocked(false);
        dto.setLockedDate(null);
        return memberDubboService.updateMemberLoginInfo(dto);
    }
    /**
     * 登录成功
     */
    private void onLoginSuccess(String phone,String token){
        //登录成功，更新会员状态信息
        UpdateLoginInfoDTO dto=new UpdateLoginInfoDTO();
        dto.setToken(token);
        dto.setPhone(phone);
        dto.setLoginFailureCount(0);
        dto.setLastLoginDate(new Date());
        memberDubboService.updateMemberLoginInfo(dto);
    }
    /**
     * 登录失败
     */
    private void onLoginFailure(String phone,int loginFailureCount){
        UpdateLoginInfoDTO dto=new UpdateLoginInfoDTO();
        dto.setLoginFailureCount(loginFailureCount);
        dto.setPhone(phone);
        int fcount=5;
        if (loginFailureCount >= fcount) {
            dto.setIsLocked(true);
            dto.setLockedDate(new Date());
        }
        memberDubboService.updateMemberLoginInfo(dto);
    }

}
