package com.kingthy.dubbo.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kingthy.dubbo.basedata.service.SysUserDubboService;
import com.kingthy.entity.SuitCategory;
import com.kingthy.entity.SysUser;
import com.kingthy.mapper.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * 系统用户
 * @author zhaochen on 2017/9/8.
 */
@Service(version = "1.0.0",timeout = 3000)
public class SysUserDubboServiceImpl implements SysUserDubboService {

    @Autowired
    private SysUserMapper  sysUserMapper;
    @Override
    public int insert(SysUser sysUser) {
        return sysUserMapper.insert(sysUser);
    }

    @Override
    public int updateByUuid(SysUser sysUser) {
        Example example = new Example(SuitCategory.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("uuid",sysUser.getUuid());
        return sysUserMapper.updateByExample(sysUser,example);
    }

    @Override
    public List<SysUser> selectAll() {
        return sysUserMapper.selectAll();
    }

    @Override
    public SysUser selectByUuid(String uuid) {
        SysUser sysUser=new SysUser();
        sysUser.setUuid(uuid);
        return sysUserMapper.selectOne(sysUser);
    }

    @Override
    public int selectCount(SysUser sysUser) {
        return sysUserMapper.selectCount(sysUser);
    }

    @Override
    public List<SysUser> select(SysUser var1) {
        return sysUserMapper.select(var1);
    }

    @Override
    public SysUser selectOne(SysUser var1) {
        return sysUserMapper.selectOne(var1);
    }

    @Override
    public PageInfo<SysUser> queryPage(Integer pageNum, Integer pageSize, SysUser sysUser) {
        PageHelper.startPage(pageNum,pageSize);
        List<SysUser> shopList = sysUserMapper.select(sysUser);
        return new PageInfo<>(shopList);
    }
}
