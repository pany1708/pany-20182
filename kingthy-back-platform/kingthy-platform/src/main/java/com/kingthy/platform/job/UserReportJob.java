package com.kingthy.platform.job;

import com.kingthy.platform.dto.report.*;
import com.kingthy.platform.entity.report.ReportRepeatBuyRate;
import com.kingthy.platform.entity.report.ReportUserData;
import com.kingthy.platform.service.report.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author chenz
 * @version 1.0.0
 * @class_name UserReportJob
 * @description 用户数据统计定时任务
 * @create 2017/7/27
 */
@Component
@Configurable
public class UserReportJob {

    private static final Logger LOG = LoggerFactory.getLogger(UserReportJob.class);

    @Autowired
    private UserReportService userReportService;

    @Autowired
    private ReportUserDataService reportUserDataService;

    @Autowired
    private ReportUserRegisterService reportUserRegisterService;

    @Autowired
    private ReportRepeatBuyRateService reportRepeatBuyRateService;

    @Autowired
    private ReportAgeGroupBuyService reportAgeGroupBuyService;

    @Autowired
    private ReportOrderAreaService reportOrderAreaService;

    /**
     * 查询并存储昨天全站注册用户数量(每天凌晨2点执行)
     */
    @Scheduled(cron = "0 0 2 * * ?")
    public void yesterdayRegisterUser(){
        UserDataReq userDataReq = new UserDataReq();
        userDataReq.setTimeRage(UserDataReq.TimeRage.yesterday);
        try {
            //查询
            int  result = userReportService.findRegisterUserNum(userDataReq);
            addRegisterUser(result, ReportUserDataReq.DataType.register.getValue(),
                    ReportUserDataReq.TimeType.yesterday.getValue());
            System.out.println("昨天全站注册用户数量为："+result);
        } catch (Exception e) {
            LOG.error("yesterdayRegisterUser 定时任务(查询昨天全站注册用户)出错,异常信息" + e);
        }
    }

    /**
     * 查询并存储过去一周全站注册用户数量(每天凌晨2点执行)
     */
    @Scheduled(cron = "0 0 2 * * ?")
    public void lastWeekRegisterUser(){
        UserDataReq userDataReq = new UserDataReq();
        userDataReq.setTimeRage(UserDataReq.TimeRage.lastWeek);
        try {
            int  result = userReportService.findRegisterUserNum(userDataReq);
            addRegisterUser(result,ReportUserDataReq.DataType.register.getValue(),
                    ReportUserDataReq.TimeType.lastWeek.getValue());
            System.out.println("过去一周全站注册用户数量为："+result);
        } catch (Exception e) {
            LOG.error("lastWeekRegisterUser 定时任务(查询过去一周全站注册用户)出错,异常信息" + e);
        }
    }

    /**
     * 查询并存储过去15天全站注册用户数量(每天凌晨2点执行)
     */
    @Scheduled(cron = "0 0 2 * * ?")
    public void lastHalfMonthRegisterUser(){
        UserDataReq userDataReq = new UserDataReq();
        userDataReq.setTimeRage(UserDataReq.TimeRage.lastHalfMonth);
        try {
            int  result = userReportService.findRegisterUserNum(userDataReq);
            addRegisterUser(result,ReportUserDataReq.DataType.register.getValue(),
                    ReportUserDataReq.TimeType.lastHalfMonth.getValue());
            System.out.println("过去15天全站注册用户数量为："+result);
        } catch (Exception e) {
            LOG.error("lastHalfMonthRegisterUser 定时任务(查询过去15天全站注册用户)出错,异常信息" + e);
        }
    }

    /**
     * 查询并存储过去一个月全站注册用户数量(每天凌晨2点执行)
     */
    @Scheduled(cron = "0 0 2 * * ?")
    public void lastMonthRegisterUser(){
        UserDataReq userDataReq = new UserDataReq();
        userDataReq.setTimeRage(UserDataReq.TimeRage.lastMonth);
        try {
            int  result = userReportService.findRegisterUserNum(userDataReq);
            addRegisterUser(result,ReportUserDataReq.DataType.register.getValue(),
                    ReportUserDataReq.TimeType.lastMonth.getValue());
            System.out.println("过去一个月全站注册用户数量为："+result);
        } catch (Exception e) {
            LOG.error("lastMonthRegisterUser 定时任务(查询过去一个月全站注册用户)出错,异常信息" + e);
        }
    }


