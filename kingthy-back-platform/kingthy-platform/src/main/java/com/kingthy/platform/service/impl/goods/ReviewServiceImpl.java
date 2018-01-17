package com.kingthy.platform.service.impl.goods;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kingthy.platform.dto.goods.BuyersShowDto;
import com.kingthy.platform.dto.goods.ReviewReq;
import com.kingthy.platform.dto.goods.ReviewUpdateReq;
import com.kingthy.platform.entity.goods.BuyersShowImg;
import com.kingthy.platform.entity.goods.ReplyComment;
import com.kingthy.platform.mapper.goods.BuyersShowImgMapper;
import com.kingthy.platform.mapper.goods.BuyersShowMapper;
import com.kingthy.platform.mapper.goods.ReplyCommentMapper;
import com.kingthy.platform.service.goods.ReviewService;
import com.kingthy.platform.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ReviewServiceImpl
 * <p>
 * yuanml 2017/5/16
 *
 * @version 1.0.0
 */
@Service(value = "reviewService")
public class ReviewServiceImpl implements ReviewService
{
    
    @Autowired
    private BuyersShowMapper buyersShowMapper;
    
    @Autowired
    private BuyersShowImgMapper buyersShowImgMapper;
    
    @Autowired
    private HttpServletRequest httpServletRequest;

    @Autowired
    private ReplyCommentMapper replyCommentMapper;
    
    private Map<String, Object> parameterMap;
    
    @Override
    public PageInfo<BuyersShowDto> listReview(ReviewReq reviewReq)
    {
        PageHelper.startPage(reviewReq.getPageNum(), reviewReq.getPageSize());

        reviewReq.setEndDate(DateUtil.putEndTime(reviewReq.getEndDate()));
        List<BuyersShowDto> buyersShowDtos = buyersShowMapper.selectList(reviewReq);
        PageInfo<BuyersShowDto> buyersShowPageInfo = new PageInfo<>(buyersShowDtos);
        return buyersShowPageInfo;
    }
    
    @Override
    public BuyersShowDto selectReview(String reviewUuid)
    {
        ReviewReq q = new ReviewReq();
        q.setReviewUuid(reviewUuid);
        List<BuyersShowDto> buyersShowDtos = buyersShowMapper.selectList(q);
        BuyersShowDto buyersShowDto = new BuyersShowDto();
        if (buyersShowDtos.size() > 0)
        {
            buyersShowDto = buyersShowDtos.get(0);
        }
        BuyersShowImg buyersShowImg = new BuyersShowImg();
        buyersShowImg.setBuyersUuid(reviewUuid);
        List<BuyersShowImg> buyersShowImgs = buyersShowImgMapper.select(buyersShowImg);
        if (buyersShowImgs.size() > 0)
        {
            buyersShowDto.setBuyersShowImgList(buyersShowImgs);
        }

        //查询最新回复
        ReplyComment replyComment = replyCommentMapper.queryReplyCommentByUuid(reviewUuid);

        if (replyComment != null){
            buyersShowDto.setReplyContent(replyComment.getContent());
        }

        return buyersShowDto;
    }
    
    @Override
    public int updateReview(ReviewUpdateReq reviewUpdateReq)
    {
        parameterMap = new HashMap<>();
        parameterMap.put("modifier", httpServletRequest.getHeader("uuid"));
        parameterMap.put("reviewUuids", reviewUpdateReq.getReviewUuids().split(","));
        parameterMap.put("status", reviewUpdateReq.getStatus());
        parameterMap.put("delFlag", reviewUpdateReq.getDelFlag());
        return buyersShowMapper.update(parameterMap);
    }
}
