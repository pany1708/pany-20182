package com.kingthy.platform.controller.report;

import com.kingthy.platform.dto.report.*;
import com.kingthy.platform.response.WebApiResponse;
import com.kingthy.platform.service.report.*;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author chenz
 * @version 1.0.0
 * @class_name ReportController
 * @description 统计报告
 * @create 2017/7/20
 */
@RestController
@RequestMapping(value = "/userReport")
public class UserReportController {

    private static final Logger LOG = LoggerFactory.getLogger(UserReportController.class);


    @Autowired
    private ReportUserDataService reportUserDataService;

    @Autowired
    private ReportAgeGroupBuyService reportAgeGroupBuyService;

    @Autowired
    private ReportOrderAreaService reportOrderAreaService;

    @Autowired
    private ReportRepeatBuyRateService reportRepeatBuyRateService;

    @Autowired
    private ReportUserRegisterService reportUserRegisterService;

    @ApiOperation(value = "查询全站用户数据", notes = "查询全站用户数据")
    @RequestMapping(value = "/userData", method = RequestMethod.POST)
    public WebApiResponse<?> registerNum(@RequestBody ReportUserDataReq reportUserDataReq){
        if (reportUserDataReq.getTimeType() == null){
            return WebApiResponse.error("时间类型不能为空");
        }
        try {
            List<ReportUserDataDto> reportUserDataDto = reportUserDataService.findRegisterNumByTime(reportUserDataReq);
            return WebApiResponse.success(reportUserDataDto);
        }catch (Exception e){
            LOG.error("/report/userData 查询查询全站用户数据出错,异常信息" + e);
            return WebApiResponse.error(WebApiResponse.ResponseMsg.EXCEPTION.getValue());
        }
    }


    @ApiOperation(value = "过去一周每日注册用户数", notes = "过去一周每日注册用户数")
    @RequestMapping(value = "/newUserNum", method = RequestMethod.GET)
    public WebApiResponse<?> lastWeekNewUserNum(){
        try {
            List<UserRegisterReportDto> result = reportUserRegisterService.userRegisterReport();
            return WebApiResponse.success(result);
        }catch (Exception e){
            LOG.error("/report/newUserNum 查询每日注册用户数出错,异常信息" + e);
            return WebApiResponse.error(WebApiResponse.ResponseMsg.EXCEPTION.getValue());
        }
    }


    @ApiOperation(value = "重复购买率", notes = "重复购买率")
    @RequestMapping(value = "/repeatBuyRate", method = RequestMethod.GET)
    public WebApiResponse<?> repeatBuyRate(){

        try {
            RepeatBuyRateReportDto result = reportRepeatBuyRateService.repeatBuyRateReport();
            return WebApiResponse.success(result);
        }catch (Exception e){
            LOG.error("/report/repeatBuyRate 查询重复购买率出错,异常信息" + e);
            return WebApiResponse.error(WebApiResponse.ResponseMsg.EXCEPTION.getValue());
        }
    }

    @ApiOperation(value = "不同年龄分段购买情况", notes = "不同年龄分段购买情况")
    @RequestMapping(value = "/findBuyDataByAge", method = RequestMethod.GET)
    public WebApiResponse<?> findBuyDataByAge(){

        try {
            List<AgeGroupBuyDto> result =  reportAgeGroupBuyService.findReportByAge();
            return WebApiResponse.success(result);
        }catch (Exception e){
            LOG.error("/report/findBuyDataByAge 查询不同年龄分段购买情况出错,异常信息" + e);
            return WebApiResponse.error(WebApiResponse.ResponseMsg.EXCEPTION.getValue());
        }
    }

    @ApiOperation(value = "订单区域分布情况", notes = "订单区域分布情况")
    @RequestMapping(value = "/findOrdersByArea", method = RequestMethod.GET)
    public WebApiResponse<?> findOrdersByArea(){

        try {
            List<OrderAreaReportDto> result = reportOrderAreaService.findOrderAreaReport();
            return WebApiResponse.success(result);
        }catch (Exception e){
            LOG.error("/report/findOrdersByArea 查询订单分布情况出错,异常信息" + e);
            return WebApiResponse.error(WebApiResponse.ResponseMsg.EXCEPTION.getValue());
        }
    }
}
