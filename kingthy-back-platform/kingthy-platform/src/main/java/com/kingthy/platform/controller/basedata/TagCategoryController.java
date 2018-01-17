package com.kingthy.platform.controller.basedata;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.kingthy.exception.PartsCategoryBizException;
import com.kingthy.exception.TagCategoryBizException;
import com.kingthy.platform.dto.basedata.CreateTagCategoryReq;
import com.kingthy.platform.dto.basedata.EditTagCategoryParam;
import com.kingthy.platform.entity.basedata.TagCategory;
import com.kingthy.platform.response.WebApiResponse;
import com.kingthy.platform.service.basedata.TagCategoryService;
import com.kingthy.platform.service.impl.basedata.TagCategoryServiceImpl;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 
 *
 * TagCategoryController(标签分类控制层)
 * 
 * 赵生辉 2017年4月1日 上午8:57:38
 * 
 * @version 1.0.0
 *
 */
@RestController
@RequestMapping("tagCategory")
public class TagCategoryController
{
    
    @Autowired
    private TagCategoryService tagCategoryService;
    
    private static final Logger LOG = LoggerFactory.getLogger(TagCategoryController.class);
    
    @ApiOperation(value = "创建标签", notes = "创建一个新的标签")
    @RequestMapping(value = "", method = RequestMethod.POST)
    public WebApiResponse<Integer> createTagCategory(
        @RequestBody @ApiParam(name = "createTagCategoryParam", value = "标签详细信息", required = true) @Validated CreateTagCategoryReq createTagCategoryReq,
        BindingResult results)
    {
        WebApiResponse webApiResponse = new WebApiResponse();
        if (results.hasErrors())
        {
            return webApiResponse.error(results.getFieldError().getDefaultMessage());
        }
        Integer returnResult;
        try
        {
            TagCategory tagCategory = new TagCategory();
            tagCategory.setClassName(createTagCategoryReq.getClassName());
            tagCategory.setStatus(createTagCategoryReq.getStatus());
            if (createTagCategoryReq.getDescription() != null && !createTagCategoryReq.getDescription().equals(""))
            {
                tagCategory.setDescription(createTagCategoryReq.getDescription());
            }
            returnResult = tagCategoryService.create(tagCategory);
        }
        catch (Exception e)
        {
            LOG.error("/tagCategory创建标签出错，异常信息" + e);
            return webApiResponse.error(WebApiResponse.ResponseMsg.EXCEPTION.getValue());
        }
        if (returnResult == TagCategoryServiceImpl.ISEXIST)
        {
            return webApiResponse.error("已存在相同标签名");
        }
        else if (returnResult == null || returnResult < 0)
        {
            return webApiResponse.error("添加标签失败");
        }
        return webApiResponse.success(returnResult);
    }
    
    @ApiOperation(value = "标签删除", notes = "删除标签信息(逻辑删除)")
    @RequestMapping(value = "/{uuid}", method = RequestMethod.DELETE)
    public WebApiResponse<Integer> deletePartsCategory(
        @ApiParam(name = "uuid", value = "标签uuid", required = true) @PathVariable String uuid)
    {
        WebApiResponse webApiResponse = new WebApiResponse();
        Integer returnResult;
        try
        {
            returnResult = tagCategoryService.deleteByUuid(uuid);
        }
        catch (TagCategoryBizException e)
        {
            LOG.error("标签删除出错，异常信息" + e.getMsg());
            return webApiResponse.error(e.getMsg(), Long.parseLong(e.getCode()));
        }
        if (returnResult == null || returnResult < 1)
        {
            return webApiResponse.error("删除标签失败");
        }
        return webApiResponse.success(returnResult);
    }
    
    @ApiOperation(value = "标签更新", notes = "更新标签信息")
    @RequestMapping(value = "", method = RequestMethod.PUT)
    public WebApiResponse<Integer> editTagCategory(
        @RequestBody @ApiParam(name = "tagCategory", value = "标签详细信息", required = true) @Validated EditTagCategoryParam editTagCategoryParam,
        BindingResult results)
    {
        WebApiResponse webApiResponse = new WebApiResponse();
        if (results.hasErrors())
        {
            return webApiResponse.error(results.getFieldError().getDefaultMessage());
        }
        Integer returnResult;
        TagCategory tagCategory;
        tagCategory = JSON.parseObject(JSON.toJSONBytes(editTagCategoryParam), TagCategory.class);
        try
        {
            returnResult = tagCategoryService.updateSelective(tagCategory);
        }
        catch (PartsCategoryBizException e)
        {
            LOG.error("更新标签的响应结果：" + e.getMsg());
            return webApiResponse.error(e.getMsg(), Long.valueOf(e.getCode()));
        }
        if (returnResult == null || returnResult < 1)
        {
            return webApiResponse.error("更新标签失败");
        }
        return webApiResponse.success(returnResult);
    }
    
