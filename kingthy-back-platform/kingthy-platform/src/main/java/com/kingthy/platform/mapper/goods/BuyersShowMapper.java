package com.kingthy.platform.mapper.goods;

import com.kingthy.platform.dto.goods.BuyersShowDto;
import com.kingthy.platform.dto.goods.ReviewReq;
import com.kingthy.platform.entity.goods.BuyersShow;
import com.kingthy.platform.util.MyMapper;

import java.util.List;
import java.util.Map;

/**
 * BuyersShowMapper
 * <p>
 * yuanml
 * 2017/5/16
 *
 * @version 1.0.0
 */
public interface BuyersShowMapper extends MyMapper<BuyersShow>
{
    /**
     * @desc 查询商品评论列表
     *
     * @author yuanml
     *
     * @param reviewReq
     * @return
     */
    List<BuyersShowDto> selectList(ReviewReq reviewReq);

    /**
     * @desc 更新商品评论状态
     *
     * @author yuanml
     *
     * @param parameterMap
     * @return
     */
    int update(Map<String, Object> parameterMap);


    BuyersShow selectCommentByUuid(String uuid);
}
