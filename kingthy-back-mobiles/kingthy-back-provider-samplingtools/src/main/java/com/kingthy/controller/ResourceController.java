package com.kingthy.controller;

import com.kingthy.request.QueryResourceInfoReq;
import com.kingthy.response.QueryResourceInfoResp;
import com.kingthy.response.QuerySubCategoryResp;
import com.kingthy.response.WebApiResponse;
import com.kingthy.service.SamplingToolsService;
import com.kingthy.util.JsonValidator;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springside.modules.utils.number.NumberUtil;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author chenz
 * @version 1.0.0
 * @class_name MaterialController
 * @description 为面料小工具提供查询以及修改服务
 * @create 2017/9/26
 */
@RestController
@RequestMapping("/resource")
public class ResourceController extends BaseController
{

    private static final Logger LOG = LoggerFactory.getLogger(ResourceController.class);

    @Autowired
    private SamplingToolsService samplingToolsService;



    @ApiOperation(value = "查询子分类信息", notes = "")
    @GetMapping(value = "/querySubCategory/{categoryId}")
    public WebApiResponse querySubCategory(
        @PathVariable @ApiParam(name = "categoryId", value = "分类ID", required = true) String categoryId)
    {

        if (!NumberUtil.isNumber(categoryId))
        {
            return WebApiResponse.error("categoryId must more than zero");
        }
        try
        {
            List<QuerySubCategoryResp> result = samplingToolsService.querySubCategoryList(Integer.parseInt(categoryId));
            return WebApiResponse.success(result);
        }
        catch (Exception e)
        {
            LOG.error("获取子分类信息出错，异常信息" + e);
            return WebApiResponse.error(e.toString());
        }
    }

    @ApiOperation(value = "根据顶级分类查询对应的资源信息")
    @PostMapping(value = "/queryResourceInfo")
    public WebApiResponse queryResourceInfo(
        @RequestBody @ApiParam(name = "queryResourceInfoReq", value = "查询参数封装") QueryResourceInfoReq queryResourceInfoReq)
    {
        if (StringUtils.isBlank(queryResourceInfoReq.getSourceUuid()))
        {
            return WebApiResponse.error("subCategory uuid is must not null");
        }
        String sourceTypeStr = queryResourceInfoReq.getSourceType();
        if (StringUtils.isBlank(sourceTypeStr) || !NumberUtil.isNumber(sourceTypeStr))
        {
            return WebApiResponse.error("category type must not null");
        }
        if (NumberUtil.toInt(sourceTypeStr) > 2)
        {
            return WebApiResponse.error("sourceType must less than 2");
        }
        try
        {
            List<QueryResourceInfoResp> result = samplingToolsService.queryResourceInfoList(queryResourceInfoReq);
            if (result != null && result.get(0).getName() != null)
            {
                Map<String, List<QueryResourceInfoResp>> group =
                    result.stream().collect(Collectors.groupingBy(QueryResourceInfoResp::getName));
                return WebApiResponse.success(group);
            }
            else
            {
                return WebApiResponse.success(result);
            }
        }
        catch (Exception e)
        {
            LOG.error("查询顶级分类下的资源失败，异常信息" + e);
            return WebApiResponse.error(e.toString());
        }
    }

    @ApiOperation(value = "更新采样结果")
    @PutMapping(value = "/updateResourceInfo")
    public WebApiResponse updateResourceInfo(
        @RequestBody @ApiParam(name = "materialInfo", value = "json数据") String materialInfo)
    {
        JsonValidator jsonValidator = new JsonValidator();
        if (StringUtils.isBlank(materialInfo) || !jsonValidator.validate(materialInfo))
        {
            return WebApiResponse.error("param is null or json error");
        }
        return WebApiResponse.success("数据更新成功");
    }

}
