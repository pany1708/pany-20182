package com.kingthy.mapper;


import com.kingthy.dto.AgeBuyDataDto;
import com.kingthy.dto.MemberUuidGroupBuyDto;
import com.kingthy.dto.OrderAreaDto;
import com.kingthy.dto.ReportUserUuidByAgeDto;
import com.kingthy.request.UserDataReq;

import java.util.List;

/**
 * UserReportMapper(统计报表映射接口)
 * <p>
 * @author 陈钊 2017年7月20日 上午9:49:34
 *
 * @version 1.0.0
 */
public interface OrderReportMapper {

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
     * 购买过一次以上商品的用户数
     *
     * @return int
     */
    int repeatBuyNum();

    /**
     * 去重查询发生过购买行为的用户数
     *
     * @return int
     */
    int totalNum();

    /**
     * 不同年龄分段购买情况
     *
     * @return List<AgeBuyDataDto>
     */
    List<AgeBuyDataDto> findBuyDataByAge();

    /**
     * 统计订单地区分布数据
     *
     * @param userDataReq
     * @return List<OrderAreaDto>
     */
    List<OrderAreaDto> findOrdersByArea(UserDataReq userDataReq);

    /**
     * 根据用户uuid查询用户消费金额
     *
     * @param reportUserUuidByAgeDtoList
     * @return
     */
    List<MemberUuidGroupBuyDto> findBuyDataByMemberUuid(List<ReportUserUuidByAgeDto> reportUserUuidByAgeDtoList);
}
