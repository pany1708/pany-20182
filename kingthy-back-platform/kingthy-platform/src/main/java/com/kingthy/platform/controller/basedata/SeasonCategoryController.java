/**
 * 系统项目名称
 * com.kingthy.platform.controller.basedata
 * SeasonController.java
 * 
 * 2017年3月29日-下午4:34:25
 *  2017金昔科技有限公司-版权所有
 *
 */
package com.kingthy.platform.controller.basedata;

import com.alibaba.fastjson.JSON;
import com.kingthy.platform.dto.basedata.MaterielSeasonReq;
import com.kingthy.platform.dto.basedata.SeasonCategoryDto;
import com.kingthy.platform.entity.basedata.SeasonCategory;
import com.kingthy.platform.response.WebApiResponse;
import com.kingthy.platform.service.basedata.SeasonCategoryService;
import com.mysql.jdbc.StringUtils;
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
 * SeasonController
 * 
 * yuanml 2017年3月29日 下午4:34:25
 * 
 * @version 1.0.0
 *
 */
@RestController
@RequestMapping("seasonCategory")
public class SeasonCategoryController
{
    @Autowired
    private SeasonCategoryService seasonCategoryService;
    
    private static final Logger LOG = LoggerFactory.getLogger(SeasonCategoryController.class);
    
    /**
     * 顶级根节点
     */
    private static final String ROOTNODE = "0";
    
    /**
     * 当前节点不是末节点 返回结果
     */
    private static final int NOLEAFNODE = -2;
    
    /**
     * 转移到节点为子节点 返回结果
     */
    private static final int ISCHILENODE = -3;
    
