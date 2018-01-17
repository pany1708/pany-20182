package com.kingthy.platform.service.impl.order;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kingthy.platform.dto.order.ShippingDto;
import com.kingthy.platform.dto.order.ShippingReq;
import com.kingthy.platform.entity.order.OrderLog;
import com.kingthy.platform.mapper.order.OrderLogMapper;
import com.kingthy.platform.mapper.order.OrdersMapper;
import com.kingthy.platform.response.WebApiResponse;
import com.kingthy.platform.service.order.ShippingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.util.StringUtils;

import java.util.Date;
import java.util.List;

/**
 * @AUTHORS xumin
 * @Description:
 * @DATE Created by 14:27 on 2017/7/12.
 * @Modified by:
 */
@Service
public class ShippingServiceImpl implements ShippingService {

    @Autowired
    private OrdersMapper ordersMapper;

    @Autowired
    private OrderLogMapper orderLogMapper;

    @Override
    public WebApiResponse<?> shippingList(ShippingReq req) throws Exception {

        PageHelper.startPage(req.getPageNum(), req.getPageSize());

        List<ShippingDto> list = ordersMapper.selectShippingList(req);

        PageInfo<ShippingDto> pageInfo = new PageInfo<>(list);

        return WebApiResponse.success(pageInfo);
    }

    /**
     * 发货
     * @param req
     * @return
     * @throws Exception
     */
    @Transactional
    @Override
    public WebApiResponse<?> delivery(ShippingReq.DeliveryReq req) throws Exception {

        int result = ordersMapper.deliveryOrders(req);

        if (result > 0){

            String content = StringUtils.isEmpty(req.getShippingNumber()) ? "订单发货,无需物流" : "订单发货,单号:"+req.getShippingNumber();

            //日志
            createLog(req.getMemberUuid(), content, req.getOrderSn(), req.getUuid());
        }

        return result > 0 ? WebApiResponse.success(WebApiResponse.ResponseMsg.SUCCESS.getValue()) : WebApiResponse.error(WebApiResponse.ResponseMsg.FAIL.getValue());
    }

    private void createLog(String memberUuid, String content, String orderSn, String orderUuid) {
        OrderLog orderLog = new OrderLog();

        orderLog.setVersion(0);
        orderLog.setDelFlag(false);
        orderLog.setCreator(memberUuid);
        orderLog.setCreateDate(new Date());
        orderLog.setType(0);
        orderLog.setOperator(memberUuid);
        orderLog.setContent(content);
        orderLog.setOrderUuid(orderUuid);
        orderLog.setOrderSn(orderSn);

        orderLogMapper.insert(orderLog);
    }

    /**
     * 修改地址
     * @param req
     * @return
     * @throws Exception
     */
    @Override
    public WebApiResponse<?> editAddress(ShippingReq.EditAddress req) throws Exception {

        int result = ordersMapper.updateOrdersAddress(req);

        if (result > 0){
            //日志
            createLog(req.getMemberUuid(), "修改收货地址:"+req.getAddress(), req.getOrderSn(), req.getUuid());
        }

        return result > 0 ? WebApiResponse.success(WebApiResponse.ResponseMsg.SUCCESS.getValue()) : WebApiResponse.error(WebApiResponse.ResponseMsg.FAIL.getValue());
    }

    /**
     * 发货详情
     * @param orderSn
     * @return
     * @throws Exception
     */
    @Override
    public WebApiResponse<?> showOrderShipping(String orderSn) throws Exception {

        return WebApiResponse.success(ordersMapper.showOrderShippingBySn(orderSn));
    }

}
