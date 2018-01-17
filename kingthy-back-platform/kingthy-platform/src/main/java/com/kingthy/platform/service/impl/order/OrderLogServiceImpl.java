package com.kingthy.platform.service.impl.order;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kingthy.platform.entity.order.OrderLog;
import com.kingthy.platform.mapper.order.OrderLogMapper;
import com.kingthy.platform.service.order.OrderLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

import java.util.List;

/**
 * 
 *
 * OrderLogServiceImpl
 * 
 * 赵生辉 2017年4月14日 上午9:52:45
 * 
 * @version 1.0.0
 *
 */
@Service(value = "orderLogService")
public class OrderLogServiceImpl implements OrderLogService
{
    @Autowired
    private transient OrderLogMapper orderLogMapper;
    
    @Override
    public int create(OrderLog orderLog)
    {
        int result = orderLogMapper.insert(orderLog);
        return result;
    }
    
    @Override
    public int update(OrderLog orderLog)
    {
        Example example = new Example(OrderLog.class);
        Criteria criteria = example.createCriteria();
        criteria.andEqualTo("id", orderLog.getId());
        int result = orderLogMapper.updateByExample(orderLog, example);
        return result;
    }
    
    @Override
    public List<OrderLog> findAllOrderLog(OrderLog orderLog)
    {
        Example example = new Example(OrderLog.class);
        Criteria criteria = example.createCriteria();
        criteria.andEqualTo("orderUuid", orderLog.getOrderUuid());
        List<OrderLog> result = orderLogMapper.selectByExample(example);
        return result;
    }
    
    @Override
    public PageInfo<OrderLog> findOrderLogPage(Integer pageNo, Integer pageSize, OrderLog orderLog)
    {
        Example example = new Example(OrderLog.class);
        Criteria criteria = example.createCriteria();
        criteria.andEqualTo("orderUuid", orderLog.getOrderUuid());
        criteria.andEqualTo("delFlag", false);
        PageHelper.startPage(pageNo, pageSize);
        List<OrderLog> result = orderLogMapper.selectByExample(example);
        PageInfo<OrderLog> pageInfo = new PageInfo<OrderLog>(result);
        return pageInfo;
    }
    
}
