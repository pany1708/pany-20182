package com.kingthy.dubbo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kingthy.dto.MomentCommentDto;
import com.kingthy.dubbo.review.service.MomentCommentDubboService;
import com.kingthy.entity.MomentComment;
import com.kingthy.mapper.MomentCommentMapper;
import com.kingthy.transaction.MomentCommentTransaction;

import tk.mybatis.mapper.entity.Example;

/**
 * MomentCommentDubboServiceImpl
 * <p>
 * 
 * @author yuanml 2017/8/4
 *
 * @version 1.0.0
 */
@Service(version = "1.0.0", timeout = 10000)
public class MomentCommentDubboServiceImpl implements MomentCommentDubboService
{
    @Autowired
    private MomentCommentMapper momentCommentMapper;
    
    @Autowired
    private MomentCommentTransaction momentCommentTransaction;
    
    @Override
    public int insert(MomentComment momentComment)
    {
        return momentCommentMapper.insert(momentComment);
    }
    
    @Override
    public int updateByUuid(MomentComment momentComment)
    {
        Example example = new Example(MomentComment.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("uuid", momentComment.getUuid());
        return momentCommentMapper.updateByExampleSelective(momentComment, example);
    }
    
    /**
     * 未被调用，若调用需编写SQL查询
     * 
     * @return
     */
    @Override
    public List<MomentComment> selectAll()
    {
        return momentCommentMapper.selectAll();
    }
    
    /**
     * 未被调用，若调用需编写SQL查询
     * 
     * @param uuid
     * @return
     */
    @Override
    public MomentComment selectByUuid(String uuid)
    {
        Example example = new Example(MomentComment.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("uuid", uuid);
        return momentCommentMapper.selectByExample(example).get(0);
    }
    
    /**
     * 未被调用，若调用需编写SQL查询
     * 
     * @param momentComment
     * @return
     */
    @Override
    public int selectCount(MomentComment momentComment)
    {
        return momentCommentMapper.selectCount(momentComment);
    }
    
    /**
     * 未被调用，若调用需编写SQL查询
     * 
     * @param momentComment
     * @return
     */
    @Override
    public List<MomentComment> select(MomentComment momentComment)
    {
        return momentCommentMapper.select(momentComment);
    }
    
    /**
     * 未被调用，若调用需编写SQL查询
     * 
     * @param momentComment
     * @return
     */
    @Override
    public MomentComment selectOne(MomentComment momentComment)
    {
        return momentCommentMapper.selectOne(momentComment);
    }
    
    /**
     * 未被调用，若调用需编写SQL查询
     * 
     * @param pageNum
     * @param pageSize
     * @param momentComment
     * @return
     */
    @Override
    public PageInfo<MomentComment> queryPage(Integer pageNum, Integer pageSize, MomentComment momentComment)
    {
        PageHelper.startPage(pageNum, pageSize);
        List<MomentComment> momentComments = this.select(momentComment);
        return new PageInfo<>(momentComments);
    }
    
    @Override
    public int updateLikes(String uuid, Integer likes)
    {
        return momentCommentMapper.updateLikes(uuid, likes);
    }
    
    @Override
    public int deleteMomentComment(String uuid)
    {
        Example exampleMomentComment = new Example(MomentComment.class);
        Example.Criteria criteriaMomentComment = exampleMomentComment.createCriteria();
        criteriaMomentComment.andEqualTo("momentUuid", uuid);
        return momentCommentMapper.deleteByExample(exampleMomentComment);
    }
    
    @Override
    public PageInfo<MomentCommentDto> selectComment(Integer pageNo, Integer pageSize, String momentUuid,
        String memberUuid)
    {
        PageHelper.startPage(pageNo, pageSize);
        List<MomentCommentDto> moments = momentCommentMapper.selectComment(momentUuid, memberUuid);
        return new PageInfo<>(moments);
    }
    
    @Override
    public int deleteByUuidAndMemberUuid(MomentComment momentComment)
    {
        Example example = new Example(MomentComment.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("uuid", momentComment.getUuid());
        criteria.andEqualTo("memberUuid", momentComment.getMemberUuid());
        return momentCommentMapper.deleteByExample(example);
    }
    
    @Override
    public int deleteByParentUuid(MomentComment momentComment)
    {
        Example exampleParent = new Example(MomentComment.class);
        Example.Criteria criteriaParent = exampleParent.createCriteria();
        criteriaParent.andEqualTo("parentUuid", momentComment.getUuid());
        return momentCommentMapper.deleteByExample(exampleParent);
    }
    
    @Override
    public int publishComment(MomentComment momentComment)
    {
        return momentCommentTransaction.publishComment(momentComment);
    }
    
    @Override
    public int deleteMomentComment(MomentComment momentComment)
    {
        return momentCommentTransaction.deleteMomentComment(momentComment);
    }
}
