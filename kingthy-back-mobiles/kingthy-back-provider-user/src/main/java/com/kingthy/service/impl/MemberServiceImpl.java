/**
 * 系统项目名称
 * com.kingthy.service.impl
 * MemberServiceImpl.java
 * 
 * 2017年4月18日-下午5:47:03
 *  2017金昔科技有限公司-版权所有
 *
 */
package com.kingthy.service.impl;


import com.alibaba.dubbo.config.annotation.Reference;
import com.kingthy.cache.CacheManager;
import com.kingthy.cache.RedisManager;
import com.kingthy.common.KryoSerializeUtils;
import com.kingthy.constant.CacheKeysConstants;
import com.kingthy.constant.RegularConstants;
import com.kingthy.dto.*;
import com.kingthy.dubbo.user.service.MemberBankCardDubboService;
import com.kingthy.dubbo.user.service.MemberDubboService;
import com.kingthy.entity.Member;
import com.kingthy.exception.BizException;
import com.kingthy.request.MemberBaseInfoReq;
import com.kingthy.response.WebApiResponse;
import com.kingthy.response.WebApiResponse.ResponseMsg;
import com.kingthy.service.MemberService;
import com.kingthy.util.BeanMapperUtil;
import com.kingthy.util.MD5Util;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 会员业务逻辑处理
 * @author 李克杰 2017年4月18日 下午5:47:03
 * @version 1.0.0
 *
 */
@Service("memberService")
public class MemberServiceImpl implements MemberService {
    private static final Logger LOGGER = LoggerFactory.getLogger(AttentionServiceImpl.class);
    @Autowired
    private CacheManager cacheManager;

    @Reference(version = "1.0.0")
    private MemberDubboService memberDubboService;

    @Reference(version = "1.0.0")
    private MemberBankCardDubboService memberBankCardDubboService;
    @Override
    public int updateMember(MemberDTO dto) {

        Member member = new Member();
        BeanMapperUtil.copyProperties(dto, member);
        int res = memberDubboService.updateByUuid(member);
        if (res > 0) {
            //更新缓存
            String cacheKey = CacheKeysConstants.generateCacheKey(MemberDTO.class, dto.getUuid());
            UpdateCacheQueueDTO updateCacheQueueDTO = new UpdateCacheQueueDTO();
            updateCacheQueueDTO.setCacheKey(cacheKey);
            cacheManager.updateCache(updateCacheQueueDTO);

        }
        return res;
    }
    @HystrixCommand(fallbackMethod = "getMemberByUuIdFallback")
    @Override
    public MemberDTO getMemberByUuId(String uuid) {


        MemberDTO dto = new MemberDTO();
        String cacheKey = CacheKeysConstants.generateCacheKey(MemberDTO.class, uuid);
        //从缓存读取
        String cacheData = cacheManager.get(cacheKey);
        if (StringUtils.isNotBlank(cacheData)) {
            dto = KryoSerializeUtils.deserializationObject(cacheData, MemberDTO.class);
        } else {
            //获取失效时间，如果已经失效，再查询数据库，即使为空直，如果没失效，也不会直接访问数据库
            long expire = cacheManager.getExpire(cacheKey);
            if (expire <= 0) {
                dto = memberDubboService.getMemberByUuid(uuid);
                if (dto != null) {
                    //绑卡信息
                    IncomeBalanceDTO incomeBalanceDTO = memberBankCardDubboService.queryBankInfoByMembersUuid(uuid);
                    if (incomeBalanceDTO != null) {
                        //设置绑定的银行卡号
                        dto.setBankCard(incomeBalanceDTO.getCardNo());
                        //设置绑定的银行
                        dto.setBankName(incomeBalanceDTO.getBankName());
                    }
                    cacheData = KryoSerializeUtils.serializationObject(dto);
                }
                cacheManager.setByValue(cacheKey, cacheData,  TimeUnit.DAYS);
            }
        }
        return dto;
    }
    private MemberDTO getMemberByUuIdFallback(String uuid, Throwable e) {
        LOGGER.error("getMemberByUuId 发生异常，进入熔断，异常信息：" + e.toString());
        throw new RuntimeException(e);
    }
    @Override
    public WebApiResponse updateName(String uuid, String name) {
        int res = memberDubboService.updateName(uuid, name);
        if (res > 0) {
            String cacheKey = CacheKeysConstants.generateCacheKey(MemberDTO.class, uuid);
            UpdateCacheQueueDTO updateCacheQueueDTO = new UpdateCacheQueueDTO();
            updateCacheQueueDTO.setCacheKey(cacheKey);
            cacheManager.updateCache(updateCacheQueueDTO);
            return WebApiResponse.success(ResponseMsg.SUCCESS.getValue());
        } else {
            return WebApiResponse.error(ResponseMsg.FAIL.getValue());
        }

    }
    @Override
    public WebApiResponse<?> unlocked(String phone) throws Exception {

        return memberDubboService.updateLocked(phone) > 0 ? WebApiResponse.success() : WebApiResponse.error("操作失败");
    }
    @Override
    public WebApiResponse<?> modifyMobile(String memberUuid,String phone) {

        int count = memberDubboService.checkPhoneIsExist(memberUuid, phone);
        if (count > 0) {
            //手机号已注册
            return WebApiResponse.error("该手机号已被其他用户注册！");
        }
        //修改手机号
        int res = memberDubboService.modifyPhone(memberUuid, phone);
        if (res > 0) {
            String cacheKey = CacheKeysConstants.generateCacheKey(MemberDTO.class, memberUuid);
            UpdateCacheQueueDTO updateCacheQueueDTO = new UpdateCacheQueueDTO();
            updateCacheQueueDTO.setCacheKey(cacheKey);
            cacheManager.updateCache(updateCacheQueueDTO);
            return WebApiResponse.success();
        } else {
            return WebApiResponse.error(ResponseMsg.FAIL.getValue());
        }
    }

