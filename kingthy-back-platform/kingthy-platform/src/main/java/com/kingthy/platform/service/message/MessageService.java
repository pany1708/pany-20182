package com.kingthy.platform.service.message;

import com.kingthy.platform.dto.message.MessageInfoReq;
import com.kingthy.platform.response.WebApiResponse;

/**
 * @AUTHORS xumin
 * @Description:
 * @DATE Created by 11:47 on 2017/7/14.
 * @Modified by:
 */
public interface MessageService {

    WebApiResponse<?> list(MessageInfoReq req) throws Exception;

    WebApiResponse<?> delMessage(String uuid, String membersUuid) throws Exception;

    WebApiResponse<?> createMessage(MessageInfoReq.CreateMessageDto req) throws Exception;

    WebApiResponse<?> editMessage(MessageInfoReq.EditMessageDto req) throws Exception;

    WebApiResponse<?> showMessage(String uuid) throws Exception;

    WebApiResponse<?> pushMessage(String uuid, String membersUuid) throws Exception;

}
