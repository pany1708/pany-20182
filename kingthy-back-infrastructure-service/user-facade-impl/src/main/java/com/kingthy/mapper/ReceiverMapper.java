package com.kingthy.mapper;

import com.kingthy.dto.MemberReceiverDTO;
import com.kingthy.entity.Receiver;
import com.kingthy.util.MyMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 *
 * 收获地址mapper---
 *
 * @author   likejie
 * @date 2017/8/7.
 */
public interface ReceiverMapper extends MyMapper<Receiver> {

    /**
     * 获取会员的收获地址
     * @param memberUuid
     * @return
     */
    List<MemberReceiverDTO> getMemberReceiverList(String memberUuid);

    /**
     * 设置默认收获地址
     *
     * @param memberUuid
     * @param uuid
     * @return
     */
    int setDefaultReceiver(@Param("memberUuid") String memberUuid, @Param("uuid") String uuid);

    /**
     * 取消默认收获地址
     * @param memberUuid
     * @param uuid
     * @return
     */
    int cancelDefaultReceiver(@Param("memberUuid") String memberUuid, @Param("uuid") String uuid);

}
