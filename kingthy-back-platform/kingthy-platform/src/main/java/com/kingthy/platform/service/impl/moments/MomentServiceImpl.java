package com.kingthy.platform.service.impl.moments;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kingthy.entity.Likes;
import com.kingthy.entity.MomentComment;
import com.kingthy.exception.BizException;
import com.kingthy.platform.dto.moments.QueryMomentPageReq;
import com.kingthy.platform.dto.moments.UpdateMomentReq;
import com.kingthy.platform.entity.moments.Moments;
import com.kingthy.platform.mapper.moments.MomentCommentMapper;
import com.kingthy.platform.mapper.moments.MomentMapper;
import com.kingthy.platform.service.moments.MomentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

import java.util.Date;
import java.util.List;

/**
 * MomentServiceImpl(描述其作用)
 * <p>
 * 赵生辉 2017年05月17日 17:29
 *
 * @version 1.0.0
 */
@Service("momentService")
public class MomentServiceImpl implements MomentService
{
    @Autowired
    private MomentMapper momentMapper;

    @Autowired
    private MomentCommentMapper momentCommentMapper;

    private static final int VERSION = 1;
    @Override
    @Transactional
    public int publishMoment(Moments moments)
    {
        Date date = new Date();
        moments.setCreateDate(date);
        moments.setModifyDate(date);
        moments.setCreator("Creator");
        moments.setModeifier("Modeifier");
        moments.setVersion(VERSION);
        moments.setDelFlag(false);
        moments.setLikeAmount(0L);
        moments.setCommentAmount(0L);
        int result = momentMapper.insertSelective(moments);
        if(result <= 0)
        {
            throw new BizException("发布动态失败");
        }
        return result;
    }

    @Override
    public PageInfo<Moments> queryMomentPage(QueryMomentPageReq queryMomentPageReq)
    {
        /*Example example = new Example(Moments.class);
        if(order.equals("time"))//排序方式，发布时间和热度两种排序
        {
            example.setOrderByClause("create_date desc");
        }
        else
        {
            example.setOrderByClause("like_amount desc ,comment_amount desc ");
        }

        Criteria criteria = example.createCriteria();
        if(members.size() > 0)//筛选
        {
            criteria.andIn("memberUuid",members);
        }*/
        PageHelper.startPage(queryMomentPageReq.getPageNo(),queryMomentPageReq.getPageSize());
        //List<Moments> moments = momentMapper.selectByExample(example);
        Moments moments = JSONObject.parseObject(JSONObject.toJSONString(queryMomentPageReq),Moments.class);
        Example example = new Example(Moments.class);
        Criteria criteria = example.createCriteria();
        if(null != queryMomentPageReq.getPhone()){
            criteria.andEqualTo("phone",queryMomentPageReq.getPhone());
        }
        if(null != queryMomentPageReq.getReview()){
            criteria.andEqualTo("phone",queryMomentPageReq.getPhone());
        }
        if(null != queryMomentPageReq.getMemberNick()){
            criteria.andEqualTo("memberNick",queryMomentPageReq.getMemberNick());
        }
        if(null != queryMomentPageReq.getBeginDate() && null != queryMomentPageReq.getEndDate()){
            criteria.andBetween("createDate",queryMomentPageReq.getBeginDate(),queryMomentPageReq.getEndDate());
        }
        List<Moments> momentsList = momentMapper.selectByExample(example);
        PageInfo<Moments> pageInfo = new PageInfo<Moments>(momentsList);
        return pageInfo;
    }

    @Override
    public int update(UpdateMomentReq updateMomentReq)
    {
        Example example = new Example(Moments.class);
        example.createCriteria().andEqualTo("uuid",updateMomentReq.getUuid());
        Moments moments = JSONObject.parseObject(JSON.toJSONString(updateMomentReq),Moments.class);
        int result = momentMapper.updateByExample(moments,example);
        return result;
    }

    @Override
    public Moments queryInfo(String uuid)
    {
        Example example = new Example(Moments.class);
        example.createCriteria().andEqualTo("uuid",uuid);
        Moments moments;
        try
        {
            moments  = momentMapper.selectByExample(example).get(0);
        }
        catch (Exception e)
        {
            return null;
        }
        return moments;
    }
}
