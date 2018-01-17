package com.kingthy.platform.mapper.goods;

import com.kingthy.platform.dto.goods.GoodsPageDto;
import com.kingthy.platform.dto.goods.GoodsPageReq;
import com.kingthy.platform.dto.order.GoodsOfficiallyReq;
import com.kingthy.platform.entity.goods.GoodsOfficially;
import com.kingthy.platform.util.MyMapper;

import java.util.List;

/**
 * @AUTHORS xumin
 * @Description:
 * @DATE Created by 16:45 on 2017/7/5.
 * @Modified by:
 */
public interface GoodsOfficiallyMapper extends MyMapper<GoodsOfficially> {

    List<GoodsPageDto> findByPage(GoodsPageReq goodsParam);

    GoodsPageDto.GoodsDetailDto selectOfficiallyGoodsInfoByUuid(String goodsUuid);

    int upOrDownBatch(GoodsOfficiallyReq.UpOrDownBatch req);
}
