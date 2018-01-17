package com.kingthy.platform.controller.basedata;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.kingthy.platform.dto.basedata.CreateBaseDataReq;
import com.kingthy.platform.dto.basedata.QueryBaseDataReq;
import com.kingthy.platform.dto.basedata.UpdateBaseDataReq;
import com.kingthy.platform.entity.basedata.SkirtCategory;
import com.kingthy.platform.response.WebApiResponse;
import com.kingthy.platform.service.basedata.SkirtCategoryService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * SkirtCategoryController(描述其作用)
 * <p>
 * 赵生辉 2017年07月10日 10:51
 *
 * @version 1.0.0
 */
@RestController
@RequestMapping("/skirCategory")
public class SkirtCategoryController
{
    private static final Logger LOG = LoggerFactory.getLogger(SkirtCategoryController.class);

    @Autowired
    private SkirtCategoryService skirtCategoryService;

    @ApiOperation(value = "添加半身裙参数参数接口", notes = "添加半身裙参数参数接口")
    @PostMapping("/create")
    public WebApiResponse<?> creataBaseData(
        @RequestBody @Validated @ApiParam(value = "createBaseDataReq",name="创建半身裙参数参数所需参数") CreateBaseDataReq createBaseDataReq)
    {
        SkirtCategory skirtCategory = JSONObject.parseObject(JSON.toJSONString(createBaseDataReq),SkirtCategory.class);
        int result = 0;
        try
        {
            result = skirtCategoryService.createSkirtCategory(skirtCategory);
        }
        catch (Exception e)
        {
            LOG.error("/skirtCategory/create"+e.toString());
            return WebApiResponse.error("创建数据失败");
        }
        if(result == 0)
        {
            return WebApiResponse.error("创建数据失败");
        }
        return WebApiResponse.success(result);
    }

    @ApiOperation(value = "分页查询半身裙参数参数接口", notes = "分页查询半身裙参数参数接口")
    @PostMapping("/query")
    public WebApiResponse<?> queryBaseData(
        @RequestBody @Validated @ApiParam(value = "queryBaseDataReq",name="查询半身裙参数参数所需参数") QueryBaseDataReq queryBaseDataReq)
    {
        SkirtCategory dressCategory = JSONObject.parseObject(JSON.toJSONString(queryBaseDataReq),SkirtCategory.class);
        PageInfo<?> result = null;
        try
        {
            result = skirtCategoryService.querySkirtCategory(queryBaseDataReq.getPageNum(),queryBaseDataReq.getPageSize(),dressCategory);
        }
        catch (Exception e)
        {
            LOG.error("/skirtCategory/query"+e.toString());
            return WebApiResponse.error("分页查询半身裙参数参数失败");
        }
        if(result == null)
        {
            return WebApiResponse.error("分页查询半身裙参数参数失败");
        }
        return WebApiResponse.success(result);
    }

    @ApiOperation(value = "查询半身裙参数参数详情接口", notes = "查询半身裙参数参数详情接口")
    @GetMapping("/query/{uuid}")
    public WebApiResponse<?> queryBaseDataInfo(@PathVariable(value = "uuid") String uuid)
    {
        SkirtCategory skirtCategory = null;
        try
        {
            skirtCategory = skirtCategoryService.querySkirtCategoryInfo(uuid);
        }
        catch (Exception e)
        {
            LOG.error("/coatCategory/queryInfo"+e.toString());
            return WebApiResponse.error("查询数据失败");
        }
        if(skirtCategory == null)
        {
            return WebApiResponse.error("查询数据失败");
        }
        return WebApiResponse.success(skirtCategory);
    }

    @ApiOperation(value = "修改半身裙参数参数接口", notes = "修改半身裙参数参数接口")
    @PutMapping("/update")
    public WebApiResponse<?> updateBaseData(
        @RequestBody @Validated @ApiParam(value = "queryBaseDataReq",name="查询半身裙参数参数所需参数") UpdateBaseDataReq updateBaseDataReq)
    {
        SkirtCategory skirtCategory = JSONObject.parseObject(JSON.toJSONString(updateBaseDataReq),SkirtCategory.class);
        int result = 0;
        try
        {
            result = skirtCategoryService.updateSkirtCategory(skirtCategory);
        }
        catch (Exception e)
        {
            LOG.error("/dressCategory/update"+e.toString());
            return WebApiResponse.error("更新数据失败");
        }
        if(result == 0)
        {
            return WebApiResponse.error("更新数据失败");
        }
        return WebApiResponse.success(result);
    }

    @ApiOperation(value = "删除半身裙参数参数接口", notes = "删除半身裙参数参数接口")
    @DeleteMapping("/delete/{uuid}")
    public WebApiResponse<?> deleteBaseData(@PathVariable(value = "uuid") String uuid)
    {
        int result = 0;
        try
        {
            result = skirtCategoryService.deleteSkirtCategory(uuid);
        }
        catch (Exception e)
        {
            LOG.error("/dressCategory/update"+e.toString());
            return WebApiResponse.error("删除数据失败");
        }
        if(result == 0)
        {
            return WebApiResponse.error("删除数据失败");
        }
        return WebApiResponse.success(result);
    }
}
