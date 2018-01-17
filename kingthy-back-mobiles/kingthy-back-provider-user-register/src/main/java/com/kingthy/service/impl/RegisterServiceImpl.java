package com.kingthy.service.impl;

import java.util.Date;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import com.alibaba.dubbo.config.annotation.Reference;
import com.kingthy.cache.RedisManager;
import com.kingthy.constant.CacheKeysConstants;
import com.kingthy.dubbo.user.service.MemberDubboService;
import com.kingthy.response.WebApiResponse;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;
import com.kingthy.dto.UserRegDTO;
import com.kingthy.entity.Member;
import com.kingthy.service.RegisterService;
import com.kingthy.util.MD5Util;

@Service(value = "registerService")

/**
 * 注册
 * @author py
 */
public class RegisterServiceImpl implements RegisterService
{

    private static final Logger LOG= LoggerFactory.getLogger(RegisterServiceImpl.class);

    @Autowired
    private RedisManager redisManager;

    @Reference(version = "1.0.0")
    private MemberDubboService memberDubboService;

    @Override
    public WebApiResponse<?> createUser(UserRegDTO userDTO)
    {

        Member searchCond=new Member();
        searchCond.setPhone(userDTO.getPhone());
        //检查手机号是否已存在
        if (memberDubboService.selectCount(searchCond) > 0)
        {
            return WebApiResponse.error("该手机号已注册");
        }
        Member member = new Member();
        BeanUtils.copyProperties(userDTO, member);
        Date currentDate = new Date();
        String salt = String.valueOf(System.currentTimeMillis());
        String pwd = MD5Util.MD5Encode(userDTO.getPwd() + salt);
        member.setUserName(userDTO.getPhone());
        member.setCreateDate(currentDate);
        member.setLastLoginDate(currentDate);
        member.setRegisterIp(userDTO.getRegIp());
        member.setIsLocked(false);
        member.setIsEnabled(false);
        member.setLoginFailureCount(0);
        member.setLockedDate(null);
        member.setSalt(salt);
        member.setPassWord(pwd);
        member.setModifyDate(currentDate);
        member.setDelFlag(false);
        member.setCreator("system");
        member.setIntegral(1000);
        member.setCreateDate(new Date());

        memberDubboService.insert(member);
        return WebApiResponse.success("注册成功");
    }
    @Override
    public int updateUserPwd(UserRegDTO userDTO)
    {
        Member searchCond=new Member();
        searchCond.setPhone(userDTO.getPhone());
        Member member = memberDubboService.selectOne(searchCond);
        if (member != null)
        {
            Member updateCond=new Member();
            String salt = member.getSalt();
            String pwd = MD5Util.MD5Encode(userDTO.getPwd() + salt);
            updateCond.setPassWord(pwd);
            updateCond.setUuid(member.getUuid());
            return memberDubboService.updateByUuid(updateCond);
        }
        return 0;
    }
    public static String getRandNum(int max)
    {
        Random random = new Random();
        String result = "";
        for (int i = 0; i < max; i++)
        {
            result += random.nextInt(10);
        }
        return result;
    }
    @Override
    public String getVerificationCode(String phone){
        // 给调用方产生随机验证码
        String key = CacheKeysConstants.PHONE_VERIFY_PREFIX + phone;
        String code="";
        String value = redisManager.get(key);
        if (StringUtils.isNotBlank(value)) {
            code = value;
        }else {
            code = getRandNum(6);
            redisManager.set(key, code, 180, TimeUnit.SECONDS);
        }
        return code;
    }
}
