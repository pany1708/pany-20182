package com.kingthy.platform.mapper.order;

import com.kingthy.platform.dto.order.ShippingDto;
import com.kingthy.platform.dto.order.ShippingReq;
import com.kingthy.platform.entity.order.Orders;
import com.kingthy.platform.entity.order.dto.OrderInfoDto;
import com.kingthy.platform.util.MyMapper;

import java.util.List;

/**
 * 
 *
 * OrdersMapper(订单映射接口)
 * 
 * 赵生辉 2017年4月14日 上午9:49:34
 * 
 * @version 1.0.0
 *
 */
public interface OrdersMapper extends MyMapper<Orders>
{
    /**
     * 
     * insertOrderParamSelective(通过订单详细信息创建一个订单) (这里描述这个方法适用条件 – 可选)
     * 
     * @param createOrderParam
     * @return 赵生辉 int
     * @exception @since 1.0.0
     */
    // int insertOrderParamSelective(CreateOrderParam createOrderParam);
    
    /**
     * 
     * selectOrderInfoByExample(查询订单详情) (查询单个订单的详细信息 – 可选)
     * 
     * @param example
     * @return 赵生辉 OrderInfoDto
     * @exception @since 1.0.0
     */
    OrderInfoDto selectOrderInfo(String uuid);
    
    /*
     * int countOrderByExample(Example example);
     * 
     * int deleteOrderByExample(Example example);
     * 
     * int deleteOrderByPrimaryKey(Integer id);
     * 
     * int insertOrder(Orders record);
     * 
     * int insertOrderSelective(Orders record);
     * 
     * List<Orders> selectOrderByExample(Example example);
     * 
     * Orders selectOrderByPrimaryKey(Integer id);
     * 
     * int updateOrderByExampleSelective(@Param("record") Orders record, @Param("example") Example example);
     * 
     * int updateOrderByExample(@Param("record") Orders record, @Param("example") Example example);
     * 
     * int updateOrderByPrimaryKeySelective(Orders record);
     * 
     * int updateOrderByPrimaryKey(Orders record);
     */

    List<ShippingDto> selectShippingList(ShippingReq req);

    int deliveryOrders(ShippingReq.DeliveryReq req);
    int updateOrdersAddress(ShippingReq.EditAddress req);

    ShippingReq.OrderShipping showOrderShippingBySn(String orderSn);
}