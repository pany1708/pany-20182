package com.kingthy.platform.service.order;

import com.kingthy.platform.dto.order.AfterSaleServiceReq;
import com.kingthy.platform.response.WebApiResponse;

/**
 * @AUTHORS xumin
 * @Description:
 * @DATE Created by 15:26 on 2017/7/11.
 * @Modified by:
 */
public interface AfterSaleServiceService {

    WebApiResponse<?> afterSaleList(AfterSaleServiceReq req) throws Exception;

    WebApiResponse<?> editStatus(AfterSaleServiceReq.EditStatusReq req) throws Exception;

    WebApiResponse<?> rejectAuditing(AfterSaleServiceReq.RejectReq req) throws Exception;

    WebApiResponse<?> queryComment(String uuid) throws Exception;
}
