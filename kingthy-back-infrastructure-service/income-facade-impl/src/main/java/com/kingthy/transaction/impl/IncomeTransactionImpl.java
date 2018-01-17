package com.kingthy.transaction.impl;

import com.kingthy.entity.MemberIncome;
import com.kingthy.entity.MemberIncomeDetail;
import com.kingthy.entity.MemberWithdrawsCash;
import com.kingthy.entity.WithdrawsCashLog;
import com.kingthy.mapper.MemberIncomeDetailMapper;
import com.kingthy.mapper.MemberIncomeMapper;
import com.kingthy.mapper.MemberWithdrawsCashMapper;
import com.kingthy.mapper.WithdrawsCashLogMapper;
import com.kingthy.request.IncomeAddReq;
import com.kingthy.request.UpdateWithDrawStatusReq;
import com.kingthy.request.WithdrawReq;
import com.kingthy.transaction.IncomeTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author  likejie on 2017/8/17.
 */

@Transactional
@Service
public class IncomeTransactionImpl implements IncomeTransaction {

    @Autowired
    private MemberIncomeMapper memberIncomeMapper;
    @Autowired
    private MemberIncomeDetailMapper memberIncomeDetailMapper;
    @Autowired
    private MemberWithdrawsCashMapper memberWithdrawsCashMapper;
    @Autowired
    private WithdrawsCashLogMapper withdrawsCashLogMapper;

    @Override
    public int addIncome(IncomeAddReq req) {
        String memberUuid=req.getMemberUuid();
        String orderSn = req.getOrderSn();

        MemberIncome income = memberIncomeMapper.queryMemberIncomeByMembersUuid(memberUuid);

        //新增收益主记录
        if (income == null){

            BigDecimal decimal = new BigDecimal(0);

            MemberIncome memberIncome = new MemberIncome();
            memberIncome.setMembersUuid(memberUuid);
            memberIncome.setCreateDate(new Date());
            memberIncome.setCreator(memberUuid);
            memberIncome.setVersion(0);
            memberIncome.setDelFlag(false);
            memberIncome.setAmount(decimal);
            memberIncome.setBalance(decimal);
            memberIncome.setWithdraws(decimal);

            memberIncomeMapper.insert(memberIncome);

            income = memberIncome;
        }

        String goodsUuid = req.getGoodsUuid();
        Integer quantity = req.getQuantity();

        //收益暂时取20%
        BigDecimal money = req.getAmount().multiply(new BigDecimal(0.2));

        MemberIncomeDetail memberIncomeDetail = new MemberIncomeDetail();
        memberIncomeDetail.setGoodsUuid(goodsUuid);
        memberIncomeDetail.setMembersUuid(memberUuid);
        memberIncomeDetail.setMoney(money);
        memberIncomeDetail.setOrderSn(orderSn);
        memberIncomeDetail.setQuantity(quantity);
        memberIncomeDetail.setCreateDate(new Date());
        memberIncomeDetail.setCreator(memberUuid);
        memberIncomeDetail.setDelFlag(false);
        memberIncomeDetail.setVersion(0);

        //新增收益明细
        memberIncomeDetailMapper.insert(memberIncomeDetail);

        //更新总收益
        MemberIncome tmp = new MemberIncome();
        tmp.setUuid(income.getUuid());
        tmp.setModifier(memberUuid);
        tmp.setModifyDate(new Date());
        tmp.setVersion(income.getVersion());
        tmp.setAmount(income.getAmount().add(money));
        int result = memberIncomeMapper.updateByUuidAndVersion(tmp);
        if(result<1){
            // 设置事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return result;
    }

    @Override
    public String withdraw(WithdrawReq req) {
        String membersUuid=req.getMembersUuid();
        String cardNo=req.getCardNo();

        //查询余额
        MemberIncome income = memberIncomeMapper.queryMemberIncomeByMembersUuid(membersUuid);

        //提现记录
        MemberWithdrawsCash cash = new MemberWithdrawsCash();
        cash.setBankCardUuid(req.getBankCardUuid());
        cash.setCardNo(cardNo);
        cash.setMembersUuid(membersUuid);
        cash.setMoney(new BigDecimal(req.getMoney()));
        cash.setStatus(MemberWithdrawsCash.StatusType.S_0.getValue());
        cash.setCreateDate(new Date());
        cash.setCreator(membersUuid);
        cash.setDelFlag(false);
        cash.setVersion(0);

        int res1=0;
        int res2=0;
        int res3=0;
        res1 = memberWithdrawsCashMapper.insert(cash);
        if (res1 > 0) {
            //修改可用余额
            MemberIncome tmp = new MemberIncome();
            tmp.setUuid(income.getUuid());
            tmp.setModifier(membersUuid);
            tmp.setModifyDate(new Date());
            tmp.setVersion(income.getVersion());
            BigDecimal withdraws = memberWithdrawsCashMapper.queryWithdraws(membersUuid);
            tmp.setWithdraws(withdraws == null ? new BigDecimal(0) : withdraws);
            BigDecimal balance = income.getAmount().subtract(withdraws);
            tmp.setBalance(balance);

            res2= memberIncomeMapper.updateByUuidAndVersion(tmp);
            if(res2<1) {
                // 设置事务回滚
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            }
            //写日志
            WithdrawsCashLog log = new WithdrawsCashLog();
            log.setCardNo(cardNo);
            log.setMembersUuid(membersUuid);
            log.setWithdrawsUuid(cash.getUuid());
            log.setCreateDate(new Date());
            log.setCreator(membersUuid);
            log.setDelFlag(false);
            log.setVersion(0);
            log.setContent("用户申请提现,提现金额:" + req.getMoney() + "元");
            res3=withdrawsCashLogMapper.insert(log);
            if(res3<1) {
                // 设置事务回滚
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            }
        }
        return cash.getUuid();
    }

    @Override
    public int updateStatusAndOrderId(UpdateWithDrawStatusReq req) {
        MemberWithdrawsCash cash = memberWithdrawsCashMapper.queryMemberWithdrawsCashByUuid(req.getWithdrawsUuid());

        req.setVersion(cash.getVersion());

        int result = memberWithdrawsCashMapper.updateStatusAndOrderId(req);

        if (result > 0){
            //写日志
            WithdrawsCashLog log = new WithdrawsCashLog();
            log.setCardNo(cash.getCardNo());
            log.setMembersUuid(req.getMemberUuid());
            log.setWithdrawsUuid(cash.getUuid());
            log.setCreateDate(new Date());
            log.setCreator(req.getMemberUuid());
            log.setDelFlag(false);
            log.setVersion(0);
            log.setContent("提现中,提现金额:" + cash.getMoney() + "元");

            result=withdrawsCashLogMapper.insert(log);

        }
        return result;
    }
}
