package com.kingthy.mq;

import com.kingthy.constant.RabbitmqConstants;
import com.kingthy.dto.UpdateOpusDTO;
import com.kingthy.response.WebApiResponse;
import com.kingthy.service.OpusService;
import jdk.nashorn.internal.parser.Token;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * OpusListener(描述其作用)
 * <p>
 * @author 赵生辉 2017年08月23日 14:13
 *
 * @version 1.0.0
 */
@Component
public class OpusListener
{
    @Autowired
    private OpusService opusService;

    private static final Logger LOGGER = LoggerFactory.getLogger(OpusListener.class);

    @RabbitListener(queues = RabbitmqConstants.UPDATE_OPUS_LISTENER_QUEUE)
    public void updateOpus(final UpdateOpusDTO updateOpusDTO)
    {
        try
        {
            int result = opusService.updateOpus(updateOpusDTO.getUuid(),updateOpusDTO.getStatus(),updateOpusDTO.getMemberUuid());

            if (result != 1){
                LOGGER.error("更新作品请求接口失败");
                throw new AmqpRejectAndDontRequeueException("业务层处理失败");
            }
        }catch (Exception e){
            LOGGER.error("更新作品失败" + e);
            throw new AmqpRejectAndDontRequeueException("更新作品业务层处理失败");
        }
    }


}
