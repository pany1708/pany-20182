package com.kingthy.mapper;

import com.kingthy.dto.MessagePlatformDto;
import com.kingthy.entity.MessagePlatform;
import com.kingthy.request.MessagePlatformReq;
import com.kingthy.util.MyMapper;

import java.util.List;

/**
 * @author  xumin
 * @Description:
 * @DATE Created by 17:08 on 2017/8/7.
 * @Modified by:
 */
public interface MessagePlatformMapper extends MyMapper<MessagePlatform> {

    /**
     * 分页查询短信平台
     * @param req
     * @return
     */
    List<MessagePlatformDto> findListPage(MessagePlatformReq req);

    /**
     * 根据uuid更新短信平台
     * @param messagePlatform
     * @return
     */
    int updateMessagePlatFromByUuid(MessagePlatform messagePlatform);
}
