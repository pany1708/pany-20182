package com.kingthy.platform.service.order;

import com.kingthy.platform.dto.order.ShippingReq;
import com.kingthy.platform.response.WebApiResponse;

/**
 * @AUTHORS xumin
 * @Description:
 * @DATE Created by 14:06 on 2017/7/12.
 * @Modified by:
 */
public interface ShippingService {

    WebApiResponse<?> shippingList(ShippingReq req) throws Exception;

    WebApiResponse<?> delivery(ShippingReq.DeliveryReq req) throws Exception;

    WebApiResponse<?> editAddress( ShippingReq.EditAddress req) throws Exception;

    WebApiResponse<?> showOrderShipping(String orderSn) throws Exception;
}
