package com.kingthy.platform.service.impl.order;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kingthy.exception.*;
import com.kingthy.platform.dto.manager.PlatformUserInfoDto;
import com.kingthy.platform.entity.order.OrderItem;
import com.kingthy.platform.entity.order.OrderLog;
import com.kingthy.platform.entity.order.Orders;
import com.kingthy.platform.entity.order.dto.OrderInfoDto;
import com.kingthy.platform.mapper.order.OrderItemMapper;
import com.kingthy.platform.mapper.order.OrderLogMapper;
import com.kingthy.platform.mapper.order.OrdersMapper;
import com.kingthy.platform.response.WebApiResponse;
import com.kingthy.platform.service.manager.PlatformUserService;
import com.kingthy.platform.service.order.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * OrdersServiceImpl
 * <p>
 * 赵生辉 2017年4月14日 上午9:52:52
 *
 * @version 1.0.0
 */
@Service(value = "orderService")
public class OrdersServiceImpl implements OrdersService
{
    @Autowired
    private transient OrdersMapper orderMapper;

    @Autowired
    private transient OrderItemMapper orderItemMapper;

    @Autowired
    private transient OrderLogMapper orderLogMapper;

    @Autowired
    private HttpServletRequest httpServletRequest;

    @Autowired
    private PlatformUserService platformUserService;

    @Override
    public WebApiResponse<?> InvoiceList() {
        return null;
    }

    @Override
    public int create(Orders order, List<OrderItem> orderItems)
    {
        Date currentDate = new Date();
        order.setCreateDate(currentDate);
        order.setModifyDate(currentDate);
        // int orderResult = orderMapper.insertOrderParamSelective(createOrderParam);
        int orderResult = orderMapper.insertSelective(order);
        if (orderResult == 0)// 订单创建失败
        {
            throw OrderBizException.DB_INSERT_RESULT_0;
        }
        List<OrderItem> orderItemList = new ArrayList<OrderItem>();
        String orderUuid = order.getUuid();
        for (OrderItem orderItem : orderItems)
        {
            orderItem.setCreateDate(currentDate);
            orderItem.setModifyDate(currentDate);
            orderItem.setOrderUuid(orderUuid);
            orderItemList.add(orderItem);
        }
        int orderItemResult = orderItemMapper.insertList(orderItemList);
        if (orderItemResult == 0)// 订单明细创建失败
        {
            throw OrderItemBizException.ORDERITEM_CREATE_ERROR;
        }
        OrderLog orderLog = new OrderLog();
        orderLog.setCreateDate(currentDate);
        orderLog.setModifyDate(currentDate);
        orderLog.setOrderUuid(orderUuid);
        orderLog.setType(0001);
        orderLog.setContent("建立订单");
        orderLog.setOperator(order.getConsignee());
        orderLog.setVersion(0);
        orderLog.setDelFlag(false);
        int orderLogResult = orderLogMapper.insertSelective(orderLog);
        if (orderLogResult == 0)// 订单记录创建失败
        {
            throw OrderLogBizException.ORDERLOG_CREATE_ERROR;
        }
        return orderItemResult;
    }

    @Override
    public int update(Orders orders)
    {
        Date currentDate = new Date();
        Example example = new Example(Orders.class);
        Criteria criteria = example.createCriteria();
        criteria.andEqualTo("uuid", orders.getUuid());
        criteria.andEqualTo("delFlag", false);
        int orderResult = orderMapper.updateByExampleSelective(orders, example);

        if (orderResult == 0)// 修改订单失败
        {
            throw OrderBizException.DB_UPDATE_RESULT_0;
        }
        String uuid = httpServletRequest.getHeader("uuid");
        PlatformUserInfoDto platformUserInfoDto;
        try
        {
            platformUserInfoDto = platformUserService.findBaseInfoByUuid(uuid);
        }
        catch (Exception e)
        {
            throw new BizException("查询登录用户失败");
        }

        OrderLog orderLog = new OrderLog();
        orderLog.setCreateDate(currentDate);
        orderLog.setModifyDate(currentDate);
        orderLog.setOrderUuid(orders.getUuid());
        orderLog.setCreator(platformUserInfoDto.getUserName());
        orderLog.setModifier(platformUserInfoDto.getUserName());
        orderLog.setType(OrderBizType.ORDER_UPDATE.getCode());
        StringBuffer content = new StringBuffer();
        content.append("修改");
        if (orders.getAddress() != null || orders.getConsignee() != null || orders.getPhone() != null)
        {
            content.append("收货人信息");
        }
        if (orders.getShippingMethodUuid() != null)
        {
            content.append("配送方式");
        }
        if (orders.getIsInvoice()!=null || orders.getInvoiceTitle()!=null)
        {
            content.append("发票信息");
        }
        orderLog.setContent(content.toString());
        orderLog.setOperator(orders.getConsignee());
        orderLog.setVersion(0);
        orderLog.setDelFlag(true);
        int orderLogResult = orderLogMapper.insertSelective(orderLog);
        if (orderLogResult == 0)// 创建订单记录
        {
            throw OrderLogBizException.DB_UPDATE_RESULT_0;
        }
        return orderResult;

    }

