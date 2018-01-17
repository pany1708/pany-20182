package com.kingthy.service;

import com.kingthy.entity.IfoOrderInfo;
import com.kingthy.request.IfoCreateOrderReq;
import com.kingthy.request.IfoFullOrderReq;

/**
 * @author xumin
 * @Description:
 * @DATE Created by 17:59 on 2017/10/12.
 * @Modified by:
 */
public interface IfoOrderInfoService
{
    int createIfoOrder(IfoCreateOrderReq ifoCreateOrderReq);

    int createFullIfoOrder(IfoFullOrderReq ifoFullOrderReq);

    int insert(IfoOrderInfo t);
}
