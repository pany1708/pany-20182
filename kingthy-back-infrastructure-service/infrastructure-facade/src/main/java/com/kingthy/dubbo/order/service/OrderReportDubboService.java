package com.kingthy.dubbo.order.service;


import com.kingthy.dto.AgeBuyDataDto;
import com.kingthy.dto.MemberUuidGroupBuyDto;
import com.kingthy.dto.OrderAreaDto;
import com.kingthy.dto.ReportUserUuidByAgeDto;
import com.kingthy.request.UserDataReq;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 订单数据统计接口
 * <p>
 * 陈钊 2017年7月19日 上午9:50:16
 *
 * @version 1.0.0
 */
public interface OrderReportDubboService {


    /**
     * 根据时间段查询下单量
     *
     * @param userDataReq
     * @return int
     */
    int findPlaceOrderNum(UserDataReq userDataReq);

    /**
     * 根据时间段查询购买量
     *
     * @param userDataReq
     * @return int
     */
    int findBuyNum(UserDataReq userDataReq);


    /**
     * 重复购买率
     *
     * @return BigDecimal
     */
    BigDecimal repeatBuyRate();

    /**
     * 统计订单地区分布数据
     *
     * @param userDataReq
     * @return ArrayList<OrderAreaDto>
     */
    ArrayList<OrderAreaDto> findOrdersByArea(UserDataReq userDataReq);

    /**
     * 根据用户uuid查询用户消费金额
     * @param reportUserUuidByAgeDtoList
     * @return
     */
    ArrayList<MemberUuidGroupBuyDto> findBuyDataByMemberUuid(List<ReportUserUuidByAgeDto> reportUserUuidByAgeDtoList);
}
