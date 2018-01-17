package com.kingthy.platform.controller.basedata;

import com.kingthy.platform.dto.basedata.CategoryReq;
import com.kingthy.platform.dto.basedata.CategoryTreeDto;
import com.kingthy.platform.dto.basedata.TransferCategoryReq;
import com.kingthy.platform.entity.basedata.ClassCategory;
import com.kingthy.platform.response.WebApiResponse;
import com.kingthy.platform.service.basedata.ClassCategoryService;
import com.mysql.jdbc.StringUtils;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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
 * ClassCategoryController(品类分类控制层)
 * 
 * 陈钊 2017年3月29日 下午2:24:45
 * 
 * @version 1.0.0
 *
 */
@RestController
@RequestMapping(value = "/classCategory")
public class ClassCategoryController
{
    private static final Logger LOG = LoggerFactory.getLogger(ClassCategoryController.class);
    
    /**
     * 禁止转移标识
     */
    private static final int transForbidden = -2;
    
    /**
     * 顶级节点uuid为0
     */
    private static final String topNode = "0";
    
    @Autowired
    private ClassCategoryService classCategoryService;
    
    @ApiOperation(value = "查询所有品类的顶级节点")
    @RequestMapping(value = "/findTopNodes", method = RequestMethod.GET)
    public WebApiResponse<List<ClassCategory>> findAllTopNodes()
    {
        List<ClassCategory> ClassCategoryList;
        try
        {
            ClassCategoryList = classCategoryService.findAllTopNodes();
        }
        catch (Exception e)
        {
            LOG.error("/classCategory/findTopNodes:" + e);
            return WebApiResponse.error(WebApiResponse.ResponseMsg.EXCEPTION.getValue());
        }
        if (ClassCategoryList != null && ClassCategoryList.size() > 0)
        {
            return WebApiResponse.success(ClassCategoryList);
        }
        return WebApiResponse.error(WebApiResponse.ResponseMsg.FAIL.getValue());
    }

    @ApiOperation(value = "查询所有品类")
    @RequestMapping(value = "/findNodes", method = RequestMethod.GET)
    public WebApiResponse<List<ClassCategory>> findAllNodes()
    {
        List<ClassCategory> ClassCategoryList;
        try
        {
            ClassCategoryList = classCategoryService.findAllNodes();
        }
        catch (Exception e)
        {
            LOG.error("/classCategory/findTopNodes:" + e);
            return WebApiResponse.error(WebApiResponse.ResponseMsg.EXCEPTION.getValue());
        }
        if (ClassCategoryList != null && ClassCategoryList.size() > 0)
        {
            return WebApiResponse.success(ClassCategoryList);
        }
        return WebApiResponse.error(WebApiResponse.ResponseMsg.FAIL.getValue());
    }
    
    @ApiOperation(value = "根据父节点主键查询其子节点", notes = "根据父节点主键查询其子节点")
    @RequestMapping(value = "findChildNodes/{classCategoryUuid}", method = RequestMethod.GET)
    public WebApiResponse<List<ClassCategory>> findAllChildNodes(
        @PathVariable @ApiParam(name = "classCategoryUuid", value = "父节点主键", required = true) String classCategoryUuid)
    {
        if (StringUtils.isEmptyOrWhitespaceOnly(classCategoryUuid))
        {
            return WebApiResponse.error("分类uuid不能为空");
        }
        List<ClassCategory> classCategoryList;
        try
        {
            classCategoryList = classCategoryService.findAllChildNodes(classCategoryUuid);
        }
        catch (Exception e)
        {
            LOG.error("/classCategory/findChildNodes/{classCategoryUuid}:" + e);
            return WebApiResponse.error(WebApiResponse.ResponseMsg.EXCEPTION.getValue());
        }
        if (classCategoryList != null && classCategoryList.size() > 0)
        {
            return WebApiResponse.success(classCategoryList);
        }
        return WebApiResponse.error(WebApiResponse.ResponseMsg.FAIL.getValue());
    }
    
