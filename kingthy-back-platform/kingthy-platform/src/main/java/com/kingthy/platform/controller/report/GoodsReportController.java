package com.kingthy.platform.controller.report;

import com.kingthy.platform.entity.report.ReportGoodsData;
import com.kingthy.platform.entity.report.ReportOnsaleGoods;
import com.kingthy.platform.response.WebApiResponse;
import com.kingthy.platform.service.report.ReportGoodsDataService;
import com.kingthy.platform.service.report.ReportOnsaleGoodsService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author chenz
 * @version 1.0.0
 * @class_name GoodsReportController
 * @description 商品统计数据
 * @create 2017/7/26
 */
@RestController
@RequestMapping(value = "/goodsReport")
public class GoodsReportController {

    private static final Logger LOG = LoggerFactory.getLogger(GoodsReportController.class);


    @Autowired
    private ReportGoodsDataService reportGoodsDataService;

    @Autowired
    private ReportOnsaleGoodsService reportOnsaleGoodsService;

    @ApiOperation(value = "查询商品数据")
    @RequestMapping(value = "/goodsData/{timeType}", method = RequestMethod.GET)
    public WebApiResponse<?> goodsData(@PathVariable int timeType)
    {
        try
        {
            List<ReportGoodsData> result = reportGoodsDataService.findByTimeType(timeType);
            return WebApiResponse.success(result);
        }
        catch (Exception e)
        {
            LOG.error("/goodsReport/goodsData:" + e);
            return WebApiResponse.error(WebApiResponse.ResponseMsg.EXCEPTION.getValue());
        }
    }

    @ApiOperation(value = "根据价格区间查询商品数量")
    @RequestMapping(value = "/priceRangeNum", method = RequestMethod.GET)
    public WebApiResponse<?> priceRangeNum()
    {
        try
        {
            List<ReportOnsaleGoods> result = reportOnsaleGoodsService.findAll();
            return WebApiResponse.success(result);
        }
        catch (Exception e)
        {
            LOG.error("/goodsReport/priceRangeNum:" + e);
            return WebApiResponse.error(WebApiResponse.ResponseMsg.EXCEPTION.getValue());
        }
    }
}
