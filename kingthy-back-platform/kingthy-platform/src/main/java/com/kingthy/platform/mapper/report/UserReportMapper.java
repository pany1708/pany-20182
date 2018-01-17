package com.kingthy.platform.mapper.report;

import com.kingthy.platform.dto.report.AgeBuyDataDto;
import com.kingthy.platform.dto.report.EverydayRegisterNumDto;
import com.kingthy.platform.dto.report.OrderAreaDto;
import com.kingthy.platform.dto.report.UserDataReq;

import java.util.List;
import java.util.Map;

/**
 *
 *
 * UserReportMapper(统计报表映射接口)
 *
 * 陈钊 2017年7月20日 上午9:49:34
 *
 * @version 1.0.0
 *
 */
public interface UserReportMapper {

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
     *
     * lastWeekNewUserNum(过去一周每日注册用户数)
     * @param userDataReq
     * @return <b>创建人：</b>陈钊<br/>
     *         List<EverydayRegisterNumDto>
     * @exception @since 1.0.0
     */
    List<EverydayRegisterNumDto> lastWeekNewUserNum(UserDataReq userDataReq);

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
     * 购买过一次以上商品的用户数
     * @return int
     */
    int repeatBuyNum();

    /**
     * 去重查询发生过购买行为的用户数
     * @return int
     */
    int totalNum();

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
