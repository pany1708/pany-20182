package com.kingthy.dubbo.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kingthy.dubbo.basedata.service.ShippingDubboService;
import com.kingthy.entity.Shipping;
import com.kingthy.mapper.ShippingMapper;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

/**
 * ShippingServiceImpl(描述其作用)
 * @author zhaochen 2017年08月28日 17:45
 *
 * @version 1.0.0
 */
@Service(version = "1.0.0",timeout = 3000)
public class ShippingServiceImpl implements ShippingDubboService
{

    @Autowired
    private ShippingMapper shippingMapper;


    @Override
    public int insert(Shipping shipping)
    {
        Date currentDate = new Date();
        shipping.setCreateDate(currentDate);
        shipping.setModifyDate(currentDate);
        shipping.setCreator("Creator");
        shipping.setModifier("Modifier");
        shipping.setDelFlag(false);
        shipping.setVersion(1);
        return shippingMapper.insertSelective(shipping);
    }

    @Override
    public int updateByUuid(Shipping shipping)
    {
        Example example = new Example(Shipping.class);
        example.createCriteria().andEqualTo("uuid",shipping.getUuid());
        return shippingMapper.updateByExampleSelective(shipping,example);
    }

    @Override
    public List<Shipping> selectAll()
    {
        return shippingMapper.selectAll();
    }

    @Override
    public Shipping selectByUuid(String uuid)
    {
        Example example = new Example(Shipping.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("uuid",uuid);
        List<Shipping> shippingList = shippingMapper.selectByExample(example);
        if (shippingList!=null && shippingList.size()>0){
            return shippingList.get(0);
        }
        return new Shipping();
    }

    @Override
    public int selectCount(Shipping shipping)
    {
        return shippingMapper.selectCount(shipping);
    }

    @Override
    public List<Shipping> select(Shipping shipping)
    {
        return shippingMapper.select(shipping);
    }

    @Override
    public Shipping selectOne(Shipping shipping)
    {
        return shippingMapper.selectOne(shipping);
    }

    @Override
    public PageInfo<Shipping> queryPage(Integer pageNum, Integer pageSize, Shipping shipping)
    {
        PageHelper.startPage(pageNum,pageSize);
        List<Shipping> shippingList = shippingMapper.select(shipping);
        return new PageInfo<>(shippingList);
    }
}
