package com.kingthy.platform.job;

import com.kingthy.platform.dto.report.GoodsPriceRangeNumDto;
import com.kingthy.platform.dto.report.ReportGoodsDataReq;
import com.kingthy.platform.entity.report.ReportGoodsData;
import com.kingthy.platform.service.report.GoodsReportService;
import com.kingthy.platform.service.report.ReportGoodsDataService;
import com.kingthy.platform.service.report.ReportOnsaleGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * @author chenz
 * @version 1.0.0
 * @class_name GoodsReportJob
 * @description 商品统计定时任务
 * @create 2017/7/27
 */
@Component
@Configurable
public class GoodsReportJob {

    @Autowired
    private ReportGoodsDataService reportGoodsDataService;

    @Autowired
    private GoodsReportService goodsReportService;

    @Autowired
    private ReportOnsaleGoodsService reportOnsaleGoodsService;

    /**
     * 查询并存储昨天上架商品总数量(每天凌晨3点执行)
     */
    @Scheduled(cron = "0 0 3 * * ?")
    public void yesterdayOnSaleNum(){
        ReportGoodsDataReq reportGoodsDataReq = new ReportGoodsDataReq();
        reportGoodsDataReq.setTimeRage(ReportGoodsDataReq.TimeRage.yesterday);
        reportGoodsDataReq.setDataType(ReportGoodsDataReq.DataType.onSale);
        addReportGoodsData(reportGoodsDataReq);
    }

    /**
     * 查询并存储上周上架商品总数量(每天凌晨3点执行)
     */
    @Scheduled(cron = "0 0 3 * * ?")
    public void lastWeekOnSaleNum(){
        ReportGoodsDataReq reportGoodsDataReq = new ReportGoodsDataReq();
        reportGoodsDataReq.setTimeRage(ReportGoodsDataReq.TimeRage.lastWeek);
        reportGoodsDataReq.setDataType(ReportGoodsDataReq.DataType.onSale);
        addReportGoodsData(reportGoodsDataReq);
    }

    /**
     * 查询并存储前15天上架商品总数量(每天凌晨3点执行)
     */
    @Scheduled(cron = "0 0 3 * * ?")
    public void lastHalfMonthOnSaleNum(){
        ReportGoodsDataReq reportGoodsDataReq = new ReportGoodsDataReq();
        reportGoodsDataReq.setTimeRage(ReportGoodsDataReq.TimeRage.lastHalfMonth);
        reportGoodsDataReq.setDataType(ReportGoodsDataReq.DataType.onSale);
        addReportGoodsData(reportGoodsDataReq);
    }

    /**
     * 查询并存储上个月上架商品总数量(每天凌晨3点执行)
     */
    @Scheduled(cron = "0 0 3 * * ?")
    public void lastMonthOnSaleNum(){
        ReportGoodsDataReq reportGoodsDataReq = new ReportGoodsDataReq();
        reportGoodsDataReq.setTimeRage(ReportGoodsDataReq.TimeRage.lastMonth);
        reportGoodsDataReq.setDataType(ReportGoodsDataReq.DataType.onSale);
        addReportGoodsData(reportGoodsDataReq);
    }

    /**
     * 添加至统计结果表中
     * @param reportGoodsDataReq
     */
    private void addReportGoodsData(ReportGoodsDataReq reportGoodsDataReq){
        int num = goodsReportService.findGoodsDataByDataType(reportGoodsDataReq);
        ReportGoodsData reportGoodsData = new ReportGoodsData();
        reportGoodsData.setDataType(reportGoodsDataReq.getDataType().getValue());
        reportGoodsData.setTimeType(reportGoodsDataReq.getTimeRage().getValue());
        reportGoodsData.setNum(num);
        reportGoodsData.setCreateDate(new Date());
        reportGoodsData.setDelFlag(false);
        reportGoodsDataService.add(reportGoodsData);
    }

    /**
     * 查询并存储昨天上衣商品总数量
     */
    @Scheduled(cron = "0 0 3 * * ?")
    public void yesterdayJacketNum(){
        ReportGoodsDataReq reportGoodsDataReq = new ReportGoodsDataReq();
        reportGoodsDataReq.setTimeRage(ReportGoodsDataReq.TimeRage.yesterday);
        reportGoodsDataReq.setDataType(ReportGoodsDataReq.DataType.jacket);
        addReportGoodsData(reportGoodsDataReq);
    }

