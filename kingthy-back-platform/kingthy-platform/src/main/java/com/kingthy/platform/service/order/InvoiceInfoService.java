package com.kingthy.platform.service.order;

import com.kingthy.platform.dto.order.InvoiceInfoReq;
import com.kingthy.platform.response.WebApiResponse;

/**
 * @AUTHORS xumin
 * @Description: 发票管理
 * @DATE Created by 15:06 on 2017/7/10.
 * @Modified by:
 */
public interface InvoiceInfoService {

    WebApiResponse<?> invoiceList(InvoiceInfoReq req) throws Exception;

    WebApiResponse<?> showInvoiceInfo(String orderSn) throws Exception;

}
