package com.kingthy.service;

import com.kingthy.entity.EventPublish;

import java.util.List;

/**
 * @author xumin
 * @Description:
 * @DATE Created by 13:51 on 2017/8/23.
 * @Modified by:
 */
public interface EventPublishService {

    void pushCartEvent(List<EventPublish> var);

    void insertEventPublish(String creator, String payload, EventPublish.EventTypeEnum eventTypeEnum);
}
