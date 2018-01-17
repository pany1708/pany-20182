package com.kingthy.dubbo.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kingthy.dubbo.order.service.EventPublishDubboService;
import com.kingthy.entity.EventPublish;
import com.kingthy.mapper.EventPublishMapper;
import com.kingthy.service.EventPublishService;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

/**
 * @author xumin
 * @Description:
 * @DATE Created by 16:19 on 2017/8/22.
 * @Modified by:
 */
@Service(version = "1.0.0", timeout = 10000, interfaceClass = EventPublishDubboService.class)
public class EventPublishDubboServiceImpl implements EventPublishDubboService
{

    @Autowired
    private EventPublishMapper eventPublishMapper;

    @Autowired
    private EventPublishService eventPublishService;

    @Override
    public int insert(EventPublish var) {
        return eventPublishMapper.insert(var);
    }

    @Override
    public int updateByUuid(EventPublish var) {

        Example example = new Example(EventPublish.class);
        example.createCriteria().andEqualTo("uuid", var.getUuid());
        return eventPublishMapper.updateByExampleSelective(var, example);
    }

    @Override
    public List<EventPublish> selectAll() {
        return eventPublishMapper.selectAll();
    }

    @Override
    public EventPublish selectByUuid(String uuid) {
        EventPublish t = new EventPublish();
        t.setUuid(uuid);
        return selectOne(t);
    }

    @Override
    public int selectCount(EventPublish var) {
        return eventPublishMapper.selectCount(var);
    }

    @Override
    public List<EventPublish> select(EventPublish var1) {
        return eventPublishMapper.select(var1);
    }

    @Override
    public EventPublish selectOne(EventPublish var1) {
        return eventPublishMapper.selectOne(var1);
    }

    @Override
    public PageInfo<EventPublish> queryPage(Integer pageNum, Integer pageSize, EventPublish var) {

        PageHelper.startPage(pageNum, pageSize);

        return new PageInfo<>(eventPublishMapper.select(var));
    }

    @Override
    public void pushEventBySharding(String sharding, final int limit)
    {

        EventPublish var1 = new EventPublish();
        var1.setSharding(sharding);
        var1.setDelFlag(false);
        var1.setEventType(EventPublish.EventTypeEnum.CART.name());
        var1.setEventStatus(EventPublish.EventStatusEnum.NEW.name());
//        queryPage(0, limit, var1).getList().forEach(vo -> eventPublishService.pushCartEvent(vo));
        eventPublishService.pushCartEvent(queryPage(0, limit, var1).getList());

    }
}