    @Override
    public int updateStatus(OrderLog orderLog)
    {
        Date currentDate = new Date();
        orderLog.setCreateDate(currentDate);
        orderLog.setModifyDate(currentDate);
        orderLog.setVersion(0);
        orderLog.setDelFlag(true);

        String uuid = httpServletRequest.getHeader("uuid");
        PlatformUserInfoDto platformUserInfoDto;
        try
        {
            platformUserInfoDto = platformUserService.findBaseInfoByUuid(uuid);
        }
        catch (Exception e)
        {
            throw new BizException("查询登录用户失败");
        }
        orderLog.setCreator(platformUserInfoDto.getUserName());
        orderLog.setModifier(platformUserInfoDto.getUserName());

        Example example = new Example(Orders.class);
        Criteria criteria = example.createCriteria();
        criteria.andEqualTo("uuid", orderLog.getOrderUuid());
        criteria.andEqualTo("delFlag", false);
        Orders orders = new Orders();
        int status = 0;
        if (orderLog.getType() == OrderBizType.ORDER_CANCEL.getCode()
            || orderLog.getType() == OrderBizType.ORDER_CONFIRM_RETURN.getCode())// 根据操作的类型修改订单的状态
        {
            status = OrderStatus.ORDER_CANCEL.getCode();
            orders.setStatus(status);
            orders.setModifyDate(currentDate);
            orders.setModifier(getUuid());
            int orderResult = orderMapper.updateByExampleSelective(orders, example);
            if (orderResult == 0)// 修改订单失败
            {
                throw OrderBizException.DB_UPDATE_RESULT_0;
            }
        }
        /*
         * else if (orderLog.getType() == OrderBizType.ORDER_REJECT_RETURN.getCode())// 驳回退单不修改订单状态仅做记录 { status =
         * OrderStatus.ORDER_CREATE.getCode(); }
         */
        int orderLogResult = orderLogMapper.insertSelective(orderLog);
        if (orderLogResult == 0)// 创建订单记录
        {
            throw OrderLogBizException.DB_UPDATE_RESULT_0;
        }

        return orderLogResult;
    }

    /**
     * @return
     * @desc 获取当前用户
     * @author yuanml
     */
    private String getUuid()
    {
        return httpServletRequest.getHeader("uuid");
    }

    @Override
    public OrderInfoDto findOrder(Orders order)
    {
        OrderInfoDto orderInfoDto = orderMapper.selectOrderInfo(order.getUuid());
        if (orderInfoDto == null)
        {
            throw OrderBizException.ORDER_SELECT_EXCEPTION;
        }
        return orderInfoDto;
    }

    @Override
    public List<Orders> findAllOrder(Orders order)
    {
        Example example = new Example(Orders.class);
        Criteria criteria = example.createCriteria();
        criteria.andEqualTo("uuid", order.getUuid());
        if (order.getStatus() != null)
        {
            criteria.andEqualTo("status", order.getStatus());
        }
        else if (order.getConsignee() != null && !order.getConsignee().equals(""))
        {
            criteria.andEqualTo("consignee", order.getConsignee());
        }
        else if (order.getShippingNumber() != null && !order.getShippingNumber().equals(""))
        {
            criteria.andEqualTo("shippingNumber", order.getShippingNumber());
        }
        criteria.andEqualTo("delFlag", false);
        List<Orders> result = orderMapper.selectByExample(example);
        if (result == null || result.size() == 0)
        {
            throw OrderBizException.ORDER_SELECT_EXCEPTION;
        }
        return result;
    }

    @Override
    public PageInfo<Orders> findAllOrderPage(int pageNo, int pageSize, Orders order)
    {
        Example example = new Example(Orders.class);
        example.setOrderByClause("create_date desc");
        Criteria criteria = example.createCriteria();
        if (order.getStatus() != null)
        {
            criteria.andEqualTo("status", order.getStatus());
        }
        if (order.getConsignee() != null && !order.getConsignee().equals(""))
        {
            criteria.andLike("consignee", "%"+order.getConsignee()+"%");
        }
        if (order.getSn() != null && !order.getSn().equals(""))
        {
            criteria.andLike("sn", "%"+order.getSn()+"%");
        }
        criteria.andEqualTo("delFlag", false);
        PageHelper.startPage(pageNo, pageSize);
        List<Orders> countries = orderMapper.selectByExample(example);
        if (countries == null)
        {
            throw PartsCategoryBizException.DB_LIST_IS_NULL;
        }
        PageInfo<Orders> pageInfo = new PageInfo<Orders>(countries);
        return pageInfo;
    }

}
