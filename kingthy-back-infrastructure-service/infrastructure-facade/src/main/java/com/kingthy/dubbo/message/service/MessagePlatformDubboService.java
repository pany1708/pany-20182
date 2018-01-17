package com.kingthy.dubbo.message.service;

import com.github.pagehelper.PageInfo;
import com.kingthy.base.BaseService;
import com.kingthy.dto.MessagePlatformDto;
import com.kingthy.entity.MessagePlatform;
import com.kingthy.request.MessagePlatformReq;

/**
 * @author xumin
 * @Description:
 * @DATE Created by 17:17 on 2017/8/7.
 * @Modified by:
 */
public interface MessagePlatformDubboService extends BaseService<MessagePlatform> {
    PageInfo<MessagePlatformDto> findListPage(MessagePlatformReq req);
    int updateMessagePlatFromByUuid(MessagePlatform messagePlatform);
}
