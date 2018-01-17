package com.kingthy.platform.mapper.order;

import com.kingthy.platform.dto.order.InvoiceInfoDto;
import com.kingthy.platform.dto.order.InvoiceInfoReq;
import com.kingthy.platform.entity.order.InvoiceInfo;
import com.kingthy.platform.util.MyMapper;

import java.util.List;

/**
 * @AUTHORS xumin
 * @Description:
 * @DATE Created by 17:50 on 2017/7/7.
 * @Modified by:
 */
public interface InvoiceInfoMapper extends MyMapper<InvoiceInfo> {

    List<InvoiceInfoDto> findInvoicePage(InvoiceInfoReq req);
}
