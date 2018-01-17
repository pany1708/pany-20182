package com.kingthy.platform.controller.basedata;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.kingthy.platform.dto.basedata.CreateTechnicsCategoryReq;
import com.kingthy.platform.dto.basedata.QueryTechnicsCategoryReq;
import com.kingthy.platform.dto.basedata.UpdateTechnicsCategoryReq;
import com.kingthy.platform.entity.basedata.TechnicsCategory;
import com.kingthy.platform.response.WebApiResponse;
import com.kingthy.platform.service.basedata.TechnicsCategoryService;
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
 * TechnicsController(描述其作用)
 * <p>
 * 赵生辉 2017年07月28日 17:17
 *
 * @version 1.0.0
 */
@RestController
@RequestMapping(value = "technicsCategory")
public class TechnicsCategoryController
{
    @Autowired
    private TechnicsCategoryService technicsCategoryService;

    private static final Logger LOG = LoggerFactory.getLogger(TechnicsCategoryController.class);

    @ApiOperation(value = "创建一个工艺", notes = "创建一个工艺")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public WebApiResponse<?> createTechnicsCategory(
        @RequestBody @ApiParam(name = "createTechnicsCategoryReq") @Validated CreateTechnicsCategoryReq createTechnicsCategory,
        BindingResult bindingResult)
    {
        TechnicsCategory technicsCategory = JSONObject.parseObject(JSON.toJSONString(createTechnicsCategory),TechnicsCategory.class);
        if (bindingResult.hasErrors())
        {
            WebApiResponse.error(bindingResult.getFieldError().getDefaultMessage());
        }
        int result;
        try
        {
            result = technicsCategoryService.create(technicsCategory);
        }
        catch (Exception e)
        {
            LOG.error("technicsCategory/create" + e);
            return WebApiResponse.error(e.getMessage());
        }
        if (0 == result)
        {
            return WebApiResponse.error("创建工艺失败");
        }
        return WebApiResponse.success(result);
    }

    @ApiOperation(value = "查询工艺详情", notes = "查询工艺详情")
    @RequestMapping(value = "/{uuid}", method = RequestMethod.GET)
    public WebApiResponse<TechnicsCategory> findMaterial(@PathVariable String uuid)
    {
        TechnicsCategory technicsCategory;
        try
        {
            technicsCategory = technicsCategoryService.queryInfo(uuid);
        }
        catch (Exception e)
        {
            LOG.error("查询工艺详情出错，异常信息" + e);
            return WebApiResponse.error(WebApiResponse.ResponseMsg.EXCEPTION.getValue());
        }
        if (technicsCategory == null)
        {
            return WebApiResponse.error("没有找到该工艺");
        }
        return WebApiResponse.success(technicsCategory);
    }

    @ApiOperation(value = "分页查询工艺列表详情", notes = "查询工艺详情")
    @RequestMapping(value = "/page", method = RequestMethod.POST)
    public WebApiResponse<?> findMaterialPage(@RequestBody QueryTechnicsCategoryReq queryTechnicsCategoryReq)
    {
        TechnicsCategory technicsCategory = JSONObject.parseObject(JSON.toJSONString(queryTechnicsCategoryReq),TechnicsCategory.class);
        PageInfo<TechnicsCategory> result;
        try
        {
            result = technicsCategoryService.queryPage(queryTechnicsCategoryReq.getPageNum(),queryTechnicsCategoryReq.getPageSize(),technicsCategory);
        }
        catch (Exception e)
        {
            LOG.error("查询工艺列表详情出错，异常信息" + e);
            return WebApiResponse.error(WebApiResponse.ResponseMsg.EXCEPTION.getValue());
        }
        if (result == null)
        {
            return WebApiResponse.error(WebApiResponse.ResponseMsg.FAIL.getValue());
        }
        return WebApiResponse.success(result);
    }

    @ApiOperation(value = "查询工艺列表详情", notes = "查询工艺详情")
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public WebApiResponse<?> findAll()
    {
        List<TechnicsCategory> result;
        try
        {
            result = technicsCategoryService.queryAll();
        }
        catch (Exception e)
        {
            LOG.error("查询工艺列表详情出错，异常信息" + e);
            return WebApiResponse.error(WebApiResponse.ResponseMsg.EXCEPTION.getValue());
        }
        if (result == null)
        {
            return WebApiResponse.error(WebApiResponse.ResponseMsg.FAIL.getValue());
        }
        return WebApiResponse.success(result);
    }

    @ApiOperation(value = "删除工艺", notes = "根据UUID删除工艺")
    @RequestMapping(value = "delete/{materialUuid}", method = RequestMethod.DELETE)
    public WebApiResponse<?> deleteMaterial(@PathVariable String materialUuid)
    {
        int result;
        try
        {
            result = technicsCategoryService.delete(materialUuid);
        }
        catch (Exception e)
        {
            LOG.error("删除工艺出错，异常信息" + e);
            return WebApiResponse.error(WebApiResponse.ResponseMsg.EXCEPTION.getValue());
        }
        if (result > 0)
        {
            return WebApiResponse.success(result, WebApiResponse.ResponseMsg.SUCCESS.getValue());
        }
        else
        {
            return WebApiResponse.error(WebApiResponse.ResponseMsg.FAIL.getValue());
        }
    }

    @ApiOperation(value = "更新工艺", notes = "根据UUID更新工艺")
    @RequestMapping(value = "update", method = RequestMethod.PUT)
    public WebApiResponse<?> updateTechnicsCategory(
        @RequestBody @ApiParam(name = "createMaterial") @Validated UpdateTechnicsCategoryReq updateTechnicsCategoryReq,
        BindingResult bindingResult)
    {
        if (bindingResult.hasErrors())
        {
            return WebApiResponse.error(bindingResult.getFieldError().getDefaultMessage());
        }
        TechnicsCategory technicsCategory = JSONObject.parseObject(JSON.toJSONString(updateTechnicsCategoryReq),TechnicsCategory.class);
        int result;
        try
        {
            result = technicsCategoryService.update(technicsCategory);
        }
        catch (Exception e)
        {
            LOG.error("更新工艺出错，异常信息" + e);
            return WebApiResponse.error(WebApiResponse.ResponseMsg.EXCEPTION.getValue());
        }
        if (result > 0)
        {
            return WebApiResponse.success(result, WebApiResponse.ResponseMsg.SUCCESS.getValue());
        }
        else
        {
            return WebApiResponse.error(WebApiResponse.ResponseMsg.FAIL.getValue());
        }
    }
}
