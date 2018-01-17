package com.kingthy.dubbo.cart.service;

import com.kingthy.entity.EventProcess;
import com.kingthy.request.EventPublishReq;

/**
 * @author xumin
 * @Description:
 * @DATE Created by 13:45 on 2017/8/23.
 * @Modified by:
 */
public interface EventProcessDubboService {

    int insert(EventProcess t);

    int insert(EventPublishReq t);

    void updateCartByEvent(String sharding, final int limit);

}
