package com.kingthy.platform.util;

import com.kingthy.constant.RabbitmqConstants;
import com.kingthy.dto.EsGoodsRabbitDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author chenz
 * @version 1.0.0
 * @class_name EsGoodsSender
 * @description 商品更改消息发送类
 * @create 2017/5/31
 */
@Component
public class EsGoodsSender {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    private static final Logger LOG = LoggerFactory.getLogger(EsGoodsSender.class);
    /**
      * send
      *
      * @params
      * @return
      * @throw
      *
      * @author chenz
      */
    public void send(String[] uuidArray){
        try {
            for (int i = 0; i < uuidArray.length; i++) {
                EsGoodsRabbitDTO esGoodsRabbitDTO = new EsGoodsRabbitDTO();
                esGoodsRabbitDTO.setGoodsUuid(uuidArray[i]);
                rabbitTemplate.convertAndSend(RabbitmqConstants.EsGoodsEnum.EXCHANGE.getValue(), RabbitmqConstants.EsGoodsEnum.ROUTING.getValue(), esGoodsRabbitDTO);
            }

        } catch (AmqpException e) {
            LOG.error("EsGoodsSender/send"+e);
        }
    }

    /**
     * send
     *
     * @params
     * @return
     * @throw
     *
     * @author chenz
     */
    public void send(String uuid){
        try {
                EsGoodsRabbitDTO esGoodsRabbitDTO = new EsGoodsRabbitDTO();
                esGoodsRabbitDTO.setGoodsUuid(uuid);
                rabbitTemplate.convertAndSend(RabbitmqConstants.EsGoodsEnum.EXCHANGE.getValue(), RabbitmqConstants.EsGoodsEnum.ROUTING.getValue(), esGoodsRabbitDTO);
        } catch (AmqpException e) {
            LOG.error("EsGoodsSender/send"+e);
        }
    }
}
