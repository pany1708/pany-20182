package com.kingthy.platform.service.goods;

import com.github.pagehelper.PageInfo;
import com.kingthy.platform.dto.goods.*;
import com.kingthy.platform.dto.goods.GoodsUuidArrayReq.GoodsOperation;
import com.kingthy.platform.response.WebApiResponse;

/**
 * 
 *
 * GoodsService(商品业务层接口)
 * 
 * 陈钊 2017年4月5日 下午7:59:11
 * 
 * @version 1.0.0
 *
 */
public interface GoodsService
{
    /**
     * 
     * findByPage(分页查询)
     * 
     * @param goodsPageReq
     * @return <b>创建人：</b>陈钊<br/>
     *         PageInfo<Goods>
     * @exception @since 1.0.0
     */
    PageInfo<GoodsPageDto> findByPage(GoodsPageReq goodsPageReq);

    WebApiResponse<?> officiallyList(GoodsPageReq goodsPageReq);

    /**
     * 
     * deleteBatch(批量修改delFlag状态,同时批量修改商品扫描表中的记录状态)
     * 
     * @param uuidArray
     * @return <b>创建人：</b>陈钊<br/>
     *         int
     * @exception @since 1.0.0
     */
    int changeDelFlagBatch(String[] uuidArray, boolean delFlag);
    
    /**
     * 
     * add(添加商品)
     * 
     * @param goodsAddParam <b>创建人：</b>陈钊<br/>
     *            void
     * @exception @since 1.0.0
     */
    String add(GoodsReq goodsAddParam);
    
    /**
     * 
     * edit(编辑商品信息)
     * 
     * @param goodsEditParam
     * @return <b>创建人：</b>陈钊<br/>
     *         int
     * @exception @since 1.0.0
     */
    int edit(GoodsReq goodsEditParam);
    
    /**
     * 
     * findDelByPage(分页查询进入回收站的商品)
     * 
     * @param delGoodsPageReq
     * @return <b>创建人：</b>陈钊<br/>
     *         PageInfo<Goods>
     * @exception @since 1.0.0
     */
    PageInfo<GoodsPageDto> findDelByPage(DelGoodsPageReq delGoodsPageReq);
    
    /**
     * 
     * delete(物理删除)
     * 
     * @param uuidArray
     * @return <b>创建人：</b>陈钊<br/>
     *         int
     * @exception @since 1.0.0
     */
    int delete(String[] uuidArray);
    
    /**
     * 
     * upOrDownBatch(批量上下架)
     * 
     * @param uuidArray
     * @return <b>创建人：</b>陈钊<br/>
     *         int
     * @exception @since 1.0.0
     */
    int upOrDownBatch(String[] uuidArray, GoodsOperation opration);
    
    /**
     * 
     * findGoodsByUuid(根据主键查找商品信息)
     * 
     * @param uuid
     * @return <b>创建人：</b>陈钊<br/>
     *         GoodsDto
     * @exception @since 1.0.0
     */
    GoodsDto findGoodsByUuid(String uuid);

    /**
     * 个人商品详情
     * @param goodsUuid
     * @return
     * @throws Exception
     */
    WebApiResponse<?> showDetail(String goodsUuid) throws Exception;

    WebApiResponse<String> applyOfficially(String goodsUuid, String memberUuid) throws Exception;
}
