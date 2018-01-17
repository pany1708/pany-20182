package com.kingthy.service.impl;

import com.kingthy.entity.IfoOrderInfo;
import com.kingthy.mapper.*;
import com.kingthy.request.IfoCreateOrderReq;
import com.kingthy.request.IfoFullOrderReq;
import com.kingthy.service.IfoOrderInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author xumin
 * @Description:创建接口表订单
 * @DATE Created by 18:00 on 2017/10/12.
 * @Modified by:
 */
@Service
@Transactional
public class IfoOrderInfoServiceImpl implements IfoOrderInfoService
{

    @Autowired
    private IfoOrderInfoMapper ifoOrderInfoMapper;

    @Autowired
    private IfoOrderInfoDetailMapper ifoOrderInfoDetailMapper;

    @Autowired
    private IfoStitchingStyleMapper ifoStitchingStyleMapper;

    @Autowired
    private IfoOrderDetailBomMapper ifoOrderDetailBomMapper;

    @Autowired
    private IfoOrderStyleFileMapper ifoOrderStyleFileMapper;

    @Override
    public int createIfoOrder(IfoCreateOrderReq ifoCreateOrderReq) {


        IfoOrderInfo var = new IfoOrderInfo();
        var.setOrderSn(ifoCreateOrderReq.getIfoOrderInfo().getOrderSn());

        int result = ifoOrderInfoMapper.selectCount(var);

        if (result <= 0){

            result = ifoOrderInfoMapper.insert(ifoCreateOrderReq.getIfoOrderInfo());
        }

        if (result > 0){

            result = ifoOrderInfoDetailMapper.insertList(ifoCreateOrderReq.getDetailList());

        }

        return result;
    }

    @Override
    public int createFullIfoOrder(IfoFullOrderReq ifoFullOrderReq) {

        int result = ifoOrderInfoMapper.insert(ifoFullOrderReq.getIfoOrderInfo());

        ifoOrderInfoDetailMapper.insertList(ifoFullOrderReq.getIfoOrderInfoDetails());

        ifoOrderDetailBomMapper.insertList(ifoFullOrderReq.getBomList());

        ifoOrderStyleFileMapper.insertList(ifoFullOrderReq.getStyleFileList());

        ifoStitchingStyleMapper.insertList(ifoFullOrderReq.getStitchingStyles());

        return result;
    }

    @Override
    public int insert(IfoOrderInfo t) {
        return ifoOrderInfoMapper.insert(t);
    }
}
