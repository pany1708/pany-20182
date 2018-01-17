package com.kingthy.mapper;

import com.kingthy.entity.GoodsFitting;
import com.kingthy.request.FittingRequest;
import com.kingthy.response.FittingResp;
import com.kingthy.util.MyMapper;

import java.util.List;

/**
 * @author xumin
 * @Description:
 * @DATE Created by 19:39 on 2017/8/15.
 * @Modified by:
 */
public interface GoodsFittingMapper extends MyMapper<GoodsFitting> {

    /**
     * 查询用户试衣总次数
     * @param goodsUuid
     * @return
     */
    Long selectFittingCountByGoodsUuid(String goodsUuid);

    List<FittingResp> selectGoodsUuidList(FittingRequest.Query query);

    String selectGoodsFittingUuid(FittingRequest var);
}
