package com.kingthy.service.impl;

import com.google.gson.Gson;
import com.kingthy.common.CommonUtils;
import com.kingthy.constant.RabbitmqConstants;
import com.kingthy.dto.EventPublishUpdateDTO;
import com.kingthy.entity.EventPublish;
import com.kingthy.mapper.EventPublishMapper;
import com.kingthy.request.EventPublishReq;
import com.kingthy.service.EventPublishService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author xumin
 * @Description:
 * @DATE Created by 14:17 on 2017/8/23.
 * @Modified by:
 */
@Service
@Transactional
public class EventPublishServiceImpl implements EventPublishService
{

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private EventPublishMapper eventPublishMapper;

    @Override
    public void pushCartEvent(List<EventPublish> var) {

        if (var == null || var.isEmpty()){
            return;
        }

        List<String> uuidList = new ArrayList<>();
        List<EventPublishReq> eventPublishReqs = new ArrayList<>();
        Gson gson = new Gson();

        for (EventPublish publish : var){
            uuidList.add(publish.getUuid());
            eventPublishReqs.add(gson.fromJson(publish.getPayload(), EventPublishReq.class));
        }

        EventPublishUpdateDTO dto = new EventPublishUpdateDTO();
        dto.setUuidList(uuidList);
        dto.setEventStatus(EventPublish.EventStatusEnum.PUBLISHED.name());
        dto.setModifyDate(new Date());

        eventPublishMapper.updateBatchEventStatus(dto);


        for (EventPublishReq req : eventPublishReqs){

            rabbitTemplate.convertAndSend(RabbitmqConstants.OrderEventEnum.EXCHANGE.getValue(),
                    RabbitmqConstants.OrderEventEnum.ROUTING.getValue(), req);
        }

/*        EventPublish eventPublish = new EventPublish();
        eventPublish.setUuid(var.getUuid());
        eventPublish.setModifyDate(new Date());
        eventPublish.setEventStatus(EventPublish.EventStatusEnum.PUBLISHED.toString());
        eventPublish.setVersion(var.getVersion() + 1);

        Example example = new Example(EventPublish.class);
        example.createCriteria().andEqualTo("uuid", eventPublish.getUuid());
        eventPublishMapper.updateByExampleSelective(eventPublish, example);

        Gson gson = new Gson();
        EventPublishReq req = gson.fromJson(var.getPayload(), EventPublishReq.class);

        rabbitTemplate.convertAndSend(RabbitmqConstants.OrderEventEnum.EXCHANGE.getValue(),
                RabbitmqConstants.OrderEventEnum.ROUTING.getValue(), req);
                */
    }

    @Override
    public void insertEventPublish(String creator, String payload, EventPublish.EventTypeEnum eventTypeEnum) {
        EventPublish var = new EventPublish();
        var.setCreateDate(new Date());
        var.setCreator(creator);
        var.setVersion(0);
        var.setDelFlag(false);
        var.setEventStatus(EventPublish.EventStatusEnum.NEW.toString());
        var.setEventType(eventTypeEnum.toString());
        var.setPayload(payload);

        eventPublishMapper.insert(var);

        Example example = new Example(EventPublish.class);
        example.createCriteria().andEqualTo("uuid", var.getUuid());

        EventPublish publish = new EventPublish();

        String sharding = CommonUtils.uuidToSharding(var.getUuid());
        publish.setSharding(sharding);

        eventPublishMapper.updateByExampleSelective(publish, example);

    }
}
