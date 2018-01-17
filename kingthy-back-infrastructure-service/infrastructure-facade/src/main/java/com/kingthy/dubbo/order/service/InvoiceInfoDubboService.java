package com.kingthy.dubbo.order.service;

import com.kingthy.base.BaseService;
import com.kingthy.dto.InvoiceInfoDto;
import com.kingthy.entity.OrderInvoice;
import com.kingthy.request.InvoiceInfoReq;

import java.util.List;

/**
 * Created by likejie on 2017/8/28.
 */
public interface InvoiceInfoDubboService extends BaseService<OrderInvoice> {

    List<InvoiceInfoDto> findInvoicePage(InvoiceInfoReq req);
}
