package com.kingthy.mapper;

import com.kingthy.dto.MessageInfoDto;
import com.kingthy.entity.MessageInfo;
import com.kingthy.request.MessageInfoReq;
import com.kingthy.util.MyMapper;

import java.util.List;

/**
 * @author  xumin
 * @Description:
 * @DATE Created by 17:04 on 2017/8/7.
 * @Modified by:
 */
public interface MessageInfoMapper extends MyMapper<MessageInfo> {
    /**
     * 查询消息列表
     * @param req
     * @return
     */
    List<MessageInfoDto> findListMessage(MessageInfoReq req);
}
