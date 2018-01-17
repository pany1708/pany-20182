package com.kingthy.platform.service.impl.order;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kingthy.platform.dto.order.OrderItemDto;
import com.kingthy.platform.dto.order.OrderItemReq;
import com.kingthy.platform.dto.order.OrderLogDto;
import com.kingthy.platform.entity.order.OrderItem;
import com.kingthy.platform.entity.order.OrderLog;
import com.kingthy.platform.mapper.order.OrderItemMapper;
import com.kingthy.platform.mapper.order.OrderLogMapper;
import com.kingthy.platform.response.WebApiResponse;
import com.kingthy.platform.service.order.OrderItemService;
import com.kingthy.platform.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 *
 * OrderItemServiceImpl
 * 
 * 赵生辉 2017年4月14日 上午9:52:39
 * 
 * @version 1.0.0
 *
 */
@Service(value = "orderItemService")
public class OrderItemServiceImpl implements OrderItemService
{
    @Autowired
    private transient OrderItemMapper orderItemMapper;

    @Autowired
    private OrderLogMapper orderLogMapper;
    
    @Override
    public int crate(OrderItem orderItem)
    {
        int result = orderItemMapper.insert(orderItem);
        return result;
    }
    
    @Override
    public int update(OrderItem orderItem)
    {
        Example example = new Example(OrderItem.class);
        Criteria criteria = example.createCriteria();
        criteria.andEqualTo("id", orderItem.getId());
        criteria.andEqualTo("delFlag", false);
        int result = orderItemMapper.updateByExample(orderItem, example);
        return result;
    }
    
    @Override
    public List<OrderItem> findAllOrderItem(OrderItem orderItem)
    {
        Example example = new Example(OrderItem.class);
        Criteria criteria = example.createCriteria();
        criteria.andEqualTo("orderUuid", orderItem.getOrderUuid());
        criteria.andEqualTo("delFlag", false);
        List<OrderItem> result = orderItemMapper.selectByExample(example);
        return result;
    }
    
    @Override
    public PageInfo<OrderItem> findOrderItemPage(Integer pageNo, Integer pageSize, OrderItem orderItem)
    {
        Example example = new Example(OrderItem.class);
        Criteria criteria = example.createCriteria();
        criteria.andEqualTo("orderUuid", orderItem.getOrderUuid());
        criteria.andEqualTo("delFlag", false);
        PageHelper.startPage(pageNo, pageSize);
        List<OrderItem> result = orderItemMapper.selectByExample(example);
        PageInfo<OrderItem> pageInfo = new PageInfo<OrderItem>(result);
        return pageInfo;
    }

    /**
     * 订单列表
     * @param req
     * @return
     * @throws Exception
     */
    @Override
    public WebApiResponse<?> list(OrderItemReq req) throws Exception {

        PageHelper.startPage(req.getPageNum(), req.getPageSize());

        req.setEndTime(DateUtil.putEndTime(req.getEndTime()));

        List<OrderItemDto> list = orderItemMapper.findOrderItemListByPage(req);

        PageInfo<OrderItemDto> pageInfo = new PageInfo<>(list);

        return WebApiResponse.success(pageInfo);
    }

    @Override
    public WebApiResponse<?> showOrderInfo(String orderSn) throws Exception {

        OrderItemDto.InvoiceInfo invoiceInfo = orderItemMapper.selectShowOrderInfo(orderSn);

        invoiceInfo.setDesinger(orderItemMapper.selectDesingerByGoodsUuid(invoiceInfo.getProductUuid()));

        //查询操作日志
        List<OrderLogDto> logList = orderLogMapper.selectOrderLogList(orderSn);

        Map<String, Object> map = new HashMap<>();

        map.put("orderInfo", invoiceInfo);
        map.put("orderLog", logList);

        return WebApiResponse.success(map);
    }

    /**
     * 取消订单
     * @param orderSn
     * @param memberUuid
     * @return
     * @throws Exception
     */
    @Override
    public WebApiResponse<?> cancelOrder(String orderSn, String memberUuid) throws Exception {

        int result = orderItemMapper.cancelOrderBySn(memberUuid, memberUuid);

        if (result > 0){

            OrderLog orderLog = new OrderLog();
            orderLog.setCreateDate(new Date());
            orderLog.setCreator(memberUuid);
            orderLog.setModifyDate(new Date());
            orderLog.setVersion(0);
            orderLog.setContent("取消订单");
            orderLog.setType(0);
            orderLog.setOrderUuid(orderItemMapper.selectOrderUuidBySn(orderSn));
            orderLog.setDelFlag(false);
            orderLog.setOrderSn(orderSn);

            orderLogMapper.insert(orderLog);
        }

        return result > 0 ? WebApiResponse.success(WebApiResponse.ResponseMsg.SUCCESS.getValue())
                : WebApiResponse.error(WebApiResponse.ResponseMsg.FAIL.getValue());
    }

}
