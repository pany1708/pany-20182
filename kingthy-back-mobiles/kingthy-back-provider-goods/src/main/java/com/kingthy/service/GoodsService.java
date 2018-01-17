package com.kingthy.service;

import com.github.pagehelper.PageInfo;
import com.kingthy.dto.BuyersShowListDTO;
import com.kingthy.dto.GoodsDTO;
import com.kingthy.dto.GoodsIncrement;
import com.kingthy.dto.GoodsOrderDTO;
import com.kingthy.entity.EsGoods;
import com.kingthy.entity.Goods;
import com.kingthy.request.*;
import com.kingthy.response.WebApiResponse;
import org.springframework.data.domain.Page;

import java.util.List;


/**
 *
 * GoodsService
 * @author xxxx
 *
 */
public interface GoodsService
{
    
    /**
     * 查询商品信息
     * 
     * @param goodsUuid
     * @return
     */
    Goods queryGoods(String goodsUuid);

    /**
     * queryGoods
     * @param goodsUuid
     * @param token
     * @return
     */
    GoodsDTO queryGoods(String goodsUuid, String token) throws Exception;

    GoodsDTO.DesignerInfo parseDesignerInfo(String goodsUuid, String token) throws Exception;

    GoodsDTO.ProductInfo parseProductInfo(String goodsUuid) throws Exception;

//    List<GoodsDTO.FigureInfo> parseFigureInfo(String token) throws Exception;

    /**
     * 分页查询所有商品
     * 
     * @param pageNo
     * @param pageSize
     * @return
     */
    PageInfo<Goods> queryAllGoods(Integer pageNo, Integer pageSize);
    
    /**
     * 分页筛选商品
     * 
     * @param goods
     * @return
     */
    PageInfo<Goods> queryGoodsSelective(QueryGoodsReq goods);

    /**
     * queryEsGoods
     * @param goods
     * @return
     */
    Page<EsGoods> queryEsGoods(QueryGoodsReq goods);
    
    /**
     * 创建一个新的商品
     * 
     * @param goods
     * @return
     */
    void createGoods(Goods goods);
    
    /**
     * 通过一个作品创建一个私有商品
     * 
     * @param uuid
     * @return
     */
    String createPrivateGoods(String uuid);
    
    /**
     * 下架商品
     * 
     * @param uuid
     * @param desingerUuid
     * @return
     */
    int downGoods(String uuid,String desingerUuid);
    
    /**
     * 上架商品
     * 
     * @param uuid
     * @param desingerUuid
     * @return
     */
    int upGoods(String uuid,String desingerUuid);
    
    /**
     * 删除商品
     * 
     * @param uuid
     * @param token
     * @return
     */
    int deleteGoods(String uuid,String token);
    
    /**
     * 商品点击量
     * 
     * @param goodsClickReq
     * @return
     * @throws Exception
     */
    WebApiResponse goodsClick(List<GoodsClickReq> goodsClickReq);
    
    /**
     * 查询 面料和风格
     * 
     * @return
     * @throws Exception
     */
    WebApiResponse queryMaterielAndStyle();

    /**
     * selectGoodsListByGoodsIds
     * @param listGoodsUuid
     * @return
     */
    List<GoodsOrderDTO> selectGoodsListByGoodsIds(List<String> listGoodsUuid);
    /**
     * selectBuyersShowList
     * @param req
     * @return
     */
    List<BuyersShowListDTO> selectBuyersShowList(BuyersShowReq req);
    /**
     * selectGoodsWarehouse
     * @param selectGoodsByWarehouseReq
     * @return
     */
    PageInfo<Goods> selectGoodsWarehouse(SelectGoodsByWarehouseReq selectGoodsByWarehouseReq);
    /**
     * esGoods
     * @param esGoodsReq
     * @return WebApiResponse
     * @throws Exception
     */
    WebApiResponse esGoods(EsGoodsReq esGoodsReq) throws Exception;

    /**
     * 删除试衣记录
     * @param fittingUuid
     * @param token
     * @return
     * @throws Exception
     */
    WebApiResponse delFitting(String fittingUuid, String token) throws Exception;

    /**
     * 新增试衣记录
     * @param fittingRequest
     * @return
     * @throws Exception
     */
    WebApiResponse createFitting(FittingRequest fittingRequest, String token) throws Exception;

    /**
     * 试衣记录列表
     * @param query
     * @param token
     * @return
     * @throws Exception
     */
    WebApiResponse fittingList(FittingRequest.Query query, String token) throws Exception;


    /**
     * 检查试衣记录是否有更新
     * @param query
     * @return
     * @throws Exception
     */
    WebApiResponse checkUpdateFitting(FittingRequest.CheckQuery query, String token) throws Exception;

    /**
     * @param pageNum
     * @param pageSize
     * @param referenceTime
     * @return
     */
    PageInfo<GoodsIncrement> queryGoodsIncrement(int pageNum, int pageSize, Long referenceTime);
}
