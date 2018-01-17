package com.kingthy.dubbo.goods.service;

import com.github.pagehelper.PageInfo;
import com.kingthy.base.BaseService;
import com.kingthy.dto.GoodsDTO;
import com.kingthy.dto.GoodsOrderDTO;
import com.kingthy.dto.GoodsPageDto;
import com.kingthy.entity.Goods;
import com.kingthy.dto.GoodsIncrement;
import com.kingthy.request.DelGoodsPageReq;
import com.kingthy.request.EsGoodsReq;
import com.kingthy.request.GoodsPageReq;

import java.util.List;
import java.util.Map;

/**
 * @author xumin
 * @Description:
 * @DATE Created by 15:38 on 2017/8/7.
 * @Modified by:
 */
public interface GoodsDubboService extends BaseService<Goods>
{
    Goods queryMaterielInfoByUUID(String uuid);

    String queryDesingerByUUID(String uuid);

    Goods queryGoodsByUUID(String uuid);

    String insertReturn(Goods goods);

    List<Goods> selectGoodsByName(String name);
    List<Goods> getGoodsListByIds(List<String> productUuids);

    List<GoodsDTO.CoverInfo> getGoodsCoverListByIds(List<String> productUuids);

    List<Goods> selectVersionByGoodsUuid(List<String> listGoodsUuid);

    List<Goods> selectGoodsUuid(List<String> listGoodsUuid);

    int updateGoodsClicks(Goods goods);

    List<GoodsOrderDTO> selectGoodsListByGoodsIds(List<String> listGoodsUuid);
    /**
     *
     * findByPage(分页查询商品信息)
     *
     * @param goodsParam
     * @return <b>创建人：</b>陈钊<br/>
     *         PageInfo<GoodsPageDto>
     * @exception @since 1.0.0
     */
    PageInfo<GoodsPageDto> findByPage(GoodsPageReq goodsParam);

    /**
     *
     * deleteBatch(改变商品delFlag状态 ，如果状态不为空，则同时更改状态)
     *
     * @param paramMap
     * @return <b>创建人：</b>陈钊<br/>
     *         int
     * @exception @since 1.0.0
     */
    int changeDelFlagBatch(Map<String, Object> paramMap);
    /**
     *
     * findDelByPage(分页查询进入回收站的商品)
     *
     * @param delGoodsPageReq
     * @return <b>创建人：</b>陈钊<br/>
     *         List<Goods>
     * @exception @since 1.0.0
     */
    List<GoodsPageDto> findDelByPage(DelGoodsPageReq delGoodsPageReq);
    /**
     *
     * deleteBatch(批量物理删除)
     *
     * @param paramMap
     * @return <b>创建人：</b>陈钊<br/>
     *         int
     * @exception @since 1.0.0
     */
    int deleteBatch(Map<String, Object> paramMap);

    int upOrDownBatch(Map map);

    List<String> selectGoodsUuidByDesinger(List<String> memberUuids);

    List<String> queryUUidByEsGoods(EsGoodsReq esGoodsReq);

    GoodsIncrement queryPageIncrement(int pageNum, int pageSize, Long referenceTime);
}
