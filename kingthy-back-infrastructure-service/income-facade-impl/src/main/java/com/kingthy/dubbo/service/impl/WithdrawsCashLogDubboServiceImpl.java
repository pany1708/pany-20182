package com.kingthy.dubbo.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kingthy.dubbo.income.service.WithdrawsCashLogDubboService;
import com.kingthy.entity.WithdrawsCashLog;
import com.kingthy.mapper.WithdrawsCashLogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @author  xumin
 * @Description:
 * @DATE Created by 17:23 on 2017/8/9.
 * @Modified by:
 */

@Service(version = "1.0.0", timeout = 3000)
public class WithdrawsCashLogDubboServiceImpl implements WithdrawsCashLogDubboService {

    @Autowired
    private WithdrawsCashLogMapper withdrawsCashLogMapper;

    @Override
    public int insert(WithdrawsCashLog withdrawsCashLog) {
        return withdrawsCashLogMapper.insert(withdrawsCashLog);
    }

    @Override
    public int updateByUuid(WithdrawsCashLog var) {
        Example example = new Example(WithdrawsCashLog.class);
        example.createCriteria().andEqualTo("uuid", var.getUuid());

        return withdrawsCashLogMapper.updateByExampleSelective(var, example);
    }

    @Override
    public List<WithdrawsCashLog> selectAll() {
        return withdrawsCashLogMapper.selectAll();
    }

    @Override
    public WithdrawsCashLog selectByUuid(String uuid) {

        WithdrawsCashLog var = new WithdrawsCashLog();
        var.setUuid(uuid);
        return selectOne(var);
    }

    @Override
    public int selectCount(WithdrawsCashLog withdrawsCashLog) {
        return withdrawsCashLogMapper.selectCount(withdrawsCashLog);
    }

    @Override
    public List<WithdrawsCashLog> select(WithdrawsCashLog var1) {
        return withdrawsCashLogMapper.select(var1);
    }

    @Override
    public WithdrawsCashLog selectOne(WithdrawsCashLog var1) {
        return withdrawsCashLogMapper.selectOne(var1);
    }

    @Override
    public PageInfo<WithdrawsCashLog> queryPage(Integer pageNum, Integer pageSize, WithdrawsCashLog withdrawsCashLog) {
        PageHelper.startPage(pageNum, pageSize);
        return new PageInfo<>(withdrawsCashLogMapper.select(withdrawsCashLog));
    }
}
