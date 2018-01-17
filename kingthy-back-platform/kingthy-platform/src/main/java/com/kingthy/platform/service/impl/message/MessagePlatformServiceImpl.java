package com.kingthy.platform.service.impl.message;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kingthy.constant.RabbitmqConstants;
import com.kingthy.platform.dto.message.MessagePlatformDto;
import com.kingthy.platform.dto.message.MessagePlatformReq;
import com.kingthy.platform.entity.message.MessagePlatform;
import com.kingthy.platform.mapper.message.MessagePlatformMapper;
import com.kingthy.platform.response.WebApiResponse;
import com.kingthy.platform.service.message.MessagePlatformService;
import com.kingthy.platform.util.DateUtil;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;

/**
 * @AUTHORS xumin
 * @Description:
 * @DATE Created by 10:20 on 2017/7/17.
 * @Modified by:
 */
@Service
public class MessagePlatformServiceImpl implements MessagePlatformService{

    @Autowired
    private MessagePlatformMapper messagePlatformMapper;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 消息平台列表
     * @param req
     * @return
     * @throws Exception
     */
    @Override
    public WebApiResponse<?> list(MessagePlatformReq req) throws Exception {

        PageHelper.startPage(req.getPageNum(), req.getPageSize());

        req.setEndTime(DateUtil.putEndTime(req.getEndTime()));

        List<MessagePlatformDto> list = messagePlatformMapper.findListPage(req);

        PageInfo<MessagePlatformDto> pageInfo = new PageInfo<>(list);

        return WebApiResponse.success(pageInfo);
    }

    /**
     * 查看消息平台明细
     * @param uuid
     * @return
     * @throws Exception
     */
    @Override
    public WebApiResponse<?> showPlat(String uuid) throws Exception {
        return WebApiResponse.success(messagePlatformMapper.showPlatByUuid(uuid));
    }

    /**
     * 启用/禁用
     * @param req
     * @return
     * @throws Exception
     */
    @Override
    public WebApiResponse<?> updateStatus(MessagePlatformReq.UpdateStatusReq req) throws Exception {

        MessagePlatform messagePlatform = new MessagePlatform();
        messagePlatform.setUuid(req.getUuid());
        messagePlatform.setModifier(req.getMembersUuid());
        messagePlatform.setModifyDate(new Date());
        messagePlatform.setStatus(req.getStatus());

        int result = messagePlatformMapper.updateMessagePlatFromByUuid(messagePlatform);

        //修改绑定短信队列
        if (result > 0){

            MessagePlatform info = messagePlatformMapper.showPlatByUuid(req.getUuid());

            if (info != null && !StringUtils.isEmpty(info.getQueueName())){

//                String queue = RabbitmqConstants.SmsMessageEnum.QUEUE.getValue();
//                String queue_a = RabbitmqConstants.SmsMessageEnum.QUEUE_A.getValue();
                String exchange = RabbitmqConstants.SmsMessageEnum.EXCHANGE.getValue();
                String routingKey = RabbitmqConstants.SmsMessageEnum.ROUTING.getValue();

                String queue = info.getQueueName();

                rabbitTemplate.execute((channel) -> {

                    if (req.getStatus() == 0){
                        channel.queueBind(queue, exchange, routingKey);
                    }
                    else{
                        channel.queueUnbind(queue, exchange, routingKey);
                    }

                    return null;
                });
            }
            else{
                return WebApiResponse.error(WebApiResponse.ResponseMsg.FAIL.getValue());
            }
        }


        return result > 0 ? WebApiResponse.success(WebApiResponse.ResponseMsg.SUCCESS.getValue())
                : WebApiResponse.error(WebApiResponse.ResponseMsg.FAIL.getValue());
    }

    /**
     * 删除
     * @param uuid
     * @param membersUuid
     * @return
     * @throws Exception
     */
    @Override
    public WebApiResponse<?> del(String uuid, String membersUuid) throws Exception {

        MessagePlatform messagePlatform = new MessagePlatform();
        messagePlatform.setUuid(uuid);
        messagePlatform.setModifier(membersUuid);
        messagePlatform.setModifyDate(new Date());
        messagePlatform.setDelFlag(true);

        int result = messagePlatformMapper.updateMessagePlatFromByUuid(messagePlatform);

        return result > 0 ? WebApiResponse.success(WebApiResponse.ResponseMsg.SUCCESS.getValue())
                : WebApiResponse.error(WebApiResponse.ResponseMsg.FAIL.getValue());
    }

    /**
     * 编辑
     * @param req
     * @return
     * @throws Exception
     */
    @Override
    public WebApiResponse<?> edit(MessagePlatformReq.EditReq req) throws Exception {

        MessagePlatform messagePlatform = new MessagePlatform();
        messagePlatform.setUuid(req.getUuid());
        messagePlatform.setModifier(req.getMembersUuid());
        messagePlatform.setModifyDate(new Date());
        messagePlatform.setPlatformName(req.getPlatformName());
        messagePlatform.setPlatformDesc(req.getPlatformDesc());
        messagePlatform.setOpenTime(new Date(req.getOpenTime()));
        messagePlatform.setStatus(req.getStatus());

        int result = messagePlatformMapper.updateMessagePlatFromByUuid(messagePlatform);

        return result > 0 ? WebApiResponse.success(WebApiResponse.ResponseMsg.SUCCESS.getValue())
                : WebApiResponse.error(WebApiResponse.ResponseMsg.FAIL.getValue());
    }
}