    @ApiOperation(value = "新增季节分类", notes = "基础数据季节分类增加")
    @RequestMapping(value = "/createSeasonCategory", method = RequestMethod.PUT)
    public WebApiResponse<?> createSeasonCategory(
        @RequestBody @ApiParam(name = "materielSeasonReq", value = "新增季节分类", required = true) @Validated MaterielSeasonReq materielSeasonReq,
        BindingResult bindingResult)
        throws Exception
    {
        if (null != materielSeasonReq && !materielSeasonReq.getClassName().isEmpty())
        {
            if (bindingResult.hasErrors())
            {
                return WebApiResponse.error(bindingResult.getFieldError().getDefaultMessage());
            }
            int result;
            try
            {
                result = seasonCategoryService.createSeasonCategory(materielSeasonReq);
            }
            catch (Exception e)
            {
                LOG.error("新增季节分类出错，异常信息" + e);
                return WebApiResponse.error(WebApiResponse.ResponseMsg.EXCEPTION.getValue());
            }
            
            if (result > 0)
            {
                return WebApiResponse.success(WebApiResponse.ResponseMsg.SUCCESS.getValue());
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
    
    @ApiOperation(value = "查找目录季节分类", notes = "基础数据季节分类查找")
    @RequestMapping(value = "/findSeasonCategoryByParentId/{parentId}", method = RequestMethod.GET)
    public WebApiResponse<?> findSeasonCategory(@PathVariable String parentId)
        throws Exception
    {
        if (null != parentId && !parentId.isEmpty())
        {
            List<SeasonCategory> seasonCategories;
            try
            {
                seasonCategories = seasonCategoryService.findSeasonCategoryByParentId(parentId);
            }
            catch (Exception e)
            {
                LOG.error("查找目录季节分类出错，异常信息" + e);
                return WebApiResponse.error(WebApiResponse.ResponseMsg.EXCEPTION.getValue());
            }
            if (null != seasonCategories && seasonCategories.size() >= 0)
            {
                return WebApiResponse.success(seasonCategories);
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
    
    /*@ApiOperation(value = "编辑季节分类", notes = "点击编辑季节分类")
    @RequestMapping(value = "/updateSeasonCategory", method = RequestMethod.POST)
    public WebApiResponse<?> updateSeasonCategoryByUuid(
        @RequestBody @ApiParam(name = "seasonCategory", value = "编辑分类信息") @Validated MaterielSeasonReq materielSeasonReq,
        BindingResult bindingResult)
    {
        if (null != materielSeasonReq)
        {
            if (bindingResult.hasErrors())
            {
                return WebApiResponse.error(bindingResult.getFieldError().getDefaultMessage());
            }
            if (null != materielSeasonReq.getParentId() && !ROOTNODE.equals(materielSeasonReq.getParentId()))
            {
                if (seasonCategoryService.findSeasonCategoryByParentId(materielSeasonReq.getUuid()).size() > 0)
                {
                    return WebApiResponse.error("分类等级不能超过两级");
                }
            }
            int result;
            try
            {
                result = seasonCategoryService.updateSeasonCategoryByUuid(materielSeasonReq);
            }
            catch (Exception e)
            {
                LOG.error("编辑季节分类出错，异常信息" + e);
                return WebApiResponse.error(WebApiResponse.ResponseMsg.EXCEPTION.getValue());
            }
            return MaterielCategoryController.getWebApiResponse(result);
        }
        else
        {
            return WebApiResponse.error(WebApiResponse.ResponseMsg.PARAMETER_ERROR.getValue());
        }
    }*/
    
    @ApiOperation(value = "显示隐藏季节分类", notes = "点击显示隐藏")
    @RequestMapping(value = "/updateStatus/{seasonCategoryUuid}/{seasonCategoryStatus}", method = RequestMethod.POST)
    public WebApiResponse<?> updateSeasonCategoryStatus(@PathVariable String seasonCategoryUuid,
        @PathVariable String seasonCategoryStatus)
    {
        if (null != seasonCategoryUuid && !seasonCategoryUuid.isEmpty())
        {
            int result;
            try
            {
                result =
                    seasonCategoryService.updateSeasonCategoryStatusByUuid(seasonCategoryUuid, seasonCategoryStatus);
            }
            catch (Exception e)
            {
                LOG.error("显示隐藏季节分类出错，异常信息" + e);
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
    
    @ApiOperation(value = "移除季节分类", notes = "点击移除季节分类")
    @RequestMapping(value = "/deleteSeasonCategory/{seasonCategoryUuid}", method = RequestMethod.DELETE)
    public WebApiResponse<?> deleteSeasonCategory(@PathVariable String seasonCategoryUuid)
    {
        if (null != seasonCategoryUuid && !seasonCategoryUuid.isEmpty())
        {
            int result;
            try
            {
                result = seasonCategoryService.deleteSeasonCategory(seasonCategoryUuid);
            }
            catch (Exception e)
            {
                LOG.error("移除季节分类出错，异常信息" + e);
                return WebApiResponse.error(WebApiResponse.ResponseMsg.EXCEPTION.getValue());
            }
            if (result > 0)
            {
                return WebApiResponse.success(WebApiResponse.ResponseMsg.SUCCESS.getValue());
            }
            else if (result == NOLEAFNODE)
            {
                return WebApiResponse.error("移除季节分类失败,该分类下存在下级分类");
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
    
    @ApiOperation(value = "转移季节分类", notes = "转移季节分类操作")
    @RequestMapping(value = "/transferSeasonCategory/{seasonCategoryUuidOld}/{seasonCategoryUuidNew}/{sourceGrade}/{targetGrade}", method = RequestMethod.POST)
    public WebApiResponse<?> transferSeasonCategory(@PathVariable String seasonCategoryUuidOld,
        @PathVariable String seasonCategoryUuidNew, @PathVariable String sourceGrade, @PathVariable String targetGrade)
    {
        if (null != seasonCategoryUuidOld && !seasonCategoryUuidOld.isEmpty() && null != seasonCategoryUuidNew
            && !seasonCategoryUuidNew.isEmpty() && null != sourceGrade && null != targetGrade)
        {
            int result;
            try
            {
                result = seasonCategoryService.transferSeasonCategory(seasonCategoryUuidOld,
                    seasonCategoryUuidNew,
                    sourceGrade,
                    targetGrade);
            }
            catch (Exception e)
            {
                LOG.error("转移季节分类出错，异常信息" + e);
                return WebApiResponse.error(WebApiResponse.ResponseMsg.EXCEPTION.getValue());
            }
            if (result >= 0)
            {
                return WebApiResponse.success(null, WebApiResponse.ResponseMsg.SUCCESS.getValue());
            }
            else if (ISCHILENODE == result)
            {
                return WebApiResponse.error("禁止将节点转移到自己子节点下面");
            }
            else
                return WebApiResponse.error(WebApiResponse.ResponseMsg.FAIL.getValue());
        }
        else
        {
            return WebApiResponse.error(WebApiResponse.ResponseMsg.PARAMETER_ERROR.getValue());
        }
    }
    
    @ApiOperation(value = "查询季节分类详情", notes = "点击编辑时查询记录详情")
    @RequestMapping(value = "/findSeasonCategoryByUuid/{seasonCategoryUuid}", method = RequestMethod.GET)
    public WebApiResponse<?> findSeasonCategoryByUuid(@PathVariable String seasonCategoryUuid)
    {
        if (null != seasonCategoryUuid && !seasonCategoryUuid.isEmpty())
        {
            SeasonCategory seasonCategory;
            try
            {
                seasonCategory = seasonCategoryService.findSeasonCategoryByUuid(seasonCategoryUuid);
            }
            catch (Exception e)
            {
                LOG.error("查询季节分类详情出错，异常信息" + e);
                return WebApiResponse.error(WebApiResponse.ResponseMsg.EXCEPTION.getValue());
            }
            if (null != seasonCategory)
            {
                return WebApiResponse.success(seasonCategory);
            }
            else
            {
                return WebApiResponse.error(WebApiResponse.ResponseMsg.FAIL.getValue());
            }
        }
        return WebApiResponse.error(WebApiResponse.ResponseMsg.PARAMETER_ERROR.getValue());
    }
    
    @ApiOperation(value = "查询所有季节分类", notes = "查询结果以展示层级分类")
    @RequestMapping(value = "/findAllSeasonCategoryTree", method = RequestMethod.GET)
    public WebApiResponse<?> findAllSeasonCategoryTree()
    {
        List<SeasonCategoryDto> seasonCategoryDtoes;
        try
        {
            seasonCategoryDtoes = seasonCategoryService.findAllSeasoncategoryTree();
        }
        catch (Exception e)
        {
            LOG.error("查询所有季节分类出错，异常信息" + e);
            return WebApiResponse.error(WebApiResponse.ResponseMsg.EXCEPTION.getValue());
        }
        if (0 <= seasonCategoryDtoes.size())
        {
            return WebApiResponse.success(JSON.parse(seasonCategoryDtoes.toString()));
        }
        else
        {
            return WebApiResponse.error(WebApiResponse.ResponseMsg.FAIL.getValue());
        }
    }
    
    @ApiOperation(value = "查询所有顶级季节分类", notes = "查询所有顶级季节分类")
    @RequestMapping(value = "/findAllSeasonCategoryTop", method = RequestMethod.GET)
    public WebApiResponse<?> findAllSeasonCategoryTop()
    {
        List<SeasonCategory> seasonCategories;
        try
        {
            seasonCategories = seasonCategoryService.findAllSeasoncategoryTop();
        }
        catch (Exception e)
        {
            LOG.error("查询所有顶级季节分类出错，异常信息" + e);
            return WebApiResponse.error(WebApiResponse.ResponseMsg.EXCEPTION.getValue());
        }
        if (0 <= seasonCategories.size())
        {
            return WebApiResponse.success(seasonCategories);
        }
        else
        {
            return WebApiResponse.error(WebApiResponse.ResponseMsg.FAIL.getValue());
        }
    }
    
    @ApiOperation(value = "验证分类名是否重复")
    @RequestMapping(value = "/validateSeasonCategoryName/{seasonCategoryName}", method = RequestMethod.GET)
    public WebApiResponse<?> validateSeasonCategoryName(@PathVariable String seasonCategoryName)
    {
        if (StringUtils.isEmptyOrWhitespaceOnly(seasonCategoryName))
        {
            return WebApiResponse.error("分类名不能为空");
        }
        else
        {
            Boolean isRepeat;
            try
            {
                isRepeat = seasonCategoryService.findSeasonCategoryName(seasonCategoryName);
            }
            catch (Exception e)
            {
                LOG.error("验证分类名是否重复出错，异常信息" + e);
                return WebApiResponse.error(WebApiResponse.ResponseMsg.EXCEPTION.getValue());
            }
            if (isRepeat)
            {
                return WebApiResponse.error("已存在相同名称");
            }
            else
            {
                return WebApiResponse.success(null, WebApiResponse.ResponseMsg.SUCCESS.getValue());
            }
        }
    }
}
