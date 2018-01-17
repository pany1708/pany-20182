package com.kingthy.platform.controller.basedata;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.kingthy.platform.dto.basedata.CreateBaseDataReq;
import com.kingthy.platform.dto.basedata.QueryBaseDataReq;
import com.kingthy.platform.dto.basedata.UpdateBaseDataReq;
import com.kingthy.platform.entity.basedata.PantsCategory;
import com.kingthy.platform.response.WebApiResponse;
import com.kingthy.platform.service.basedata.PantsCategoryService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * PantsController(描述其作用)
 * <p>
 * 赵生辉 2017年07月10日 9:49
 *
 * @version 1.0.0
 */
@RestController
@RequestMapping("/pantsCategory")
public class PantsCategoryController
{
    private static final Logger LOG = LoggerFactory.getLogger(PantsCategoryController.class);

    @Autowired
    private PantsCategoryService pantsCategoryService;

    @ApiOperation(value = "添加裤装参数接口", notes = "添加裤装参数接口")
    @PostMapping("/create")
    public WebApiResponse<?> creataBaseData(
        @RequestBody @Validated @ApiParam(value = "createBaseDataReq",name="创建裤装参数所需参数") CreateBaseDataReq createBaseDataReq)
    {
        PantsCategory pantsCategory = JSONObject.parseObject(JSON.toJSONString(createBaseDataReq),PantsCategory.class);
        int result = 0;
        try
        {
            result = pantsCategoryService.createPantsCategory(pantsCategory);
        }
        catch (Exception e)
        {
            LOG.error("/dressCategory/create"+e.toString());
            return WebApiResponse.error("创建裤装参数失败");
        }
        if(result == 0)
        {
            return WebApiResponse.error("创建裤装参数失败");
        }
        return WebApiResponse.success(result);
    }

    @ApiOperation(value = "分页查询裤装参数接口", notes = "分页查询裤装参数接口")
    @PostMapping("/query")
    public WebApiResponse<?> queryBaseData(
        @RequestBody @Validated @ApiParam(value = "queryBaseDataReq",name="查询裤装参数所需参数") QueryBaseDataReq queryBaseDataReq)
    {
        PantsCategory pantsCategory = JSONObject.parseObject(JSON.toJSONString(queryBaseDataReq),PantsCategory.class);
        PageInfo<?> result = null;
        try
        {
            result = pantsCategoryService.queryPantsCategory(queryBaseDataReq.getPageNum(),queryBaseDataReq.getPageSize(),pantsCategory);
        }
        catch (Exception e)
        {
            LOG.error("/dressCategory/query"+e.toString());
            return WebApiResponse.error("创建裤装参数失败");
        }
        if(result == null)
        {
            return WebApiResponse.error("创建裤装参数失败");
        }
        return WebApiResponse.success(result);
    }

    @ApiOperation(value = "查询裤装参数详情接口", notes = "查询裤装参数详情接口")
    @GetMapping("/query/{uuid}")
    public WebApiResponse<?> queryBaseDataInfo(@PathVariable(value = "uuid") String uuid)
    {
        PantsCategory pantsCategory = null;
        try
        {
            pantsCategory = pantsCategoryService.queryPantsCategoryInfo(uuid);
        }
        catch (Exception e)
        {
            LOG.error("/pantsCategory/queryInfo"+e.toString());
            return WebApiResponse.error("查询裤装参数失败");
        }
        if(pantsCategory == null)
        {
            return WebApiResponse.error("查询裤装参数失败");
        }
        return WebApiResponse.success(pantsCategory);
    }

    @ApiOperation(value = "修改裤装参数接口", notes = "修改裤装参数接口")
    @PutMapping("/update")
    public WebApiResponse<?> updateBaseData(
        @RequestBody @Validated @ApiParam(value = "queryBaseDataReq",name="查询裤装参数所需参数") UpdateBaseDataReq updateBaseDataReq)
    {
        PantsCategory pantsCategory = JSONObject.parseObject(JSON.toJSONString(updateBaseDataReq),PantsCategory.class);
        int result = 0;
        try
        {
            result = pantsCategoryService.updatePantsCategory(pantsCategory);
        }
        catch (Exception e)
        {
            LOG.error("/pantsCategory/update"+e.toString());
            return WebApiResponse.error("更新裤装参数失败");
        }
        if(result == 0)
        {
            return WebApiResponse.error("更新裤装参数失败");
        }
        return WebApiResponse.success(result);
    }

    @ApiOperation(value = "删除裤装参数接口", notes = "删除裤装参数接口")
    @DeleteMapping("/delete/{uuid}")
    public WebApiResponse<?> deleteBaseData(@PathVariable(value = "uuid") String uuid)
    {
        int result = 0;
        try
        {
            result = pantsCategoryService.deletePantsCategory(uuid);
        }
        catch (Exception e)
        {
            LOG.error("/pantsCategory/update"+e.toString());
            return WebApiResponse.error("删除裤装参数失败");
        }
        if(result == 0)
        {
            return WebApiResponse.error("删除裤装参数失败");
        }
        return WebApiResponse.success(result);
    }
}
