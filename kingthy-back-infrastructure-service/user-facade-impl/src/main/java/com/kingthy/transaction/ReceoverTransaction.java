package com.kingthy.transaction;

import com.kingthy.entity.Receiver;

/**
 * @author  likejie
 * @date 2017/8/17.
 */
public interface ReceoverTransaction {

    /**
     * 新增收获地址
     * @param receiver
     * @return
     */
    int addReceiver(Receiver receiver);

    /**
     * 设置默认的收获地址
     * @param memberUuid
     * @param uuid
     * @return
     */
    int setDefaultReceiver(String memberUuid, String uuid);
}
