package com.kingthy.platform.mapper.manager;

import com.kingthy.platform.dto.manager.PlatformRolePageReq;
import com.kingthy.platform.entity.manager.PlatformRole;
import com.kingthy.platform.util.MyMapper;

import java.util.List;
import java.util.Map;

public interface PlatformRoleMapper extends MyMapper<PlatformRole>
{
    /**
     * 
     * findByPage(分页查找角色)
     * 
     * @param platformRolePageReq
     * @return <b>创建人：</b>陈钊<br/>
     *         List<PlatformRole>
     * @exception @since 1.0.0
     */
    List<PlatformRole> findByPage(PlatformRolePageReq platformRolePageReq);
    
    /**
     * 
     * findByUuid(根据uuid查找菜单信息)
     * 
     * @param uuid
     * @return <b>创建人：</b>陈钊<br/>
     *         PlatformRole
     * @exception @since 1.0.0
     */
    PlatformRole findByUuid(String uuid);
    
    /**
     * 
     * findRolesByUserName(根据用户名查询其角色)
     * 
     * @param userName
     * @return <b>创建人：</b>陈钊<br/>
     *         List<PlatformRole>
     * @exception @since 1.0.0
     */
    List<PlatformRole> findRolesByUserName(String userName);
    
    /**
     * 
     * assigned(给角色分配权限)
     * 
     * @param assigendMap
     * @return <b>创建人：</b>陈钊<br/>
     *         int
     * @exception @since 1.0.0
     */
    int assigned(Map<String, Object> assigendMap);
    
    /**
     * 
     * findMenuByRole(根据角色uuid查询角色权限)
     * 
     * @param roleUuid
     * @return <b>创建人：</b>陈钊<br/>
     *         List<String>
     * @exception @since 1.0.0
     */
    List<String> findMenuByRole(String roleUuid);
    
    /**
     * 
     * deleteByRoleID(根据角色uuid删除其所有权限)
     * 
     * @param roleUuid
     * @return <b>创建人：</b>陈钊<br/>
     *         int
     * @exception @since 1.0.0
     */
    int deleteMenuByRoleUuid(String roleUuid);
    
    /**
     * 
     * findRoleUsersCount(根据角色uuid查询有多少用户属于此角色)
     * 
     * @param uuid
     * @return <b>创建人：</b>陈钊<br/>
     *         int
     * @exception @since 1.0.0
     */
    int findRoleUsersCount(String uuid);
    
    /**
     * 
     * findCountByName(根据角色名查找角色数量)
     * 
     * @param name
     * @return <b>创建人：</b>陈钊<br/>
     *         int
     * @exception @since 1.0.0
     */
    int findCountByName(String name);
    
    /**
     * 
     * addInitPerminssion(给角色分配初始权限--当前用户信息权限)
     * 
     * @param roleUuid
     * @return <b>创建人：</b>陈钊<br/>
     *         int
     * @exception @since 1.0.0
     */
    int addInitPerminssion(String roleUuid);
}