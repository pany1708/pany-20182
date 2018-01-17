package com.kingthy.platform.service.manager;

import com.github.pagehelper.PageInfo;
import com.kingthy.platform.dto.manager.MenuAssignedReq;
import com.kingthy.platform.dto.manager.PlatformRolePageReq;
import com.kingthy.platform.dto.manager.PlatformRoleReq;
import com.kingthy.platform.entity.manager.PlatformRole;

import java.util.List;

/**
 * 
 *
 * PlatformRole(平台角色接口)
 * 
 * 陈钊 2017年5月4日 下午4:03:00
 * 
 * @version 1.0.0
 *
 */
public interface PlatformRoleService
{
    /**
     * 
     * findByPage(分页查询角色信息)
     * 
     * @param platformRolePageReq
     * @return <b>创建人：</b>陈钊<br/>
     *         PageInfo<PlatformRole>
     * @exception @since 1.0.0
     */
    PageInfo<PlatformRole> findByPage(PlatformRolePageReq platformRolePageReq);
    
    /**
     * 
     * edit(修改角色信息)
     * 
     * @param platformRoleReq
     * @return <b>创建人：</b>陈钊<br/>
     *         int
     * @exception @since 1.0.0
     */
    int edit(PlatformRoleReq platformRoleReq);
    
    /**
     * 
     * add(创建角色)
     * 
     * @param platformRoleReq
     * @return <b>创建人：</b>陈钊<br/>
     *         int
     * @exception @since 1.0.0
     */
    int add(PlatformRoleReq platformRoleReq);
    
    /**
     * 
     * delete(删除角色)
     * 
     * @param uuid
     * @return <b>创建人：</b>陈钊<br/>
     *         int
     * @exception @since 1.0.0
     */
    Integer delete(String uuid);
    
    /**
     * 
     * findByUuid(根据uuid查询角色信息)
     * 
     * @param uuid
     * @return <b>创建人：</b>陈钊<br/>
     *         PlatformRole
     * @exception @since 1.0.0
     */
    PlatformRole findByUuid(String uuid);
    
    /**
     * 
     * findRolesByUserName(根据用户名查询其所有角色)
     * 
     * @param userName
     * @return <b>创建人：</b>陈钊<br/>
     *         List<PlatformRole>
     * @exception @since 1.0.0
     */
    List<PlatformRole> findRolesByUserName(String userName);
    
    /**
     * 
     * assignedMenu(给角色分配权限)
     * 
     * @param menuAssignedReq
     * @return <b>创建人：</b>陈钊<br/>
     *         int
     * @exception @since 1.0.0
     */
    int assignedMenu(MenuAssignedReq menuAssignedReq);
    
    /**
     * 
     * findMenuByRoleUuid(根据角色uuid查询菜单)
     * 
     * @param roleUuid
     * @return <b>创建人：</b>陈钊<br/>
     *         List<String>
     * @exception @since 1.0.0
     */
    List<String> findMenuByRoleUuid(String roleUuid);
    
    /**
     * 
     * findCountByName(检查角色名是否重复)
     * 
     * @param name
     * @return <b>创建人：</b>陈钊<br/>
     *         int
     * @exception @since 1.0.0
     */
    int findCountByName(String name);
    
    /**
     * 
     * isSuperAdmin(判断角色是否为超级管理员)
     * 
     * @param roleUuid
     * @return <b>创建人：</b>陈钊<br/>
     *         boolean
     * @exception @since 1.0.0
     */
    boolean isSuperAdmin(String roleUuid);
    
    /**
     * 
     * findAll(查询所有角色信息)
     * 
     * @return <b>创建人：</b>陈钊<br/>
     *         List<PlatformRole>
     * @exception @since 1.0.0
     */
    List<PlatformRole> findAll();

    /**
     * isNameChanged(检查角色名是否改变)
     * @param name
     * @param uuid
     * @return
     */
    boolean isNameChanged(String name,String uuid);
}
