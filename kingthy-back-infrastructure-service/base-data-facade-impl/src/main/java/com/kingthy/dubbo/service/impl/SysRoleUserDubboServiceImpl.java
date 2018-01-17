package com.kingthy.dubbo.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kingthy.dubbo.basedata.service.SysRoleUserDubboService;
import com.kingthy.entity.SysRoleUser;
import com.kingthy.mapper.SysRoleUserMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 角色用户
 * @author zhaochen on 2017/9/8.
 */
@Service(version = "1.0.0",timeout = 3000)
public class SysRoleUserDubboServiceImpl  implements SysRoleUserDubboService {

    @Autowired
    private SysRoleUserMapper sysRoleUserMapper;

    @Override
    public int insert(SysRoleUser sysRoleUser) {
        return sysRoleUserMapper.insert(sysRoleUser);
    }

    @Override
    public int updateByUuid(SysRoleUser sysRoleUser) {
        return 0;
    }

    @Override
    public List<SysRoleUser> selectAll() {
        return sysRoleUserMapper.selectAll();
    }

    @Override
    public SysRoleUser selectByUuid(String uuid) {
        return null;
    }

    @Override
    public int selectCount(SysRoleUser sysRoleUser) {
        return sysRoleUserMapper.selectCount(sysRoleUser);
    }

    @Override
    public List<SysRoleUser> select(SysRoleUser var1) {
        return sysRoleUserMapper.select(var1);
    }

    @Override
    public SysRoleUser selectOne(SysRoleUser var1) {
        return sysRoleUserMapper.selectOne(var1);
    }

    @Override
    public PageInfo<SysRoleUser> queryPage(Integer pageNum, Integer pageSize, SysRoleUser sysRoleUser) {
        PageHelper.startPage(pageNum,pageSize);
        List<SysRoleUser> shopList = sysRoleUserMapper.select(sysRoleUser);
        return new PageInfo<>(shopList);
    }
}
