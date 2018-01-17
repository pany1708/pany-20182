package com.kingthy.dubbo.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kingthy.dubbo.review.service.ReplyCommentDubboService;
import com.kingthy.entity.ReplyComment;
import com.kingthy.mapper.ReplyCommentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * Created by likejie on 2017/8/24.
 * @author likejie
 */
@Service(version = "1.0.0", timeout = 10000)
public class ReplyCommentDubboServiceImpl  implements ReplyCommentDubboService{

    @Autowired
    private ReplyCommentMapper mapper;

    @Override
    public int insert(ReplyComment replyComment) {
        return mapper.insert(replyComment);
    }

    @Override
    public int updateByUuid(ReplyComment replyComment) {
        Example example = new Example(ReplyComment.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("uuid", replyComment.getUuid());
        return mapper.updateByExample(replyComment,example);
    }

    /**
     * 未被调用，若调用需编写SQL查询
     * 
     * @return
     */
    @Override
    public List<ReplyComment> selectAll() {
        return mapper.selectAll();
    }

    @Override
    public ReplyComment selectByUuid(String uuid) {
        ReplyComment replyComment = new ReplyComment();
        replyComment.setUuid(uuid);
        return mapper.selectOne(replyComment);
    }

    /**
     * 未被调用，若调用需编写SQL查询
     * 
     * @param replyComment
     * @return
     */
    @Override
    public int selectCount(ReplyComment replyComment) {
        return mapper.selectCount(replyComment);
    }

    /**
     * 未被调用，若调用需编写SQL查询
     * 
     * @param var1
     * @return
     */
    @Override
    public List<ReplyComment> select(ReplyComment var1) {
        return mapper.select(var1);
    }

    /**
     * 未被调用，若调用需编写SQL查询
     * 
     * @param var1
     * @return
     */
    @Override
    public ReplyComment selectOne(ReplyComment var1) {
        return mapper.selectOne(var1);
    }

    /**
     * 未被调用，若调用需编写SQL查询
     * 
     * @param pageNum
     * @param pageSize
     * @param replyComment
     * @return
     */
    @Override
    public PageInfo<ReplyComment> queryPage(Integer pageNum, Integer pageSize, ReplyComment replyComment) {
        PageHelper.startPage(pageNum,pageSize);
        List<ReplyComment> moments = mapper.select(replyComment);
        return new PageInfo<>(moments);
    }
}
