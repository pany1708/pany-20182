package com.kingthy.platform.mapper.goods;

import com.kingthy.platform.dto.goods.DelGoodsPageReq;
import com.kingthy.platform.dto.goods.GoodsDto;
import com.kingthy.platform.dto.goods.GoodsPageDto;
import com.kingthy.platform.dto.goods.GoodsPageReq;
import com.kingthy.platform.entity.goods.Goods;
import com.kingthy.platform.util.MyMapper;

import java.util.List;
import java.util.Map;

/**
 * 
 *
 * GoodsMapper(商品映射接口)
 * 
 * 陈钊 2017年4月11日 下午7:45:55
 * 
 * @version 1.0.0
 *
 */
public interface GoodsMapper extends MyMapper<Goods>
{

    GoodsPageDto.GoodsDetailDto selectGoodsInfoByUuid(String goodsUuid);

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
     * deleteBatch(批量物理删除)
     * 
     * @param paramMap
     * @return <b>创建人：</b>陈钊<br/>
     *         int
     * @exception @since 1.0.0
     */
    int deleteBatch(Map<String, Object> paramMap);
    
    /**
     * 
     * updateStatus(批量更新商品上架状态)
     * 
     * @param paramMap
     * @return <b>创建人：</b>陈钊<br/>
     *         int
     * @exception @since 1.0.0
     */
    int updateStatusBatch(Map<String, Object> paramMap);
    
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
     * upOrDownBatch(批量上下架)
     * 
     * @param paramMap
     * @return <b>创建人：</b>陈钊<br/>
     *         int
     * @exception @since 1.0.0
     */
    int upOrDownBatch(Map<String, Object> paramMap);
    
    /**
     * 
     * findGoodsByUuid(根据uuid查询商品详细信息)
     * 
     * @param uuid
     * @return <b>创建人：</b>陈钊<br/>
     *         GoodsDto
     * @exception @since 1.0.0
     */
    GoodsDto findGoodsByUuid(String uuid);

    Goods findGoodsInfoByUuid(String uuid);
}