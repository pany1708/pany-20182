package com.kingthy.platform.service.goods;

import com.github.pagehelper.PageInfo;
import com.kingthy.platform.dto.goods.BuyersShowDto;
import com.kingthy.platform.dto.goods.ReviewReq;
import com.kingthy.platform.dto.goods.ReviewUpdateReq;

/**
 * ReviewService
 * <p>
 * yuanml 2017/5/16
 *
 * @version 1.0.0
 */
public interface ReviewService
{
    /**
     * @desc 查询商品评论列表
     *
     * @author yuanml
     *
     * @param reviewReq
     * @return
     */
    PageInfo<BuyersShowDto> listReview(ReviewReq reviewReq);
    
    /**
     * @desc 查看评论详情
     *
     * @author yuanml
     *
     * @param reviewUuid
     * @return
     */
    BuyersShowDto selectReview(String reviewUuid);
    
    /**
     * @desc 隐藏/显示/删除商品评论
     * @author yuanml
     *
     * @param reviewUpdateReq
     * @return
     */
    int updateReview(ReviewUpdateReq reviewUpdateReq);
}
