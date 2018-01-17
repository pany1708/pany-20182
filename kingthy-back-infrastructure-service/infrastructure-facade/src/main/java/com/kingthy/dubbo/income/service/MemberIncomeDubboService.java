package com.kingthy.dubbo.income.service;

import com.kingthy.base.BaseService;
import com.kingthy.entity.MemberIncome;
import com.kingthy.entity.MemberIncomeDetail;
import com.kingthy.request.IncomeAddReq;
import com.kingthy.request.UpdateWithDrawStatusReq;
import com.kingthy.request.WithdrawReq;

import java.math.BigDecimal;

/**
 * @author xumin
 * @Description:
 * @DATE Created by 10:13 on 2017/8/7.
 * @Modified by:
 */
public interface MemberIncomeDubboService extends BaseService<MemberIncome> {


    BigDecimal queryBalance(String membersUuid);

    MemberIncome queryMemberIncomeByMembersUuid(String membersUuid);

    int updateByUuidAndVersion(MemberIncome memberIncome);
    /**增加收益***/
    int addIncome(IncomeAddReq req);
    /**提现***/
    String withdraw(WithdrawReq req);
    /**更新提现状态及流水号***/
    int  updateStatusAndOrderId(UpdateWithDrawStatusReq req);
}
