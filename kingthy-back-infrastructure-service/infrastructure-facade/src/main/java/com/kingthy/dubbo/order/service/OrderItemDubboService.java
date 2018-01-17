package com.kingthy.dubbo.order.service;

import com.github.pagehelper.PageInfo;
import com.kingthy.base.BaseService;
import com.kingthy.dto.IncomeDetailDTO;
import com.kingthy.dto.MemberOrderDTO;
import com.kingthy.dto.OrderItemDto;
import com.kingthy.dto.SingleOrderDTO;
import com.kingthy.entity.BuyersShow;
import com.kingthy.entity.OrderItem;
import com.kingthy.request.MemberOrderReq;
import com.kingthy.request.OrderItemReq;
import java.util.List;

/**
 * @author xumin
 * @Description:
 * @DATE Created by 13:53 on 2017/8/3.
 * @Modified by:
 */

public interface OrderItemDubboService extends BaseService<OrderItem>{
    /**
     * 获取会员订单列表
     */
    List<MemberOrderDTO> getMemberOrderList(String memberUuid, Integer orderStatus);

    int updateOrderItemBySn(OrderItem var);
    /**
     * 分页获取会员订单列表
     */
    PageInfo<MemberOrderDTO> pageQueryMemberOrders(MemberOrderReq req);

    IncomeDetailDTO.OrderItem selectOrderItemInfo(String orderSn);

    /**
     * 根据orderSn查询BuyersShow
     * @param orderSn
     * @return
     */
    BuyersShow selectGoodsUuIdAndOrderUuIdByOrderSn(String orderSn);

    /**
     * 根据uuid查询商品月度销量
     * @param goodsUuid
     * @return Long
     */
    Long selectSaleCountByGoodsUuidAndMonth(String goodsUuid);

    SingleOrderDTO selectOrderItemInfoBySn(String orderSn);

    Long selectSaleCountByGoodsUuid(String goodsUuid);


    List<OrderItemDto> findOrderItemListByPage(OrderItemReq req);

    OrderItemDto.OrderDetail selectOrderDetail(String orderSn);

    OrderItemDto.InvoiceInfo selectShowOrderInfo(String orderSn);

    int cancelOrderBySn(String orderSn, String memberUuid);

    String selectOrderUuidBySn(String orderSn);

    PageInfo<OrderItemDto> findOrderItemsByPage(OrderItemReq req);

    List<OrderItem> selectOrderItemList(List<String> snList);
}
