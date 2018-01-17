package com.kingthy.service.impl;

import com.kingthy.entity.AfterSaleSchedule;
import com.kingthy.entity.AfterSaleService;
import com.kingthy.mapper.AfterSaleScheduleMapper;
import com.kingthy.mapper.AfterSaleServiceMapper;
import com.kingthy.request.AfterSaleServiceReq;
import com.kingthy.service.AfterSaleTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

/**
 * Created by likejie on 2017/8/25.
 */
@Transactional
@Service
public class AfterSaleTransactionImpl implements AfterSaleTransaction {


    @Autowired
    private AfterSaleServiceMapper afterSaleServiceMapper;

    @Autowired
    private AfterSaleScheduleMapper afterSaleScheduleMapper;


    @Override
    public int updateStatus(AfterSaleServiceReq.EditStatusReq req) {


        AfterSaleService afterSaleService = new AfterSaleService();

        afterSaleService.setModifier(req.getMemberUuid());
        afterSaleService.setModifyDate(new Date());
        afterSaleService.setUuid(req.getUuid());
        afterSaleService.setStatus(req.getStatus());
        afterSaleService.setShippingNumber(req.getShippingNumber());
        afterSaleService.setDelFlag(req.getDelFlag());
        afterSaleService.setRefundsAddrUuid(req.getRefundsAddrUuid());

        int result=afterSaleServiceMapper.updateStatus(afterSaleService);

        if(result > 0){

            AfterSaleService af = selectByUuid(req.getUuid());
            if(af==null){
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                return 0;
            }
            String memo = "";
            AfterSaleSchedule afterSaleSchedule = new AfterSaleSchedule();
            afterSaleSchedule.setGoodsUuid(af.getGoodsUuid());

            afterSaleSchedule.setOrderSn(af.getOrderSn());
            afterSaleSchedule.setSaleServiceUuid(req.getUuid());
            afterSaleSchedule.setStatus(req.getStatus());
            afterSaleSchedule.setCreateDate(new Date());
            afterSaleSchedule.setCreator(req.getMemberUuid());
            afterSaleSchedule.setDelFlag(false);
            afterSaleSchedule.setVersion(0);

            //记录售后进度 售后状态 0:发起售后 1:-审核通过 2:厂家确认收货,待生产 3:生产中 4:已包装发货，等待收货 5:已收货 6:已评价
            switch (req.getStatus()){
                case 1:
                    memo = "审核通过";

                    break;
                case 2:

                    memo = "厂家确认收货,待生产";
                    break;
                case 3:

                    memo = "生产中";
                    break;
                case 4:

                    memo = "已包装发货，等待收货";
                    break;
                case 5:

                    memo = "已收货";
                    break;
                case 6:

                    memo = "已评价";
                    break;
            }

            afterSaleSchedule.setMemo(memo);
            result=afterSaleScheduleMapper.insert(afterSaleSchedule);
        }
        return result;
    }

    @Override
    public int rejectAuditing(AfterSaleServiceReq.RejectReq req) {
        int result = afterSaleServiceMapper.updateRejectAuditing(req);

        if (result > 0 ){

            AfterSaleService af = selectByUuid(req.getUuid());

            AfterSaleSchedule afterSaleSchedule = new AfterSaleSchedule();
            afterSaleSchedule.setGoodsUuid(af.getGoodsUuid());

            afterSaleSchedule.setOrderSn(af.getOrderSn());
            afterSaleSchedule.setSaleServiceUuid(req.getUuid());
            afterSaleSchedule.setStatus(1);
            afterSaleSchedule.setCreateDate(new Date());
            afterSaleSchedule.setCreator(req.getMemberUuid());
            afterSaleSchedule.setDelFlag(false);
            afterSaleSchedule.setVersion(0);
            afterSaleSchedule.setMemo("审核不通过");
            result=afterSaleScheduleMapper.insert(afterSaleSchedule);
        }
        return result;
    }

    private AfterSaleService selectByUuid(String uuid) {
        AfterSaleService cond = new AfterSaleService();
        cond.setUuid(uuid);

        return afterSaleServiceMapper.selectOne(cond);


    }
}
