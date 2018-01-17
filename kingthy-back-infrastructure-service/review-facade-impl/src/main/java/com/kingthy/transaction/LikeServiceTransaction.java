package com.kingthy.transaction;

import com.kingthy.entity.Likes;
import com.kingthy.exception.BizException;
import com.kingthy.mapper.LikesMapper;
import com.kingthy.mapper.MomentCommentMapper;
import com.kingthy.mapper.MomentsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

/**
 * @author chenz
 * @version 1.0.0
 * @class_name LikeServiceTransaction
 * @description 点赞（事务）
 * @create 2017/8/17
 */
@Component
public class LikeServiceTransaction {
    @Autowired
    private LikesMapper likesMapper;

    @Autowired
    private MomentCommentMapper momentCommentMapper;

    @Autowired
    private MomentsMapper momentsMapper;

    @Transactional(rollbackFor = Exception.class)
    public int createLike(Likes likes) {
        int result = likesMapper.insertSelective(likes);
        if(result <= 0)
        {
            throw new BizException("点赞失败");
        }
        if(likes.getType() == Likes.LikesType.moments.getValue())
        {
            momentsMapper.updateLikes(likes.getMomentUuid(),1,0);
        }
        else if(likes.getType() == Likes.LikesType.comment.getValue())
        {
            momentCommentMapper.updateLikes(likes.getMomentUuid(),1);
        }
        return result;
    }

    @Transactional(rollbackFor = Exception.class)
    public int deleteLike(Likes likes)
    {
        Example example = new Example(Likes.class);
        Example.Criteria criteria =example.createCriteria();
        criteria.andEqualTo("delFlag",false);
        criteria.andEqualTo("type",likes.getType());
        criteria.andEqualTo("momentUuid",likes.getMomentUuid());
        criteria.andEqualTo("memberUuid",likes.getMemberUuid());
        int result = likesMapper.deleteByExample(example);
        if(result == 0)
        {
            throw new BizException("取消点赞失败");
        }
        if(likes.getType() == Likes.LikesType.moments.getValue())
        {
            momentsMapper.updateLikes(likes.getMomentUuid(),-1,0);
        }
        else if(likes.getType() == Likes.LikesType.comment.getValue())
        {
            momentCommentMapper.updateLikes(likes.getMomentUuid(),-1);
        }
        return result;
    }
}
