package com.kingthy.dubbo.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kingthy.dubbo.basedata.service.SysMenuDubboService;
import com.kingthy.entity.SuitCategory;
import com.kingthy.entity.SysMenu;
import com.kingthy.mapper.SysMenuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 *
 * 系统菜单
 * @author zhaochen on 2017/9/8.
 */
@Service(version = "1.0.0",timeout = 3000)
public class SysMenuDubboServiceImpl  implements SysMenuDubboService{
    @Autowired
    private SysMenuMapper sysMenuMapper;

    @Override
    public int insert(SysMenu sysMenu) {
        return sysMenuMapper.insert(sysMenu);
    }

    @Override
    public int updateByUuid(SysMenu sysMenu) {
        SysMenu sysMenuResult = selectByUuid(sysMenu.getUuid());
        Integer currentVersion  = sysMenuResult.getVersion();
        Example example = new Example(SuitCategory.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("uuid",sysMenu.getUuid());
        criteria.andEqualTo("version",currentVersion);
        sysMenu.setVersion(currentVersion+1);
        return sysMenuMapper.updateByExample(sysMenu,example);
    }

    @Override
    public List<SysMenu> selectAll() {
        return sysMenuMapper.selectAll();
    }

    @Override
    public SysMenu selectByUuid(String uuid) {
        Example example = new Example(SysMenu.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("uuid",uuid);
        List<SysMenu> sysMenuList = sysMenuMapper.selectByExample(example);
        if (sysMenuList!=null && sysMenuList.size()>0){
            return sysMenuList.get(0);
        }
        return new SysMenu();
    }

    @Override
    public int selectCount(SysMenu sysMenu) {
        return sysMenuMapper.selectCount(sysMenu);
    }

    @Override
    public List<SysMenu> select(SysMenu var1) {
        return sysMenuMapper.select(var1);
    }

    @Override
    public SysMenu selectOne(SysMenu var1) {
        return sysMenuMapper.selectOne(var1);
    }

    @Override
    public PageInfo<SysMenu> queryPage(Integer pageNum, Integer pageSize, SysMenu sysMenu) {
        PageHelper.startPage(pageNum,pageSize);
        List<SysMenu> shopList = sysMenuMapper.select(sysMenu);
        return new PageInfo<>(shopList);
    }
}
