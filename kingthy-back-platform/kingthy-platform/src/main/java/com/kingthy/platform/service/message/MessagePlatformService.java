package com.kingthy.platform.service.message;

import com.kingthy.platform.dto.message.MessagePlatformReq;
import com.kingthy.platform.response.WebApiResponse;

/**
 * @AUTHORS xumin
 * @Description:
 * @DATE Created by 10:12 on 2017/7/17.
 * @Modified by:
 */
public interface MessagePlatformService {

    /**
     * 消息平台列表
     * @param req
     * @return
     * @throws Exception
     */
    WebApiResponse<?> list(MessagePlatformReq req) throws Exception;

    /**
     * 查看平台
     * @param uuid
     * @return
     * @throws Exception
     */
    WebApiResponse<?> showPlat(String uuid) throws Exception;

    WebApiResponse<?> updateStatus(MessagePlatformReq.UpdateStatusReq req) throws Exception;

    WebApiResponse<?> del(String uuid, String membersUuid) throws Exception;

    WebApiResponse<?> edit(MessagePlatformReq.EditReq req) throws Exception;
}
