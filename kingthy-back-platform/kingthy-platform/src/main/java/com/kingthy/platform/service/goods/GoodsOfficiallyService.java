package com.kingthy.platform.service.goods;

import com.kingthy.platform.dto.order.GoodsOfficiallyReq;
import com.kingthy.platform.response.WebApiResponse;

/**
 * @AUTHORS xumin
 * @Description:
 * @DATE Created by 15:41 on 2017/7/26.
 * @Modified by:
 */
public interface GoodsOfficiallyService {

    /**
     * 官方商品详情
     * @param goodsUuid
     * @return
     * @throws Exception
     */
    WebApiResponse<?> showOfficiallyDetail(String goodsUuid) throws Exception;

    /**
     * 创建官方商品
     * @param req
     * @return
     * @throws Exception
     */
    WebApiResponse<?> createGoods(GoodsOfficiallyReq req) throws Exception;

    /**
     *
     * @param req
     * @return
     * @throws Exception
     */
    WebApiResponse<?> upOrDownBatch(GoodsOfficiallyReq.UpOrDownBatch req) throws Exception;
}
