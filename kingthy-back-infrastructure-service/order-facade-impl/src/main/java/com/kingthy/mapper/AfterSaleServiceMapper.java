package com.kingthy.mapper;

import com.kingthy.dto.AfterSaleServiceDetailDto;
import com.kingthy.entity.AfterSaleService;
import com.kingthy.request.AfterSaleServiceReq;
import com.kingthy.response.AfterSaleServiceResp;
import com.kingthy.util.MyMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author  xumin
 * @Description:
 * @DATE Created by 18:21 on 2017/8/4.
 * @Modified by:
 */
public interface AfterSaleServiceMapper extends MyMapper<AfterSaleService>
{
    int updateAfterSaleServiceByOrderSn(AfterSaleService var);

    List<AfterSaleServiceResp> selectSaleServiceList(@Param("offset") int offset, @Param("pageSize") int pageSize, @Param("memberUuid") String memberUuid);

    List<AfterSaleServiceDetailDto> selectListByConditon(AfterSaleServiceReq req);

    int updateStatus(AfterSaleService entity);

    int updateRejectAuditing(AfterSaleServiceReq.RejectReq req);


}
