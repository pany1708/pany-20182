package com.kingthy.platform.controller.goods;

import com.github.pagehelper.PageInfo;
import com.kingthy.platform.dto.goods.BuyersShowDto;
import com.kingthy.platform.dto.goods.ReviewReq;
import com.kingthy.platform.dto.goods.ReviewUpdateReq;
import com.kingthy.platform.response.WebApiResponse;
import com.kingthy.platform.service.goods.ReviewService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * ReviewController
 * <p>
 * yuanml 2017/5/16
 *
 * @version 1.0.0
 */
@RestController
@RequestMapping("review")
public class ReviewController
{
    private static final Logger LOG = LoggerFactory.getLogger(ReviewController.class);
    
    @Autowired
    private ReviewService reviewService;
    
    @ApiOperation(value = "商品评论列表查询")
    @RequestMapping(value = "list", method = RequestMethod.POST)
    public WebApiResponse reviewList(@RequestBody @ApiParam(name = "reviewReq") ReviewReq reviewReq)
    {
        PageInfo<BuyersShowDto> buyersShows;
        try
        {
            buyersShows = reviewService.listReview(reviewReq);
        }
        catch (Exception e)
        {
            LOG.error("商品评论列表查询出错，异常信息" + e);
            return WebApiResponse.error(WebApiResponse.ResponseMsg.EXCEPTION.getValue());
        }
        if (buyersShows.getTotal() >= 0)
        {
            return WebApiResponse.success(buyersShows, WebApiResponse.ResponseMsg.SUCCESS.getValue());
        }
        else
        {
            return WebApiResponse.error(WebApiResponse.ResponseMsg.FAIL.getValue());
        }
    }
    
    @ApiOperation(value = "商品评论详情")
    @RequestMapping(value = "detail/{reviewUuid}", method = RequestMethod.GET)
    public WebApiResponse reviewDetail(@PathVariable String reviewUuid)
    {
        BuyersShowDto buyersShowDto;
        try
        {
            buyersShowDto = reviewService.selectReview(reviewUuid);
        }
        catch (Exception e)
        {
            LOG.error("商品评论详情出错，异常信息" + e);
            return WebApiResponse.error(WebApiResponse.ResponseMsg.EXCEPTION.getValue());
        }
        if (null != buyersShowDto)
        {
            return WebApiResponse.success(buyersShowDto, WebApiResponse.ResponseMsg.SUCCESS.getValue());
        }
        else
        {
            return WebApiResponse.error(WebApiResponse.ResponseMsg.FAIL.getValue());
        }
    }
    
    @ApiOperation(value = "商品评论显示/隐藏/删除")
    @RequestMapping(value = "updateReview", method = RequestMethod.POST)
    public WebApiResponse reviewDetailWithPlatForm(
        @RequestBody @ApiParam(name = "reviewUpdateReq") ReviewUpdateReq reviewUpdateReq)
    {
        int result;
        try
        {
            result = reviewService.updateReview(reviewUpdateReq);
        }
        catch (Exception e)
        {
            LOG.error("商品评论显示隐藏出错，异常信息" + e);
            return WebApiResponse.error(WebApiResponse.ResponseMsg.EXCEPTION.getValue());
        }
        if (0 <= result)
        {
            return WebApiResponse.success(result, WebApiResponse.ResponseMsg.SUCCESS.getValue());
        }
        else
        {
            return WebApiResponse.error(WebApiResponse.ResponseMsg.FAIL.getValue());
        }
    }
    
}
