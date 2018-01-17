package com.kingthy.dubbo.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kingthy.dto.InvoiceInfoDto;
import com.kingthy.dubbo.order.service.InvoiceInfoDubboService;
import com.kingthy.entity.OrderInvoice;
import com.kingthy.entity.Orders;
import com.kingthy.mapper.InvoiceInfoMapper;
import com.kingthy.request.InvoiceInfoReq;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 *  InvoiceInfoDubboServiceImpl
 * @author likejie
 * @date 2017/8/28.
 */
@Service(version = "1.0.0",timeout = 10000)
public class InvoiceInfoDubboServiceImpl implements InvoiceInfoDubboService {

    @Autowired
    private InvoiceInfoMapper invoiceInfoMapper;

    @Override
    public int insert(OrderInvoice orderInvoice) {
        return invoiceInfoMapper.insert(orderInvoice);
    }

    @Override
    public int updateByUuid(OrderInvoice orderInvoice) {
        Example example = new Example(Orders.class);
        example.createCriteria().andEqualTo("uuid", orderInvoice.getUuid());
        return invoiceInfoMapper.updateByExample(orderInvoice,example);
    }

    @Override
    public List<OrderInvoice> selectAll() {
        return invoiceInfoMapper.selectAll();
    }

    @Override
    public OrderInvoice selectByUuid(String uuid) {
        OrderInvoice cond=new OrderInvoice();
        cond.setUuid(uuid);
        return invoiceInfoMapper.selectOne(cond);
    }

    @Override
    public int selectCount(OrderInvoice orderInvoice) {
        return invoiceInfoMapper.selectCount(orderInvoice);
    }

    @Override
    public List<OrderInvoice> select(OrderInvoice var1) {
        return invoiceInfoMapper.select(var1);
    }

    @Override
    public OrderInvoice selectOne(OrderInvoice var1) {
        return invoiceInfoMapper.selectOne(var1);
    }

    @Override
    public PageInfo<OrderInvoice> queryPage(Integer pageNum, Integer pageSize, OrderInvoice orderInvoice) {

        PageHelper.startPage(pageNum, pageSize);
        return new PageInfo<>(invoiceInfoMapper.select(orderInvoice));
    }

    @Override
    public List<InvoiceInfoDto> findInvoicePage(InvoiceInfoReq req) {
        return invoiceInfoMapper.findInvoicePage(req);
    }
}
