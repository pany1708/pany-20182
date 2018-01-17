package com.kingthy.mapper;

import com.kingthy.entity.MemberIncome;
import com.kingthy.util.MyMapper;

import java.math.BigDecimal;

/**
 * @author  xumin
 * @Description:
 * @DATE Created by 11:39 on 2017/8/7.
 * @Modified by:
 */
public interface MemberIncomeMapper extends MyMapper<MemberIncome>
{
    BigDecimal queryBalance(String membersUuid);

    MemberIncome queryMemberIncomeByMembersUuid(String membersUuid);

    int updateByUuidAndVersion(MemberIncome memberIncome);
}
