package com.kingthy.platform.controller.goods;

import com.github.pagehelper.PageInfo;
import com.kingthy.platform.dto.goods.*;
import com.kingthy.platform.response.WebApiResponse;
import com.kingthy.platform.service.goods.GoodsService;
import com.kingthy.platform.util.EsGoodsSender;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 
 *
 * GoodsController(商品控制层)
 * 
 * 陈钊 2017年4月5日 下午7:57:49
 * 
 * @version 1.0.0
 *
 */
@RestController
@RequestMapping(value = "/goods")
public class GoodsController
{
    private static final Logger LOG = LoggerFactory.getLogger(GoodsController.class);
    
    @Autowired
    private GoodsService goodsService;

    @Autowired
    private EsGoodsSender esGoodsSender;

    @ApiOperation(value = "分页查询个人商品信息")
    @RequestMapping(value = "/goodsList", method = RequestMethod.POST)
    public WebApiResponse<?> findByPage(
        @RequestBody @ApiParam(name = "goodsParam", value = "查询条件", required = true) GoodsPageReq goodsPageReq)
    {
        PageInfo<GoodsPageDto> goodsPage;
        try
        {
            goodsPage = goodsService.findByPage(goodsPageReq);
        }
        catch (Exception e)
        {
            LOG.error("/goods/goodsList:" + e);
            return WebApiResponse.error(WebApiResponse.ResponseMsg.EXCEPTION.getValue());
        }
        if (goodsPage != null)
        {
            return WebApiResponse.success(goodsPage);
        }
        
        return WebApiResponse.error(WebApiResponse.ResponseMsg.FAIL.getValue());
    }

    @ApiOperation(value = "分页查询官方商品信息")
    @RequestMapping(value = "/officiallyList", method = RequestMethod.POST)
    public WebApiResponse<?> officiallyList(@RequestBody @ApiParam(name = "goodsParam", value = "查询条件", required = true) GoodsPageReq goodsPageReq){

        WebApiResponse<?> result = null;

        try {

            result = goodsService.officiallyList(goodsPageReq);
        }catch (Exception e){
            LOG.error("/goods/officiallyList:" + e);
            return WebApiResponse.error(WebApiResponse.ResponseMsg.EXCEPTION.getValue());
        }

        return result;
    }

    @ApiOperation(value = "申请成为官方商品")
    @RequestMapping(value = "/officially/{goodsUuid}/{memberUuid}", method = RequestMethod.PUT)
    public WebApiResponse<String> applyOfficially(@PathVariable("goodsUuid") @ApiParam(name = "goodsUuid", required = true) String goodsUuid,
                                                  @PathVariable("memberUuid") @ApiParam(name = "goodsUuid", required = true)String memberUuid){

        WebApiResponse<String> result = null;

        try{

            result = goodsService.applyOfficially(goodsUuid, memberUuid);

        }catch (Exception e){
            e.printStackTrace();
            LOG.error("/goods/officially/{goodsUuid}/{memberUuid}:" + e);
            return WebApiResponse.error(WebApiResponse.ResponseMsg.EXCEPTION.getValue());
        }

        return result;
    }
    
    @ApiOperation(value = "批量将商品放入回收站或者从回收站还原")
    @RequestMapping(value = "/deleteOrRestore", method = RequestMethod.PUT)
    public WebApiResponse<String> deleteOrRestore(
        @RequestBody @ApiParam(name = "uuidArray", value = "商品uuid数组", required = true) @Validated GoodsUuidArrayReq uuidArray,
        BindingResult validatedResult)
    {
        if (validatedResult.hasErrors())
        {
            return WebApiResponse.error(validatedResult.getFieldError().getDefaultMessage());
        }
        if (uuidArray.getDelFlag() == null)
        {
            return WebApiResponse.error("必须传入delFlag状态");
        }
        int result;
        try
        {
            result = goodsService.changeDelFlagBatch(uuidArray.getArray(), uuidArray.getDelFlag());
        }
        catch (Exception e)
        {
            LOG.error("/goods/deleteOrRestore:" + e);
            return WebApiResponse.error(WebApiResponse.ResponseMsg.EXCEPTION.getValue());
        }
        if (result > 0)
        {
            //发送消息
            esGoodsSender.send(uuidArray.getArray());
            return WebApiResponse.success(null, WebApiResponse.ResponseMsg.SUCCESS.getValue());
        }
        
        return WebApiResponse.error(WebApiResponse.ResponseMsg.FAIL.getValue());
    }
    
    @ApiOperation(value = "批量删除商品")
    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public WebApiResponse<String> delete(
        @RequestBody @ApiParam(name = "uuidArray", value = "商品uuid数组", required = true) @Validated GoodsUuidArrayReq uuidArray,
        BindingResult validatedResult)
    {
        if (validatedResult.hasErrors())
        {
            return WebApiResponse.error(validatedResult.getFieldError().getDefaultMessage());
        }
        int result;
        try
        {
            result = goodsService.delete(uuidArray.getArray());
        }
        catch (Exception e)
        {
            LOG.error("/goods/deleteOrRestore:" + e);
            return WebApiResponse.error(WebApiResponse.ResponseMsg.EXCEPTION.getValue());
        }
        if (result > 0)
        {
            //发送消息
            esGoodsSender.send(uuidArray.getArray());
            return WebApiResponse.success(null, WebApiResponse.ResponseMsg.SUCCESS.getValue());
        }
        
        return WebApiResponse.error(WebApiResponse.ResponseMsg.FAIL.getValue());
    }
    
