package com.kingthy.platform.mapper.order;

import com.kingthy.platform.dto.order.AfterSaleServiceDto;
import com.kingthy.platform.dto.order.AfterSaleServiceReq;
import com.kingthy.platform.entity.order.AfterSaleService;
import com.kingthy.platform.util.MyMapper;

import java.util.List;

/**
 * @AUTHORS xumin
 * @Description:
 * @DATE Created by 11:13 on 2017/7/11.
 * @Modified by:
 */
public interface AfterSaleServiceMapper extends MyMapper<AfterSaleService> {

    List<AfterSaleServiceDto> selectSaleServiceList(AfterSaleServiceReq req);

    int updateRejectAuditing(AfterSaleServiceReq.RejectReq req);

    int updateStatus(AfterSaleService afterSaleService);

    AfterSaleService selectServiceInfoByUuid(String uuid);
}
