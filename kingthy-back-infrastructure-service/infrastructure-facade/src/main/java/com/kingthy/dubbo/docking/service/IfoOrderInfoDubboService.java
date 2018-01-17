package com.kingthy.dubbo.docking.service;

import com.kingthy.entity.IfoOrderInfo;
import com.kingthy.request.IfoCreateOrderReq;
import com.kingthy.request.IfoFullOrderReq;

/**
 * @author xumin
 * @Description:
 * @DATE Created by 16:46 on 2017/9/20.
 * @Modified by:
 */
public interface IfoOrderInfoDubboService {

    int insert(IfoOrderInfo t);

    int createIfoOrder(IfoCreateOrderReq ifoCreateOrderReq);

    int createFullIfoOrder(IfoFullOrderReq ifoFullOrderReq);
}
