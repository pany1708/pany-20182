package com.kingthy.dubbo.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kingthy.dto.WithDrawsCashDTO;
import com.kingthy.dubbo.income.service.MemberWithdrawsCashDubboService;
import com.kingthy.entity.MemberWithdrawsCash;
import com.kingthy.mapper.MemberWithdrawsCashMapper;
import com.kingthy.request.UpdateWithDrawStatusReq;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Example;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author  xumin
 * @Description:
 * @DATE Created by 14:18 on 2017/8/7.
 * @Modified by:
 */
@Service(version = "1.0.0", timeout = 3000)
public class MemberWithdrawsCashDubboServiceImpl implements MemberWithdrawsCashDubboService {


    @Autowired
    private MemberWithdrawsCashMapper memberWithdrawsCashMapper;

    @Override
    public int insert(MemberWithdrawsCash var) {
        return memberWithdrawsCashMapper.insert(var);
    }

    @Override
    public int updateByUuid(MemberWithdrawsCash var) {

        Example example = new Example(MemberWithdrawsCash.class);
        example.createCriteria().andEqualTo("uuid", var.getUuid());

        return memberWithdrawsCashMapper.updateByExampleSelective(var, example);
    }

    @Override
    public List<MemberWithdrawsCash> selectAll() {
        return memberWithdrawsCashMapper.selectAll();
    }

    @Override
    public MemberWithdrawsCash selectByUuid(String uuid) {

        MemberWithdrawsCash var1 = new MemberWithdrawsCash();
        var1.setUuid(uuid);

        return selectOne(var1);
    }

    @Override
    public int selectCount(MemberWithdrawsCash var) {
        return memberWithdrawsCashMapper.selectCount(var);
    }

    @Override
    public List<MemberWithdrawsCash> select(MemberWithdrawsCash var1) {
        return memberWithdrawsCashMapper.select(var1);
    }

    @Override
    public MemberWithdrawsCash selectOne(MemberWithdrawsCash var1) {
        return memberWithdrawsCashMapper.selectOne(var1);
    }

    @Override
    public PageInfo<MemberWithdrawsCash> queryPage(Integer pageNum, Integer pageSize, MemberWithdrawsCash var) {
        PageHelper.startPage(pageNum, pageSize);
        return new PageInfo<>(memberWithdrawsCashMapper.select(var));
    }

    @Override
    public BigDecimal queryWithdraws(String membersUuid) {
        return memberWithdrawsCashMapper.queryWithdraws(membersUuid);
    }

    @Override
    public List<WithDrawsCashDTO> selectWithdrawsCashList(String membersUuid, Integer offset, Integer pageSize) {
        return memberWithdrawsCashMapper.selectWithdrawsCashList(membersUuid,offset,pageSize);
    }

    @Override
    public int updateStatusAndOrderId(UpdateWithDrawStatusReq req) {
        return memberWithdrawsCashMapper.updateStatusAndOrderId(req);
    }

    @Override
    public MemberWithdrawsCash queryMemberWithdrawsCashByUuid(String uuid) {
        return memberWithdrawsCashMapper.queryMemberWithdrawsCashByUuid(uuid);
    }

    @Override
    public List<MemberWithdrawsCash> selectWithdrawsCashListByStatus(Integer status) {
        return memberWithdrawsCashMapper.selectWithdrawsCashListByStatus(status);
    }

    @Override
    public int updateStatusSuccess(List<String> uuid) {
        return memberWithdrawsCashMapper.updateStatusSuccess(uuid);
    }
}
