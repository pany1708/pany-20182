package com.kingthy.dubbo.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kingthy.dubbo.payment.service.PaymentConfigDubboService;
import com.kingthy.entity.PaymentConfig;
import com.kingthy.mapper.PaymentConfigMapper;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * PaymentConfigDubboServiceImpl
 * <p>
 * @author yuanml
 * 2017/8/22
 *
 * @version 1.0.0
 */
@Service(version = "1.0.0",timeout = 3000)
public class PaymentConfigDubboServiceImpl implements PaymentConfigDubboService
{

    @Autowired
    private PaymentConfigMapper paymentConfigMapper;

    @Override
    public int insert(PaymentConfig paymentConfig)
    {
        return paymentConfigMapper.insert(paymentConfig);
    }

    @Override
    public int updateByUuid(PaymentConfig paymentConfig)
    {
        Example example = new Example(PaymentConfig.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("uuid",paymentConfig.getUuid());
        return paymentConfigMapper.updateByExampleSelective(paymentConfig,example);
    }

    @Override
    public List<PaymentConfig> selectAll()
    {
        return paymentConfigMapper.selectAll();
    }

    @Override
    public PaymentConfig selectByUuid(String uuid)
    {
        Example example = new Example(PaymentConfig.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("uuid",uuid);
        return paymentConfigMapper.selectByExample(example).get(0);
    }

    @Override
    public int selectCount(PaymentConfig paymentConfig)
    {
        return paymentConfigMapper.selectCount(paymentConfig);
    }

    @Override
    public List<PaymentConfig> select(PaymentConfig paymentConfig)
    {
        return paymentConfigMapper.select(paymentConfig);
    }

    @Override
    public PaymentConfig selectOne(PaymentConfig paymentConfig)
    {
        return paymentConfigMapper.selectOne(paymentConfig);
    }

    @Override
    public PageInfo<PaymentConfig> queryPage(Integer pageNum, Integer pageSize, PaymentConfig paymentConfig)
    {
        PageHelper.startPage(pageNum,pageSize);
        return new PageInfo<>(this.select(paymentConfig));
    }
}
