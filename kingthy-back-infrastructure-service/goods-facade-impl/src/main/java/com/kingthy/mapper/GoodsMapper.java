package com.kingthy.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.kingthy.dto.GoodsDTO;
import com.kingthy.dto.GoodsOrderDTO;
import com.kingthy.dto.GoodsPageDto;
import com.kingthy.entity.Goods;
import com.kingthy.request.DelGoodsPageReq;
import com.kingthy.request.EsGoodsReq;
import com.kingthy.request.GoodsPageReq;
import com.kingthy.util.MyMapper;

/**
 * @author xumin
 * @Description:
 * @DATE Created by 15:19 on 2017/8/7.
 * @Modified by:
 */
public interface GoodsMapper extends MyMapper<Goods>
{

    Goods queryMaterielInfoByUUID(String uuid);

    String queryDesingerByUUID(String uuid);

    Goods queryGoodsByUUID(String uuid);

    List<Goods> getGoodsListByIds(@Param("productUuids") List<String> productUuids);

    List<GoodsDTO.CoverInfo> getGoodsCoverListByIds(@Param("productUuids") List<String> productUuids);

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
     *         List<Goods>
     * @exception @since 1.0.0
     */
    List<GoodsPageDto> findByPage(GoodsPageReq goodsParam);

    /**
     *
     * findByPage(分页查询商品信息)
     *
     * @param goods
     * @return <b>创建人：</b>赵生辉<br/>
     *         List<Goods>
     * @exception @since 1.0.0
     */
    List<Goods> findGoodsByPage(Goods goods);

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
     * @param goodsParam
     * @return <b>创建人：</b>陈钊<br/>
     *         List<Goods>
     * @exception @since 1.0.0
     */
    List<GoodsPageDto> findDelByPage(DelGoodsPageReq goodsParam);

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

    List<Goods> queryIncrementGoods(@Param("referenceTime") Long referenceTime);
}
