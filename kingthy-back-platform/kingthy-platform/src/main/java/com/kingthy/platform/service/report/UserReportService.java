package com.kingthy.platform.service.report;

import com.kingthy.platform.dto.report.AgeBuyDataDto;
import com.kingthy.platform.dto.report.EverydayRegisterNumDto;
import com.kingthy.platform.dto.report.OrderAreaDto;
import com.kingthy.platform.dto.report.UserDataReq;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 *
 *
 * 用户数据统计接口
 *
 * 陈钊 2017年7月19日 上午9:50:16
 *
 * @version 1.0.0
 *
 */
public interface UserReportService {

    /**
     *
     * findRegisterUserNum(根据时间段查询全站注册用户数量)
     *
     * @param userDataReq
     * @return <b>创建人：</b>陈钊<br/>
     *         int
     * @exception @since 1.0.0
     */
    int findRegisterUserNum(UserDataReq userDataReq);

    /**
     * 根据时间段查询下单量
     * @param userDataReq
     * @return int
     */
    int findPlaceOrderNum(UserDataReq userDataReq);

    /**
     * 根据时间段查询购买量
     * @param userDataReq
     * @return int
     */
    int findBuyNum(UserDataReq userDataReq);

    /**
     *
     * lastWeekNewUserNum(过去一周每日注册用户数)
     * @param userDataReq
     * @return <b>创建人：</b>陈钊<br/>
     *         List<EverydayRegisterNumDto>
     * @exception @since 1.0.0
     */
    List<EverydayRegisterNumDto> lastWeekNewUserNum(UserDataReq userDataReq);

    /**
     * 重复购买率
     * @return BigDecimal
     */
    BigDecimal repeatBuyRate();

    /**
     * 不同年龄分段购买情况
     * @return List<AgeBuyDataDto>
     */
    List<AgeBuyDataDto> findBuyDataByAge();

    /**
     * 统计订单地区分布数据
     * @param userDataReq
     * @return List<OrderAreaDto>
     */
    List<OrderAreaDto> findOrdersByArea(UserDataReq userDataReq);
}
