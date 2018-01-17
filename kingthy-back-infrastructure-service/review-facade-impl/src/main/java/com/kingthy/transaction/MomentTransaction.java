package com.kingthy.transaction;

import com.kingthy.entity.Likes;
import com.kingthy.entity.MomentComment;
import com.kingthy.entity.Moments;
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
 * @class_name MomentTransaction
 * @description 删除动态（加事务）
 * @create 2017/8/18
 */
@Component
public class MomentTransaction {
    @Autowired
    private MomentsMapper momentsMapper;

    @Autowired
    private MomentCommentMapper momentCommentMapper;

    @Autowired
    private LikesMapper likesMapper;

    @Transactional(rollbackFor = Exception.class)
    public int deleteMoment(String uuid, String memberUuid) {
        Moments moments = new Moments();
        moments.setDelFlag(true);
        moments.setUuid(uuid);
        moments.setMemberUuid(memberUuid);
        Example example = new Example(Moments.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("uuid", uuid);
        criteria.andEqualTo("memberUuid",memberUuid);
        //逻辑删除动态
        int result = momentsMapper.updateByExampleSelective(moments, example);

        Example exampleLikes = new Example(Likes.class);
        Example.Criteria criteriaLikes = exampleLikes.createCriteria();
        criteriaLikes.andEqualTo("momentUuid",uuid);
        //删除动态的点赞记录
        likesMapper.deleteByExample(exampleLikes);

        //删除动态的评论的点赞记录
        likesMapper.deleteByMoment(uuid);

        Example exampleMomentComment = new Example(MomentComment.class);
        Example.Criteria criteriaMomentComment = exampleMomentComment.createCriteria();
        criteriaMomentComment.andEqualTo("momentUuid",uuid);
        //删除动态的评论记录
        momentCommentMapper.deleteByExample(exampleMomentComment);
        if(result == 0){
            throw new BizException("删除动态失败");
        }
        return result;
    }
}
