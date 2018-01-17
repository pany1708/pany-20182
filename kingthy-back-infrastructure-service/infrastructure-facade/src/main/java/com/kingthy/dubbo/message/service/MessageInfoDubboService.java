package com.kingthy.dubbo.message.service;

import com.github.pagehelper.PageInfo;
import com.kingthy.base.BaseService;
import com.kingthy.dto.MessageInfoDto;
import com.kingthy.entity.MessageInfo;
import com.kingthy.request.MessageInfoReq;

/**
 * @author xumin
 * @Description:
 * @DATE Created by 17:16 on 2017/8/7.
 * @Modified by:
 */
public interface MessageInfoDubboService extends BaseService<MessageInfo> {
    PageInfo<MessageInfoDto> findListMessage(MessageInfoReq req);
}