    @Override
    public WebApiResponse<?> modifyPassword(String uuid, String password) {
        //密码加密
        String salt = String.valueOf(System.currentTimeMillis());
        String md5Pwd = MD5Util.MD5Encode(password + salt);
        int res = memberDubboService.modifyPassword(uuid, md5Pwd, salt);
        if (res > 0) {
            return WebApiResponse.success();
        } else {
            return WebApiResponse.error(ResponseMsg.FAIL.getValue());
        }
    }

    @Override
    public WebApiResponse<?> modifyPaymentPassword(String membereUuid,String paymentPassword) {

        /**密码加密，并修改密码**/
        String paymentSalt = String.valueOf(System.currentTimeMillis());
        String md5Pwd = MD5Util.MD5Encode(paymentPassword + paymentSalt);
        int res = memberDubboService.modifyPaymentPassword(membereUuid, md5Pwd, paymentSalt);
        if (res > 0) {
            String cacheKey = CacheKeysConstants.generateCacheKey(MemberDTO.class, membereUuid);
            UpdateCacheQueueDTO updateCacheQueueDTO = new UpdateCacheQueueDTO();
            updateCacheQueueDTO.setCacheKey(cacheKey);
            cacheManager.updateCache(updateCacheQueueDTO);
            return WebApiResponse.success();
        } else {
            return WebApiResponse.error(ResponseMsg.FAIL.getValue());
        }
    }
    @Override
    public WebApiResponse<?> verifyPaymentPassword(String uuid, String paymentPassword) {

        Member member = memberDubboService.selectByUuid(uuid);
        if (member != null) {
            String salt = member.getPaymenSalt();
            String userMd5Pwd = MD5Util.MD5Encode(paymentPassword + salt);
            if (userMd5Pwd.equals(member.getPaymentPassword())) {
                return WebApiResponse.success("验证成功！");
            } else {
                return WebApiResponse.error("支付密码错误！");
            }
        } else {
            return WebApiResponse.error("用户不存在！");
        }

    }
    @Override
    public WebApiResponse<?> verifyLoginPassword(String memberUuid,String password) {

        //验证登录密码
        Member member = memberDubboService.selectByUuid(memberUuid);
        if (member != null) {
            String salt = member.getSalt();
            //用户输入的密码
            String inputPassword = MD5Util.MD5Encode(password + salt);
            //数据库中的密码
            if (inputPassword.equals(member.getPassWord())) {
                return WebApiResponse.success("验证成功！");
            } else {

                return WebApiResponse.error("登录密码错误！");
            }
        } else {
            return WebApiResponse.error("用户不存在！");
        }
    }
    @HystrixCommand(fallbackMethod = "getMembersBaseInfoFallback",
            commandProperties = {
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "5000"),
                    @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "5000"),
                    @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "20"),
                    @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "50"),
                    @HystrixProperty(name = "fallback.isolation.semaphore.maxConcurrentRequests", value = "10")

            })
    @Override
    public List<MemberBaseInfoDTO> getMembersBaseInfo(MemberBaseInfoReq memberBaseInfoReq) {
        try {
            return memberDubboService.getMembersBaseInfo(memberBaseInfoReq);
        } catch (Exception ex) {
            LOGGER.info(ex.toString());
        }
        return null;
    }
    private List<MemberBaseInfoDTO> getMembersBaseInfoFallback(MemberBaseInfoReq memberBaseInfoReq, Throwable e) {
        LOGGER.error("getMembersBaseInfo 发生异常，进入熔断，异常信息：" + e.toString());
        throw new RuntimeException(e);
    }
}
