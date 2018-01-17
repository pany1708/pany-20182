package com.kingthy.platform.controller.goods;

import com.kingthy.platform.dto.order.GoodsOfficiallyReq;
import com.kingthy.platform.response.WebApiResponse;
import com.kingthy.platform.service.goods.GoodsOfficiallyService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @AUTHORS xumin
 * @Description:
 * @DATE Created by 15:39 on 2017/7/26.
 * @Modified by:
 */
@RestController
@RequestMapping(value = "/officially/goods")
public class GoodsOfficiallyController
{

    private static final Logger LOG = LoggerFactory.getLogger(GoodsOfficiallyController.class);

    @Autowired
    private GoodsOfficiallyService goodsOfficiallyService;

    @ApiOperation(value = "官方商品详情")
    @RequestMapping(value = "/show/{goodsUuid}", method = RequestMethod.GET)
    public WebApiResponse<?> showOfficiallyDetail(@PathVariable @ApiParam(name = "goodsUuid", value = "查询条件", required = true) String goodsUuid){

        WebApiResponse<?> result = null;

        try {

            result = goodsOfficiallyService.showOfficiallyDetail(goodsUuid);

        }catch (Exception e){
            LOG.error("/show/{goodsUuid}: 商品详情 " + e);
            return WebApiResponse.error(WebApiResponse.ResponseMsg.EXCEPTION.getValue());
        }

        return result;
    }

    @ApiOperation(value = "创建商品")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public WebApiResponse<?> createGoods(@RequestBody @ApiParam(name = "GoodsOfficiallyReq", value = "查询条件", required = true) GoodsOfficiallyReq req){

        WebApiResponse<?> result = null;

        try {

            result = goodsOfficiallyService.createGoods(req);

        }catch (Exception e){
            LOG.error("/show/create: 创建商品 " + e);
            return WebApiResponse.error(WebApiResponse.ResponseMsg.EXCEPTION.getValue());
        }

        return result;
    }

    @ApiOperation(value = "商品上下架")
    @RequestMapping(value = "/upOrDownBatch", method = RequestMethod.PUT)
    public WebApiResponse<?> upOrDownBatch(GoodsOfficiallyReq.UpOrDownBatch req){

        WebApiResponse<?> result = null;

        try {

            result = goodsOfficiallyService.upOrDownBatch(req);

        }catch (Exception e){
            LOG.error("/show/upOrDownBatch: 商品上下架 " + e);
            return WebApiResponse.error(WebApiResponse.ResponseMsg.EXCEPTION.getValue());
        }

        return result;
    }
}
