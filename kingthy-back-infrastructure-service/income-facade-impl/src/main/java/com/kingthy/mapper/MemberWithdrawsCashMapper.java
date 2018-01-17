package com.kingthy.mapper;

import com.kingthy.dto.WithDrawsCashDTO;
import com.kingthy.entity.MemberWithdrawsCash;
import com.kingthy.request.UpdateWithDrawStatusReq;
import com.kingthy.util.MyMapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author  xumin
 * @Description:
 * @DATE Created by 14:14 on 2017/8/7.
 * @Modified by:
 */
public interface MemberWithdrawsCashMapper extends MyMapper<MemberWithdrawsCash>
{
    BigDecimal queryWithdraws(String membersUuid);
    List<WithDrawsCashDTO> selectWithdrawsCashList(@Param("membersUuid") String membersUuid, @Param("offset") Integer offset, @Param("pageSize") Integer pageSize);

    int updateStatusAndOrderId(UpdateWithDrawStatusReq req);

    MemberWithdrawsCash queryMemberWithdrawsCashByUuid(String uuid);

    List<MemberWithdrawsCash> selectWithdrawsCashListByStatus(Integer status);

    int updateStatusSuccess(@Param("list") List<String> uuid);
}
