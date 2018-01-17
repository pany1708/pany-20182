package com.kingthy.dubbo.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kingthy.dubbo.basedata.service.ShopDubboService;
import com.kingthy.entity.Shop;
import com.kingthy.mapper.ShopMapper;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

/**
 * ShopServiceImpl(描述其作用)
 * @author zhaochen 2017年08月28日 17:46
 *
 * @version 1.0.0
 */
@Service(version = "1.0.0",timeout = 3000)
public class ShopServiceImpl implements ShopDubboService
{

    @Autowired
    private ShopMapper shopMapper;

    @Override
    public int insert(Shop shop)
    {
        Date currentDate = new Date();
        shop.setCreateDate(currentDate);
        shop.setModifyDate(currentDate);
        shop.setDelFlag(false);
        shop.setVersion(1);
        return shopMapper.insertSelective(shop);
    }

    @Override
    public int updateByUuid(Shop shop)
    {
        Shop shopResult = selectByUuid(shop.getUuid());
        Integer currentVersion = shopResult.getVersion();
        Example example = new Example(Shop.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("uuid",shop.getUuid());
        criteria.andEqualTo("version",currentVersion);
        shop.setVersion(currentVersion+1);
        return shopMapper.updateByExampleSelective(shop,example);
    }

    @Override
    public List<Shop> selectAll()
    {
        return shopMapper.selectAll();
    }

    @Override
    public Shop selectByUuid(String uuid)
    {
        return shopMapper.selectByUuid(uuid);
    }

    @Override
    public int selectCount(Shop shop)
    {
        return shopMapper.selectCountByName(shop);
    }

    @Override
    public List<Shop> select(Shop shop)
    {
        return shopMapper.select(shop);
    }

    @Override
    public Shop selectOne(Shop shop)
    {
        return shopMapper.selectOne(shop);
    }

    @Override
    public PageInfo<Shop> queryPage(Integer pageNum, Integer pageSize, Shop shop)
    {
        PageHelper.startPage(pageNum,pageSize);
        List<Shop> shopList = shopMapper.select(shop);
        return new PageInfo<>(shopList);
    }
}