    @ApiOperation(value = "添加商品")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public WebApiResponse<String> add(
        @RequestBody @ApiParam(name = "goodsAddParam", value = "商品信息", required = true) @Validated GoodsReq goodsAddParam,
        BindingResult bindingResult)
    {
        if (bindingResult.hasErrors())
        {
            return WebApiResponse.error(bindingResult.getFieldError().getDefaultMessage());
        }
        String result;
        try
        {
            result = goodsService.add(goodsAddParam);
        }
        catch (Exception e)
        {
            LOG.error("/goods/add:" + e);
            return WebApiResponse.error(WebApiResponse.ResponseMsg.EXCEPTION.getValue());
        }
        if (result.length() > 1)
        {
            //发送消息
            esGoodsSender.send(result);
            return WebApiResponse.success(result, WebApiResponse.ResponseMsg.SUCCESS.getValue());
        }
        else
        {
            return WebApiResponse.error(WebApiResponse.ResponseMsg.FAIL.getValue());
        }
    }
    
    @ApiOperation(value = "根据uuid查询商品信息")
    @RequestMapping(value = "/getInfo/{uuid}", method = RequestMethod.GET)
    public WebApiResponse<GoodsDto> findByUuid(
        @PathVariable @ApiParam(name = "uuid", value = "商品uuid", required = true) String uuid)
    {
        LOG.info("根据uuid查询商品信息，uuid=" + uuid);
        GoodsDto goodsDto;
        try
        {
            goodsDto = goodsService.findGoodsByUuid(uuid);
        }
        catch (Exception e)
        {
            LOG.error("/goods/getInfo/{uuid}:" + e);
            return WebApiResponse.error(WebApiResponse.ResponseMsg.EXCEPTION.getValue());
        }
        if (goodsDto != null)
        {
            return WebApiResponse.success(goodsDto);
        }
        return WebApiResponse.error(WebApiResponse.ResponseMsg.FAIL.getValue());
    }
    
    @ApiOperation(value = "修改商品")
    @RequestMapping(value = "/edit", method = RequestMethod.PUT)
    public WebApiResponse<String> edit(
        @RequestBody @ApiParam(name = "goodsEditParam", value = "商品信息", required = true) @Validated GoodsReq goodsEditParam,
        BindingResult bindingResult)
    {
        if (bindingResult.hasErrors())
        {
            return WebApiResponse.error(bindingResult.getFieldError().getDefaultMessage());
        }
        int result;
        try
        {
            result = goodsService.edit(goodsEditParam);
        }
        catch (Exception e)
        {
            LOG.error("/goods/edit:" + e);
            return WebApiResponse.error(WebApiResponse.ResponseMsg.EXCEPTION.getValue());
        }
        if (result > 0)
        {
            //发送消息
            esGoodsSender.send(goodsEditParam.getUuid());
            return WebApiResponse.success(null, WebApiResponse.ResponseMsg.SUCCESS.getValue());
        }
        else
        {
            return WebApiResponse.success(WebApiResponse.ResponseMsg.FAIL.getValue());
        }
    }
    
    @ApiOperation(value = "商品批量上下架")
    @RequestMapping(value = "/upOrDownBatch", method = RequestMethod.PUT)
    public WebApiResponse<String> upOrDownBatch(
        @RequestBody @ApiParam(name = "uuidArray", value = "商品uuid数组", required = true) @Validated GoodsUuidArrayReq uuidArray,
        BindingResult validatedResult)
    {
        if (validatedResult.hasErrors())
        {
            return WebApiResponse.error(validatedResult.getFieldError().getDefaultMessage());
        }
        if (uuidArray.getGoodsOperation() == null)
        {
            return WebApiResponse.error("必须传入操作码");
        }
        int result;
        try
        {
            result = goodsService.upOrDownBatch(uuidArray.getArray(), uuidArray.getGoodsOperation());
        }
        catch (Exception e)
        {
            LOG.error("/goods/upOrDownBatch:" + e);
            return WebApiResponse.error(WebApiResponse.ResponseMsg.EXCEPTION.getValue());
        }
        if (result > 0)
        {
            //发送消息
            esGoodsSender.send(uuidArray.getArray());
            return WebApiResponse.success(null, WebApiResponse.ResponseMsg.SUCCESS.getValue());
        }
        return WebApiResponse.error(WebApiResponse.ResponseMsg.FAIL.getValue());
    }
    
    @ApiOperation(value = "分页查询回收站商品")
    @RequestMapping(value = "/findDel", method = RequestMethod.POST)
    public WebApiResponse<?> findDelByPage(
        @RequestBody @ApiParam(name = "goodsParam", value = "查询条件", required = true) DelGoodsPageReq goodsParam)
    {
        PageInfo<GoodsPageDto> page;
        try
        {
            page = goodsService.findDelByPage(goodsParam);
        }
        catch (Exception e)
        {
            LOG.error("/goods/findDel:" + e);
            return WebApiResponse.error(WebApiResponse.ResponseMsg.EXCEPTION.getValue());
        }
        if (page != null)
        {
            return WebApiResponse.success(page);
        }
        
        return WebApiResponse.error(WebApiResponse.ResponseMsg.FAIL.getValue());
    }

    @ApiOperation(value = "个人商品详情")
    @RequestMapping(value = "/show/{goodsUuid}", method = RequestMethod.GET)
    public WebApiResponse<?> showDetail(@PathVariable @ApiParam(name = "goodsUuid", value = "查询条件", required = true) String goodsUuid){

        WebApiResponse<?> result = null;

        try {

            result = goodsService.showDetail(goodsUuid);

        }catch (Exception e){
            LOG.error("/show/goodsUuid: 商品详情 " + e);
            return WebApiResponse.error(WebApiResponse.ResponseMsg.EXCEPTION.getValue());
        }

        return result;
    }

}
