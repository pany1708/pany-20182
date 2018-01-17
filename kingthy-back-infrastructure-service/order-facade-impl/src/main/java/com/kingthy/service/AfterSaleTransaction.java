package com.kingthy.service;

import com.kingthy.request.AfterSaleServiceReq;

/**
 * @author  likejie on 2017/8/25.
 */
public interface AfterSaleTransaction {
    int updateStatus(AfterSaleServiceReq.EditStatusReq req);
    int rejectAuditing(AfterSaleServiceReq.RejectReq req);
}