    /**
     * 查询并存储昨天用户下单数量(每天凌晨2点执行)
     */
    @Scheduled(cron = "0 0 2 * * ?")
    public void yesterdayOrdersNum(){
        UserDataReq userDataReq = new UserDataReq();
        userDataReq.setTimeRage(UserDataReq.TimeRage.yesterday);
        try {
            int  result = userReportService.findPlaceOrderNum(userDataReq);
            addRegisterUser(result,ReportUserDataReq.DataType.order.getValue(),
                    ReportUserDataReq.TimeType.yesterday.getValue());
        } catch (Exception e) {
            LOG.error("yesterdayOrdersNum 定时任务(查询并存储昨天用户下单数量)出错,异常信息" + e);
        }
    }

    /**
     * 查询并存储上周用户下单数量(每天凌晨2点执行)
     */
    @Scheduled(cron = "0 0 2 * * ?")
    public void lastWeekOrdersNum(){
        UserDataReq userDataReq = new UserDataReq();
        userDataReq.setTimeRage(UserDataReq.TimeRage.lastWeek);
        try {
            int  result = userReportService.findPlaceOrderNum(userDataReq);
            addRegisterUser(result,ReportUserDataReq.DataType.order.getValue(),
                    ReportUserDataReq.TimeType.lastWeek.getValue());
        } catch (Exception e) {
            LOG.error("lastWeekOrdersNum 定时任务(查询并存储上周用户下单数量)出错,异常信息" + e);
        }
    }

    /**
     * 查询并存储前15天用户下单数量(每天凌晨2点执行)
     */
    @Scheduled(cron = "0 0 2 * * ?")
    public void lastHalfMonthOrdersNum(){
        UserDataReq userDataReq = new UserDataReq();
        userDataReq.setTimeRage(UserDataReq.TimeRage.lastHalfMonth);
        try {
            int  result = userReportService.findPlaceOrderNum(userDataReq);
            addRegisterUser(result,ReportUserDataReq.DataType.order.getValue(),
                    ReportUserDataReq.TimeType.lastHalfMonth.getValue());
        } catch (Exception e) {
            LOG.error("lastHalfMonthOrdersNum 定时任务(查询并存储前15天用户下单数量)出错,异常信息" + e);
        }
    }

    /**
     * 查询并存储上个月用户下单数量(每天凌晨2点执行)
     */
    @Scheduled(cron = "0 0 2 * * ?")
    public void lastMonthOrdersNum(){
        UserDataReq userDataReq = new UserDataReq();
        userDataReq.setTimeRage(UserDataReq.TimeRage.lastMonth);
        try {
            int  result = userReportService.findPlaceOrderNum(userDataReq);
            addRegisterUser(result,ReportUserDataReq.DataType.order.getValue(),
                    ReportUserDataReq.TimeType.lastMonth.getValue());
        } catch (Exception e) {
            LOG.error("lastMonthOrdersNum 定时任务(查询并存储上个月用户下单数量)出错,异常信息" + e);
        }
    }

    /**
     * 查询并存储昨天用户购买量(每天凌晨2点执行)
     */
    @Scheduled(cron = "0 0 2 * * ?")
    public void yesterdayBuyNum(){
        UserDataReq userDataReq = new UserDataReq();
        userDataReq.setTimeRage(UserDataReq.TimeRage.yesterday);
        try {
            int  result = userReportService.findBuyNum(userDataReq);
            addRegisterUser(result,ReportUserDataReq.DataType.buy.getValue(),
                    ReportUserDataReq.TimeType.yesterday.getValue());
        } catch (Exception e) {
            LOG.error("yesterdayBuyNum 定时任务(查询并存储昨天用户购买量)出错,异常信息" + e);
        }
    }

    /**
     * 查询并存储上周用户购买量(每天凌晨2点执行)
     */
    @Scheduled(cron = "0 0 2 * * ?")
    public void lastWeekBuyNum(){
        UserDataReq userDataReq = new UserDataReq();
        userDataReq.setTimeRage(UserDataReq.TimeRage.lastWeek);
        try {
            int  result = userReportService.findBuyNum(userDataReq);
            addRegisterUser(result,ReportUserDataReq.DataType.buy.getValue(),
                    ReportUserDataReq.TimeType.lastWeek.getValue());
        } catch (Exception e) {
            LOG.error("lastWeekBuyNum 定时任务(查询并存储上周用户购买量)出错,异常信息" + e);
        }
    }

