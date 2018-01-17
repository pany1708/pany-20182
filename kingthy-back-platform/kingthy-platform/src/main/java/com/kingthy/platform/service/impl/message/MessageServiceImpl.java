package com.kingthy.platform.service.impl.message;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import com.kingthy.constant.RabbitmqConstants;
import com.kingthy.dto.SmsMessageInfoDTO;
import com.kingthy.platform.dto.message.MessageInfoDto;
import com.kingthy.platform.dto.message.MessageInfoReq;
import com.kingthy.platform.entity.message.MessageInfo;
import com.kingthy.platform.mapper.member.MemberMapper;
import com.kingthy.platform.mapper.message.MessageInfoMapper;
import com.kingthy.platform.response.WebApiResponse;
import com.kingthy.platform.service.message.MessageService;
import com.kingthy.platform.util.DateUtil;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @AUTHORS xumin
 * @Description:
 * @DATE Created by 13:58 on 2017/7/14.
 * @Modified by:
 */
@Service
public class MessageServiceImpl implements MessageService{

    @Autowired
    private MessageInfoMapper messageInfoMapper;

    @Autowired
    private MemberMapper memberMapper;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 短信列表
     * @param req
     * @return
     * @throws Exception
     */
    @Override
    public WebApiResponse<?> list(MessageInfoReq req) throws Exception {

        PageHelper.startPage(req.getPageNum(), req.getPageSize());

        req.setEndTime(DateUtil.putEndTime(req.getEndTime()));

        List<MessageInfoDto> list = messageInfoMapper.findListMessage(req);

        PageInfo<MessageInfoDto> pageInfo = new PageInfo<>(list);

        return WebApiResponse.success(pageInfo);
    }

    /**
     * 删除消息
     * @param uuid
     * @param membersUuid
     * @return
     * @throws Exception
     */
    @Override
    public WebApiResponse<?> delMessage(String uuid, String membersUuid) throws Exception {

        MessageInfo req = new MessageInfo();
        req.setDelFlag(true);
        req.setUuid(uuid);
        req.setModifyDate(new Date());
        req.setModifier(membersUuid);

        int result = messageInfoMapper.updateByUuidSelective(req);

        return result > 0 ? WebApiResponse.success(WebApiResponse.ResponseMsg.SUCCESS.getValue()) : WebApiResponse.error(WebApiResponse.ResponseMsg.FAIL.getValue());
    }

    /**
     * 创建消息
     * @param req
     * @return
     * @throws Exception
     */
    @Override
    public WebApiResponse<?> createMessage(MessageInfoReq.CreateMessageDto req) throws Exception {

        MessageInfo messageInfo = new MessageInfo();

        messageInfo.setCreateDate(new Date());
        messageInfo.setCreator(req.getMembersUuid());
        messageInfo.setVersion(0);
        messageInfo.setDelFlag(false);
        messageInfo.setUrl(req.getUrl());
        messageInfo.setTitle(req.getTitle());
        messageInfo.setPushTime(req.getPushTime());
        messageInfo.setPushTarget(req.getPushTarget());
        messageInfo.setPushStatus(req.getPushStatus());
        messageInfo.setMessagePlatformUuid(req.getMessagePlatformUuid());
        messageInfo.setContent(req.getContent());

        int result = messageInfoMapper.insert(messageInfo);

        return result > 0 ? WebApiResponse.success(WebApiResponse.ResponseMsg.SUCCESS.getValue()) : WebApiResponse.error(WebApiResponse.ResponseMsg.FAIL.getValue());
    }

    /**
     * 修改消息
     * @param req
     * @return
     * @throws Exception
     */
    @Override
    public WebApiResponse<?> editMessage(MessageInfoReq.EditMessageDto req) throws Exception {

        Gson gson = new Gson();

        MessageInfo messageInfo = gson.fromJson(gson.toJson(req), MessageInfo.class);

        messageInfo.setModifier(req.getMembersUuid());

        messageInfo.setModifyDate(new Date());

        int result = messageInfoMapper.updateByUuidSelective(messageInfo);

        return result > 0 ? WebApiResponse.success(WebApiResponse.ResponseMsg.SUCCESS.getValue())
                : WebApiResponse.error(WebApiResponse.ResponseMsg.FAIL.getValue());
    }

    /**
     * 查看消息
     * @param uuid
     * @return
     * @throws Exception
     */
    @Override
    public WebApiResponse<?> showMessage(String uuid) throws Exception {

        return WebApiResponse.success(messageInfoMapper.selectMessageByUuid(uuid));
    }

    /**
     * 发布消息
     * @param uuid
     * @param membersUuid
     * @return
     * @throws Exception
     */
    @Override
    public WebApiResponse<?> pushMessage(String uuid, String membersUuid) throws Exception {

        try {

            SmsMessageInfoDTO dto = new SmsMessageInfoDTO();
            dto.setMessageUuid(uuid);
            //发布消息
            rabbitTemplate.convertAndSend(RabbitmqConstants.SmsMessageEnum.EXCHANGE.getValue(), RabbitmqConstants.SmsMessageEnum.EXCHANGE.getValue());

        }catch (Exception e){
            throw e;
        }

        String memberName = memberMapper.selectMemberName(membersUuid);

        MessageInfo req = new MessageInfo();

        req.setUuid(uuid);

        req.setMemberName(memberName);

        req.setModifier(membersUuid);

        req.setModifyDate(new Date());

        //修改发布时间,发布人员
//        int result = messageInfoMapper.updateByUuidSelective(req);

        messageInfoMapper.updateByUuidSelective(req);

        return WebApiResponse.success(WebApiResponse.ResponseMsg.SUCCESS.getValue());
    }
}
