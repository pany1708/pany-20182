package com.kingthy.dubbo.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import com.kingthy.common.CommonUtils;
import com.kingthy.dubbo.cart.service.EventProcessDubboService;
import com.kingthy.entity.EventProcess;
import com.kingthy.mapper.EventProcessMapper;
import com.kingthy.request.EventPublishReq;
import com.kingthy.service.CartServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

/**
 * @author  xumin
 * @Description:
 * @DATE Created by 15:30 on 2017/8/23.
 * @Modified by:
 */
@Service(version = "1.0.0", timeout = 10000, interfaceClass = EventProcessDubboService.class)
public class EventProcessDubboServiceImpl implements EventProcessDubboService {

    @Autowired
    private EventProcessMapper eventProcessMapper;

    @Autowired
    private transient CartServiceImpl cartServiceImpl;

    @Override
    public int insert(EventProcess t) {
        eventProcessMapper.insert(t);

        String sharding = CommonUtils.uuidToSharding(t.getUuid());

        t.setSharding(sharding);

        return eventProcessMapper.updateByPrimaryKeySelective(t);
    }

    @Override
    public int insert(EventPublishReq t)
    {

        EventProcess var = new EventProcess();
        var.setCreateDate(new Date());
        var.setVersion(0);
        var.setDelFlag(false);
        var.setEventStatus(EventProcess.EventStatusEnum.NEW.toString());
        var.setEventType(EventProcess.EventTypeEnum.CART.toString());

        Gson gson = new Gson();

        String payload = gson.toJson(t);

        var.setPayload(payload);

        return insert(var);
    }

    private PageInfo<EventProcess> findPage(Integer pageNum, Integer pageSize, EventProcess var) {

        PageHelper.startPage(pageNum, pageSize);

        return new PageInfo<>(eventProcessMapper.select(var));
    }

    @Override
    public void updateCartByEvent(String sharding, int limit) {
        EventProcess var1 = new EventProcess();
        var1.setSharding(sharding);
        var1.setDelFlag(false);

        findPage(0, limit, var1).getList().forEach(v -> cartServiceImpl.updateCartInfoByOrder(v));
    }
}
