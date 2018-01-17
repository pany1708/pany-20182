package com.kingthy.mapper;


import com.kingthy.dto.IncomeBalanceDTO;
import com.kingthy.entity.MemberBankCard;
import com.kingthy.util.MyMapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author  by likejie on 2017/8/7.
 */
public interface MemberBankCardMapper  extends MyMapper<MemberBankCard> {

    /**
     * 通过用户id查询银行卡信息
     * @param membersUuid
     * @return
     */
    IncomeBalanceDTO queryBankInfoByMembersUuid(@Param("membersUuid") String membersUuid);

    /**
     * 通过用户id查询银行卡信息
     * @param uuid
     * @return
     */
    MemberBankCard selectBankCardInfo(@Param("uuid") String uuid);

    /**
     * 通过用户id查询银行卡信息
     * @param membersUuid
     * @param cardNo
     * @return
     */
    int countBankCardByCardNoAndMemberUuid(@Param("cardNo") String cardNo, @Param("membersUuid")String membersUuid);
}
