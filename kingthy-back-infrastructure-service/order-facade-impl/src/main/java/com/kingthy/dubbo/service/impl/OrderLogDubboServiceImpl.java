package com.kingthy.dubbo.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kingthy.dubbo.order.service.OrderLogDubboService;
import com.kingthy.entity.OrderItem;
import com.kingthy.entity.OrderLog;
import com.kingthy.mapper.OrderLogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @author xumin
 * @Description:
 * @DATE Created by 17:13 on 2017/8/4.
 * @Modified by:
 */
@Service(version = "1.0.0", timeout = 10000)
public class OrderLogDubboServiceImpl implements OrderLogDubboService {

    @Autowired
    private OrderLogMapper orderLogMapper;

    @Override
    public int insert(OrderLog orderLog) {
        return orderLogMapper.insert(orderLog);
    }

    @Override
    public int updateByUuid(OrderLog orderLog) {
        Example example = new Example(OrderItem.class);
        example.createCriteria().andEqualTo("uuid", orderLog.getUuid());
        return orderLogMapper.updateByExample(orderLog,example);
    }

    @Override
    public List<OrderLog> selectAll() {
        return orderLogMapper.selectAll();
    }

    @Override
    public OrderLog selectByUuid(String uuid) {
        OrderLog orderLog=new OrderLog();
        orderLog.setUuid(uuid);
        return orderLogMapper.selectOne(orderLog);
    }

    @Override
    public int selectCount(OrderLog orderLog) {
        return orderLogMapper.selectCount(orderLog);
    }

    @Override
    public List<OrderLog> select(OrderLog var1) {
        return orderLogMapper.select(var1);
    }

    @Override
    public OrderLog selectOne(OrderLog var1) {
        return orderLogMapper.selectOne(var1);
    }

    @Override
    public PageInfo<OrderLog> queryPage(Integer pageNum, Integer pageSize, OrderLog orderLog) {

        PageHelper.startPage(pageNum, pageSize);

        return new PageInfo<>(orderLogMapper.select(orderLog));
    }

}
