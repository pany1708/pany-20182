package com.kingthy.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.kingthy.dto.BuyersShowDTO;
import com.kingthy.dto.BuyersShowListDTO;
import com.kingthy.dto.BuyersShowReviewDTO;
import com.kingthy.entity.BuyersShow;
import com.kingthy.request.BuyersShowReq;
import com.kingthy.request.ReviewReq;
import com.kingthy.util.MyMapper;

/**
 * @author xumin
 * @Description:
 * @DATE Created by 17:48 on 2017/8/2.
 * @Modified by:
 */
public interface BuyersShowMapper extends MyMapper<BuyersShow>
{
    /**
     * 通过商品id查询买家秀及图片信息
     * @param goodsUuid
     * @return List<BuyersShowDTO>
     */
    List<BuyersShowDTO> selectBuyersShowByGoodsUuid(String goodsUuid);

    /**
     * 查询数量
     * @param goodsUuid
     * @return Long
     */
    Long selectBuyerShowCountByGoodUuid(@Param("goodsUuid") String goodsUuid);

    /**
     * 查询买家秀
     * @param req
     * @return
     */
    List<BuyersShowListDTO> selectBuyersShowList(BuyersShowReq req);

    /**
     *
     * 查询商品评论列表
     * @param req
     * @return
     */
    List<BuyersShowReviewDTO> selectReviewList(ReviewReq req);

    /**
     * 更新买家秀
     * @param map
     * @return
     */
    int updateReview(Map map);

    /**
     * 查询买家秀详情
     * @param uuid
     * @return
     */
    BuyersShowReviewDTO findBuyersShowReviewDTOByUuid(String uuid);
    

}