    @ApiOperation(value = "添加分类接口", notes = "根据父节点uuid添加分类")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public WebApiResponse<String> addNode(
        @RequestBody @ApiParam(name = "addClassCategoryParam", value = "添加分类", required = true) @Validated CategoryReq categoryReq,
        BindingResult bindingResult)
    {
        if (bindingResult.hasErrors())
        {
            return WebApiResponse.error(bindingResult.getFieldError().getDefaultMessage());
        }
        int result;
        try
        {
            result = classCategoryService.addNode(categoryReq);
        }
        catch (Exception e)
        {
            LOG.error("/classCategory/add:" + e);
            return WebApiResponse.error(WebApiResponse.ResponseMsg.EXCEPTION.getValue());
        }
        if (result > 0)
        {
            return WebApiResponse.success(null, WebApiResponse.ResponseMsg.SUCCESS.getValue());
        }
        else
        {
            return WebApiResponse.error(WebApiResponse.ResponseMsg.FAIL.getValue());
        }
    }
    
    @ApiOperation(value = "删除分类接口", notes = "根据uuid删除分类")
    @RequestMapping(value = "/delete/{classCategoryUuid}", method = RequestMethod.DELETE)
    public WebApiResponse<String> deleteNode(
        @PathVariable @ApiParam(name = "classCategoryUuid", value = "节点主键", required = true) String classCategoryUuid)
    {
        if (StringUtils.isEmptyOrWhitespaceOnly(classCategoryUuid))
        {
            return WebApiResponse.error("分类uuid不能为空");
        }
        if (classCategoryService.findAllChildNodesNum(classCategoryUuid) > 0)
        {
            return WebApiResponse.error("对不起，该类别不是末级分类，无法移除");
        }
        int result;
        try
        {
            result = classCategoryService.delete(classCategoryUuid);
        }
        catch (Exception e)
        {
            LOG.error("/classCategory/delete/{classCategoryUuid}:" + e);
            return WebApiResponse.error(WebApiResponse.ResponseMsg.EXCEPTION.getValue());
        }
        if (result > 0)
        {
            return WebApiResponse.success(null, WebApiResponse.ResponseMsg.SUCCESS.getValue());
        }
        return WebApiResponse.error(WebApiResponse.ResponseMsg.FAIL.getValue());
        
    }
    
    @ApiOperation(value = "查询分类信息接口", notes = "根据uuid查询分类信息")
    @RequestMapping(value = "/get/{classCategoryUuid}", method = RequestMethod.GET)
    public WebApiResponse<ClassCategory> getClassCategory(
        @PathVariable @ApiParam(name = "classCategoryUuid", value = "节点主键", required = true) String classCategoryUuid)
    {
        if (StringUtils.isEmptyOrWhitespaceOnly(classCategoryUuid))
        {
            return WebApiResponse.error("分类uuid不能为空");
        }
        ClassCategory classCategory;
        try
        {
            classCategory = classCategoryService.findClassCategoryByUuid(classCategoryUuid);
        }
        catch (Exception e)
        {
            LOG.error("/classCategory/get/{classCategoryUuid}:" + e);
            return WebApiResponse.error(WebApiResponse.ResponseMsg.EXCEPTION.getValue());
        }
        if (classCategory != null)
        {
            return WebApiResponse.success(classCategory);
        }
        return WebApiResponse.error(WebApiResponse.ResponseMsg.FAIL.getValue());
    }
    
    @ApiOperation(value = "编辑分类信息接口", notes = "根据uuid编辑分类信息")
    @RequestMapping(value = "/edit", method = RequestMethod.PUT)
    public WebApiResponse<?> editClassCategory(
        @RequestBody @ApiParam(name = "editClassCategoryParam", value = "编辑信息", required = true) @Validated CategoryReq categoryReq,
        BindingResult bindingResult)
    {
        if (bindingResult.hasErrors())
        {
            return WebApiResponse.error(bindingResult.getFieldError().getDefaultMessage());
        }
        if (categoryReq.getUuid().equals(categoryReq.getParentId()))
        {
            return WebApiResponse.error("上级节点不能为自己本身");
        }
        int result;
        try
        {
            if (classCategoryService.findAllChildNodesNum(categoryReq.getUuid()) > 0
                && !topNode.equals(categoryReq.getParentId()))
            {
                return WebApiResponse.error("分类等级不能超过两级");
            }
            result = classCategoryService.edit(categoryReq);
        }
        catch (Exception e)
        {
            LOG.error("/classCategory/edit:" + e);
            return WebApiResponse.error(WebApiResponse.ResponseMsg.EXCEPTION.getValue());
        }
        if (result > 0)
        {
            return WebApiResponse.success(result);
        }
        else if (result == transForbidden)
        {
            return WebApiResponse.error("禁止将节点转移到自己的子孙节点下");
        }
        return WebApiResponse.error(WebApiResponse.ResponseMsg.FAIL.getValue());
    }
    