    /**
     * 查询并存储前15天用户购买量(每天凌晨2点执行)
     */
    @Scheduled(cron = "0 0 2 * * ?")
    public void lastHalfMonthBuyNum(){
        UserDataReq userDataReq = new UserDataReq();
        userDataReq.setTimeRage(UserDataReq.TimeRage.lastHalfMonth);
        try {
            int  result = userReportService.findBuyNum(userDataReq);
            addRegisterUser(result,ReportUserDataReq.DataType.buy.getValue(),
                    ReportUserDataReq.TimeType.lastHalfMonth.getValue());
        } catch (Exception e) {
            LOG.error("lastHalfMonthBuyNum 定时任务(查询并存储前15天用户购买量)出错,异常信息" + e);
        }
    }

    /**
     * 查询并存储上个月用户购买量(每天凌晨2点执行)
     */
    @Scheduled(cron = "0 0 2 * * ?")
    public void lastMonthBuyNum(){
        UserDataReq userDataReq = new UserDataReq();
        userDataReq.setTimeRage(UserDataReq.TimeRage.lastMonth);
        try {
            int  result = userReportService.findBuyNum(userDataReq);
            addRegisterUser(result,ReportUserDataReq.DataType.buy.getValue(),
                    ReportUserDataReq.TimeType.lastMonth.getValue());
        } catch (Exception e) {
            LOG.error("lastMonthBuyNum 定时任务(查询并存储上个月用户购买量)出错,异常信息" + e);
        }
    }


    /**
     * 添加不同类型的ReportUserData
     * @param result
     * @param dataType
     * @param timeType
     */
    public void addRegisterUser(int result,int dataType,int timeType){
        //插入
        ReportUserData reportUserData = new ReportUserData();
        reportUserData.setDataType(dataType);
        reportUserData.setNumber(result);
        reportUserData.setStatus(true);
        reportUserData.setTimeType(timeType);
        reportUserData.setDelFlag(false);
        reportUserData.setVersion(1);
        reportUserData.setCreateDate(new Date());
        reportUserDataService.add(reportUserData);
    }

    /**
     * 查询并存储过去一周每日注册用户数(每天凌晨2点执行)
     */
    @Scheduled(cron = "0 0 2 * * ?")
    public void lastWeekNewUser(){
        UserDataReq userDataReq = new UserDataReq();
        userDataReq.setTimeRage(UserDataReq.TimeRage.lastWeek);
        try {
            List<EverydayRegisterNumDto> result = userReportService.lastWeekNewUserNum(userDataReq);
            if (result!=null && result.size()>0){
                reportUserRegisterService.add(result);
            }
        } catch (Exception e) {
            LOG.error("lastWeekNewUser 定时任务(查询过去一周每日注册用户数)出错,异常信息" + e);
        }
    }

    /**
     * 查询并存储昨天的重复购买率(每天凌晨2点执行)
     */
    @Scheduled(cron = "0 0 2 * * ?")
    public void repeatBuyRate(){
        try {
            BigDecimal result = userReportService.repeatBuyRate();
            ReportRepeatBuyRate reportRepeatBuyRate = new ReportRepeatBuyRate();
            reportRepeatBuyRate.setRate(result);
            reportRepeatBuyRate.setStatus(true);
            reportRepeatBuyRate.setCreateDate(new Date());
            reportRepeatBuyRate.setDelFlag(false);
            reportRepeatBuyRate.setVersion(1);
            reportRepeatBuyRateService.add(reportRepeatBuyRate);
        } catch (Exception e) {
            LOG.error("repeatBuyRate 定时任务(查询并存储昨天的重复购买率)出错,异常信息" + e);
        }
    }

    /**
     * 查询并存储不同年龄分段购买情况
     */
    @Scheduled(cron = "0 0 2 * * ?")
    public void findBuyDataByAge(){
        try {
            List<AgeBuyDataDto> result = userReportService.findBuyDataByAge();
            if (result !=null && result.size()>0){
                reportAgeGroupBuyService.add(result);
            }
        } catch (Exception e) {
            LOG.error("findBuyDataByAge 定时任务(查询并存储不同年龄分段购买情况)出错,异常信息" + e);
        }
    }

    /**
     * 查询并存储过去一周订单区域分布情况
     */
    @Scheduled(cron = "0 0 2 * * ?")
    public void findOrdersByArea(){
        UserDataReq userDataReq = new UserDataReq();
        userDataReq.setTimeRage(UserDataReq.TimeRage.lastMonth);
        try {
            List<OrderAreaDto> result = userReportService.findOrdersByArea(userDataReq);
            if (result != null && result.size()>0){
                reportOrderAreaService.add(result);
            }
        } catch (Exception e) {
            LOG.error("findOrdersByArea 定时任务(查询并存储过去一周订单区域分布情况)出错,异常信息" + e);
        }
    }
}
