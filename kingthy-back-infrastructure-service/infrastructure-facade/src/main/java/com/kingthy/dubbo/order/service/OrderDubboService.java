package com.kingthy.dubbo.order.service;

import com.github.pagehelper.PageInfo;
import com.kingthy.base.BaseService;
import com.kingthy.dto.OrderInfoDto;
import com.kingthy.dto.ShippingDto;
import com.kingthy.entity.Orders;
import com.kingthy.request.CreateOrderReq;
import com.kingthy.request.ShippingReq;
import com.kingthy.response.CreateOrderResponse;

import java.util.List;

/**
 * @author xumin
 * @Description:
 * @DATE Created by 13:47 on 2017/8/2.
 * @Modified by:
 */
public interface OrderDubboService extends BaseService<Orders> {

    /**
     * 修改订单
     * @param var
     * @return
     */
    int updateOrderBySn(Orders var);

    /**
     * 创建订单
     * @param var
     * @return
     */
    CreateOrderResponse createOrder(CreateOrderReq var);

    /**
     * 判断订单号是否被创建过
     * @param orderSn 主订单号
     * @return
     */
    int checkOrderSnExists(String orderSn);

    OrderInfoDto selectOrderInfo(String uuid);

    PageInfo<ShippingDto> selectShippingList(ShippingReq req);

    /**
     * 修改运单号
     * @param req
     * @return
     */
    int deliveryOrders(ShippingReq.DeliveryReq req);

    int updateOrdersAddress(ShippingReq.EditAddress req);

    /**
     * 查询配送信息
     * @param orderSn
     * @return
     */
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

}

