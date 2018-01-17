package com.kingthy.dubbo.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kingthy.dubbo.basedata.service.SysRoleDubboService;
import com.kingthy.entity.SuitCategory;
import com.kingthy.entity.SysRole;
import com.kingthy.mapper.SysRoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * 系统角色
 * @author zhaochen on 2017/9/8.
 */
@Service(version = "1.0.0",timeout = 3000)
public class SysRoleDubboServiceImpl implements SysRoleDubboService {

    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Override
    public int insert(SysRole sysRole) {
        return sysRoleMapper.insert(sysRole);
    }

    @Override
    public int updateByUuid(SysRole sysRole) {
        SysRole sysRoleResult = selectByUuid(sysRole.getUuid());
        Integer currentVersion  = sysRoleResult.getVersion();
        Example example = new Example(SuitCategory.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("uuid",sysRole.getUuid());
        criteria.andEqualTo("version",currentVersion);
        sysRole.setVersion(currentVersion+1);
        return sysRoleMapper.updateByExample(sysRole,example);
    }

    @Override
    public List<SysRole> selectAll() {
        return sysRoleMapper.selectAll();
    }

    @Override
    public SysRole selectByUuid(String uuid) {
        Example example = new Example(SysRole.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("uuid",uuid);
        List<SysRole> sysRoleList = sysRoleMapper.selectByExample(example);
        if (sysRoleList!=null && sysRoleList.size()>0){
            return sysRoleList.get(0);
        }
        return new SysRole();
    }

    @Override
    public int selectCount(SysRole sysRole) {
        return sysRoleMapper.selectCount(sysRole);
    }

    @Override
    public List<SysRole> select(SysRole var1) {
        return sysRoleMapper.select(var1);
    }

    @Override
    public SysRole selectOne(SysRole var1) {
        return sysRoleMapper.selectOne(var1);
    }

    @Override
    public PageInfo<SysRole> queryPage(Integer pageNum, Integer pageSize, SysRole sysRole) {
        PageHelper.startPage(pageNum,pageSize);
        List<SysRole> shopList = sysRoleMapper.select(sysRole);
        return new PageInfo<>(shopList);
    }
}
