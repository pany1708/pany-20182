package com.kingthy.dubbo.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kingthy.dto.IncomeDetailDTO;
import com.kingthy.dto.MemberOrderDTO;
import com.kingthy.dto.OrderItemDto;
import com.kingthy.dto.SingleOrderDTO;
import com.kingthy.dubbo.order.service.OrderItemDubboService;
import com.kingthy.entity.BuyersShow;
import com.kingthy.entity.OrderItem;
import com.kingthy.mapper.OrderItemMapper;
import com.kingthy.request.MemberOrderReq;
import com.kingthy.request.OrderItemReq;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Example;
import java.util.List;

/**
 * @author  xumin
 * @Description:
 * @DATE Created by 13:54 on 2017/8/3.
 * @Modified by:
 */
@Service(version = "1.0.0", timeout = 10000, interfaceClass = OrderItemDubboService.class)
public class OrderItemDubboServiceImpl implements OrderItemDubboService {

    @Autowired
    private OrderItemMapper orderItemMapper;

    @Override
    public int insert(OrderItem orderItem) {
        return orderItemMapper.insert(orderItem);
    }

    @Override
    public int updateByUuid(OrderItem var) {

        Example example = new Example(OrderItem.class);
        example.createCriteria().andEqualTo("uuid", var.getUuid());
        return orderItemMapper.updateByExampleSelective(var, example);
    }

    @Override
    public List<OrderItem> selectAll() {
        return orderItemMapper.selectAll();
    }

    @Override
    public OrderItem selectByUuid(String uuid) {

        OrderItem orderItem  = new OrderItem();
        orderItem.setUuid(uuid);

        return orderItemMapper.selectOne(orderItem);
    }

    @Override
    public int selectCount(OrderItem orderItem) {

        return orderItemMapper.selectCount(orderItem);
    }

    @Override
    public List<OrderItem> select(OrderItem var1) {
        return orderItemMapper.select(var1);
    }

    @Override
    public OrderItem selectOne(OrderItem var1) {
        return orderItemMapper.selectOne(var1);
    }

    @Override
    public PageInfo<OrderItem> queryPage(Integer pageNum, Integer pageSize, OrderItem orderItem) {

        PageHelper.startPage(pageNum, pageSize," createDate desc");
        return new PageInfo<>(orderItemMapper.select(orderItem));
    }

    @Override
    public int updateOrderItemBySn(OrderItem var) {

        OrderItem t = new OrderItem();
        t.setSn(var.getSn());
        t = orderItemMapper.selectOne(t);
        Long version = t.getVersion();
        var.setVersion(version + 1);

        Example example = new Example(OrderItem.class);
        example.createCriteria().andEqualTo("sn", var.getSn())
                .andEqualTo("delFlag", false).andEqualTo("version",  version);
        return orderItemMapper.updateByExampleSelective(var, example);
    }

    @Override
    public List<MemberOrderDTO> getMemberOrderList(String memberUuid, Integer orderStatus) {
        return orderItemMapper.getMemberOrderList(memberUuid,orderStatus);
    }

    @Override
    public PageInfo<MemberOrderDTO> pageQueryMemberOrders(MemberOrderReq req) {

        PageHelper.startPage(req.getPageNum(), req.getPageSize());
        List<MemberOrderDTO> list= orderItemMapper.getMemberOrderList(req.getMemberUuid(),req.getOrderStatus());

        PageInfo<MemberOrderDTO> pageInfo=new PageInfo<>(list);
        return pageInfo;
    }
    @Override
    public IncomeDetailDTO.OrderItem selectOrderItemInfo(String orderSn) {
        return orderItemMapper.selectOrderItemInfo(orderSn);
    }

    @Override
    public SingleOrderDTO selectOrderItemInfoBySn(String orderSn) {
        return orderItemMapper.selectOrderItemInfoBySn(orderSn);
    }

    @Override
    public Long selectSaleCountByGoodsUuid(String goodsUuid) {
        return orderItemMapper.selectSaleCountByGoodsUuid(goodsUuid);
    }


    @Override
    public Long selectSaleCountByGoodsUuidAndMonth(String goodsUuid) {
        return orderItemMapper.selectSaleCountByGoodsUuidAndMonth(goodsUuid);
    }

    @Override
    public BuyersShow selectGoodsUuIdAndOrderUuIdByOrderSn(String orderSn) {
        return orderItemMapper.selectGoodsUuIdAndOrderUuIdByOrderSn(orderSn);
    }
    @Override
    public List<OrderItemDto> findOrderItemListByPage(OrderItemReq req) {
        return orderItemMapper.findOrderItemListByPage(req);
    }

    @Override
    public OrderItemDto.OrderDetail selectOrderDetail(String orderSn) {
        return orderItemMapper.selectOrderDetail(orderSn);
    }

    @Override
    public OrderItemDto.InvoiceInfo selectShowOrderInfo(String orderSn) {
        return orderItemMapper.selectShowOrderInfo(orderSn);
    }

    @Override
    public int cancelOrderBySn(String orderSn, String memberUuid) {
        return orderItemMapper.cancelOrderBySn(orderSn,memberUuid);
    }

    @Override
    public String selectOrderUuidBySn(String orderSn) {
        return orderItemMapper.selectOrderUuidBySn(orderSn);
    }

    @Override
    public PageInfo<OrderItemDto> findOrderItemsByPage(OrderItemReq req) {
        PageHelper.startPage(req.getPageNum(),req.getPageSize());
        List<OrderItemDto> orderItemDtoList = orderItemMapper.findOrderItemListByPage(req);
        PageInfo<OrderItemDto> pageInfo = new PageInfo<OrderItemDto>(orderItemDtoList);
        return pageInfo;
    }

    @Override
    public List<OrderItem> selectOrderItemList(List<String> snList) {

        Example example = new Example(OrderItem.class);
        example.createCriteria().andIn("sn", snList)
                .andEqualTo("delFlag", false);

        return orderItemMapper.selectBath(snList);
    }
}
