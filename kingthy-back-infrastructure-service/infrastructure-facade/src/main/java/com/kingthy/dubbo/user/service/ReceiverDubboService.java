package com.kingthy.dubbo.user.service;

import com.kingthy.base.BaseService;
import com.kingthy.dto.MemberReceiverDTO;
import com.kingthy.entity.Receiver;

import java.util.List;

/**
 * 收货地址服务接口
 * @author   likejie
 * @date  2017/8/7
 */
public interface ReceiverDubboService extends BaseService<Receiver> {
    /**
     * 获取会员的收获地址
     * @param memberUuid
     * @return
     */
    List<MemberReceiverDTO> getMemberReceiverList(String memberUuid);

    /**
     * 获取会员默认的收获地址
     * @param memberUuid
     * @return
     */
    Receiver getDefaultReceiver(String memberUuid);



    /**
     * 设置默认收获地址
     * @param memberUuid
     * @param uuid
     * @return
     */
    int setDefaultReceiver(String memberUuid, String uuid);

    /**
     * 删除
     * @param uuid 会员收获地址业务主键
     * @param memberUuid
     * @return
     */
    int deleteReceiver(String uuid,String memberUuid);

    /**
     * 取消默认收获地址
     * @param memberUuid
     * @param uuid
     * @return
     */
    int cancelDefaultReceiver(String memberUuid, String uuid);

}
