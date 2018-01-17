package com.kingthy.mapper;

import com.kingthy.dto.OrderInfoDto;
import com.kingthy.dto.ShippingDto;
import com.kingthy.entity.Orders;
import com.kingthy.request.ShippingReq;
import com.kingthy.util.MyMapper;

import java.util.List;

/**
 * @author  xumin
 * @Description:
 * @DATE Created by 17:48 on 2017/8/2.
 * @Modified by:
 */
public interface OrderMapper extends MyMapper<Orders> {

    int updateByUuid(Orders orders);

    OrderInfoDto selectOrderInfo(String uuid);

    List<ShippingDto> selectShippingList(ShippingReq req);

    int deliveryOrders(ShippingReq.DeliveryReq req);

    int updateOrdersAddress(ShippingReq.EditAddress req);

    ShippingReq.OrderShipping showOrderShippingBySn(String orderSn);

    /**
     * 查询子订单是否 是支付成功的状态
     * @param orderItemSn
     * @return
     */
    Long selectOrdersCountByOrderItemSn(String orderItemSn);

    /**
     * 根据子订单号 查询主订单号
     * @param orderItemSn
     * @return
     */
    String selectSnCountByOrderItemSn(String orderItemSn);

    /**
     * 判断订单号是否被创建过
     * @param orderSn 主订单号
     * @return
     */
    int checkOrderSnExists(String orderSn);
}
