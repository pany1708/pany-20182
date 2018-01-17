package com.kingthy.platform.controller.order;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.kingthy.platform.dto.order.CreataShopReq;
import com.kingthy.platform.dto.order.QueryShopPageReq;
import com.kingthy.platform.dto.order.UpdateShopReq;
import com.kingthy.platform.entity.order.Shop;
import com.kingthy.platform.response.WebApiResponse;
import com.kingthy.platform.service.order.ShopService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * ShopController(描述其作用)
 * <p>
 * 赵生辉 2017年07月21日 10:29
 *
 * @version 1.0.0
 */
@RestController
@RequestMapping("shop")
public class ShopController
{
    @Autowired
    private ShopService shopService;

    private static final Logger LOG = LoggerFactory.getLogger(ShopController.class);

    @ApiOperation(value = "添加体验店参数接口", notes = "添加体验店参数接口")
    @PostMapping("/create")
    public WebApiResponse<?> creataShop(
        @RequestBody @Validated @ApiParam(value = "createBaseDataReq",name="创建体验店参数所需参数") CreataShopReq creataShopReq)
    {
        Shop shop = JSONObject.parseObject(JSON.toJSONString(creataShopReq),Shop.class);
        int result = 0;
        try
        {
            result = shopService.create(shop);
        }
        catch (Exception e)
        {
            LOG.error("/shop/create"+e.toString());
            return WebApiResponse.error("创建体验店参数失败");
        }
        if(result == 0)
        {
            return WebApiResponse.error("创建体验店参数失败");
        }
        return WebApiResponse.success(result);
    }

    @ApiOperation(value = "分页查询体验店参数接口", notes = "分页查询体验店参数接口")
    @PostMapping("/query")
    public WebApiResponse<?> queryShopPage(
        @RequestBody @Validated @ApiParam(value = "queryBaseDataReq",name="查询体验店参数所需参数") QueryShopPageReq queryBaseDataReq)
    {
        PageInfo<?> result = null;
        try
        {
            result = shopService.queryShopPage(queryBaseDataReq);
        }
        catch (Exception e)
        {
            LOG.error("/shop/query"+e.toString());
            return WebApiResponse.error("创建体验店参数失败");
        }
        if(result == null)
        {
            return WebApiResponse.error("创建体验店参数失败");
        }
        return WebApiResponse.success(result);
    }

    @ApiOperation(value = "查询体验店参数详情接口", notes = "查询体验店参数详情接口")
    @GetMapping("/query/{uuid}")
    public WebApiResponse<?> queryShop(@PathVariable(value = "uuid") String uuid)
    {
        Shop shop = null;
        try
        {
            shop = shopService.queryShopInfo(uuid);
        }
        catch (Exception e)
        {
            LOG.error("/shop/queryInfo"+e.toString());
            return WebApiResponse.error("查询体验店参数失败");
        }
        if(shop == null)
        {
            return WebApiResponse.error("查询体验店参数失败");
        }
        return WebApiResponse.success(shop);
    }

    @ApiOperation(value = "修改体验店参数接口", notes = "修改体验店参数接口")
    @PutMapping("/update")
    public WebApiResponse<?> updateShop(
        @RequestBody @Validated @ApiParam(value = "queryBaseDataReq",name="查询体验店参数所需参数") UpdateShopReq updateShopReq)
    {
        Shop shop = JSONObject.parseObject(JSON.toJSONString(updateShopReq),Shop.class);
        int result = 0;
        try
        {
            result = shopService.update(shop);
        }
        catch (Exception e)
        {
            LOG.error("/shop/update"+e.toString());
            return WebApiResponse.error(e.getMessage());
        }
        if(result == 0)
        {
            return WebApiResponse.error("更新体验店参数失败");
        }
        return WebApiResponse.success(result);
    }

    @ApiOperation(value = "删除体验店参数接口", notes = "删除体验店参数接口")
    @DeleteMapping("/delete/{uuid}")
    public WebApiResponse<?> deleteShop(@PathVariable(value = "uuid") String uuid)
    {
        int result = 0;
        try
        {
            result = shopService.delete(uuid);
        }
        catch (Exception e)
        {
            LOG.error("/shop/update"+e.toString());
            return WebApiResponse.error("删除体验店参数失败");
        }
        if(result == 0)
        {
            return WebApiResponse.error("删除体验店参数失败");
        }
        return WebApiResponse.success(result);
    }

}
