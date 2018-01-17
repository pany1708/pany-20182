/**
 * 系统项目名称
 * com.kingthy.controller
 * MemberController.java
 * 
 * 2017年4月18日-下午7:57:33
 *  2017金昔科技有限公司-版权所有
 *
 */
package com.kingthy.controller;

import java.util.List;
import java.util.concurrent.*;
import com.kingthy.cache.RedisManager;
import com.kingthy.common.CommonUtils;
import com.kingthy.constant.CacheKeysConstants;
import com.kingthy.constant.RegularConstants;
import com.kingthy.dto.*;
import com.kingthy.request.MemberBaseInfoReq;
import com.kingthy.wsclient.WebSocketClient;
import com.netflix.hystrix.exception.HystrixRuntimeException;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.kingthy.response.WebApiResponse;
import com.kingthy.response.WebApiResponse.ResponseMsg;
import com.kingthy.service.MemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;


/**
 *
 * 会员中心--信息管理
 * 
 * @author  李克杰 2017年4月18日 下午7:57:33
 * 
 * @version 1.0.0
 *
 */
@Api
@RestController
@RequestMapping("/member")
public class MemberController 
{

    private static final Logger LOG = LoggerFactory.getLogger(MemberController.class);
    /**线程池对象*/
    private static final ExecutorService THREAD_POOL =  new ThreadPoolExecutor(
            10,
            200, 3,
            TimeUnit.SECONDS,
            new ArrayBlockingQueue<>(3),new ThreadPoolExecutor.DiscardPolicy());

    private static  final int MAX_LEN=20;
    @Autowired
    private MemberService memberService;

    @Autowired
    private WebSocketClient wsclient;

    @Autowired
    private RedisManager redisManager;


    @ApiOperation(value = "获取会员的个人资料接口", notes = "获取会员的个人资料接口")
    @GetMapping("/getMemberInfo")
    public WebApiResponse<?> getMemberInfo(
            @RequestHeader(CommonUtils.REQUEST_HEADER_PARAMETER) String token)
    {
        try
        {
            // 从缓存获取token
            String memberUuid = redisManager.get(token);
            if (StringUtils.isNotBlank(memberUuid))
            {
                MemberDTO myInfo = memberService.getMemberByUuId(memberUuid);
                return WebApiResponse.success(myInfo);
            }
            else
            {
                return WebApiResponse.error(ResponseMsg.TOKEN_ERROR.getValue());
            }
        }catch (HystrixRuntimeException e){
            LOG.error("getMemberInfo error",e);
            if (e.getFailureType()== HystrixRuntimeException.FailureType.TIMEOUT ) {
                return WebApiResponse.error(WebApiResponse.ResponseMsg.TIMEOUT.getValue());
            }
            return WebApiResponse.error(WebApiResponse.ResponseMsg.HYSTRIX_EXCEPTION.getValue());
        } catch (Exception e) {
            LOG.error("getMemberInfo error",e);
            return WebApiResponse.error(ResponseMsg.EXCEPTION.getValue());
        }
        
    }
    
    /**
     * 更新会员
     * 
     */
    @ApiOperation(value = "更新会员资料接口", notes = "更新会员资料接口")
    @PostMapping("/updateMember")
    public WebApiResponse<?> updateMember(
            @RequestBody @ApiParam(name = "member", value = "会员更新对象") MemberDTO member,
            @RequestHeader(CommonUtils.REQUEST_HEADER_PARAMETER) String token)
    {
        try
        {
            String memberUuid = redisManager.get(token);
            if (StringUtils.isBlank(memberUuid))
            {
                return WebApiResponse.error(ResponseMsg.TOKEN_ERROR.getValue());
            }
            member.setUuid(memberUuid);
            // 更新
            int res = memberService.updateMember(member);
            if (res > 0)
            {
                return WebApiResponse.success();
            }
            else
            {
                return WebApiResponse.error(ResponseMsg.FAIL.getValue());
            }
            
        }
        catch (Exception e)
        {
            LOG.error("updateMember error:" + e.toString());
            return WebApiResponse.error(e.toString());
        }
    }

    @ApiOperation(value = "解除锁定用户")
    @RequestMapping(value = "/unlocked/{phone}", method = RequestMethod.PUT)
    public WebApiResponse<?> unlocked(@PathVariable @ApiParam(name = "phone", value = "phone", required = true)String phone){
        try {
            return memberService.unlocked(phone);
        }catch (Exception e){
            LOG.error("/unlocked/{phone} error:" + e.toString());
            return WebApiResponse.error(e.toString());
        }
    }

