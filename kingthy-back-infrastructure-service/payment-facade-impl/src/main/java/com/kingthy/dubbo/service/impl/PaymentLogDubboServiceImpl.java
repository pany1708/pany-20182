package com.kingthy.dubbo.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kingthy.dubbo.payment.service.PaymentLogDubboService;
import com.kingthy.entity.PaymentLog;
import com.kingthy.mapper.PaymentLogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * PaymentLogDubboServiceImpl
 * <p>
 * @author yuanml
 * 2017/8/22
 *
 * @version 1.0.0
 */
@Service(version = "1.0.0",timeout = 3000)
public class PaymentLogDubboServiceImpl implements PaymentLogDubboService
{

    @Autowired
    private PaymentLogMapper paymentLogMapper;

    @Override
    public int insert(PaymentLog paymentLog)
    {
        return paymentLogMapper.insert(paymentLog);
    }

    @Override
    public int updateByUuid(PaymentLog paymentLog)
    {
        Example example = new Example(PaymentLog.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("uuid",paymentLog.getUuid());
        return paymentLogMapper.updateByExampleSelective(paymentLog,example);
    }

    @Override
    public List<PaymentLog> selectAll()
    {
        return paymentLogMapper.selectAll();
    }

    @Override
    public PaymentLog selectByUuid(String uuid)
    {
        Example example = new Example(PaymentLog.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("uuid",uuid);
        return paymentLogMapper.selectByExample(example).get(0);
    }

    @Override
    public int selectCount(PaymentLog paymentLog)
    {
        return paymentLogMapper.selectCount(paymentLog);
    }

    @Override
    public List<PaymentLog> select(PaymentLog paymentLog)
    {
        return paymentLogMapper.select(paymentLog);
    }

    @Override
    public PaymentLog selectOne(PaymentLog paymentLog)
    {
        return paymentLogMapper.selectOne(paymentLog);
    }

    @Override
    public PageInfo<PaymentLog> queryPage(Integer pageNum, Integer pageSize, PaymentLog paymentLog)
    {
        PageHelper.startPage(pageNum,pageSize);
        return new PageInfo<>(this.select(paymentLog));
    }
}
