package com.kingthy.platform.controller.basedata;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.kingthy.platform.dto.basedata.CreateSegmentCategoryReq;
import com.kingthy.platform.dto.basedata.QuerySegmentPageReq;
import com.kingthy.platform.dto.basedata.UpdateSegmentReq;
import com.kingthy.platform.entity.basedata.SegmentCategory;
import com.kingthy.platform.response.WebApiResponse;
import com.kingthy.platform.service.basedata.SegmentCategoryService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * SegmentCategoryController(描述其作用)
 * <p>
 * 赵生辉 2017年07月11日 11:03
 *
 * @version 1.0.0
 */
@RestController
@RequestMapping("/segmentCategory")
public class SegmentCategoryController
{

    @Autowired
    private SegmentCategoryService segmentCategoryService;

    private static final Logger LOG = LoggerFactory.getLogger(SegmentCategoryController.class);

    @ApiOperation(value = "创建新的分段参数")
    @PostMapping("/create")
    public WebApiResponse<?> createSegmentCategory(@RequestBody CreateSegmentCategoryReq createSegmentCategoryReq)
    {
        SegmentCategory segmentCategory  = JSONObject.parseObject(JSON.toJSONString(createSegmentCategoryReq),SegmentCategory.class);
        int result;
        try
        {
            result = segmentCategoryService.createSegmentCategory(segmentCategory);
            if(result == 0)
            {
                return WebApiResponse.error("创建分段参数失败");
            }
        }
        catch (Exception e)
        {
            LOG.error("/segmentCategory/create"+e.toString());
            return WebApiResponse.error("创建分段参数失败");
        }
        return WebApiResponse.success(result);
    }

    @ApiOperation(value = "分页查询分段参数")
    @PostMapping("/query")
    public WebApiResponse<?> querySegmentPage(@RequestBody QuerySegmentPageReq querySegmentPageReq)
    {
        SegmentCategory segmentCategory  = JSONObject.parseObject(JSON.toJSONString(querySegmentPageReq),SegmentCategory.class);
        PageInfo<SegmentCategory> result;
        try
        {
            result = segmentCategoryService.querySegmentCategory(querySegmentPageReq.getPageNum(),querySegmentPageReq.getPageSize(),segmentCategory);
            if(result == null)
            {
                return WebApiResponse.error("分页查询分段参数失败");
            }
        }
        catch (Exception e)
        {
            LOG.error("/segmentCategory/query"+e.toString());
            return WebApiResponse.error("分页查询分段参数失败");
        }
        return WebApiResponse.success(result);
    }


    @ApiOperation(value = "查询分段参数详情")
    @GetMapping("/queryInfo/{uuid}")
    public WebApiResponse<?> querySegmentInfo(@PathVariable(value = "uuid") String uuid)
    {
        SegmentCategory result;
        try
        {
            result = segmentCategoryService.querySegmentCategory(uuid);
            if(result == null)
            {
                return WebApiResponse.error("查询分段参数详情失败");
            }
        }
        catch (Exception e)
        {
            LOG.error("/segmentCategory/queryInfo"+e.toString());
            return WebApiResponse.error("查询分段参数详情失败");
        }
        return WebApiResponse.success(result);
    }

    @ApiOperation(value = "修改分段参数详情")
    @PutMapping("/update")
    public WebApiResponse<?> UpdateSegment(@RequestBody UpdateSegmentReq updateSegmentReq)
    {
        SegmentCategory segmentCategory  = JSONObject.parseObject(JSON.toJSONString(updateSegmentReq),SegmentCategory.class);
        int result;
        try
        {
            result = segmentCategoryService.updateSegmentCategory(segmentCategory);
            if(result == 0)
            {
                return WebApiResponse.error("修改分段参数详情失败");
            }
        }
        catch (Exception e)
        {
            LOG.error("/segmentCategory/update"+e.toString());
            return WebApiResponse.error("修改分段参数详情失败");
        }
        return WebApiResponse.success(result);
    }

    @ApiOperation(value = "删除分段参数详情")
    @DeleteMapping("/delete/{uuid}")
    public WebApiResponse<?> deleteSegment(@PathVariable(value = "uuid") String uuid)
    {
        int result;
        try
        {
            result = segmentCategoryService.deleteSegmentCategory(uuid);
            if(result == 0)
            {
                return WebApiResponse.error("删除分段参数详情失败");
            }
        }
        catch (Exception e)
        {
            LOG.error("/segmentCategory/delete"+e.toString());
            return WebApiResponse.error("删除分段参数详情失败");
        }
        return WebApiResponse.success(result);
    }

}
