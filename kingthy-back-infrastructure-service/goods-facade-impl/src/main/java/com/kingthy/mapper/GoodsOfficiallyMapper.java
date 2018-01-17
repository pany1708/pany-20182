package com.kingthy.mapper;

import com.kingthy.dto.GoodsPageDto;
import com.kingthy.entity.Goods;
import com.kingthy.entity.GoodsOfficially;
import com.kingthy.request.GoodsOfficiallyReq;
import com.kingthy.request.GoodsPageReq;
import com.kingthy.util.MyMapper;

import java.util.List;

/**
 * @author  xumin
 * @Description:
 * @DATE Created by 15:20 on 2017/8/7.
 * @Modified by:
 */
public interface GoodsOfficiallyMapper extends MyMapper<Goods> {
    /**
     *
     * findByPage(分页查询商品信息)
     *
     * @param goodsParam
     * @return <b>创建人：</b>陈钊<br/>
     *         List<Goods>
     * @exception @since 1.0.0
     */
    List<GoodsPageDto> findByPage(GoodsPageReq goodsParam);

    GoodsPageDto.GoodsDetailDto selectOfficiallyGoodsInfoByUuid(String goodsUuid);

    int upOrDownBatch(GoodsOfficiallyReq.UpOrDownBatch req);
}
