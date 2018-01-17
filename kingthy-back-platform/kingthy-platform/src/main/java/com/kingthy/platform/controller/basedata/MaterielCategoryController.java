/**
\ * 系统项目名称
 * com.kingthy.platform.controller.basedata
 * MaterielController.java
 * 
 * 2017年3月29日-下午4:34:25
 *  2017金昔科技有限公司-版权所有
 *
 */
package com.kingthy.platform.controller.basedata;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.kingthy.platform.dto.basedata.MaterielSeasonReq;
import com.kingthy.platform.dto.basedata.QueryMaterielCategoryReq;
import com.kingthy.platform.dto.basedata.UpdateMaterielCategoryReq;
import com.kingthy.platform.entity.basedata.MaterielCategory;
import com.kingthy.platform.response.WebApiResponse;
import com.kingthy.platform.service.basedata.MaterielCategoryService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *
 * MaterielController
 * 
 * yuanml 2017年3月29日 下午4:34:25
 * 
 * @version 1.0.0
 *
 */
@RestController
@RequestMapping("materielCategory")
public class MaterielCategoryController
{
    @Autowired
    private MaterielCategoryService materielCategoryService;
    
    private static final Logger LOG = LoggerFactory.getLogger(MaterielCategoryController.class);
    
    /**
     * 顶级根节点
     */
    private static final String ROOTNODE = "0";
    
    /**
     * 当前节点不是末节点 返回结果
     */
    private static final int NOLEAFNODE = -2;
    
    /**
     * 转移到节点为子节点 返回结果
     */
    private static final int ISCHILENODE = -3;
    
    @ApiOperation(value = "新增材质分类", notes = "基础数据材质分类增加")
    @PostMapping("/create")
    public WebApiResponse<?> createMaterielCate(@RequestBody MaterielSeasonReq materielSeasonReq)
    {
        MaterielCategory dressCategory = JSONObject.parseObject(JSON.toJSONString(materielSeasonReq),MaterielCategory.class);
        int result = 0;
        try
        {
            result = materielCategoryService.createMaterielCategory(dressCategory);
        }
        catch (Exception e)
        {
            LOG.error("/materielCategory/create"+e.toString());
            return WebApiResponse.error("创建材质失败");
        }
        if(result == 0)
        {
            return WebApiResponse.error("创建材质失败");
        }
        return WebApiResponse.success(result);
    }

    @ApiOperation(value = "分页查询材质参数接口", notes = "分页查询材质参数接口")
    @PostMapping("/query")
    public WebApiResponse<?> queryMaterielCategory(
        @RequestBody @Validated @ApiParam(value = "queryBaseDataReq",name="查询材质参数所需参数") QueryMaterielCategoryReq queryMaterielCategoryReq)
    {
        MaterielCategory materielCategory = JSONObject.parseObject(JSON.toJSONString(queryMaterielCategoryReq),MaterielCategory.class);
        PageInfo<?> result = null;
        try
        {
            result = materielCategoryService.findMaterielCategory(queryMaterielCategoryReq.getPageNum(),queryMaterielCategoryReq.getPageSize(),materielCategory);
        }
        catch (Exception e)
        {
            LOG.error("/materielCategory/query"+e.toString());
            return WebApiResponse.error("分页查询材质失败");
        }
        if(result == null)
        {
            return WebApiResponse.error("分页查询材质失败");
        }
        return WebApiResponse.success(result);
    }

    @ApiOperation(value = "查询材质参数详情接口", notes = "查询材质参数详情接口")
    @GetMapping("/query/{uuid}")
    public WebApiResponse<?> queryMaterielCategoryInfo(@PathVariable(value = "uuid") String uuid)
    {
        MaterielCategory materielCategory = null;
        try
        {
            materielCategory = materielCategoryService.findMaterielCategoryByUuid(uuid);
        }
        catch (Exception e)
        {
            LOG.error("/materielCategory/queryInfo"+e.toString());
            return WebApiResponse.error("查询材质参数失败");
        }
        if(materielCategory == null)
        {
            return WebApiResponse.error("查询材质参数失败");
        }
        return WebApiResponse.success(materielCategory);
    }

    @ApiOperation(value = "修改材质参数接口", notes = "修改材质参数接口")
    @PutMapping("/update")
    public WebApiResponse<?> updateMaterielCategory(
        @RequestBody @Validated @ApiParam(value = "queryBaseDataReq",name="查询材质参数所需参数") UpdateMaterielCategoryReq updateMaterielCategoryReq)
    {
        MaterielCategory materielCategory = JSONObject.parseObject(JSON.toJSONString(updateMaterielCategoryReq),MaterielCategory.class);
        int result = 0;
        try
        {
            result = materielCategoryService.updateMaterielCategoryByUuid(materielCategory);
        }
        catch (Exception e)
        {
            LOG.error("/materielCategory/update"+e.toString());
            return WebApiResponse.error("更新材质参数失败");
        }
        if(result == 0)
        {
            return WebApiResponse.error("更新材质参数失败");
        }
        return WebApiResponse.success(result);
    }

    @ApiOperation(value = "删除材质参数接口", notes = "删除材质参数接口")
    @DeleteMapping("/delete/{uuid}")
    public WebApiResponse<?> deleteMaterielCategory(@PathVariable(value = "uuid") String uuid)
    {
        int result = 0;
        try
        {
            result = materielCategoryService.deleteMaterielCategory(uuid);
        }
        catch (Exception e)
        {
            LOG.error("/materielCategory/delete"+e.toString());
            return WebApiResponse.error("删除材质参数失败");
        }
        if(result == 0)
        {
            return WebApiResponse.error("删除材质参数失败");
        }
        return WebApiResponse.success(result);
    }

    @ApiOperation(value = "查询所有材质接口", notes = "查询所有材质接口")
    @GetMapping("/queryAll")
    public WebApiResponse<?> queryAllMaterielCategory()
    {
        List<MaterielCategory> result;
        try
        {
            result = materielCategoryService.findAllMaterielcategory();
        }
        catch (Exception e)
        {
            LOG.error("/materielCategory/queryAll"+e.toString());
            return WebApiResponse.error("查询材质失败");
        }
        if(result == null)
        {
            return WebApiResponse.error("查询材质失败");
        }
        return WebApiResponse.success(result);
    }
}
