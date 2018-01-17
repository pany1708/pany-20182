package com.kingthy.platform.controller.material;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.kingthy.platform.dto.material.AddUpdateMaterialReq;
import com.kingthy.platform.dto.material.FindPage;
import com.kingthy.platform.dto.material.MaterialDto;
import com.kingthy.platform.dto.material.UpdateMaterialReq;
import com.kingthy.platform.entity.material.Material;
import com.kingthy.platform.response.WebApiResponse;
import com.kingthy.platform.service.material.MaterialService;
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
 * MaterialController
 * 
 * 赵生辉 2017年4月17日 下午4:36:28
 * 
 * @version 1.0.0
 *
 */
@RestController
@RequestMapping(value = "material")
public class MaterialController
{
    @Autowired
    private MaterialService materialService;
    
    private static final Logger LOG = LoggerFactory.getLogger(MaterialController.class);
    
    @ApiOperation(value = "创建一个面料", notes = "创建一个面料")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public WebApiResponse<?> createMaterial(
        @RequestBody @ApiParam(name = "createMaterial") @Validated AddUpdateMaterialReq addUpdateMaterialReq,
        BindingResult bindingResult)
    {
        Material material = JSONObject.parseObject(JSON.toJSONString(addUpdateMaterialReq),Material.class);
        if (bindingResult.hasErrors())
        {
            WebApiResponse.error(bindingResult.getFieldError().getDefaultMessage());
        }
        int result;
        try
        {
            result = materialService.create(material);
        }
        catch (Exception e)
        {
            LOG.error("material/create" + e);
            return WebApiResponse.error(e.getMessage());
        }
        if (0 == result)
        {
            return WebApiResponse.error("创建面料失败");
        }
        return WebApiResponse.success(result);
    }
    
    @ApiOperation(value = "查询面料详情", notes = "查询面料详情")
    @RequestMapping(value = "/{uuid}", method = RequestMethod.GET)
    public WebApiResponse<MaterialDto> findMaterial(@PathVariable String uuid)
    {
        MaterialDto materialDto;
        try
        {
            materialDto = materialService.findMaterial(uuid);
        }
        catch (Exception e)
        {
            LOG.error("查询面料详情出错，异常信息" + e);
            return WebApiResponse.error(WebApiResponse.ResponseMsg.EXCEPTION.getValue());
        }
        if (materialDto == null)
        {
            return WebApiResponse.error(WebApiResponse.ResponseMsg.FAIL.getValue());
        }
        return WebApiResponse.success(materialDto);
    }
    
    @ApiOperation(value = "查询面料列表详情", notes = "查询面料详情")
    @RequestMapping(value = "/page", method = RequestMethod.POST)
    public WebApiResponse<?> findMaterialPage(@RequestBody FindPage findPage)
    {
        Material material = JSONObject.parseObject(JSON.toJSONString(findPage),Material.class);
        PageInfo<Material> result;
        try
        {
            result = materialService.findMaterialPage(findPage.getPageNum(),findPage.getPageSize(),material);
        }
        catch (Exception e)
        {
            LOG.error("查询面料列表详情出错，异常信息" + e);
            return WebApiResponse.error(WebApiResponse.ResponseMsg.EXCEPTION.getValue());
        }
        if (result == null)
        {
            return WebApiResponse.error(WebApiResponse.ResponseMsg.FAIL.getValue());
        }
        return WebApiResponse.success(result);
    }
    
    @ApiOperation(value = "删除面料", notes = "根据UUID删除面料")
    @RequestMapping(value = "deleteMaterial/{materialUuid}", method = RequestMethod.DELETE)
    public WebApiResponse<?> deleteMaterial(@PathVariable String materialUuid)
    {
        int result;
        try
        {
            result = materialService.deleteMaterial(materialUuid);
        }
        catch (Exception e)
        {
            LOG.error("删除面料出错，异常信息" + e);
            return WebApiResponse.error(WebApiResponse.ResponseMsg.EXCEPTION.getValue());
        }
        if (result > 0)
        {
            return WebApiResponse.success(result, WebApiResponse.ResponseMsg.SUCCESS.getValue());
        }
        else
        {
            return WebApiResponse.error(WebApiResponse.ResponseMsg.FAIL.getValue());
        }
    }
    
    @ApiOperation(value = "更新面料", notes = "根据UUID更新面料")
    @RequestMapping(value = "updateMaterial", method = RequestMethod.PUT)
    public WebApiResponse<?> updateMaterial(
        @RequestBody @ApiParam(name = "createMaterial") @Validated UpdateMaterialReq updateMaterialReq,
        BindingResult bindingResult)
    {
        if (bindingResult.hasErrors())
        {
            return WebApiResponse.error(bindingResult.getFieldError().getDefaultMessage());
        }
        Material material = JSONObject.parseObject(JSON.toJSONString(updateMaterialReq),Material.class);
        int result;
        try
        {
            result = materialService.updateMaterial(material);
        }
        catch (Exception e)
        {
            LOG.error("更新面料出错，异常信息" + e);
            return WebApiResponse.error(WebApiResponse.ResponseMsg.EXCEPTION.getValue());
        }
        if (result > 0)
        {
            return WebApiResponse.success(result, WebApiResponse.ResponseMsg.SUCCESS.getValue());
        }
        else
        {
            return WebApiResponse.error(WebApiResponse.ResponseMsg.FAIL.getValue());
        }
    }
    
    @ApiOperation(value = "查找颜色", notes = "查找颜色")
    @RequestMapping(value = "selectColor/{rgb}", method = RequestMethod.GET)
    public WebApiResponse<?> selectColor(@PathVariable String rgb)
    {
        List<String> strings;
        try
        {
            strings = materialService.selectColor(rgb);
        }
        catch (Exception e)
        {
            LOG.error("查找颜色出错，异常信息" + e);
            return WebApiResponse.error(WebApiResponse.ResponseMsg.EXCEPTION.getValue());
        }
        if (strings.size() >= 0)
        {
            return WebApiResponse.success(strings, WebApiResponse.ResponseMsg.SUCCESS.getValue());
        }
        else
        {
            return WebApiResponse.error(WebApiResponse.ResponseMsg.FAIL.getValue());
        }
    }
}
