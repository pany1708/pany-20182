package com.kingthy.dubbo.service.impl;

import java.util.List;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.kingthy.dubbo.review.service.BuyersShowImgDubboService;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.pagehelper.PageInfo;
import com.kingthy.entity.BuyersShowImg;
import com.kingthy.mapper.BuyersShowImgMapper;

import tk.mybatis.mapper.entity.Example;

/**
 * BuyersShowImgDubboServiceImpl
 * <p>
 * @author yuanml
 * 2017/8/4
 *
 * @version 1.0.0
 */
@Service(version = "1.0.0", timeout = 10000)
public class BuyersShowImgDubboServiceImpl implements BuyersShowImgDubboService
{
    @Autowired
    private BuyersShowImgMapper buyersShowImgMapper;
    @Override
    public int insert(BuyersShowImg buyersShowImg)
    {
        return buyersShowImgMapper.insert(buyersShowImg);
    }

    @Override
    public int updateByUuid(BuyersShowImg buyersShowImg)
    {
        Example example = new Example(BuyersShowImg.class);
        Example.Criteria criteria =example.createCriteria();
        criteria.andEqualTo("uuid",buyersShowImg.getUuid());
        return buyersShowImgMapper.updateByExampleSelective(buyersShowImg,example);
    }

    /**
     * 未被调用，若调用需编写SQL查询
     * 
     * @return
     */
    @Override
    public List<BuyersShowImg> selectAll()
    {
        return buyersShowImgMapper.selectAll();
    }

    /**
     * 未被调用，若调用需编写SQL查询
     * 
     * @param uuid
     * @return
     */
    @Override
    public BuyersShowImg selectByUuid(String uuid)
    {
        Example example = new Example(BuyersShowImg.class);
        Example.Criteria criteria =example.createCriteria();
        criteria.andEqualTo("uuid",uuid);
        return buyersShowImgMapper.selectByExample(example).get(0);
    }

    /**
     * 未被调用，若调用需编写SQL查询
     * 
     * @param buyersShowImg
     * @return
     */
    @Override
    public int selectCount(BuyersShowImg buyersShowImg)
    {
        return buyersShowImgMapper.selectCount(buyersShowImg);
    }

    @Override
    public List<BuyersShowImg> select(BuyersShowImg buyersShowImg)
    {
        return buyersShowImgMapper.select(buyersShowImg);
    }

    /**
     * 未被调用，若调用需编写SQL查询
     * 
     * @param buyersShowImg
     * @return
     */
    @Override
    public BuyersShowImg selectOne(BuyersShowImg buyersShowImg)
    {
        return buyersShowImgMapper.selectOne(buyersShowImg);
    }

    /**
     * 未被调用，若调用需编写SQL查询
     * 
     * @param pageNum
     * @param pageSize
     * @param buyersShowImg
     * @return
     */
    @Override
    public PageInfo<BuyersShowImg> queryPage(Integer pageNum, Integer pageSize, BuyersShowImg buyersShowImg)
    {
        PageHelper.startPage(pageNum,pageSize);
        List<BuyersShowImg> buyersShowImgs = this.select(buyersShowImg);
        return new PageInfo<>(buyersShowImgs);
    }
}
