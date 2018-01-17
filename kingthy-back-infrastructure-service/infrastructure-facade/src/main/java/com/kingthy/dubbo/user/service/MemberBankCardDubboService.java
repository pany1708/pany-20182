package com.kingthy.dubbo.user.service;

import com.kingthy.base.BaseService;
import com.kingthy.dto.IncomeBalanceDTO;
import com.kingthy.entity.MemberBankCard;


/**
 *
 * @author likejie
 * @date  2017/8/7.
 */
public interface MemberBankCardDubboService extends BaseService<MemberBankCard> {

    /**
     * 根据UUID查询银行卡信息
     * @param uuid
     * @return
     */
    MemberBankCard selectBankCardInfo(String uuid);

    /**
     * 根据卡号和会员UUID查询银行卡信息
     * @param cardNo
     * @param membersUuid
     * @return
     */
    int countBankCardByCardNoAndMemberUuid(String cardNo, String membersUuid);

    /**
     * 根据会员UUID查询银行卡信息
     * @param membersUuid
     * @return
     */
    IncomeBalanceDTO queryBankInfoByMembersUuid( String membersUuid);
}
