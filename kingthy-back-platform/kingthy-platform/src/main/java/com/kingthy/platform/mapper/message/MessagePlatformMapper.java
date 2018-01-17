package com.kingthy.platform.mapper.message;

import com.kingthy.platform.dto.message.MessagePlatformDto;
import com.kingthy.platform.dto.message.MessagePlatformReq;
import com.kingthy.platform.entity.message.MessagePlatform;
import com.kingthy.platform.util.MyMapper;

import java.util.List;

/**
 * @AUTHORS xumin
 * @Description:
 * @DATE Created by 10:52 on 2017/7/14.
 * @Modified by:
 */
public interface MessagePlatformMapper extends MyMapper<MessagePlatform> {

    List<MessagePlatformDto> findListPage(MessagePlatformReq req);

    MessagePlatform showPlatByUuid(String uuid);

    int updateMessagePlatFromByUuid(MessagePlatform messagePlatform);
}
