package com.kingthy.platform.controller.basedata;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.kingthy.platform.dto.basedata.CreateBaseDataReq;
import com.kingthy.platform.dto.basedata.QueryBaseDataReq;
import com.kingthy.platform.dto.basedata.UpdateBaseDataReq;
import com.kingthy.platform.entity.basedata.BaseData;
import com.kingthy.platform.response.WebApiResponse;
import com.kingthy.platform.service.basedata.BaseDataService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * BaseDataController(描述其作用)
 * <p>
 * 赵生辉 2017年07月05日 10:26
 *
 * @version 1.0.0
 */
@RestController
@RequestMapping(value = "/baseData")
public class BaseDataController
{
    @Autowired
    private BaseDataService baseDataService;

    private static final Logger LOG = LoggerFactory.getLogger(BaseDataController.class);

    @ApiOperation(value = "添加基础数据接口", notes = "根据父节点uuid添加分类")
    @PostMapping("/create")
    public WebApiResponse<?> creataBaseData(
        @RequestBody @Validated @ApiParam(value = "createBaseDataReq",name="创建基础数据所需参数") CreateBaseDataReq createBaseDataReq)
    {
        BaseData baseData = JSONObject.parseObject(JSON.toJSONString(createBaseDataReq),BaseData.class);
        int result = 0;
        try
        {
            result = baseDataService.createBaseData(baseData);
        }
        catch (Exception e)
        {
            LOG.error("/baseData/create"+e.toString());
            return WebApiResponse.error(e.getMessage().toString());
        }
        if(result == 0)
        {
            return WebApiResponse.error("创建数据失败");
        }
        return WebApiResponse.success(result);
    }

    @ApiOperation(value = "分页查询基础数据接口", notes = "分页查询基础数据接口")
    @PostMapping("/query")
    public WebApiResponse<?> queryBaseData(
        @RequestBody @Validated @ApiParam(value = "queryBaseDataReq",name="查询基础数据所需参数") QueryBaseDataReq queryBaseDataReq)
    {
        BaseData baseData = JSONObject.parseObject(JSON.toJSONString(queryBaseDataReq),BaseData.class);
        PageInfo<?> result = null;
        try
        {
            result = baseDataService.queryBaseData(queryBaseDataReq.getPageNum(),queryBaseDataReq.getPageSize(),baseData);
        }
        catch (Exception e)
        {
            LOG.error("/baseData/query"+e.toString());
            return WebApiResponse.error(e.getMessage().toString());
        }
        if(result == null)
        {
            return WebApiResponse.error("创建数据失败");
        }
        return WebApiResponse.success(result);
    }

    @ApiOperation(value = "查询基础数据详情接口", notes = "根据父节点uuid添加分类")
    @GetMapping("/query/{uuid}")
    public WebApiResponse<?> queryBaseDataInfo(@PathVariable(value = "uuid") String uuid)
    {
        BaseData baseData = null;
        try
        {
            baseData = baseDataService.queryBaseDataInfo(uuid);
        }
        catch (Exception e)
        {
            LOG.error("/baseData/queryInfo"+e.toString());
            return WebApiResponse.error(e.getMessage().toString());
        }
        if(baseData == null)
        {
            return WebApiResponse.error("查询数据失败");
        }
        return WebApiResponse.success(baseData);
    }

    @ApiOperation(value = "修改基础数据接口", notes = "根据父节点uuid添加分类")
    @PutMapping("/update")
    public WebApiResponse<?> updateBaseData(
        @RequestBody @Validated @ApiParam(value = "queryBaseDataReq",name="查询基础数据所需参数") UpdateBaseDataReq updateBaseDataReq)
    {
        BaseData baseData = JSONObject.parseObject(JSON.toJSONString(updateBaseDataReq),BaseData.class);
        int result = 0;
        try
        {
            result = baseDataService.updateBaseData(baseData);
        }
        catch (Exception e)
        {
            LOG.error("/baseData/update"+e.toString());
            return WebApiResponse.error(e.getMessage().toString());
        }
        if(result == 0)
        {
            return WebApiResponse.error("更新数据失败");
        }
        return WebApiResponse.success(result);
    }

    @ApiOperation(value = "删除基础数据接口", notes = "根据父节点uuid添加分类")
    @DeleteMapping("/delete/{uuid}")
    public WebApiResponse<?> deleteBaseData(@PathVariable(value = "uuid") String uuid)
    {
        int result = 0;
        try
        {
            result = baseDataService.deleteBaseData(uuid);
        }
        catch (Exception e)
        {
            LOG.error("/baseData/update"+e.toString());
            return WebApiResponse.error(e.getMessage().toString());
        }
        if(result == 0)
        {
            return WebApiResponse.error("删除数据失败");
        }
        return WebApiResponse.success(result);
    }
}
