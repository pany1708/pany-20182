package com.kingthy.platform.service.manager;

import com.github.pagehelper.PageInfo;
import com.kingthy.platform.dto.manager.PlatformMenuPageReq;
import com.kingthy.platform.dto.manager.PlatformMenuReq;
import com.kingthy.platform.entity.manager.PlatformMenu;

import java.util.List;

/**
 * 
 *
 * PlatformMenuService(平台菜单业务层接口)
 * 
 * 陈钊 2017年5月5日 下午3:00:01
 * 
 * @version 1.0.0
 *
 */
public interface PlatformMenuService
{
    /**
     * 
     * findAll(查询全部)
     * 
     * @return <b>创建人：</b>陈钊<br/>
     *         List<PlatformMenu>
     * @exception @since 1.0.0
     */
    List<PlatformMenu> findAll();
    
    /**
     * 
     * findByPage(分页查询菜单)
     * 
     * @param platformMenuPageReq
     * @return <b>创建人：</b>陈钊<br/>
     *         PageInfo<PlatformMenu>
     * @exception @since 1.0.0
     */
    PageInfo<PlatformMenu> findByPage(PlatformMenuPageReq platformMenuPageReq);
    
    /**
     * 
     * add(新增菜单)
     * 
     * @param platformMenuReq
     * @return <b>创建人：</b>陈钊<br/>
     *         int
     * @exception @since 1.0.0
     */
    int add(PlatformMenuReq platformMenuReq);
    
    /**
     * 
     * edit(编辑菜单)
     * 
     * @param platformMenuReq
     * @return <b>创建人：</b>陈钊<br/>
     *         int
     * @exception @since 1.0.0
     */
    int edit(PlatformMenuReq platformMenuReq);
    
    /**
     * 
     * delete(删除菜单)
     * 
     * @param uuid
     * @return <b>创建人：</b>陈钊<br/>
     *         int
     * @exception @since 1.0.0
     */
    int delete(String uuid);
    
    /**
     * 
     * findByUuid(查询菜单信息)
     * 
     * @param uuid
     * @return <b>创建人：</b>陈钊<br/>
     *         PlatformMenu
     * @exception @since 1.0.0
     */
    PlatformMenu findByUuid(String uuid);
    
    /**
     * 
     * findMenuByRoleUuid(根据角色uuid查询其所有菜单权限)
     * 
     * @param uuid
     * @return <b>创建人：</b>陈钊<br/>
     *         List<PlatformMenu>
     * @exception @since 1.0.0
     */
    List<PlatformMenu> findMenuByRoleUuid(String uuid);
}
