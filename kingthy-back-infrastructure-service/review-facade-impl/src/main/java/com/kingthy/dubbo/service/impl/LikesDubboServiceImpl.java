package com.kingthy.dubbo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kingthy.dto.BuyersShowListDTO;
import com.kingthy.dubbo.review.service.LikesDubboService;
import com.kingthy.entity.Likes;
import com.kingthy.mapper.LikesMapper;
import com.kingthy.mapper.MomentCommentMapper;
import com.kingthy.mapper.MomentsMapper;
import com.kingthy.transaction.LikeServiceTransaction;

import tk.mybatis.mapper.entity.Example;

/**
 * LikesDubboServiceImpl
 * <p>
 * 
 * @author yuanml 2017/8/9
 *
 * @version 1.0.0
 */
@Service(version = "1.0.0", timeout = 10000)
public class LikesDubboServiceImpl implements LikesDubboService
{
    @Autowired
    private LikesMapper likesMapper;
    
    @Autowired
    private LikeServiceTransaction likeServiceTransaction;
    
    @Override
    public int insert(Likes likes)
    {
        return likesMapper.insert(likes);
    }
    
    @Override
    public int updateByUuid(Likes likes)
    {
        Example example = new Example(Likes.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("uuid", likes.getUuid());
        return likesMapper.updateByExampleSelective(likes, example);
    }
    
    /**
     * 未被调用，若调用需编写SQL查询
     * 
     * @return
     */
    @Override
    public List<Likes> selectAll()
    {
        return likesMapper.selectAll();
    }
    
    /**
     * 未被调用，若调用需编写SQL查询
     * 
     * @param uuid
     * @return
     */
    @Override
    public Likes selectByUuid(String uuid)
    {
        Example example = new Example(Likes.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("uuid", uuid);
        return likesMapper.selectByExample(example).get(0);
    }
    
    /**
     * 未被调用，若调用需编写SQL查询
     * 
     * @param likes
     * @return
     */
    @Override
    public int selectCount(Likes likes)
    {
        return likesMapper.selectCount(likes);
    }
    
    /**
     * 未被调用，若调用需编写SQL查询
     * 
     * @param likes
     * @return
     */
    @Override
    public List<Likes> select(Likes likes)
    {
        return likesMapper.select(likes);
    }
    
    /**
     * 未被调用，若调用需编写SQL查询
     * 
     * @param likes
     * @return
     */
    @Override
    public Likes selectOne(Likes likes)
    {
        return likesMapper.selectOne(likes);
    }
    
    /**
     * 未被调用，若调用需编写SQL查询
     * 
     * @param pageNum
     * @param pageSize
     * @param likes
     * @return
     */
    @Override
    public PageInfo<Likes> queryPage(Integer pageNum, Integer pageSize, Likes likes)
    {
        PageHelper.startPage(pageNum, pageSize);
        List<Likes> list = this.select(likes);
        return new PageInfo<>(list);
    }
    
    @Override
    public List<BuyersShowListDTO.LikesDTO> selectLikeCountByMomentUuidList(List<String> listMomentUuid)
    {
        return likesMapper.selectLikeCountByMomentUuidList(listMomentUuid);
    }
    
    @Override
    public List<BuyersShowListDTO.LikesDTO> selectLikesByMomentUuidAndMemberUuid(List<String> listMomentUuid,
        String memberUuid)
    {
        return likesMapper.selectLikesByMomentUuidAndMemberUuid(listMomentUuid, memberUuid);
    }
    
    @Override
    public int insertSelective(Likes likes)
    {
        return likesMapper.insertSelective(likes);
    }
    
    @Override
    public int selectCountByExample(Likes likes)
    {
        return likesMapper.selectCount(likes);
    }
    
    @Override
    public int deleteByExample(Likes likes)
    {
        Example example = new Example(Likes.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("delFlag", false);
        criteria.andEqualTo("type", likes.getType());
        criteria.andEqualTo("momentUuid", likes.getMomentUuid());
        criteria.andEqualTo("memberUuid", likes.getMemberUuid());
        return likesMapper.deleteByExample(example);
    }
    
    @Override
    public int deleteByMoment(String momentUuid)
    {
        return likesMapper.deleteByMoment(momentUuid);
    }
    
    @Override
    public int deleteByComment(String momentCommentUuid)
    {
        return likesMapper.deleteByComment(momentCommentUuid);
    }
    
    @Override
    public int createLike(Likes likes)
    {
        return likeServiceTransaction.createLike(likes);
    }
    
    @Override
    public int deleteLike(Likes likes)
    {
        return likeServiceTransaction.deleteLike(likes);
    }
}
