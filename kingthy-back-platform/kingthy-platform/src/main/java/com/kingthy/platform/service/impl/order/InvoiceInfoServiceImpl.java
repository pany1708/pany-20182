package com.kingthy.platform.service.impl.order;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kingthy.platform.dto.order.InvoiceInfoDto;
import com.kingthy.platform.dto.order.InvoiceInfoReq;
import com.kingthy.platform.dto.order.OrderItemDto;
import com.kingthy.platform.mapper.order.InvoiceInfoMapper;
import com.kingthy.platform.mapper.order.OrderItemMapper;
import com.kingthy.platform.response.WebApiResponse;
import com.kingthy.platform.service.order.InvoiceInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @AUTHORS xumin
 * @Description:
 * @DATE Created by 15:11 on 2017/7/10.
 * @Modified by:
 */
@Service
public class InvoiceInfoServiceImpl implements InvoiceInfoService {

    @Autowired
    private InvoiceInfoMapper invoiceInfoMapper;

    @Autowired
    private OrderItemMapper orderItemMapper;

    /**
     * 发票列表
     * @param req
     * @return
     */
    @Override
    public WebApiResponse<?> invoiceList(InvoiceInfoReq req) throws Exception{

        PageHelper.startPage(req.getPageNum(), req.getPageSize());

        List<InvoiceInfoDto> list = invoiceInfoMapper.findInvoicePage(req);

        PageInfo<InvoiceInfoDto> pageInfo = new PageInfo<>(list);

        return WebApiResponse.success(pageInfo);
    }

    /**
     * 发票详情
     * @param orderSn
     * @return
     * @throws Exception
     */
    @Override
    public WebApiResponse<?> showInvoiceInfo(String orderSn) throws Exception {

        //订单详情
        OrderItemDto.OrderDetail orderDetail = orderItemMapper.selectOrderDetail(orderSn);

        //发票信息
        InvoiceInfoReq req = new InvoiceInfoReq();
        req.setOrderSn(orderSn);
        List<InvoiceInfoDto> invoiceList = invoiceInfoMapper.findInvoicePage(req);

        Map<String, Object> map = new HashMap<>();
        map.put("orderDetail", orderDetail);
        map.put("invoiceList", invoiceList);

        return WebApiResponse.success(map);
    }

}
