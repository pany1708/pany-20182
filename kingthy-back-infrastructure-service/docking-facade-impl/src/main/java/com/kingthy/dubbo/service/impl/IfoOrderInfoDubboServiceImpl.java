package com.kingthy.dubbo.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.kingthy.dubbo.docking.service.IfoOrderInfoDubboService;
import com.kingthy.entity.IfoOrderInfo;
import com.kingthy.mapper.IfoOrderInfoMapper;
import com.kingthy.request.IfoCreateOrderReq;
import com.kingthy.request.IfoFullOrderReq;
import com.kingthy.service.IfoOrderInfoService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author xumin
 * @Description:
 * @DATE Created by 16:47 on 2017/9/20.
 * @Modified by:
 */
@Service(version = "1.0.0", timeout = 3000)
public class IfoOrderInfoDubboServiceImpl implements IfoOrderInfoDubboService
{

    @Autowired
    private IfoOrderInfoService ifoOrderInfoService;

    @Override
    public int insert(IfoOrderInfo t) {
        return ifoOrderInfoService.insert(t);
    }

    @Override
    public int createIfoOrder(IfoCreateOrderReq ifoCreateOrderReq) {
        return ifoOrderInfoService.createIfoOrder(ifoCreateOrderReq);
    }

    @Override
    public int createFullIfoOrder(IfoFullOrderReq ifoFullOrderReq) {
        return ifoOrderInfoService.createFullIfoOrder(ifoFullOrderReq);
    }
}
