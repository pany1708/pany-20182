package com.kingthy.dubbo.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.kingthy.dto.MemberUuidGroupBuyDto;
import com.kingthy.dto.OrderAreaDto;
import com.kingthy.dto.ReportUserUuidByAgeDto;
import com.kingthy.dubbo.order.service.OrderReportDubboService;
import com.kingthy.mapper.OrderReportMapper;
import com.kingthy.request.UserDataReq;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author chenz
 * @version 1.0.0
 * @class_name OrderReportDubboServiceImpl
 * @description 订单数据统计接口实现类
 * @create 2017/8/28
 */
@Service(version = "1.0.0",timeout = 10000)
public class OrderReportDubboServiceImpl implements OrderReportDubboService {

    @Autowired
    private OrderReportMapper orderReportMapper;

    @Override
    public int findPlaceOrderNum(UserDataReq userDataReq) {
        return orderReportMapper.findPlaceOrderNum(userDataReq);
    }

    @Override
    public int findBuyNum(UserDataReq userDataReq) {
        return orderReportMapper.findBuyNum(userDataReq);
    }

    @Override
    public BigDecimal repeatBuyRate() {
        int repeatNum = orderReportMapper.repeatBuyNum();
        int totalNum = orderReportMapper.totalNum();
        if (repeatNum == 0) {
            return BigDecimal.ZERO;
        } else {
            BigDecimal bigDecimalRepeat = new BigDecimal(repeatNum);
            BigDecimal bigDecimalTotal = new BigDecimal(totalNum);
            BigDecimal result = bigDecimalRepeat.divide(bigDecimalTotal, 2, BigDecimal.ROUND_HALF_UP);
            return result;
        }
    }

    @Override
    public ArrayList<OrderAreaDto> findOrdersByArea(UserDataReq userDataReq) {
        List<OrderAreaDto> list = orderReportMapper.findOrdersByArea(userDataReq);
        ArrayList<OrderAreaDto> arrayList = new ArrayList<>();
        if (list != null) {
            arrayList = (ArrayList<OrderAreaDto>) list;
        }
        return arrayList;
    }

    @Override
    public ArrayList<MemberUuidGroupBuyDto> findBuyDataByMemberUuid(List<ReportUserUuidByAgeDto> reportUserUuidByAgeDtoList) {
        List<MemberUuidGroupBuyDto> list = orderReportMapper.findBuyDataByMemberUuid(reportUserUuidByAgeDtoList);
        ArrayList<MemberUuidGroupBuyDto> arrayList = new ArrayList<>();
        if (list != null) {
            arrayList = (ArrayList<MemberUuidGroupBuyDto>) list;
        }
        return arrayList;
    }
}
