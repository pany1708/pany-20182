package com.kingthy.service.impl;

import com.google.gson.Gson;
import com.kingthy.common.CommonUtils;
import com.kingthy.entity.EventPublish;
import com.kingthy.entity.OrderLog;
import com.kingthy.entity.Orders;
import com.kingthy.mapper.EventPublishMapper;
import com.kingthy.mapper.OrderItemMapper;
import com.kingthy.mapper.OrderLogMapper;
import com.kingthy.mapper.OrderMapper;
import com.kingthy.request.CreateOrderReq;
import com.kingthy.request.EventPublishReq;
import com.kingthy.response.CreateOrderResponse;
import com.kingthy.service.EventPublishService;
import com.kingthy.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author xumin
 * @Description:
 * @DATE Created by 16:34 on 2017/8/17.
 * @Modified by:
 */

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderItemMapper orderItemMapper;

    @Autowired
    private OrderMapper ordersMapper;

    @Autowired
    private OrderLogMapper orderLogMapper;

    @Autowired
    private EventPublishService eventPublishService;

    /**
     * 创建订单
     * @param var
     * @return
     */
    @Override
    public CreateOrderResponse createOrder(CreateOrderReq var) {
        Orders orders = var.getOrders();

        int result = ordersMapper.insert(orders);

        List<String> orderItemList = new ArrayList<>();

        if (result > 0){

            //主订单日志
            insertOrderLog(orders.getUuid(), orders.getCreator(), "创建订单", orders.getSn());

            //商品数量 用于更新购物车
            List<EventPublishReq.GoodsAndQuantity> list = new ArrayList<>();

            //子订单
            var.getOrderItemList().forEach(orderItem -> {

                orderItem.setOrderUuid(orders.getUuid());

                int itemResult = orderItemMapper.insert(orderItem);

                if (itemResult > 0){

                    insertOrderLog(orderItem.getUuid(), orderItem.getCreator(), "创建子订单", orderItem.getSn());

                    orderItemList.add(orderItem.getSn());
                }

                EventPublishReq.GoodsAndQuantity quantity = new EventPublishReq.GoodsAndQuantity();

                quantity.setGoodsUuid(orderItem.getProductUuid());
                quantity.setQuantity(orderItem.getQuantity());

                list.add(quantity);
            });

            //保存事件
            if (!list.isEmpty()){
                EventPublishReq eventPublishReq = new EventPublishReq();
                eventPublishReq.setMemberUuid(orders.getCreator());
                eventPublishReq.setGoodsAndQuantityList(list);

                Gson gson = new Gson();
                String payload = gson.toJson(eventPublishReq);
                insertEventPublish(orders.getCreator(), payload, EventPublish.EventTypeEnum.CART);
            }
        }

        CreateOrderResponse response = new CreateOrderResponse();
        response.setListOrderItemSn(orderItemList);
        response.setOrdersSn(orders.getSn());

        return response;
    }

//    private void updateCartInfo(OrderDTO orderDTO, Map<String, OrderCreateDTO> mapGoods, String creator) {


    /**
     * 创建日志
     * @param orderUuid
     * @param creator
     * @param content
     * @param orderSn
     */
    private void insertOrderLog(String orderUuid, String creator, String content, String orderSn)
    {
        OrderLog var1 = new OrderLog();
        var1.setCreateDate(new Date());
        var1.setCreator(creator);
        var1.setModifyDate(new Date());
        var1.setModifier(creator);
        var1.setVersion(0);
        var1.setContent(content);
        var1.setType(0);
        var1.setOrderUuid(orderUuid);
        var1.setDelFlag(false);
        var1.setOrderSn(orderSn);

        orderLogMapper.insert(var1);
    }

    /**
     * 保存事件
     * @param creator
     * @param payload
     */
    private void insertEventPublish(String creator, String payload, EventPublish.EventTypeEnum eventTypeEnum){
        eventPublishService.insertEventPublish(creator, payload, eventTypeEnum);
    }
}
