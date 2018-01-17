package com.kingthy.dubbo.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kingthy.dto.IncomeDetailDTO;
import com.kingthy.dubbo.income.service.MemberIncomeDetailDubboService;
import com.kingthy.entity.MemberIncome;
import com.kingthy.entity.MemberIncomeDetail;
import com.kingthy.mapper.MemberIncomeDetailMapper;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @author  xumin
 * @Description:
 * @DATE Created by 13:53 on 2017/8/7.
 * @Modified by:
 */

@Service(version = "1.0.0", timeout = 3000)
public class MemberIncomeDetailDubboServiceImpl implements MemberIncomeDetailDubboService {

    @Autowired
    private MemberIncomeDetailMapper memberIncomeDetailMapper;

    @Override
    public int insert(MemberIncomeDetail var) {
        return memberIncomeDetailMapper.insert(var);
    }

    @Override
    public int updateByUuid(MemberIncomeDetail var) {

        Example example = new Example(MemberIncome.class);
        example.createCriteria().andEqualTo("uuid", var.getUuid());

        return memberIncomeDetailMapper.updateByExampleSelective(var, example);
    }

    @Override
    public List<MemberIncomeDetail> selectAll() {
        return memberIncomeDetailMapper.selectAll();
    }

    @Override
    public MemberIncomeDetail selectByUuid(String uuid) {
        MemberIncomeDetail var1 = new MemberIncomeDetail();
        var1.setUuid(uuid);
        return selectOne(var1);
    }

    @Override
    public int selectCount(MemberIncomeDetail var) {
        return memberIncomeDetailMapper.selectCount(var);
    }

    @Override
    public List<MemberIncomeDetail> select(MemberIncomeDetail var1) {
        return memberIncomeDetailMapper.select(var1);
    }

    @Override
    public MemberIncomeDetail selectOne(MemberIncomeDetail var1) {
        return memberIncomeDetailMapper.selectOne(var1);
    }

    @Override
    public PageInfo<MemberIncomeDetail> queryPage(Integer pageNum, Integer pageSize, MemberIncomeDetail var) {

        PageHelper.startPage(pageNum, pageSize);

        return new PageInfo<>(memberIncomeDetailMapper.select(var));
    }

    @Override
    public List<IncomeDetailDTO> selectIncomeList(String membersUuid, Integer offset, Integer pageSize) {
        return memberIncomeDetailMapper.selectIncomeList(membersUuid,offset,pageSize);
    }

    @Override
    public int countIncomeDetailByOrderSn(String orderSn) {
        return memberIncomeDetailMapper.countIncomeDetailByOrderSn(orderSn);
    }
}
