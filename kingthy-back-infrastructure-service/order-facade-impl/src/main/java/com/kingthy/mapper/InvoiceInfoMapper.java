package com.kingthy.mapper;


import com.kingthy.dto.InvoiceInfoDto;
import com.kingthy.entity.OrderInvoice;
import com.kingthy.request.InvoiceInfoReq;
import com.kingthy.util.MyMapper;

import java.util.List;

/**
 * @author  xumin
 * @Description:
 * @DATE Created by 17:50 on 2017/7/7.
 * @Modified by:
 */
public interface InvoiceInfoMapper extends MyMapper<OrderInvoice> {

    List<InvoiceInfoDto> findInvoicePage(InvoiceInfoReq req);
}
