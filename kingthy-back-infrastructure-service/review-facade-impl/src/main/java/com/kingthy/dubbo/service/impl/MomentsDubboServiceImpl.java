package com.kingthy.dubbo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kingthy.dto.MomentDto;
import com.kingthy.dubbo.review.service.MomentsDubboService;
import com.kingthy.entity.Moments;
import com.kingthy.mapper.MomentsMapper;
import com.kingthy.request.QueryMomentPageReq;
import com.kingthy.transaction.MomentTransaction;

import tk.mybatis.mapper.entity.Example;

/**
 * @author yuanml
 * @Description:
 * @DATE Created by 13:54 on 2017/8/3.
 * @Modified by:
 */
@Service(version = "1.0.0", timeout = 10000)
public class MomentsDubboServiceImpl implements MomentsDubboService
{
    @Autowired
    private MomentsMapper momentsMapper;

    @Autowired
    private MomentTransaction momentTransaction;
    
    @Override
    public int insert(Moments moments)
    {
        return momentsMapper.insert(moments);
    }
    
    @Override
    public int updateByUuid(Moments moments)
    {
        Example example = new Example(Moments.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("uuid", moments.getUuid());
        return momentsMapper.updateByExampleSelective(moments, example);
    }
    
    /**
     * 未被调用，若调用需编写SQL查询
     * 
     * @return
     */
    @Override
    public List<Moments> selectAll()
    {
        return momentsMapper.selectAll();
    }
    
    @Override
    public Moments selectByUuid(String uuid)
    {
        Moments cond=new Moments();
        cond.setUuid(uuid);
        return momentsMapper.selectOne(cond);
    }
    
    /**
     * 未被调用，若调用需编写SQL查询
     * 
     * @param moments
     * @return
     */
    @Override
    public int selectCount(Moments moments)
    {
        return momentsMapper.selectCount(moments);
    }
    
    /**
     * 未被调用，若调用需编写SQL查询
     * 
     * @param moments
     * @return
     */
    @Override
    public List<Moments> select(Moments moments)
    {
        return momentsMapper.select(moments);
    }
    
    /**
     * 未被调用，若调用需编写SQL查询
     * 
     * @param moments
     * @return
     */
    @Override
    public Moments selectOne(Moments moments)
    {
        return momentsMapper.selectOne(moments);
    }
    
    /**
     * 未被调用，若调用需编写SQL查询
     * 
     * @param pageNum
     * @param pageSize
     * @param moments
     * @return
     */
    @Override
    public PageInfo<Moments> queryPage(Integer pageNum, Integer pageSize, Moments moments)
    {
        PageHelper.startPage(pageNum, pageSize);
        List<Moments> buyersShowList = this.select(moments);
        return new PageInfo<>(buyersShowList);
    }

    @Override
    public int updateLikes(String uuid, Integer likes, Integer comments) {
        return momentsMapper.updateLikes(uuid,likes,comments);
    }

    @Override
    public int publishMoment(Moments moments) {
        return momentsMapper.insertSelective(moments);
    }

    @Override
    public PageInfo<MomentDto> selectMoment(QueryMomentPageReq queryMomentPageReq) {
        PageHelper.startPage(queryMomentPageReq.getPageNum(),queryMomentPageReq.getPageSize());

        Example example = new Example(Moments.class);
        Example.Criteria criteria = example.createCriteria();
        if(null != queryMomentPageReq.getPhone()){
            criteria.andEqualTo("phone",queryMomentPageReq.getPhone());
        }
        if(!StringUtils.isEmpty(queryMomentPageReq.getReview())){
            criteria.andEqualTo("review",queryMomentPageReq.getReview());
        }
        if(null != queryMomentPageReq.getMemberNick()){
            criteria.andEqualTo("memberNick",queryMomentPageReq.getMemberNick());
        }

        if(null != queryMomentPageReq.getStartTime()){
            criteria.andGreaterThanOrEqualTo("createDate", queryMomentPageReq.getStartTime());
        }

        if(null != queryMomentPageReq.getEndTime()){
            criteria.andLessThanOrEqualTo("createDate", queryMomentPageReq.getEndTime());
        }

        if (!StringUtils.isEmpty(queryMomentPageReq.getMemberUuid())){
            criteria.andEqualTo("memberUuid", queryMomentPageReq.getMemberUuid());
        }
        if (!StringUtils.isEmpty(queryMomentPageReq.getContext())){
            criteria.andLike("context", queryMomentPageReq.getContext());
        }
        criteria.andEqualTo("delFlag" ,false);
        List<MomentDto> moments = momentsMapper.selectMoment(queryMomentPageReq);
        return new PageInfo<>(moments);
    }

    @Override
    public int deleteMoment(String uuid, String memberUuid) {
        return momentTransaction.deleteMoment(uuid,memberUuid);
    }
}
