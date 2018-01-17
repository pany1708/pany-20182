package com.kingthy.platform.service.impl.report;

import com.kingthy.platform.dto.report.AgeBuyDataDto;
import com.kingthy.platform.dto.report.EverydayRegisterNumDto;
import com.kingthy.platform.dto.report.OrderAreaDto;
import com.kingthy.platform.dto.report.UserDataReq;
import com.kingthy.platform.mapper.report.UserReportMapper;
import com.kingthy.platform.service.report.UserReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author chenz
 * @version 1.0.0
 * @class_name UserReportServiceImpl
 * @description 用户数据统计实现类
 * @create 2017/7/19
 */
@Service("userReportService")
public class UserReportServiceImpl implements UserReportService {

    @Autowired
    private transient UserReportMapper userReportMapper;

    @Override
    public int findRegisterUserNum(UserDataReq userDataReq) {
        return userReportMapper.findRegisterUserNum(userDataReq);
    }

    @Override
    public int findPlaceOrderNum(UserDataReq userDataReq) {
        return userReportMapper.findPlaceOrderNum(userDataReq);
    }

    @Override
    public int findBuyNum(UserDataReq userDataReq) {
        return userReportMapper.findBuyNum(userDataReq);
    }

    @Override
    public List<EverydayRegisterNumDto> lastWeekNewUserNum(UserDataReq userDataReq) {
        return userReportMapper.lastWeekNewUserNum(userDataReq);
    }

    @Override
    public BigDecimal repeatBuyRate() {
        int repeatNum = userReportMapper.repeatBuyNum();
        int totalNum = userReportMapper.totalNum();
        if (repeatNum==0){
            return BigDecimal.ZERO;
        }else{
            BigDecimal bigDecimalRepeat = new BigDecimal(repeatNum);
            BigDecimal bigDecimalTotal = new BigDecimal(totalNum);
            BigDecimal result  = bigDecimalRepeat.divide(bigDecimalTotal,2,BigDecimal.ROUND_HALF_UP);
            return result;
        }
    }

    @Override
    public List<AgeBuyDataDto> findBuyDataByAge() {
        return userReportMapper.findBuyDataByAge();
    }

    @Override
    public List<OrderAreaDto> findOrdersByArea(UserDataReq userDataReq) {
        return userReportMapper.findOrdersByArea(userDataReq);
    }
}
