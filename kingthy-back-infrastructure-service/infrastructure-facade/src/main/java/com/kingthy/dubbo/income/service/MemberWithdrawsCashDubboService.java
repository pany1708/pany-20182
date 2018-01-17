package com.kingthy.dubbo.income.service;

import com.kingthy.base.BaseService;
import com.kingthy.dto.WithDrawsCashDTO;
import com.kingthy.entity.MemberWithdrawsCash;
import com.kingthy.request.UpdateWithDrawStatusReq;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author xumin
 * @Description:
 * @DATE Created by 14:16 on 2017/8/7.
 * @Modified by:
 */
public interface MemberWithdrawsCashDubboService extends BaseService<MemberWithdrawsCash> {

    BigDecimal queryWithdraws(String membersUuid);
    List<WithDrawsCashDTO> selectWithdrawsCashList( String membersUuid, Integer offset, Integer pageSize);

    int updateStatusAndOrderId(UpdateWithDrawStatusReq req);

    MemberWithdrawsCash queryMemberWithdrawsCashByUuid(String uuid);

    List<MemberWithdrawsCash> selectWithdrawsCashListByStatus(Integer status);

    int updateStatusSuccess( List<String> uuid);
}
