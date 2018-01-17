package com.kingthy.dubbo.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kingthy.dto.OrderInfoDto;
import com.kingthy.dto.ShippingDto;
import com.kingthy.dubbo.order.service.OrderDubboService;
import com.kingthy.entity.Orders;
import com.kingthy.mapper.OrderMapper;
import com.kingthy.request.CreateOrderReq;
import com.kingthy.request.ShippingReq;
import com.kingthy.response.CreateOrderResponse;
import com.kingthy.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @author xumin
 * @Description:
 * @DATE Created by 14:33 on 2017/8/2.
 * @Modified by:
 */
@Service(version = "1.0.0", timeout = 10000, interfaceClass = OrderDubboService.class)
public class OrderDubboFacadeImpl implements OrderDubboService {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private OrderService orderService;

    @Override
    public int insert(Orders orders) {
        return orderMapper.insert(orders);
    }

    @Override
    public int updateByUuid(Orders var) {

        Example example = new Example(Orders.class);
        example.createCriteria().andEqualTo("uuid", var.getUuid());
        return orderMapper.updateByExampleSelective(var, example);
    }

    @Override
    public List<Orders> selectAll() {
        return orderMapper.selectAll();
    }

    @Override
    public Orders selectByUuid(String uuid) {
        Orders t = new Orders();
        t.setUuid(uuid);
        return selectOne(t);
    }

    @Override
    public int selectCount(Orders orders) {
        return orderMapper.selectCount(orders);
    }

    @Override
    public List<Orders> select(Orders t) {
        return orderMapper.select(t);
    }

    @Override
    public Orders selectOne(Orders t) {
        return orderMapper.selectOne(t);
    }

    @Override
    public PageInfo<Orders> queryPage(Integer pageNum, Integer pageSize, Orders orders) {

        PageHelper.startPage(pageNum, pageSize);

        return new PageInfo<>(orderMapper.select(orders));
    }

    @Override
    public int updateOrderBySn(Orders var) {

        Orders t = new Orders();
        t.setSn(var.getSn());
        t = orderMapper.selectOne(t);
        Integer version = t.getVersion();

        var.setVersion(version + 1);

        Example example = new Example(Orders.class);
        example.createCriteria().andEqualTo("sn", var.getSn())
                .andEqualTo("version",  version).andEqualTo("delFlag", false);
        return orderMapper.updateByExampleSelective(var, example);
    }

    @Override
    public CreateOrderResponse createOrder(CreateOrderReq var) {
        return orderService.createOrder(var);
    }

    @Override
    public int checkOrderSnExists(String orderSn) {
        return orderMapper.checkOrderSnExists(orderSn);
    }

    @Override
    public OrderInfoDto selectOrderInfo(String uuid)
    {
        return orderMapper.selectOrderInfo(uuid);
    }

    @Override
    public PageInfo<ShippingDto> selectShippingList(ShippingReq req)
    {
        PageHelper.startPage(req.getPageNum(), req.getPageSize());

        List<ShippingDto> list = orderMapper.selectShippingList(req);

        PageInfo<ShippingDto> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    @Override
    public int deliveryOrders(ShippingReq.DeliveryReq req)
    {
        return orderMapper.deliveryOrders(req);
    }

    @Override
    public int updateOrdersAddress(ShippingReq.EditAddress req)
    {
        return orderMapper.updateOrdersAddress(req);
    }

    @Override
    public ShippingReq.OrderShipping showOrderShippingBySn(String orderSn)
    {
        return orderMapper.showOrderShippingBySn(orderSn);
    }

    /**
     * 查询子订单是否 是支付成功的状态
     * @param orderItemSn
     * @return
     */
    @Override
    public Long selectOrdersCountByOrderItemSn(String orderItemSn) {
        return orderMapper.selectOrdersCountByOrderItemSn(orderItemSn);
    }

    @Override
    public String selectSnCountByOrderItemSn(String orderItemSn) {
        return orderMapper.selectSnCountByOrderItemSn(orderItemSn);
    }

}
