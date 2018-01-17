package com.kingthy.dubbo.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kingthy.dubbo.income.service.MemberIncomeDubboService;
import com.kingthy.entity.*;
import com.kingthy.mapper.MemberIncomeDetailMapper;
import com.kingthy.mapper.MemberIncomeMapper;
import com.kingthy.mapper.MemberWithdrawsCashMapper;
import com.kingthy.mapper.WithdrawsCashLogMapper;
import com.kingthy.request.IncomeAddReq;
import com.kingthy.request.UpdateWithDrawStatusReq;
import com.kingthy.request.WithdrawReq;
import com.kingthy.transaction.IncomeTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Example;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author  xumin
 * @Description:
 * @DATE Created by 11:29 on 2017/8/7.
 * @Modified by:
 */
@Service(version = "1.0.0", timeout = 3000)
public class MemberIncomeDubboServiceImpl implements MemberIncomeDubboService {

    @Autowired
    private MemberIncomeMapper memberIncomeMapper;
    @Autowired
    private MemberIncomeDetailMapper memberIncomeDetailMapper;
    @Autowired
    private MemberWithdrawsCashMapper memberWithdrawsCashMapper;
    @Autowired
    private WithdrawsCashLogMapper withdrawsCashLogMapper;

    @Autowired
    private IncomeTransaction incomeTransaction;

    @Override
    public int insert(MemberIncome memberIncome) {
        return memberIncomeMapper.insert(memberIncome);
    }

    @Override
    public int updateByUuid(MemberIncome var) {

        Example example = new Example(MemberIncome.class);
        example.createCriteria().andEqualTo("uuid", var.getUuid());

        return memberIncomeMapper.updateByExampleSelective(var, example);
    }

    @Override
    public List<MemberIncome> selectAll() {
        return memberIncomeMapper.selectAll();
    }

    @Override
    public MemberIncome selectByUuid(String uuid) {
        MemberIncome var1 = new MemberIncome();
        var1.setUuid(uuid);
        return selectOne(var1);
    }

    @Override
    public int selectCount(MemberIncome memberIncome) {
        return memberIncomeMapper.selectCount(memberIncome);
    }

    @Override
    public List<MemberIncome> select(MemberIncome var1) {
        return memberIncomeMapper.select(var1);
    }

    @Override
    public MemberIncome selectOne(MemberIncome var1) {
        return memberIncomeMapper.selectOne(var1);
    }

    @Override
    public PageInfo<MemberIncome> queryPage(Integer pageNum, Integer pageSize, MemberIncome var) {
        PageHelper.startPage(pageNum, pageSize);
        return new PageInfo<>(select(var));
    }

    @Override
    public BigDecimal queryBalance(String membersUuid) {
        return memberIncomeMapper.queryBalance(membersUuid);
    }

    @Override
    public MemberIncome queryMemberIncomeByMembersUuid(String membersUuid) {
        return memberIncomeMapper.queryMemberIncomeByMembersUuid(membersUuid);
    }

    @Override
    public int updateByUuidAndVersion(MemberIncome memberIncome) {
        return memberIncomeMapper.updateByUuidAndVersion(memberIncome);
    }

    @Override
    public int addIncome(IncomeAddReq req) {
        return incomeTransaction.addIncome(req);
    }

    ///提现到银行卡
    @Override
    public String withdraw(WithdrawReq req) {

        return incomeTransaction.withdraw(req);
    }

    @Override
    public int updateStatusAndOrderId(UpdateWithDrawStatusReq req) {
        return incomeTransaction.updateStatusAndOrderId(req);
    }
}
