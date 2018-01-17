/**
 * 系统项目名称
 * com.kingthy.platform.controller.member
 * MemberRankController.java
 * 
 * 2017年4月17日-下午8:04:05
 *  2017金昔科技有限公司-版权所有
 *
 */
package com.kingthy.platform.controller.member;

import com.kingthy.platform.dto.member.MemberRankReq;
import com.kingthy.platform.entity.member.MemberRank;
import com.kingthy.platform.response.WebApiResponse;
import com.kingthy.platform.service.member.MemberRankService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *
 * MemberRankController
 * 
 * yuanml 2017年4月17日 下午8:04:05
 * 
 * @version 1.0.0
 *
 */
@RestController
@RequestMapping("memberRank")
public class MemberRankController
{
    @Autowired
    private MemberRankService memberRankService;
    
    private static final Logger LOG = LoggerFactory.getLogger(MemberRankController.class);
    
    @ApiOperation(value = "查询等级列表", notes = "查询等级列表")
    @RequestMapping(value = "findMemberRank", method = RequestMethod.GET)
    public WebApiResponse<?> findMemberRank()
    {
        List<MemberRank> memberRanks;
        try
        {
            memberRanks = memberRankService.findMemberRank();
        }
        catch (Exception e)
        {
            LOG.error("查询等级列表出错，异常信息" + e);
            return WebApiResponse.error(WebApiResponse.ResponseMsg.EXCEPTION.getValue());
        }
        if (null != memberRanks && memberRanks.size() > 0)
        {
            return WebApiResponse.success(memberRanks);
        }
        else
        {
            return WebApiResponse.error(WebApiResponse.ResponseMsg.FAIL.getValue());
        }
    }
    
    @ApiOperation(value = "查询单个等级列表", notes = "查询单个等级列表")
    @RequestMapping(value = "findMemberRankOne/{memberRankUuid}", method = RequestMethod.GET)
    public WebApiResponse<?> findMemberRankOne(@PathVariable String memberRankUuid)
    {
        if (null != memberRankUuid && !memberRankUuid.isEmpty())
        {
            MemberRank memberRank;
            try
            {
                memberRank = memberRankService.findOneMemberRank(memberRankUuid);
            }
            catch (Exception e)
            {
                LOG.error("查询单个等级列表出错，异常信息" + e);
                return WebApiResponse.error(WebApiResponse.ResponseMsg.EXCEPTION.getValue());
            }
            if (null != memberRank)
            {
                return WebApiResponse.success(memberRank);
            }
            else
            {
                return WebApiResponse.error(WebApiResponse.ResponseMsg.FAIL.getValue());
            }
        }
        else
        {
            return WebApiResponse.error(WebApiResponse.ResponseMsg.PARAMETER_ERROR.getValue());
        }
    }
    
    @ApiOperation(value = "增加等级列表", notes = "增加等级列表")
    @RequestMapping(value = "addMemberRank", method = RequestMethod.PUT)
    public WebApiResponse<?> addMemberRank(
        @RequestBody @ApiParam(name = "memberRankReq", value = "增加等级列表参数") @Validated MemberRankReq memberRankReq,
        BindingResult bindingResult)
    {
        if (null != memberRankReq)
        {
            
            if (bindingResult.hasErrors())
            {
                return WebApiResponse.error(bindingResult.getFieldError().getDefaultMessage());
            }
            int result;
            try
            {
                result = memberRankService.addMemberRank(memberRankReq);
            }
            catch (Exception e)
            {
                LOG.error("增加等级列表出错，异常信息" + e);
                return WebApiResponse.error(WebApiResponse.ResponseMsg.EXCEPTION.getValue());
            }
            if (result > 0)
            {
                return WebApiResponse.success(null, WebApiResponse.ResponseMsg.SUCCESS.getValue());
            }
            else
            {
                return WebApiResponse.error(WebApiResponse.ResponseMsg.FAIL.getValue());
            }
        }
        else
        {
            return WebApiResponse.error(WebApiResponse.ResponseMsg.PARAMETER_ERROR.getValue());
        }
    }
    
    @ApiOperation(value = "修改等级列表", notes = "修改等级列表")
    @RequestMapping(value = "editMemberRank", method = RequestMethod.POST)
    public WebApiResponse<?> editMemberRank(
        @RequestBody @ApiParam(name = "memberRankReq", value = "修改等级列表参数") @Validated MemberRankReq memberRankReq,
        BindingResult bindingResult)
    {
        if (null != memberRankReq)
        {
            
            if (bindingResult.hasErrors())
            {
                return WebApiResponse.error(bindingResult.getFieldError().getDefaultMessage());
            }
            int result;
            try
            {
                result = memberRankService.editMemberRank(memberRankReq);
            }
            catch (Exception e)
            {
                LOG.error("修改等级列表出错，异常信息" + e);
                return WebApiResponse.error(WebApiResponse.ResponseMsg.EXCEPTION.getValue());
            }
            if (result > 0)
            {
                return WebApiResponse.success(null, WebApiResponse.ResponseMsg.SUCCESS.getValue());
            }
            else
            {
                return WebApiResponse.error(WebApiResponse.ResponseMsg.FAIL.getValue());
            }
        }
        else
        {
            return WebApiResponse.error(WebApiResponse.ResponseMsg.PARAMETER_ERROR.getValue());
        }
    }
    
    @ApiOperation(value = "删除等级列表", notes = "删除等级列表")
    @RequestMapping(value = "deleteMemberRank/{memberRankUuid}", method = RequestMethod.DELETE)
    public WebApiResponse<?> deleteMemberRank(@PathVariable String memberRankUuid)
    {
        if (null != memberRankUuid && !memberRankUuid.isEmpty())
        {
            int result;
            try
            {
                result = memberRankService.deleteMemberRank(memberRankUuid);
            }
            catch (Exception e)
            {
                LOG.error("删除等级列表出错，异常信息" + e);
                return WebApiResponse.error(WebApiResponse.ResponseMsg.EXCEPTION.getValue());
            }
            if (result > 0)
            {
                return WebApiResponse.success(null, WebApiResponse.ResponseMsg.SUCCESS.getValue());
            }
            else
            {
                return WebApiResponse.error(WebApiResponse.ResponseMsg.FAIL.getValue());
            }
        }
        else
        {
            return WebApiResponse.error(WebApiResponse.ResponseMsg.PARAMETER_ERROR.getValue());
        }
    }
}