    @ApiOperation(value = "修改手机号")
    @PostMapping("/modifyMobile")
    public WebApiResponse<?> modifyMobile(
            @RequestBody UpdateUserDTO dto,
            @RequestHeader(CommonUtils.REQUEST_HEADER_PARAMETER) String token)
    {
        try
        {
            String memberUuid = redisManager.get(token);
            if(StringUtils.isBlank(memberUuid)){
                return WebApiResponse.error(ResponseMsg.TOKEN_ERROR.getValue());
            }
            if(StringUtils.isBlank(dto.getPhone())){
                return WebApiResponse.error("手机号码不能为空");
            }
            if(StringUtils.isBlank(dto.getCode())){
                return WebApiResponse.error("验证码不能为空");
            }
            //手机号格式是否正确
            if (!RegularConstants.isPhone(dto.getPhone())) {
                //手机号格式不正确
                return WebApiResponse.error("手机号码格式不正确！");
            }
            //获取缓存的验证码
            String key = CacheKeysConstants.PHONE_VERIFY_PREFIX + dto.getPhone();
            String code = redisManager.get(key);
            if (StringUtils.isBlank(code)) {
                return WebApiResponse.error("验证码已过期！");
            }
            if (!code.equalsIgnoreCase(dto.getCode())) {
                //手机验证码验证失败
                return WebApiResponse.error("验证码错误！");
            }
            dto.setMemberUuid(memberUuid);
            //修改手机号
            return memberService.modifyMobile(dto.getMemberUuid(),dto.getPhone());
        }
        catch (Exception e)
        {
            LOG.error("modifyMobile error:" + e.toString());
            return WebApiResponse.error(e.toString());
        }
    }
    @ApiOperation(value = "修改用户名称")
    @PostMapping("/modifyUserName")
    public WebApiResponse<?> modifyUserName(
            @RequestHeader(CommonUtils.REQUEST_HEADER_PARAMETER) String token,
            @RequestBody @ApiParam(name = "member", value = "参数列表") UpdateUserDTO dto) {
        try {
            String uuid = redisManager.get(token);
            if (StringUtils.isBlank(uuid)) {
                return WebApiResponse.error(ResponseMsg.TOKEN_ERROR.getValue());
            }
            if (StringUtils.isBlank(dto.getUserName())) {
                return WebApiResponse.error("名称不能为空");
            }
            if (dto.getUserName().length()>MAX_LEN) {
                return WebApiResponse.error("名称最多输入20个字符");
            }
            return memberService.updateName(uuid, dto.getUserName());
        } catch (Exception ex) {
            LOG.error("updateName error:" + ex.toString());
            return WebApiResponse.error(ResponseMsg.EXCEPTION.getValue());
        }
    }
    @ApiOperation(value = "修改密码")
    @PostMapping("/modifyPassword")
    public WebApiResponse<?> modifyPassword(
            @RequestHeader(CommonUtils.REQUEST_HEADER_PARAMETER) String token,
            @RequestBody @ApiParam(name = "UpdateUserDTO", value = "参数列表") UpdateUserDTO dto){

        try
        {
            String memberUuid=redisManager.get(token);
            if(StringUtils.isBlank(memberUuid)){
                return WebApiResponse.error(ResponseMsg.TOKEN_ERROR.getValue());
            }
            if(StringUtils.isBlank(dto.getPassword())){
                return WebApiResponse.error("密码不能为空");
            }
            if (!RegularConstants.isPassword(dto.getPassword())) {
                return WebApiResponse.error("密码格式不正确，请输入8-16位的数字和字符，且需包含至少1个字母和字符");
            }
            //修改密码
            return memberService.modifyPassword(memberUuid,dto.getPassword());
        }
        catch (Exception e)
        {
            LOG.error("modifyPassword error:" + e.toString());
            return WebApiResponse.error(e.toString());
        }

    }

    @ApiOperation(value = "修改支付密码")
    @PostMapping("/modifyPaymentPassword")
    public WebApiResponse<?> modifyPaymentPassword(
            @RequestHeader(CommonUtils.REQUEST_HEADER_PARAMETER) String token,
            @RequestBody @ApiParam(name = "UpdateUserDTO", value = "参数列表") UpdateUserDTO dto){

        try
        {

            String memberUuid=redisManager.get(token);
            if(StringUtils.isBlank(memberUuid)){
                return WebApiResponse.error(ResponseMsg.TOKEN_ERROR.getValue());
            }
            if(StringUtils.isBlank(dto.getPaymentPassword())){
                return WebApiResponse.error("支付密码不能为空");
            }
            /**密码格式校验**/
            if (!RegularConstants.isPayPassword(dto.getPaymentPassword())) {
                return WebApiResponse.error("支付密码格式不正确,请输入6-16数字和字母");
            }
            //修改支付密码
            return memberService.modifyPaymentPassword(memberUuid,dto.getPaymentPassword());
        }
        catch (Exception e)
        {
            LOG.error("modifyPaymentPassword error:" + e.toString());
            return WebApiResponse.error(e.toString());
        }
    }

