package com.kingthy.mapper;

import com.kingthy.dto.IncomeDetailDTO;
import com.kingthy.dto.MemberOrderDTO;
import com.kingthy.dto.OrderItemDto;
import com.kingthy.dto.SingleOrderDTO;
import com.kingthy.entity.BuyersShow;
import com.kingthy.entity.OrderItem;
import com.kingthy.request.MemberOrderReq;
import com.kingthy.request.OrderItemReq;
import com.kingthy.util.MyMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author
 */
public interface OrderItemMapper extends MyMapper<OrderItem>
{

    /**
     * 获取会员订单列表
     */
    List<MemberOrderDTO> getMemberOrderList(@Param("memberUuid") String memberUuid, @Param("orderStatus") Integer orderStatus);
    Long getMemberOrderList_COUNT(@Param("memberUuid") String memberUuid, @Param("orderStatus") Integer orderStatus);
    /**
     * 分页获取会员订单列表
     */
    List<MemberOrderDTO> pageQueryMemberOrders(MemberOrderReq req);

    IncomeDetailDTO.OrderItem selectOrderItemInfo(String orderSn);

    /**
     * 通过orderSn查询BuyersShow
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

    int cancelOrderBySn( @Param("orderSn") String orderSn, @Param("memberUuid")String memberUuid);

    String selectOrderUuidBySn(String orderSn);

    List<OrderItem> selectBath(List<String> list);

}