    @ApiOperation(value = "查询所有标签", notes = "查询所有的标签信息")
    @RequestMapping(value = "", method = RequestMethod.GET)
    public WebApiResponse<List<TagCategory>> findPartsCategory()
    {
        WebApiResponse webApiResponse = new WebApiResponse();
        TagCategory tagCategory = new TagCategory();
        List<TagCategory> returnResult;
        try
        {
            returnResult = tagCategoryService.findAllTagCategory(tagCategory);
        }
        catch (Exception e)
        {
            LOG.error("/tagCategory查询所有标签出错，异常信息" + e);
            return webApiResponse.error(WebApiResponse.ResponseMsg.EXCEPTION.getValue());
        }
        return webApiResponse.success(returnResult);
    }
    
    @ApiOperation(value = "分页查询所有标签", notes = "通过条件分页查询标签信息")
    @RequestMapping(value = "/page/{pageNo}/{pageSize}", method = RequestMethod.GET)
    public WebApiResponse<PageInfo<TagCategory>> findPartsCategoryPage(@PathVariable String pageNo,
        @PathVariable String pageSize)
    {
        WebApiResponse webApiResponse = new WebApiResponse();
        PageInfo<TagCategory> pageInfo;
        try
        {
            pageInfo = tagCategoryService.findAllTagCategoryPage(Integer.valueOf(pageNo),
                Integer.valueOf(pageSize),
                new TagCategory());
        }
        catch (NumberFormatException e)
        {
            LOG.error("/tagCategory/page/{pageNo}/{pageSize}出错，异常信息" + e);
            return webApiResponse.error(WebApiResponse.ResponseMsg.EXCEPTION.getValue());
        }
        return webApiResponse.success(pageInfo);
    }
    
    @ApiOperation(value = "修改标签的状态", notes = "修改标签是否显示状态")
    @RequestMapping(value = "/changeStatus/{uuid}/{status}", method = RequestMethod.PUT)
    public WebApiResponse<Integer> updatePartsCategoryStatus(
        @ApiParam(name = "uuid", value = "标签uuid", required = true) @PathVariable String uuid,
        @ApiParam(name = "status", value = "标签状态", required = true) @PathVariable String status)
    {
        WebApiResponse webApiResponse = new WebApiResponse();
        int result;
        try
        {
            result = tagCategoryService.updateShowOrHide(uuid, Boolean.valueOf(status));
        }
        catch (Exception e)
        {
            LOG.error("/tagCategory/changeStatus/{uuid}/{status}出错，异常信息" + e);
            return webApiResponse.error(WebApiResponse.ResponseMsg.EXCEPTION.getValue());
        }
        if (result < 1)
        {
            return webApiResponse.error("修改标签的状态失败");
        }
        return webApiResponse.success(result);
    }
    
    @ApiOperation(value = "查询标签", notes = "作品标签管理时查询标签")
    @RequestMapping(value = "/findTags/{tagName}", method = RequestMethod.GET)
    public WebApiResponse<?> findTags(
        @ApiParam(name = "tagName", value = "查询标签", required = true) @PathVariable String tagName)
    {
        if (null != tagName && !tagName.isEmpty())
        {
            List<TagCategory> tags;
            try
            {
                tags = tagCategoryService.findTags(tagName);
            }
            catch (Exception e)
            {
                LOG.error("/tagCategory/findTags/{tagName}出错，异常信息" + e);
                return WebApiResponse.error(WebApiResponse.ResponseMsg.EXCEPTION.getValue());
            }
            if (null != tags && tags.size() > 0)
            {
                return WebApiResponse.success(tags);
            }
            else
            {
                return WebApiResponse.error("查询标签失败或无匹配标签");
            }
        }
        else
        {
            return WebApiResponse.error("查询参数为空");
        }
    }
    
    @ApiOperation(value = "验证分类名是否重复")
    @RequestMapping(value = "/validateTagCategoryName/{tagCategoryName}", method = RequestMethod.GET)
    public WebApiResponse<?> validateTagCategoryName(@PathVariable String tagCategoryName)
    {
        if (StringUtils.isBlank(tagCategoryName))
        {
            return WebApiResponse.error("分类名不能为空");
        }
        else
        {
            Boolean isRepeat;
            try
            {
                isRepeat = tagCategoryService.findTagCategoryName(tagCategoryName);
            }
            catch (Exception e)
            {
                LOG.error("/tagCategory/validateTagCategoryName/{tagCategoryName}出错，异常信息" + e);
                return WebApiResponse.error(WebApiResponse.ResponseMsg.EXCEPTION.getValue());
            }
            if (isRepeat)
            {
                return WebApiResponse.error("已存在相同名称");
            }
            else
            {
                return WebApiResponse.success("OK");
            }
        }
    }
    
}
