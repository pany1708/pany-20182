package com.kingthy.platform.mapper.message;

import com.kingthy.platform.dto.message.MessageInfoDto;
import com.kingthy.platform.dto.message.MessageInfoReq;
import com.kingthy.platform.entity.message.MessageInfo;
import com.kingthy.platform.util.MyMapper;

import java.util.List;

/**
 * @AUTHORS xumin
 * @Description:
 * @DATE Created by 10:35 on 2017/7/14.
 * @Modified by:
 */
public interface MessageInfoMapper extends MyMapper<MessageInfo> {

    List<MessageInfoDto> findListMessage(MessageInfoReq req);

    int updateByUuidSelective(MessageInfo req);

    MessageInfo selectMessageByUuid(String uuid);
}
