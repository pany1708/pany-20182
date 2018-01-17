package com.kingthy.dubbo.review.service;

import com.github.pagehelper.PageInfo;
import com.kingthy.base.BaseService;
import com.kingthy.dto.BuyersShowDTO;
import com.kingthy.dto.BuyersShowListDTO;
import com.kingthy.dto.BuyersShowReviewDTO;
import com.kingthy.entity.BuyersShow;
import com.kingthy.request.BuyersShowReq;
import com.kingthy.request.ReviewReq;
import com.kingthy.request.ReviewUpdateReq;

import java.util.List;
import java.util.Map;

/**
 * BuyersShowDubboService
 * <p>
 * yuanml
 * 2017/8/4
 *
 * @version 1.0.0
 */
public interface BuyersShowDubboService extends BaseService<BuyersShow>
{
    /**
     * 通过商品id查询买家秀及图片信息
     * @param goodsUuid
     * @return List<BuyersShowDTO>
     */
    List<BuyersShowDTO> selectBuyersShowByGoodsUuid(String goodsUuid);

    Long selectBuyerShowCountByGoodUuid(String goodsUuid);

    List<BuyersShowListDTO> selectBuyersShowList(BuyersShowReq req);

    PageInfo<BuyersShowReviewDTO> selectReviewList(ReviewReq req);

    int updateReview(Map map);

    String insertReturnUuid(BuyersShow buyersShow);

    BuyersShowReviewDTO findBuyersShowReviewDTOByUuid(String uuid);
}