    /**
     * 查询并存储上周上衣商品总数量
     */
    @Scheduled(cron = "0 0 3 * * ?")
    public void lastWeekJacketNum(){
        ReportGoodsDataReq reportGoodsDataReq = new ReportGoodsDataReq();
        reportGoodsDataReq.setTimeRage(ReportGoodsDataReq.TimeRage.lastWeek);
        reportGoodsDataReq.setDataType(ReportGoodsDataReq.DataType.jacket);
        addReportGoodsData(reportGoodsDataReq);
    }

    /**
     * 查询并存储前15天上衣商品总数量
     */
    @Scheduled(cron = "0 0 3 * * ?")
    public void lastHalfMonthJacketNum(){
        ReportGoodsDataReq reportGoodsDataReq = new ReportGoodsDataReq();
        reportGoodsDataReq.setTimeRage(ReportGoodsDataReq.TimeRage.lastHalfMonth);
        reportGoodsDataReq.setDataType(ReportGoodsDataReq.DataType.jacket);
        addReportGoodsData(reportGoodsDataReq);
    }

    /**
     * 查询并存储上个月天上衣商品总数量
     */
    @Scheduled(cron = "0 0 3 * * ?")
    public void lastMonthJacketNum(){
        ReportGoodsDataReq reportGoodsDataReq = new ReportGoodsDataReq();
        reportGoodsDataReq.setTimeRage(ReportGoodsDataReq.TimeRage.lastMonth);
        reportGoodsDataReq.setDataType(ReportGoodsDataReq.DataType.jacket);
        addReportGoodsData(reportGoodsDataReq);
    }

    /**
     * 查询并存储昨天裤子商品总数量
     */
    @Scheduled(cron = "0 0 3 * * ?")
    public void yesterdayPantsNum(){
        ReportGoodsDataReq reportGoodsDataReq = new ReportGoodsDataReq();
        reportGoodsDataReq.setTimeRage(ReportGoodsDataReq.TimeRage.yesterday);
        reportGoodsDataReq.setDataType(ReportGoodsDataReq.DataType.pants);
        addReportGoodsData(reportGoodsDataReq);
    }

    /**
     * 查询并存储上周裤子商品总数量
     */
    @Scheduled(cron = "0 0 3 * * ?")
    public void lastWeekPantsNum(){
        ReportGoodsDataReq reportGoodsDataReq = new ReportGoodsDataReq();
        reportGoodsDataReq.setTimeRage(ReportGoodsDataReq.TimeRage.lastWeek);
        reportGoodsDataReq.setDataType(ReportGoodsDataReq.DataType.pants);
        addReportGoodsData(reportGoodsDataReq);
    }

    /**
     * 查询并存储前15天裤子商品总数量
     */
    @Scheduled(cron = "0 0 3 * * ?")
    public void lastHalfMonthPantsNum(){
        ReportGoodsDataReq reportGoodsDataReq = new ReportGoodsDataReq();
        reportGoodsDataReq.setTimeRage(ReportGoodsDataReq.TimeRage.lastHalfMonth);
        reportGoodsDataReq.setDataType(ReportGoodsDataReq.DataType.pants);
        addReportGoodsData(reportGoodsDataReq);
    }

    /**
     * 查询并存储上个月天裤子商品总数量
     */
    @Scheduled(cron = "0 0 3 * * ?")
    public void lastMonthPantsNum(){
        ReportGoodsDataReq reportGoodsDataReq = new ReportGoodsDataReq();
        reportGoodsDataReq.setTimeRage(ReportGoodsDataReq.TimeRage.lastMonth);
        reportGoodsDataReq.setDataType(ReportGoodsDataReq.DataType.pants);
        addReportGoodsData(reportGoodsDataReq);
    }

    /**
     * 查询并存储昨天裙子商品总数量
     */
    @Scheduled(cron = "0 0 3 * * ?")
    public void yesterdayDressNum(){
        ReportGoodsDataReq reportGoodsDataReq = new ReportGoodsDataReq();
        reportGoodsDataReq.setTimeRage(ReportGoodsDataReq.TimeRage.yesterday);
        reportGoodsDataReq.setDataType(ReportGoodsDataReq.DataType.dress);
        addReportGoodsData(reportGoodsDataReq);
    }