    @ApiOperation(value = "转移分类接口", notes = "从原分类下转移到新分类下")
    @RequestMapping(value = "/transfer", method = RequestMethod.PUT)
    public WebApiResponse<String> transferNodes(
        @RequestBody @ApiParam(name = "transferClassCategoryParam", value = "编辑信息", required = true) @Validated TransferCategoryReq transferCategoryReq,
        BindingResult bindingResult)
    {
        if (bindingResult.hasErrors())
        {
            return WebApiResponse.error(bindingResult.getFieldError().getDefaultMessage());
        }
        if (transferCategoryReq.getSourceUuid().equals(transferCategoryReq.getTargetUuid()))
        {
            return WebApiResponse.error("同一个分类之间不能转移");
        }
        int result;
        try
        {
            result = classCategoryService.transfer(transferCategoryReq);
        }
        catch (Exception e)
        {
            LOG.error("/classCategory/transfer:" + e);
            return WebApiResponse.error(WebApiResponse.ResponseMsg.EXCEPTION.getValue());
        }
        if (result >= 0)
        {
            return WebApiResponse.success(null, WebApiResponse.ResponseMsg.SUCCESS.getValue());
        }
        else if (result == transForbidden)
        {
            return WebApiResponse.error("禁止将节点转移到自己的子孙节点下");
        }
        return WebApiResponse.error(WebApiResponse.ResponseMsg.FAIL.getValue());
    }
    
    @ApiOperation(value = "改变分类状态接口", notes = "改变分类状态")
    @RequestMapping(value = "/changeStatus/{classCategoryUuid}/{status}", method = RequestMethod.PUT)
    public WebApiResponse<String> editStatus(
        @PathVariable @ApiParam(name = "classCategoryUuid", value = "节点主键", required = true) String classCategoryUuid,
        @PathVariable @ApiParam(name = "status", value = "状态", required = true) boolean status)
    {
        if (StringUtils.isEmptyOrWhitespaceOnly(classCategoryUuid))
        {
            return WebApiResponse.error("分类uuid不能为空");
        }
        int result;
        try
        {
            result = classCategoryService.editStatus(classCategoryUuid, status);
        }
        catch (Exception e)
        {
            LOG.error("/classCategory/changeStatus/{classCategoryUuid}/{status}:" + e);
            return WebApiResponse.error(WebApiResponse.ResponseMsg.EXCEPTION.getValue());
        }
        if (result > 0)
        {
            return WebApiResponse.success(null, WebApiResponse.ResponseMsg.SUCCESS.getValue());
        }
        return WebApiResponse.error(WebApiResponse.ResponseMsg.FAIL.getValue());
    }
    
    @ApiOperation(value = "查询所有品类的信息，并返回树结构")
    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    public WebApiResponse<List<CategoryTreeDto>> findAll()
    {
        List<CategoryTreeDto> classCategoryTreeList;
        try
        {
            classCategoryTreeList = classCategoryService.getTree();
        }
        catch (Exception e)
        {
            LOG.error("/classCategory/findAll:" + e);
            return WebApiResponse.error(WebApiResponse.ResponseMsg.EXCEPTION.getValue());
        }
        if (classCategoryTreeList != null && classCategoryTreeList.size() > 0)
        {
            return WebApiResponse.success(classCategoryTreeList);
        }
        return WebApiResponse.error(WebApiResponse.ResponseMsg.FAIL.getValue());
    }
    
    @ApiOperation(value = "验证分类名是否重复")
    @RequestMapping(value = "/validateClassCategoryName/{classCategoryName}", method = RequestMethod.GET)
    public WebApiResponse<?> validateClassCategoryName(@PathVariable String classCategoryName)
    {
        if (StringUtils.isEmptyOrWhitespaceOnly(classCategoryName))
        {
            return WebApiResponse.error("分类名不能为空");
        }
        else
        {
            Boolean isRepeat;
            try
            {
                isRepeat = classCategoryService.findClassCategoryName(classCategoryName);
            }
            catch (Exception e)
            {
                LOG.error("/classCategory/validateClassCategoryName/{classCategoryName}:" + e);
                return WebApiResponse.error(WebApiResponse.ResponseMsg.EXCEPTION.getValue());
            }
            if (isRepeat)
            {
                return WebApiResponse.error("已存在相同名称");
            }
            else
            {
                return WebApiResponse.success(null, WebApiResponse.ResponseMsg.SUCCESS.getValue());
            }
        }
    }
    
}
