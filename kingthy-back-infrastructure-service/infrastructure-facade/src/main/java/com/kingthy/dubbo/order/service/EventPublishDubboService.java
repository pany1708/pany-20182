package com.kingthy.dubbo.order.service;

import com.kingthy.base.BaseService;
import com.kingthy.entity.EventPublish;


/**
 * @author xumin
 * @Description:
 * @DATE Created by 16:17 on 2017/8/22.
 * @Modified by:
 */
public interface EventPublishDubboService extends BaseService<EventPublish>
{
    void pushEventBySharding(String sharding, final int limit);
}
