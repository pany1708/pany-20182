package com.kingthy.dubbo.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kingthy.dubbo.basedata.service.SysRoleMenuDubboService;
import com.kingthy.entity.SysRoleMenu;
import com.kingthy.mapper.SysRoleMenuMapper;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.List;

/**
 * 角色菜单
 * @author zhaochen on 2017/9/8.
 */
@Service(version = "1.0.0",timeout = 3000)
public class SysRoleMenuDubboServiceImpl implements SysRoleMenuDubboService {
    @Autowired
    private SysRoleMenuMapper sysRoleMenuMapper;

    @Override
    public int insert(SysRoleMenu sysRoleMenu) {
        return sysRoleMenuMapper.insert(sysRoleMenu);
    }

    @Override
    public int updateByUuid(SysRoleMenu sysRoleMenu) {
        return 0;
    }

    @Override
    public List<SysRoleMenu> selectAll() {
        return sysRoleMenuMapper.selectAll();
    }

    @Override
    public SysRoleMenu selectByUuid(String uuid) {
       return null;
    }

    @Override
    public int selectCount(SysRoleMenu sysRoleMenu) {
        return sysRoleMenuMapper.selectCount(sysRoleMenu);
    }

    @Override
    public List<SysRoleMenu> select(SysRoleMenu var1) {
        return sysRoleMenuMapper.select(var1);
    }

    @Override
    public SysRoleMenu selectOne(SysRoleMenu var1) {
        return sysRoleMenuMapper.selectOne(var1);
    }

    @Override
    public PageInfo<SysRoleMenu> queryPage(Integer pageNum, Integer pageSize, SysRoleMenu sysRoleMenu) {
        PageHelper.startPage(pageNum,pageSize);
        List<SysRoleMenu> shopList = sysRoleMenuMapper.select(sysRoleMenu);
        return new PageInfo<>(shopList);
    }
}
