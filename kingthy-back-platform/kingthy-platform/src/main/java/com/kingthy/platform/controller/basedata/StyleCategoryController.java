package com.kingthy.platform.controller.basedata;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.kingthy.platform.dto.basedata.CreateStyleCategoryReq;
import com.kingthy.platform.dto.basedata.QueryStyleCategoryPageReq;
import com.kingthy.platform.dto.basedata.UpdateStyleCategoryReq;
import com.kingthy.platform.entity.basedata.StyleCategory;
import com.kingthy.platform.response.WebApiResponse;
import com.kingthy.platform.service.basedata.StyleCategoryService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 
 *
 * StyleCategoryController(风格分类控制层)
 * 
 * 陈钊 2017年3月29日 下午2:24:45
 * 
 * @version 1.0.0
 *
 */
@RestController
@RequestMapping(value = "/styleCategory")
public class StyleCategoryController
{
    private static final Logger LOG = LoggerFactory.getLogger(StyleCategoryController.class);
    
    /**
     * 禁止转移标识
     */
    private static final int transForbidden = -2;
    
    /**
     * 顶级节点uuid为0
     */
    private static final String topNode = "0";
    
    @Autowired
    private StyleCategoryService styleCategoryService;

    @ApiOperation(value = "创建风格")
    @PostMapping("/create")
    public WebApiResponse<?> createStyleCategory(@RequestBody CreateStyleCategoryReq createStyleCategoryReq)
    {

        StyleCategory styleCategory  = JSONObject.parseObject(JSONObject.toJSONString(createStyleCategoryReq),StyleCategory.class);
        int result = 0;
        try
        {
            result = styleCategoryService.create(styleCategory);
        }
        catch (Exception e)
        {
            LOG.error("/styleCategory/create"+e.toString());
            return WebApiResponse.error("创建风格失败");
        }
        if(result == 0)
        {
            return WebApiResponse.error("创建风格失败");
        }
        return WebApiResponse.success(result);
    }

    @ApiOperation(value = "分页查询风格列表")
    @PostMapping("/query")
    public WebApiResponse<?> queryStyleCategoryPage(@RequestBody QueryStyleCategoryPageReq queryStyleCategoryPageReq)
    {

        StyleCategory styleCategory  = JSONObject.parseObject(JSONObject.toJSONString(queryStyleCategoryPageReq),StyleCategory.class);
        PageInfo<StyleCategory> result = null;
        try
        {
            result = styleCategoryService.findAllTagCategoryPage(queryStyleCategoryPageReq.getPageNum(),queryStyleCategoryPageReq.getPageSize(),styleCategory);
        }
        catch (Exception e)
        {
            LOG.error("/styleCategory/query"+e.toString());
            return WebApiResponse.error("查询风格失败");
        }
        if(result == null)
        {
            return WebApiResponse.error("查询风格失败");
        }
        return WebApiResponse.success(result);
    }

    @ApiOperation(value = "修改风格信息")
    @PutMapping("/update")
    public WebApiResponse<?> updateStyleCategory(@RequestBody UpdateStyleCategoryReq updateStyleCategoryReq)
    {

        StyleCategory styleCategory  = JSONObject.parseObject(JSONObject.toJSONString(updateStyleCategoryReq),StyleCategory.class);
        int result = 0;
        try
        {
            result = styleCategoryService.updateSelective(styleCategory);
        }
        catch (Exception e)
        {
            LOG.error("/styleCategory/update"+e.toString());
            return WebApiResponse.error("修改风格失败");
        }
        if(result == 0)
        {
            return WebApiResponse.error("修改风格失败");
        }
        return WebApiResponse.success(result);
    }

    @ApiOperation(value = "查询风格详情")
    @GetMapping("/queryInfo/{uuid}")
    public WebApiResponse<?> queryStyleCategoryInfo(@PathVariable(value = "uuid") String uuid)
    {
        StyleCategory result = null;
        try
        {
            result = styleCategoryService.findStyleCategoryInfo(uuid);
        }
        catch (Exception e)
        {
            LOG.error("/styleCategory/queryInfo"+e.toString());
            return WebApiResponse.error("查询风格详情失败");
        }
        if(result == null)
        {
            return WebApiResponse.error("查询风格详情失败");
        }
        return WebApiResponse.success(result);
    }

    @ApiOperation(value = "删除风格")
    @DeleteMapping("/delete/{uuid}")
    public WebApiResponse<?> deleteStyleCategory(@PathVariable(value = "uuid") String uuid)
    {
        int result = 0;
        try
        {
            result = styleCategoryService.deleteByUuid(uuid);
        }
        catch (Exception e)
        {
            LOG.error("/styleCategory/delete"+e.toString());
            return WebApiResponse.error("删除风格失败");
        }
        if(result == 0)
        {
            return WebApiResponse.error("删除风格失败");
        }
        return WebApiResponse.success(result);
    }

    

}
