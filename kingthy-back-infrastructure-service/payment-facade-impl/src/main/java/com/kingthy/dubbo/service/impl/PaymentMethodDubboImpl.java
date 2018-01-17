package com.kingthy.dubbo.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kingthy.dubbo.payment.service.PaymentMethodDubboService;
import com.kingthy.entity.PaymentMethod;
import com.kingthy.mapper.PaymentMethodMapper;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * PaymentMethodDubboImpl
 * <p>
 * @author yuanml
 * 2017/8/22
 *
 * @version 1.0.0
 */
@Service(version = "1.0.0",timeout = 3000)
public class PaymentMethodDubboImpl implements PaymentMethodDubboService
{

    @Autowired
    private PaymentMethodMapper paymentMethodMapper;

    @Override
    public int insert(PaymentMethod paymentMethod)
    {
        return paymentMethodMapper.insert(paymentMethod);
    }

    @Override
    public int updateByUuid(PaymentMethod paymentMethod)
    {
        Example example = new Example(PaymentMethod.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("uuid",paymentMethod.getUuid());
        return paymentMethodMapper.updateByExampleSelective(paymentMethod,paymentMethod);
    }

    @Override
    public List<PaymentMethod> selectAll()
    {
        return paymentMethodMapper.selectAll();
    }

    @Override
    public PaymentMethod selectByUuid(String uuid)
    {
        Example example = new Example(PaymentMethod.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("uuid",uuid);
        return paymentMethodMapper.selectByExample(example).get(0);
    }

    @Override
    public int selectCount(PaymentMethod paymentMethod)
    {
        return paymentMethodMapper.selectCount(paymentMethod);
    }

    @Override
    public List<PaymentMethod> select(PaymentMethod paymentMethod)
    {
        return paymentMethodMapper.select(paymentMethod);
    }

    @Override
    public PaymentMethod selectOne(PaymentMethod paymentMethod)
    {
        return paymentMethodMapper.selectOne(paymentMethod);
    }

    @Override
    public PageInfo<PaymentMethod> queryPage(Integer pageNum, Integer pageSize, PaymentMethod paymentMethod)
    {
        PageHelper.startPage(pageNum,pageSize);
        return new PageInfo<>(this.select(paymentMethod));
    }
}
