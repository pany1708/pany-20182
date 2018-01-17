package com.kingthy.transaction.impl;

import com.kingthy.mapper.ReceiverMapper;
import com.kingthy.transaction.ReceoverTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import  com.kingthy.entity.Receiver;

/**
 * @author likeji 2017/8/17.
 */
@Service
public class ReceiverTransactionImpl implements ReceoverTransaction {

    @Autowired
    private ReceiverMapper mapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int addReceiver(Receiver receiver) {


        if (receiver.getIsDefault())
        {
            // 设置会员所有地址为非默认
            mapper.cancelDefaultReceiver(receiver.getMemberUuid(), "");
        }
        // 设置指定地址为默认地址
        int res = mapper.insert(receiver);
        if (res == 0)
        {
            // 设置事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return res;
    }
    @Transactional(rollbackFor = Exception.class)
    @Override
    public int setDefaultReceiver(String memberUuid, String uuid) {
        // 设置会员所有地址为非默认
        mapper.cancelDefaultReceiver(memberUuid, "");
        // 设置指定地址为默认地址
        int res = mapper.setDefaultReceiver(memberUuid, uuid);
        if (res == 0)
        {
            // 设置事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return res;
    }

}
