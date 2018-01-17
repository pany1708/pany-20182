package com.kingthy.platform.controller.basedata;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.kingthy.platform.dto.basedata.CreateBaseDataReq;
import com.kingthy.platform.dto.basedata.QueryBaseDataReq;
import com.kingthy.platform.dto.basedata.UpdateBaseDataReq;
import com.kingthy.platform.entity.basedata.SuitCategory;
import com.kingthy.platform.response.WebApiResponse;
import com.kingthy.platform.service.basedata.SuitCategoryService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * SuitCategoryController(描述其作用)
 * <p>
 * 赵生辉 2017年07月10日 10:51
 *
 * @version 1.0.0
 */
@RestController
@RequestMapping("/suitCategory")
public class SuitCategoryController
{
    private static final Logger LOG = LoggerFactory.getLogger(SuitCategoryController.class);

    @Autowired
    private SuitCategoryService suitCategoryService;

    @ApiOperation(value = "添加套装参数接口", notes = "添加套装参数接口")
    @PostMapping("/create")
    public WebApiResponse<?> creataBaseData(
        @RequestBody @Validated @ApiParam(value = "createBaseDataReq",name="创建套装参数所需参数") CreateBaseDataReq createBaseDataReq)
    {
        SuitCategory suitCategory = JSONObject.parseObject(JSON.toJSONString(createBaseDataReq),SuitCategory.class);
        int result = 0;
        try
        {
            result = suitCategoryService.createSuitCategory(suitCategory);
        }
        catch (Exception e)
        {
            LOG.error("/dressCategory/create"+e.toString());
            return WebApiResponse.error("创建套装参数失败");
        }
        if(result == 0)
        {
            return WebApiResponse.error("创建套装参数失败");
        }
        return WebApiResponse.success(result);
    }

    @ApiOperation(value = "分页查询套装参数接口", notes = "分页查询套装参数接口")
    @PostMapping("/query")
    public WebApiResponse<?> queryBaseData(
        @RequestBody @Validated @ApiParam(value = "queryBaseDataReq",name="查询套装参数所需参数") QueryBaseDataReq queryBaseDataReq)
    {
        SuitCategory suitCategory = JSONObject.parseObject(JSON.toJSONString(queryBaseDataReq),SuitCategory.class);
        PageInfo<?> result = null;
        try
        {
            result = suitCategoryService.querySuitCategory(queryBaseDataReq.getPageNum(),queryBaseDataReq.getPageSize(),suitCategory);
        }
        catch (Exception e)
        {
            LOG.error("/dressCategory/query"+e.toString());
            return WebApiResponse.error("创建套装参数失败");
        }
        if(result == null)
        {
            return WebApiResponse.error("创建套装参数失败");
        }
        return WebApiResponse.success(result);
    }

    @ApiOperation(value = "查询套装参数详情接口", notes = "查询套装参数详情接口")
    @GetMapping("/query/{uuid}")
    public WebApiResponse<?> queryBaseDataInfo(@PathVariable(value = "uuid") String uuid)
    {
        SuitCategory suitCategory = null;
        try
        {
            suitCategory = suitCategoryService.querySuitCategoryInfo(uuid);
        }
        catch (Exception e)
        {
            LOG.error("/coatCategory/queryInfo"+e.toString());
            return WebApiResponse.error("查询套装参数失败");
        }
        if(suitCategory == null)
        {
            return WebApiResponse.error("查询套装参数失败");
        }
        return WebApiResponse.success(suitCategory);
    }

    @ApiOperation(value = "修改套装参数接口", notes = "修改套装参数接口")
    @PutMapping("/update")
    public WebApiResponse<?> updateBaseData(
        @RequestBody @Validated @ApiParam(value = "queryBaseDataReq",name="查询套装参数所需参数") UpdateBaseDataReq updateBaseDataReq)
    {
        SuitCategory suitCategory = JSONObject.parseObject(JSON.toJSONString(updateBaseDataReq),SuitCategory.class);
        int result = 0;
        try
        {
            result = suitCategoryService.updateSuitCategory(suitCategory);
        }
        catch (Exception e)
        {
            LOG.error("/dressCategory/update"+e.toString());
            return WebApiResponse.error("更新套装参数失败");
        }
        if(result == 0)
        {
            return WebApiResponse.error("更新套装参数失败");
        }
        return WebApiResponse.success(result);
    }

    @ApiOperation(value = "删除套装参数接口", notes = "删除套装参数接口")
    @DeleteMapping("/delete/{uuid}")
    public WebApiResponse<?> deleteBaseData(@PathVariable(value = "uuid") String uuid)
    {
        int result = 0;
        try
        {
            result = suitCategoryService.deleteSuitCategory(uuid);
        }
        catch (Exception e)
        {
            LOG.error("/suitCategory/update"+e.toString());
            return WebApiResponse.error("删除套装参数失败");
        }
        if(result == 0)
        {
            return WebApiResponse.error("删除套装参数失败");
        }
        return WebApiResponse.success(result);
    }
}
