package com.kingthy.transaction;

import com.kingthy.request.IncomeAddReq;
import com.kingthy.request.UpdateWithDrawStatusReq;
import com.kingthy.request.WithdrawReq;

/**
 * @author  likejie on 2017/8/17.
 */
public interface IncomeTransaction {
    int addIncome(IncomeAddReq req);
    String withdraw(WithdrawReq req);
    int updateStatusAndOrderId(UpdateWithDrawStatusReq req);
}
