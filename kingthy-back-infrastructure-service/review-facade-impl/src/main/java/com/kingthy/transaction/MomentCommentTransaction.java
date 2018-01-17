package com.kingthy.transaction;

import com.kingthy.entity.MomentComment;
import com.kingthy.exception.BizException;
import com.kingthy.mapper.LikesMapper;
import com.kingthy.mapper.MomentCommentMapper;
import com.kingthy.mapper.MomentsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;

/**
 * @author chenz
 * @version 1.0.0
 * @class_name MomentCommentTransaction
 * @description 动态评论（加事务）
 * @create 2017/8/17
 */
@Component
public class MomentCommentTransaction {

    @Autowired
    private MomentsMapper momentsMapper;

    @Autowired
    private MomentCommentMapper momentCommentMapper;

    @Autowired
    private LikesMapper likesMapper;

    @Transactional(rollbackFor = Exception.class)
    public int publishComment(MomentComment momentComment) {
        Date date = new Date();
        momentComment.setCreateDate(date);
        momentComment.setModifyDate(date);
        momentComment.setCreator("Creator");
        momentComment.setModeifier("Modeifier");
        momentComment.setVersion(1);
        momentComment.setDelFlag(false);
        momentComment.setLikeAmount(0L);
        int result = momentCommentMapper.insert(momentComment);
        if(result == 0)
        {
            throw new BizException("发布评论失败");
        }
        int resultUpdate = momentsMapper.updateLikes(momentComment.getMomentUuid(),0,1);
        if(resultUpdate == 0){
            throw new BizException("发布评论失败");
        }
        return  result;
    }

    @Transactional(rollbackFor = Exception.class)
    public int deleteMomentComment(MomentComment momentComment) {

        Example example = new Example(MomentComment.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("uuid",momentComment.getUuid());
        criteria.andEqualTo("memberUuid",momentComment.getMemberUuid());
        Example exampleParent = new Example(MomentComment.class);
        Example.Criteria criteriaParent = exampleParent.createCriteria();
        criteriaParent.andEqualTo("parentUuid",momentComment.getUuid());

        //删除评论及其回复的点赞
        likesMapper.deleteByComment(momentComment.getUuid());
        //删除该评论的回复
        int momentResult = momentCommentMapper.deleteByExample(exampleParent);
        //删除动态的评论
        int result = momentCommentMapper.deleteByExample(example);
        int amount = 0 - (momentResult + 1);
        if(result != 0)
        {
            int resultMoment = momentsMapper.updateLikes(momentComment.getMomentUuid(),0,amount);
            if(resultMoment == 0)
            {
                throw new BizException("删除评论失败");
            }
        }
        else
        {
            throw new BizException("删除评论失败");
        }
        return result;
    }
}