    @ApiOperation(value = "验证支付密码")
    @PostMapping("/verifyPaymentPassword")
    public WebApiResponse<?> verifyPaymentPassword(
            @RequestHeader(CommonUtils.REQUEST_HEADER_PARAMETER) String token,
            @RequestBody @ApiParam(name = "UpdateUserDTO", value = "参数列表") UpdateUserDTO dto){

        try
        {
            String memberUuid=redisManager.get(token);
            if(StringUtils.isBlank(memberUuid)){
                return WebApiResponse.error(ResponseMsg.TOKEN_ERROR.getValue());
            }
            if(StringUtils.isBlank(dto.getPaymentPassword())){
                return WebApiResponse.error("支付密码不能为空");
            }
            //修改支付密码
            return memberService.verifyPaymentPassword(memberUuid,dto.getPaymentPassword());
        }
        catch (Exception e)
        {
            LOG.error("MemberController.verifyPaymentPassword error:" + e.toString());
            return WebApiResponse.error(e.toString());
        }
    }
    @ApiOperation(value = "验证手机号和登录密码")
    @PostMapping("/verifyPhoneAndLoginPassword")
    public WebApiResponse<?> verifyPhoneAndLoginPassword(
            @RequestHeader(CommonUtils.REQUEST_HEADER_PARAMETER) String token,
            @RequestBody @ApiParam(name = "UpdateUserDTO", value = "参数列表") UpdateUserDTO dto){

        try
        {
            String memberUuid=redisManager.get(token);
            if(StringUtils.isBlank(memberUuid)){
                return WebApiResponse.error(ResponseMsg.TOKEN_ERROR.getValue());
            }
            if(StringUtils.isBlank(dto.getPhone())){
                return WebApiResponse.error("手机号不能为空");
            }
            if(StringUtils.isBlank(dto.getCode())){
                return WebApiResponse.error("验证码不能为空");
            }
            if(StringUtils.isBlank(dto.getPassword())){
                return WebApiResponse.error("登录密码不能为空");
            }
            if (!RegularConstants.isPhone(dto.getPhone())) {
                return WebApiResponse.error("手机号码格式不正确！");
            }
            //获取缓存的验证码
            String key = CacheKeysConstants.PHONE_VERIFY_PREFIX + dto.getPhone();
            String code = redisManager.get(key);
            if (StringUtils.isBlank(code)) {
                return WebApiResponse.error("验证码已过期！");
            }
            if (!code.equalsIgnoreCase(dto.getCode())) {
                return WebApiResponse.error("验证码错误！");
            }
            //验证登录密码
            return memberService.verifyLoginPassword(memberUuid,dto.getPassword());
        }
        catch (Exception e)
        {
            LOG.error("verifyPhoneAndLoginPassword error:" + e.toString());
            return WebApiResponse.error(e.toString());
        }
    }
    @ApiOperation(value = "扫码登录成功，通知PC登录")
    @PostMapping("/pcQRcodeLogin")
    public WebApiResponse<?> pcQRcodeLogin(
            @RequestHeader(CommonUtils.REQUEST_HEADER_PARAMETER) String token,
            @RequestBody  @ApiParam(name = "messageDTO", value = "对象") WsMessageDTO messageDTO){
        try
        {
            if(StringUtils.isBlank(token)){
                return WebApiResponse.error(ResponseMsg.TOKEN_ERROR.getValue());
            }
            if(StringUtils.isBlank(messageDTO.getReceiveChannelId())){
                return WebApiResponse.error("参数receiveChannelId无效！");
            }
            //二维码登录授权
            messageDTO.setRequestCode(WsRequestCode.QRCODE_LOGIN_AUTH.getValue());
            messageDTO.setToken(token);
            /***发送通知****/
            //创建websocket连接
            wsclient.connect();
            //发送websocket消息
            wsclient.sendMessage(messageDTO);
            //关闭websocket连接
            wsclient.closeConnect();
            return WebApiResponse.success();
        }catch (Exception e){
            LOG.error("pcQRcodeLogin error:" + e.toString());
            return WebApiResponse.error(e.toString());
        }
    }

    @ApiOperation(value = "根据uuid获取会员的基础资料接口", notes = "根据uuid获取会员的基础资料接口")
    @PostMapping("/getMemberBaseInfo")
    public WebApiResponse getMemberBaseInfo(
            @RequestHeader(CommonUtils.REQUEST_HEADER_PARAMETER) String token,
            @RequestBody  @ApiParam(name = "memberBaseInfoReq", value = "对象") MemberBaseInfoReq memberBaseInfoReq)
    {
        try
        {

            String uuid=redisManager.get(token);
            if(StringUtils.isBlank(uuid)){
                return WebApiResponse.error(ResponseMsg.TOKEN_ERROR.getValue());
            }

            if (memberBaseInfoReq.getUuids() == null || memberBaseInfoReq.getUuids().isEmpty())
            {
                return WebApiResponse.error("uuid不能为空");
            }
            List<MemberBaseInfoDTO> myInfo = memberService.getMembersBaseInfo(memberBaseInfoReq);
            return WebApiResponse.success(myInfo);

        }catch (HystrixRuntimeException e){
            LOG.error("getMemberBaseInfo error",e);
            if (e.getFailureType()== HystrixRuntimeException.FailureType.TIMEOUT ) {
                return WebApiResponse.error(WebApiResponse.ResponseMsg.TIMEOUT.getValue());
            }
            return WebApiResponse.error(WebApiResponse.ResponseMsg.HYSTRIX_EXCEPTION.getValue());
        } catch (Exception e) {
            LOG.error("getMemberBaseInfo error",e);
            return WebApiResponse.error(ResponseMsg.EXCEPTION.getValue());
        }

    }
}
