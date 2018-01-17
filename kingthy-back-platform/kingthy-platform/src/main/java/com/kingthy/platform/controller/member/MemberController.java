package com.kingthy.platform.controller.member;

import com.github.pagehelper.PageInfo;
import com.github.pagehelper.StringUtil;
import com.kingthy.platform.dto.member.MemberDto;
import com.kingthy.platform.dto.member.MemberPageReq;
import com.kingthy.platform.dto.member.MemberUuidArrayReq;
import com.kingthy.platform.entity.member.Member;
import com.kingthy.platform.response.WebApiResponse;
import com.kingthy.platform.service.member.MemberService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 
 *
 * MemberController(会员控制层)
 * 
 * 陈钊 2017年4月14日 下午3:50:27
 * 
 * @version 1.0.0
 *
 */
@RestController("/member")
public class MemberController
{
    private static final Logger LOG = LoggerFactory.getLogger(MemberController.class);
    
    @Autowired
    private MemberService memberService;
    
    @ApiOperation(value = "分页查询用户信息")
    @RequestMapping(value = "/memberList", method = RequestMethod.POST)
    public WebApiResponse<?> findByPage(
        @RequestBody @ApiParam(name = "memberPageReq", value = "查询条件", required = true) MemberPageReq memberPageReq)
    {
        if (null != memberPageReq)
        {
            PageInfo<Member> memberPage;
            try
            {
                memberPage = memberService.findMemberByPage(memberPageReq);
            }
            catch (Exception e)
            {
                LOG.error("分页查询用户信息出错，异常信息" + e);
                return WebApiResponse.error(WebApiResponse.ResponseMsg.EXCEPTION.getValue());
            }
            if (memberPage != null)
            {
                return WebApiResponse.success(memberPage);
            }
            
            return WebApiResponse.error(WebApiResponse.ResponseMsg.FAIL.getValue());
        }
        else
        {
            return WebApiResponse.error("请求参数为空");
        }
    }
    
    @ApiOperation(value = "冻结或解冻用户")
    @RequestMapping(value = "/lockOrUnlock", method = RequestMethod.PUT)
    public WebApiResponse<String> lockOrUnlock(
        @RequestBody @ApiParam(name = "uuidArrayReq", value = "用户uuid数组", required = true) @Validated MemberUuidArrayReq uuidArrayReq,
        BindingResult bindingResult)
    {
        if (bindingResult.hasErrors())
        {
            return WebApiResponse.error(bindingResult.getFieldError().getDefaultMessage());
        }
        int result;
        try
        {
            result = memberService.changeIsLockedBatch(uuidArrayReq);
        }
        catch (Exception e)
        {
            LOG.error("冻结解冻用户出错，异常信息" + e);
            return WebApiResponse.error(WebApiResponse.ResponseMsg.EXCEPTION.getValue());
        }
        if (result > 0)
        {
            return WebApiResponse.success(null, WebApiResponse.ResponseMsg.SUCCESS.getValue());
        }
        
        return WebApiResponse.error(WebApiResponse.ResponseMsg.FAIL.getValue());
    }
    
    @ApiOperation(value = "根据uuid查询用户基本信息")
    @RequestMapping(value = "/getInfo/{uuid}", method = RequestMethod.GET)
    public WebApiResponse<Member> findByUuid(
        @PathVariable @ApiParam(name = "uuid", value = "用户uuid", required = true) String uuid)
    {
        if (StringUtil.isEmpty(uuid))
        {
            return WebApiResponse.error("商品uuid不能为空");
        }
        Member member;
        try
        {
            member = memberService.findByUuid(uuid);
        }
        catch (Exception e)
        {
            LOG.error("查询用户基本信息出错，异常信息" + e);
            return WebApiResponse.error(WebApiResponse.ResponseMsg.EXCEPTION.getValue());
        }
        if (member != null)
        {
            return WebApiResponse.success(member);
        }
        return WebApiResponse.error(WebApiResponse.ResponseMsg.FAIL.getValue());
    }
}
