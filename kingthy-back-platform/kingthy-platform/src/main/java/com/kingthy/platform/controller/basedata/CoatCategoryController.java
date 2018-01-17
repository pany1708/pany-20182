package com.kingthy.platform.controller.basedata;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.kingthy.platform.dto.basedata.CreateBaseDataReq;
import com.kingthy.platform.dto.basedata.QueryBaseDataReq;
import com.kingthy.platform.dto.basedata.UpdateBaseDataReq;
import com.kingthy.platform.entity.basedata.CoatCategory;
import com.kingthy.platform.response.WebApiResponse;
import com.kingthy.platform.service.basedata.CoatCategoryService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * CoatCategoryController(描述其作用)
 * <p>
 * 赵生辉 2017年07月05日 18:37
 *
 * @version 1.0.0
 */
@RestController
@RequestMapping("/coatCategory")
public class CoatCategoryController
{
    @Autowired
    private CoatCategoryService coatCategoryService;

    private static final Logger LOG = LoggerFactory.getLogger(CoatCategoryController.class);

    @ApiOperation(value = "添加上装参数接口", notes = "添加上装参数接口")
    @PostMapping("/create")
    public WebApiResponse<?> creataBaseData(
        @RequestBody @Validated @ApiParam(value = "createBaseDataReq",name="创建上装参数所需参数") CreateBaseDataReq createBaseDataReq)
    {
        CoatCategory coatCategory = JSONObject.parseObject(JSON.toJSONString(createBaseDataReq),CoatCategory.class);
        int result = 0;
        try
        {
            result = coatCategoryService.createCoatCategory(coatCategory);
        }
        catch (Exception e)
        {
            LOG.error("/coatCategory/create"+e.toString());
            return WebApiResponse.error("创建上装参数失败");
        }
        if(result == 0)
        {
            return WebApiResponse.error("创建上装参数失败");
        }
        return WebApiResponse.success(result);
    }

    @ApiOperation(value = "分页查询上装参数接口", notes = "分页查询上装参数接口")
    @PostMapping("/query")
    public WebApiResponse<?> queryBaseData(
        @RequestBody @Validated @ApiParam(value = "queryBaseDataReq",name="查询上装参数所需参数") QueryBaseDataReq queryBaseDataReq)
    {
        CoatCategory coatCategory = JSONObject.parseObject(JSON.toJSONString(queryBaseDataReq),CoatCategory.class);
        PageInfo<?> result = null;
        try
        {
            result = coatCategoryService.queryCoatCategory(queryBaseDataReq.getPageNum(),queryBaseDataReq.getPageSize(),coatCategory);
        }
        catch (Exception e)
        {
            LOG.error("/coatCategory/query"+e.toString());
            return WebApiResponse.error("创建上装参数失败");
        }
        if(result == null)
        {
            return WebApiResponse.error("创建上装参数失败");
        }
        return WebApiResponse.success(result);
    }

    @ApiOperation(value = "查询上装参数详情接口", notes = "查询上装参数详情接口")
    @GetMapping("/query/{uuid}")
    public WebApiResponse<?> queryBaseDataInfo(@PathVariable(value = "uuid") String uuid)
    {
        CoatCategory coatCategory = null;
        try
        {
            coatCategory = coatCategoryService.queryCoatCategoryInfo(uuid);
        }
        catch (Exception e)
        {
            LOG.error("/coatCategory/queryInfo"+e.toString());
            return WebApiResponse.error("查询上装参数失败");
        }
        if(coatCategory == null)
        {
            return WebApiResponse.error("查询上装参数失败");
        }
        return WebApiResponse.success(coatCategory);
    }

    @ApiOperation(value = "修改上装参数接口", notes = "修改上装参数接口")
    @PutMapping("/update")
    public WebApiResponse<?> updateBaseData(
        @RequestBody @Validated @ApiParam(value = "queryBaseDataReq",name="查询上装参数所需参数") UpdateBaseDataReq updateBaseDataReq)
    {
        CoatCategory coatCategory = JSONObject.parseObject(JSON.toJSONString(updateBaseDataReq),CoatCategory.class);
        int result = 0;
        try
        {
            result = coatCategoryService.updateCoatCategory(coatCategory);
        }
        catch (Exception e)
        {
            LOG.error("/coatCategory/update"+e.toString());
            return WebApiResponse.error("更新上装参数失败");
        }
        if(result == 0)
        {
            return WebApiResponse.error("更新上装参数失败");
        }
        return WebApiResponse.success(result);
    }

    @ApiOperation(value = "删除上装参数接口", notes = "删除上装参数接口")
    @DeleteMapping("/delete/{uuid}")
    public WebApiResponse<?> deleteBaseData(@PathVariable(value = "uuid") String uuid)
    {
        int result = 0;
        try
        {
            result = coatCategoryService.deleteCoatCategory(uuid);
        }
        catch (Exception e)
        {
            LOG.error("/coatCategory/update"+e.toString());
            return WebApiResponse.error("删除上装参数失败");
        }
        if(result == 0)
        {
            return WebApiResponse.error("删除上装参数失败");
        }
        return WebApiResponse.success(result);
    }
}
