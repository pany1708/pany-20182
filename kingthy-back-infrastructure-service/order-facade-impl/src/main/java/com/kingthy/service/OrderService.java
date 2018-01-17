package com.kingthy.service;

import com.kingthy.request.CreateOrderReq;
import com.kingthy.response.CreateOrderResponse;

/**
 * @author xumin
 * @Description:
 * @DATE Created by 16:26 on 2017/8/17.
 * @Modified by:
 */
public interface OrderService {

    CreateOrderResponse createOrder(CreateOrderReq var);
}
