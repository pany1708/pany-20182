package com.kingthy.dubbo.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kingthy.dubbo.payment.service.PaymentDubboService;
import com.kingthy.entity.Payment;
import com.kingthy.mapper.PaymentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * PaymentDubboServiceImpl
 * <p>
 * @author yuanml
 * 2017/8/22
 *
 * @version 1.0.0
 */
@Service(version = "1.0.0",timeout = 3000)
public class PaymentDubboServiceImpl implements PaymentDubboService
{

    @Autowired
    private PaymentMapper paymentMapper;

    @Override
    public int insert(Payment payment)
    {
        return paymentMapper.insert(payment);
    }

    @Override
    public int updateByUuid(Payment payment)
    {
        Example example = new Example(Payment.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("uuid",payment.getUuid());
        return paymentMapper.updateByExampleSelective(payment,example);
    }

    @Override
    public List<Payment> selectAll()
    {
        return paymentMapper.selectAll();
    }

    @Override
    public Payment selectByUuid(String uuid)
    {
        Example example = new Example(Payment.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("uuid",uuid);
        return paymentMapper.selectByExample(example).get(0);
    }

    @Override
    public int selectCount(Payment payment)
    {
        return paymentMapper.selectCount(payment);
    }

    @Override
    public List<Payment> select(Payment payment)
    {
        return paymentMapper.select(payment);
    }

    @Override
    public Payment selectOne(Payment payment)
    {
        return paymentMapper.selectOne(payment);
    }

    @Override
    public PageInfo<Payment> queryPage(Integer pageNum, Integer pageSize, Payment payment)
    {
        PageHelper.startPage(pageNum,pageSize);
        return new PageInfo<>(this.select(payment));
    }
}
