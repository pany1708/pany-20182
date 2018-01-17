package com.kingthy.dubbo.order.service;

import com.github.pagehelper.PageInfo;
import com.kingthy.base.BaseService;
import com.kingthy.dto.AfterSaleServiceDetailDto;
import com.kingthy.entity.AfterSaleService;
import com.kingthy.request.AfterSaleServiceReq;
import com.kingthy.response.AfterSaleServiceResp;

import java.util.List;

/**
 * @author xumin
 * @Description:
 * @DATE Created by 18:17 on 2017/8/4.
 * @Modified by:
 */
public interface AfterSaleServiceDubboService extends BaseService<AfterSaleService> {

    int updateAfterSaleServiceByOrderSn(AfterSaleService var);

    List<AfterSaleServiceResp> selectSaleServiceList(int offset, int pageSize, String memberUuid);

    PageInfo<AfterSaleServiceDetailDto> pageingQueryAfterSaleService(AfterSaleServiceReq var);

    int updateStatus(AfterSaleServiceReq.EditStatusReq req );
    int rejectAuditing(AfterSaleServiceReq.RejectReq req);
}
