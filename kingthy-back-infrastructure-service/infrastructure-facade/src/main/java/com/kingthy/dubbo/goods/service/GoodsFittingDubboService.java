package com.kingthy.dubbo.goods.service;


import com.kingthy.base.BaseService;
import com.kingthy.entity.GoodsFitting;
import com.kingthy.request.FittingRequest;
import com.kingthy.response.FittingResp;

import java.util.List;

/**
 * 试衣记录
 * @Author:xumin
 * @Description:
 * @Date:14:35 2017/12/22
 */
public interface GoodsFittingDubboService extends BaseService<GoodsFitting>{

    /**
     *
     * @param goodsUuid
     * @return
     */
    Long selectFittingCountByGoodsUuid(String goodsUuid);

    List<FittingResp> selectGoodsUuidList(FittingRequest.Query query);

    /**
     * 查询商品是否有试衣记录
     * @param var
     * @return
     */
    String selectGoodsFittingUuid(FittingRequest var);
}
