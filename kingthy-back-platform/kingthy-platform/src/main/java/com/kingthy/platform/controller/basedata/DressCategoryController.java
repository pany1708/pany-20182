package com.kingthy.platform.controller.basedata;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.kingthy.platform.dto.basedata.CreateBaseDataReq;
import com.kingthy.platform.dto.basedata.QueryBaseDataReq;
import com.kingthy.platform.dto.basedata.UpdateBaseDataReq;
import com.kingthy.platform.entity.basedata.DressCategory;
import com.kingthy.platform.response.WebApiResponse;
import com.kingthy.platform.service.basedata.DressCategoryService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * DressCategoryController(描述其作用)
 * <p>
 * 赵生辉 2017年07月10日 10:54
 *
 * @version 1.0.0
 */
@RestController
@RequestMapping("/dressCategory")
public class DressCategoryController
{
    private static final Logger LOG = LoggerFactory.getLogger(DressCategoryController.class);

    @Autowired
    private DressCategoryService dressCategoryService;

    @ApiOperation(value = "添加连衣裙参数接口", notes = "添加连衣裙参数接口")
    @PostMapping("/create")
    public WebApiResponse<?> creataBaseData(
        @RequestBody @Validated @ApiParam(value = "createBaseDataReq",name="创建连衣裙参数所需参数") CreateBaseDataReq createBaseDataReq)
    {
        DressCategory dressCategory = JSONObject.parseObject(JSON.toJSONString(createBaseDataReq),DressCategory.class);
        int result = 0;
        try
        {
            result = dressCategoryService.createDressCategory(dressCategory);
        }
        catch (Exception e)
        {
            LOG.error("/dressCategory/create"+e.toString());
            return WebApiResponse.error("创建连衣裙参数失败");
        }
        if(result == 0)
        {
            return WebApiResponse.error("创建连衣裙参数失败");
        }
        return WebApiResponse.success(result);
    }

    @ApiOperation(value = "分页查询连衣裙参数接口", notes = "分页查询连衣裙参数接口")
    @PostMapping("/query")
    public WebApiResponse<?> queryBaseData(
        @RequestBody @Validated @ApiParam(value = "queryBaseDataReq",name="查询连衣裙参数所需参数") QueryBaseDataReq queryBaseDataReq)
    {
        DressCategory dressCategory = JSONObject.parseObject(JSON.toJSONString(queryBaseDataReq),DressCategory.class);
        PageInfo<?> result = null;
        try
        {
            result = dressCategoryService.queryDressCategory(queryBaseDataReq.getPageNum(),queryBaseDataReq.getPageSize(),dressCategory);
        }
        catch (Exception e)
        {
            LOG.error("/dressCategory/query"+e.toString());
            return WebApiResponse.error("创建连衣裙参数失败");
        }
        if(result == null)
        {
            return WebApiResponse.error("创建连衣裙参数失败");
        }
        return WebApiResponse.success(result);
    }

    @ApiOperation(value = "查询连衣裙参数详情接口", notes = "查询连衣裙参数详情接口")
    @GetMapping("/query/{uuid}")
    public WebApiResponse<?> queryBaseDataInfo(@PathVariable(value = "uuid") String uuid)
    {
        DressCategory dressCategory = null;
        try
        {
            dressCategory = dressCategoryService.queryDressCategoryInfo(uuid);
        }
        catch (Exception e)
        {
            LOG.error("/dressCategory/queryInfo"+e.toString());
            return WebApiResponse.error("查询连衣裙参数失败");
        }
        if(dressCategory == null)
        {
            return WebApiResponse.error("查询连衣裙参数失败");
        }
        return WebApiResponse.success(dressCategory);
    }

    @ApiOperation(value = "修改连衣裙参数接口", notes = "修改连衣裙参数接口")
    @PutMapping("/update")
    public WebApiResponse<?> updateBaseData(
        @RequestBody @Validated @ApiParam(value = "queryBaseDataReq",name="查询连衣裙参数所需参数") UpdateBaseDataReq updateBaseDataReq)
    {
        DressCategory dressCategory = JSONObject.parseObject(JSON.toJSONString(updateBaseDataReq),DressCategory.class);
        int result = 0;
        try
        {
            result = dressCategoryService.updateDressCategory(dressCategory);
        }
        catch (Exception e)
        {
            LOG.error("/dressCategory/update"+e.toString());
            return WebApiResponse.error("更新连衣裙参数失败");
        }
        if(result == 0)
        {
            return WebApiResponse.error("更新连衣裙参数失败");
        }
        return WebApiResponse.success(result);
    }

    @ApiOperation(value = "删除连衣裙参数接口", notes = "删除连衣裙参数接口")
    @DeleteMapping("/delete/{uuid}")
    public WebApiResponse<?> deleteBaseData(@PathVariable(value = "uuid") String uuid)
    {
        int result = 0;
        try
        {
            result = dressCategoryService.deleteDressCategory(uuid);
        }
        catch (Exception e)
        {
            LOG.error("/dressCategory/update"+e.toString());
            return WebApiResponse.error("删除连衣裙参数失败");
        }
        if(result == 0)
        {
            return WebApiResponse.error("删除连衣裙参数失败");
        }
        return WebApiResponse.success(result);
    }
}
