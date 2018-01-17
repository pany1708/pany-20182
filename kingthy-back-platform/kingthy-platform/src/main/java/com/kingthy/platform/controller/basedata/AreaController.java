package com.kingthy.platform.controller.basedata;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.kingthy.platform.dto.basedata.AreaDto;
import com.kingthy.platform.dto.basedata.QueryAllAreaReq;
import com.kingthy.platform.dto.basedata.QueryAreaReq;
import com.kingthy.platform.dto.basedata.QueryParentAreaReq;
import com.kingthy.platform.entity.basedata.Area;
import com.kingthy.platform.response.WebApiResponse;
import com.kingthy.platform.service.basedata.AreaService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * AreaController(描述其作用)
 * <p>
 * 赵生辉 2017年07月06日 14:44
 *
 * @version 1.0.0
 */
@RestController
@RequestMapping(value = "/area")
public class AreaController
{
    @Autowired
    private AreaService areaService;

    private static final Logger LOG = LoggerFactory.getLogger(AreaController.class);

    @ApiOperation(value = "添加地区")
    @PostMapping("/create")
    public WebApiResponse<?> createArea(
        @RequestBody @Validated @ApiParam(value = "area",name="创建基础数据所需参数") Area area)
    {
        int result = areaService.createArea(area);
        if(result == 0)
        {
            return WebApiResponse.error("添加地区失败");
        }
        return WebApiResponse.success(result);
    }

    @ApiOperation(value = "查询地区")
    @PostMapping("/query")
    public WebApiResponse<?> queryArea(
        @RequestBody @Validated @ApiParam(value = "area",name="查询地区所需参数") QueryAreaReq queryAreaReq)
    {
        Area area = JSONObject.parseObject(JSONObject.toJSONString(queryAreaReq),Area.class);
        PageInfo<Area> result = areaService.queryAreaInfo(queryAreaReq.getPageNum(),queryAreaReq.getPageSize(),area);
        if(result == null)
        {
            return WebApiResponse.error("查询地区失败");
        }
        return WebApiResponse.success(result);
    }

    @ApiOperation(value = "查询上级地区")
    @PostMapping("/queryParentArea")
    public WebApiResponse<?> queryParentArea(
        @RequestBody @Validated @ApiParam(value = "area",name="查询上级地区所需参数") QueryParentAreaReq queryParentAreaReq)
    {
        PageInfo<Area> result = areaService.queryParentAreaInfo(queryParentAreaReq.getPageNum(),queryParentAreaReq.getPageSize(),queryParentAreaReq.getParentId());
        if(result == null)
        {
            return WebApiResponse.error("查询地区失败");
        }
        return WebApiResponse.success(result);
    }

    @ApiOperation(value = "条件查询所有地区")
    @PostMapping("/queryAllArea")
    public WebApiResponse<?> queryArea(
        @RequestBody @Validated @ApiParam(value = "queryAllAreaReq",name="查询上级地区所需参数") QueryAllAreaReq queryAllAreaReq)
    {
        Area area = JSONObject.parseObject(JSON.toJSONString(queryAllAreaReq),Area.class);
        List<Area> result = areaService.queryArea(area);
        if(result == null)
        {
            return WebApiResponse.error("查询地区失败");
        }
        return WebApiResponse.success(result);
    }

    @ApiOperation(value = "查询地区详情")
    @GetMapping("/queryAreaInfo/{id}")
    public WebApiResponse<?> queryParentArea(@PathVariable(value = "id") int id)
    {
        Area result = areaService.queryAreaInfo(id);
        if(result == null)
        {
            return WebApiResponse.error("查询地区失败");
        }
        return WebApiResponse.success(result);
    }

    @ApiOperation(value = "删除地区")
    @DeleteMapping("/delete/{uuid}")
    public WebApiResponse<?> deleteArea(
        @PathVariable(value = "uuid") int uuid)
    {
        int result = areaService.deleteAreaInfo(uuid);
        if(result == 0)
        {
            return WebApiResponse.error("删除地区失败");
        }
        return WebApiResponse.success(result);
    }

    @ApiOperation(value = "修改地区")
    @PostMapping("/update")
    public WebApiResponse<?> updateArea(
        @RequestBody @Validated @ApiParam(value = "area",name="修改地区所需参数") Area area)
    {
        int result = areaService.updateAreaInfo(area);
        if(result == 0)
        {
            return WebApiResponse.error("修改地区失败");
        }
        return WebApiResponse.success(result);
    }

    @ApiOperation(value = "结构查询所有地区")
    @GetMapping("/queryAreaAll")
    public WebApiResponse<?> queryAreaAll()
    {
        List<AreaDto> result = areaService.queryAreaAll();
        if(result == null)
        {
            return WebApiResponse.error("结构查询所有地区失败");
        }
        return WebApiResponse.success(result);
    }
}
