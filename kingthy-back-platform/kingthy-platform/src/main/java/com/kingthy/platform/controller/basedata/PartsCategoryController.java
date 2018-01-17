package com.kingthy.platform.controller.basedata;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.kingthy.platform.dto.basedata.CreatePartsCategoryReq;
import com.kingthy.platform.dto.basedata.EditPartsCategoryReq;
import com.kingthy.platform.dto.basedata.QueryPartsCategoryReq;
import com.kingthy.platform.entity.basedata.PartsCategory;
import com.kingthy.platform.response.WebApiResponse;
import com.kingthy.platform.service.basedata.PartsCategoryService;
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
 * PartsCategoryController(部件分类控制层)
 * <p>
 * 赵生辉 2017年3月29日 下午2:03:44
 *
 * @version 1.0.0
 */
@RestController
@RequestMapping("/partsCategory")
public class PartsCategoryController
{

    @Autowired
    private PartsCategoryService partsCategoryService;

    private static final Logger LOG = LoggerFactory.getLogger(PartsCategoryController.class);

    @ApiOperation(value = "部件添加", notes = "添加新的部件信息")
    @PostMapping(value = "/create")
    public WebApiResponse<?> addPartsCategory(
        @RequestBody @ApiParam(name = "PartsCategory", value = "部件详细信息", required = true) @Validated CreatePartsCategoryReq createPartsCategoryParam,
        BindingResult results)
    {
        WebApiResponse webApiResponse = new WebApiResponse();
        if (results.hasErrors())
        {
            return webApiResponse.error(results.getFieldError().getDefaultMessage());
        }
        PartsCategory partsCategory =
            JSONObject.parseObject(JSON.toJSONString(createPartsCategoryParam), PartsCategory.class);
        Integer returnResult;
        try
        {
            returnResult = partsCategoryService.create(partsCategory);
        }
        catch (Exception e)
        {
            LOG.error("/partsCategory/create,异常" + e);
            return webApiResponse.error(WebApiResponse.ResponseMsg.EXCEPTION.getValue());
        }
        if (returnResult == null || returnResult < 1)
        {
            return webApiResponse.error("添加部件失败");
        }
        return webApiResponse.success(returnResult);
    }

    @ApiOperation(value = "部件删除", notes = "删除部件信息(逻辑删除)")
    @DeleteMapping("/delete/{uuid}")
    public WebApiResponse<?> deletePartsCategory(@PathVariable(value = "uuid")String uuid)
    {
        WebApiResponse webApiResponse = new WebApiResponse();
        Integer returnResult;
        try
        {
            returnResult = partsCategoryService.deleteById(uuid);
        }
        catch (Exception e)
        {
            LOG.info("/partsCategory/delete：" + e.getMessage() + "，异常" + e);
            return webApiResponse.error(e.getMessage());

        }
        if (returnResult == null || returnResult < 1)
        {
            return webApiResponse.error("删除部件失败");
        }
        return webApiResponse.success(returnResult);
    }

    @ApiOperation(value = "查询部件详情", notes = "查询部件详情")
    @GetMapping("/queryInfo/{uuid}")
    public WebApiResponse<?> queryPartsCategory(@PathVariable(value = "uuid")String uuid)
    {
        WebApiResponse webApiResponse = new WebApiResponse();
        PartsCategory returnResult;
        try
        {
            returnResult = partsCategoryService.findPartsCategory(uuid);
        }
        catch (Exception e)
        {
            LOG.info("/partsCategory/queryInfo：" + e.getMessage() + "，异常" + e);
            return webApiResponse.error(e.getMessage());
        }
        if (returnResult == null)
        {
            return webApiResponse.error("查询部件失败");
        }
        return webApiResponse.success(returnResult);
    }

    @ApiOperation(value = "部件更新", notes = "更新部件信息")
    @PutMapping("/update")
    public WebApiResponse<?> editPartsCategory(
        @RequestBody @ApiParam(name = "editPartsCategoryParam", value = "部件详细信息", required = true) @Validated EditPartsCategoryReq editPartsCategoryParam,
        BindingResult results)
    {
        WebApiResponse webApiResponse = new WebApiResponse();
        if (results.hasErrors())
        {
            return webApiResponse.error(results.getFieldError().getDefaultMessage());
        }
        Integer returnResult;
        PartsCategory partsCategory = JSON.parseObject(JSON.toJSONString(editPartsCategoryParam), PartsCategory.class);
        try
        {
            returnResult = partsCategoryService.updateSelective(partsCategory);
        }
        catch (Exception e)
        {
            LOG.error("/partsCategory/update：" + e.getMessage() + "，异常" + e);
            return webApiResponse.error(e.getMessage());
        }
        if (returnResult == null || returnResult < 1)
        {
            return webApiResponse.error("修改部件失败");
        }
        return webApiResponse.success(returnResult);
    }

    @ApiOperation(value = "查询所有部件", notes = "查询所有的部件信息")
    @GetMapping("/queryAll")
    public WebApiResponse<?> findPartsCategory()
    {
        List<PartsCategory> returnResult;
        try
        {
            returnResult = partsCategoryService.findAllPartsCategory();
        }
        catch (Exception e)
        {
            LOG.error("/partsCategory/queryAll，异常信息" + e);
            return WebApiResponse.error(e.getMessage());
        }
        return WebApiResponse.success(returnResult);
    }

    @ApiOperation(value = "分页查询所有部件", notes = "通过条件分页查询部件信息")
    @PostMapping("/page")
    public WebApiResponse<?> findPartsCategoryPage(
        @RequestBody QueryPartsCategoryReq queryPartsCategoryReq)
    {

        PartsCategory partsCategory =
            JSONObject.parseObject(JSON.toJSONString(queryPartsCategoryReq), PartsCategory.class);
        WebApiResponse webApiResponse = new WebApiResponse();
        PageInfo<PartsCategory> pageInfo;
        try
        {
            pageInfo = partsCategoryService.findAllPartsCategoryPage(queryPartsCategoryReq.getPageNum(),
                queryPartsCategoryReq.getPageSize(),
                partsCategory);
        }
        catch (Exception e)
        {
            LOG.error("/partsCategory/page/，异常信息" + e);
            return webApiResponse.error(e.getMessage());
        }
        return webApiResponse.success(pageInfo);
    }

    /*@ApiOperation(value = "验证分类名是否重复")
    @RequestMapping(value = "/validatePartsCategoryName/{partsCategoryName}", method = RequestMethod.GET)
    public WebApiResponse<?> validatePartsCategoryName(@PathVariable String partsCategoryName)
    {
        if (StringUtils.isBlank(partsCategoryName))
        {
            return WebApiResponse.error("分类名不能为空");
        }
        else
        {
            Boolean isRepeat;
            try
            {
                isRepeat = partsCategoryService.findPartsCategoryName(partsCategoryName);
            }
            catch (Exception e)
            {
                LOG.error("/partsCategory/validatePartsCategoryName/{partsCategoryName}出错，异常信息" + e);
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
    }*/


}
