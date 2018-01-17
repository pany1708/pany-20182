package com.kingthy.platform.mapper.manager;

import com.kingthy.platform.dto.manager.PlatformMenuPageReq;
import com.kingthy.platform.entity.manager.PlatformMenu;
import com.kingthy.platform.util.MyMapper;

import java.util.List;
import java.util.Map;

public interface PlatformMenuMapper extends MyMapper<PlatformMenu>
{
    /**
     * 
     * findByPage(分页查找菜单)
     * 
     * @param platformMenuPageReq
     * @return <b>创建人：</b>陈钊<br/>
     *         List<PlatformMenu>
     * @exception @since 1.0.0
     */
    List<PlatformMenu> findByPage(PlatformMenuPageReq platformMenuPageReq);
    
    /**
     * 
     * findByUuid(根据uuid查询菜单)
     * 
     * @param uuid
     * @return <b>创建人：</b>陈钊<br/>
     *         PlatformMenu
     * @exception @since 1.0.0
     */
    PlatformMenu findByUuid(String uuid);
    
    /**
     * 
     * findMenuByRoleUuid(根据roleUuid查询其所有菜单权限)
     * 
     * @param uuid
     * @return <b>创建人：</b>陈钊<br/>
     *         List<PlatformMenu>
     * @exception @since 1.0.0
     */
    List<PlatformMenu> findMenuByRoleUuid(String uuid);
    
    /**
     * 
     * findMenuIdByUrl(根据菜单链接查询菜单uuid)
     * 
     * @param map
     * @return <b>创建人：</b>陈钊<br/>
     *         List<String>
     * @exception @since 1.0.0
     */
    List<String> findMenuIdByUrl(Map<String, Object> map);
}