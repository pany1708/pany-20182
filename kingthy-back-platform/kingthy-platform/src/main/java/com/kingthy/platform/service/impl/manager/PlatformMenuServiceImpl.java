package com.kingthy.platform.service.impl.manager;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kingthy.platform.dto.manager.PlatformMenuPageReq;
import com.kingthy.platform.dto.manager.PlatformMenuReq;
import com.kingthy.platform.entity.manager.PlatformMenu;
import com.kingthy.platform.mapper.manager.PlatformMenuMapper;
import com.kingthy.platform.service.manager.PlatformMenuService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

import java.util.Date;
import java.util.List;

/**
 * 
 *
 * PlatformMenuServiceImpl(平台菜单业务层实现类)
 * 
 * 陈钊 2017年5月5日 下午3:04:15
 * 
 * @version 1.0.0
 *
 */
@Service("platformMenuService")
public class PlatformMenuServiceImpl implements PlatformMenuService
{
    private static final String modifier = "user";
    
    private static final String creator = "user";
    
    private static final int version = 1;
    
    @Autowired
    private PlatformMenuMapper platformMenuMapper;
    
    @Override
    public List<PlatformMenu> findAll()
    {
        return platformMenuMapper.selectAll();
    }
    
    @Override
    public PageInfo<PlatformMenu> findByPage(PlatformMenuPageReq platformMenuPageReq)
    {
        PageHelper.startPage(platformMenuPageReq.getPageNum(), platformMenuPageReq.getPageSize());
        List<PlatformMenu> menuList = platformMenuMapper.findByPage(platformMenuPageReq);
        PageInfo<PlatformMenu> page = new PageInfo<PlatformMenu>(menuList);
        return page;
    }
    
    @Override
    public int add(PlatformMenuReq platformMenuReq)
    {
        int result = 0;
        PlatformMenu platformMenu = new PlatformMenu();
        BeanUtils.copyProperties(platformMenuReq, platformMenu);
        platformMenu.setCreateDate(new Date());
        platformMenu.setCreator(creator);
        platformMenu.setDelFlag(false);
        platformMenu.setVersion(version);
        result = platformMenuMapper.insert(platformMenu);
        return result;
    }
    
    @Override
    public int edit(PlatformMenuReq platformMenuReq)
    {
        int result = 0;
        Example example = new Example(PlatformMenu.class);
        Criteria criteria = example.createCriteria();
        criteria.andEqualTo("uuid", platformMenuReq.getUuid());
        List<PlatformMenu> menuList = platformMenuMapper.selectByExample(example);
        if (menuList != null && menuList.size() > 0)
        {
            PlatformMenu platformMenu = menuList.get(0);
            platformMenu.setModifier(modifier);
            platformMenu.setModifyDate(new Date());
            platformMenu.setDescription(platformMenuReq.getDescription());
            platformMenu.setMenuName(platformMenuReq.getMenuName());
            platformMenu.setUrl(platformMenuReq.getUrl());
            result = platformMenuMapper.updateByExample(platformMenu, example);
        }
        return result;
    }
    
    @Override
    public int delete(String uuid)
    {
        int result = 0;
        Example example = new Example(PlatformMenu.class);
        Criteria criteria = example.createCriteria();
        criteria.andEqualTo("uuid", uuid);
        List<PlatformMenu> menuList = platformMenuMapper.selectByExample(example);
        if (menuList != null && menuList.size() > 0)
        {
            PlatformMenu platformMenu = menuList.get(0);
            platformMenu.setModifier(modifier);
            platformMenu.setModifyDate(new Date());
            platformMenu.setDelFlag(true);
            result = platformMenuMapper.updateByExample(platformMenu, example);
        }
        return result;
    }
    
    @Override
    public PlatformMenu findByUuid(String uuid)
    {
        return platformMenuMapper.findByUuid(uuid);
    }
    
    @Override
    public List<PlatformMenu> findMenuByRoleUuid(String uuid)
    {
        List<PlatformMenu> menuList = platformMenuMapper.findMenuByRoleUuid(uuid);
        if (menuList != null && menuList.size() > 0)
        {
            return menuList;
        }
        return null;
    }
    
}
