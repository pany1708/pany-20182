package com.kingthy.dubbo.goods.service;

import com.github.pagehelper.PageInfo;
import com.kingthy.base.BaseService;
import com.kingthy.dto.GoodsPageDto;
import com.kingthy.entity.Goods;
import com.kingthy.entity.GoodsOfficially;
import com.kingthy.request.GoodsOfficiallyReq;
import com.kingthy.request.GoodsPageReq;

import java.util.List;

/**
 * @author  xumin
 * @Description:
 * @DATE Created by 15:41 on 2017/8/7.
 * @Modified by:
 */
public interface GoodsOfficiallyDubboService extends BaseService<Goods>
{
    /**
     *
     * findByPage(分页查询商品信息)
     *
     * @param goodsParam
     * @return <b>创建人：</b>陈钊<br/>
     *         List<Goods>
     * @exception @since 1.0.0
     */
    PageInfo<GoodsPageDto> findByPage(GoodsPageReq goodsParam);

    int upOrDownBatch(GoodsOfficiallyReq.UpOrDownBatch req);

    GoodsPageDto.GoodsDetailDto selectOfficiallyGoodsInfoByUuid(String goodsUuid);
}