    /**
     * 查询并存储上周裙子商品总数量
     */
    @Scheduled(cron = "0 0 3 * * ?")
    public void lastWeekDressNum(){
        ReportGoodsDataReq reportGoodsDataReq = new ReportGoodsDataReq();
        reportGoodsDataReq.setTimeRage(ReportGoodsDataReq.TimeRage.lastWeek);
        reportGoodsDataReq.setDataType(ReportGoodsDataReq.DataType.dress);
        addReportGoodsData(reportGoodsDataReq);
    }

    /**
     * 查询并存储前15天裙子商品总数量
     */
    @Scheduled(cron = "0 0 3 * * ?")
    public void lastHalfMonthDressNum(){
        ReportGoodsDataReq reportGoodsDataReq = new ReportGoodsDataReq();
        reportGoodsDataReq.setTimeRage(ReportGoodsDataReq.TimeRage.lastHalfMonth);
        reportGoodsDataReq.setDataType(ReportGoodsDataReq.DataType.dress);
        addReportGoodsData(reportGoodsDataReq);
    }

    /**
     * 查询并存储上个月天裙子商品总数量
     */
    @Scheduled(cron = "0 0 3 * * ?")
    public void lastMonthDressNum(){
        ReportGoodsDataReq reportGoodsDataReq = new ReportGoodsDataReq();
        reportGoodsDataReq.setTimeRage(ReportGoodsDataReq.TimeRage.lastMonth);
        reportGoodsDataReq.setDataType(ReportGoodsDataReq.DataType.dress);
        addReportGoodsData(reportGoodsDataReq);
    }

    /**
     * 查询并存储昨天外套商品总数量
     */
    @Scheduled(cron = "0 0 3 * * ?")
    public void yesterdaySuitNum(){
        ReportGoodsDataReq reportGoodsDataReq = new ReportGoodsDataReq();
        reportGoodsDataReq.setTimeRage(ReportGoodsDataReq.TimeRage.yesterday);
        reportGoodsDataReq.setDataType(ReportGoodsDataReq.DataType.suit);
        addReportGoodsData(reportGoodsDataReq);
    }

    /**
     * 查询并存储上周外套商品总数量
     */
    @Scheduled(cron = "0 0 3 * * ?")
    public void lastWeekSuitNum(){
        ReportGoodsDataReq reportGoodsDataReq = new ReportGoodsDataReq();
        reportGoodsDataReq.setTimeRage(ReportGoodsDataReq.TimeRage.lastWeek);
        reportGoodsDataReq.setDataType(ReportGoodsDataReq.DataType.suit);
        addReportGoodsData(reportGoodsDataReq);
    }

    /**
     * 查询并存储前15天外套商品总数量
     */
    @Scheduled(cron = "0 0 3 * * ?")
    public void lastHalfMonthSuitNum(){
        ReportGoodsDataReq reportGoodsDataReq = new ReportGoodsDataReq();
        reportGoodsDataReq.setTimeRage(ReportGoodsDataReq.TimeRage.lastHalfMonth);
        reportGoodsDataReq.setDataType(ReportGoodsDataReq.DataType.suit);
        addReportGoodsData(reportGoodsDataReq);
    }

    /**
     * 查询并存储上个月天外套商品总数量
     */
    @Scheduled(cron = "0 0 3 * * ?")
    public void lastMonthSuitNum(){
        ReportGoodsDataReq reportGoodsDataReq = new ReportGoodsDataReq();
        reportGoodsDataReq.setTimeRage(ReportGoodsDataReq.TimeRage.lastMonth);
        reportGoodsDataReq.setDataType(ReportGoodsDataReq.DataType.suit);
        addReportGoodsData(reportGoodsDataReq);
    }

    /**
     * 查询并存储昨天上架商品价格分布
     */
    @Scheduled(cron = "0 0 3 * * ?")
    public void addOnSaleGoodsData(){
        List<GoodsPriceRangeNumDto> list = goodsReportService.findNumByPriceRange();
        reportOnsaleGoodsService.add(list);
    }
}
