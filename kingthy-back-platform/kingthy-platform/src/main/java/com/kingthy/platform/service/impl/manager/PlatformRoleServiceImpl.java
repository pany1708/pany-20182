package com.kingthy.platform.service.impl.manager;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kingthy.platform.dto.manager.MenuAssignedReq;
import com.kingthy.platform.dto.manager.PlatformRolePageReq;
import com.kingthy.platform.dto.manager.PlatformRoleReq;
import com.kingthy.platform.entity.manager.PlatformRole;
import com.kingthy.platform.mapper.manager.PlatformMenuMapper;
import com.kingthy.platform.mapper.manager.PlatformRoleMapper;
import com.kingthy.platform.service.manager.PlatformRoleService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 *
 * PlatformRoleServiceImpl(平台角色实现类)
 * 
 * 陈钊 2017年5月4日 下午4:21:38
 * 
 * @version 1.0.0
 *
 */
@Service("platformRoleService")
public class PlatformRoleServiceImpl implements PlatformRoleService
{
    
    private static final int version = 1;
    
    private static final String superAdmin = "超级管理员";
    
    @Autowired
    private PlatformRoleMapper platformRoleMapper;
    
    @Autowired
    private PlatformMenuMapper platformMenuMapper;
    
    @Autowired
    private HttpServletRequest request;
    
    /**
     * 
     * getCurrentUser(得到当前操作用户的uuid)
     * 
     * @return <b>创建人：</b>陈钊<br/>
     *         String
     * @exception @since 1.0.0
     */
    private String getCurrentUser()
    {
        String currentUser = request.getHeader("uuid");
        if (currentUser == null)
        {
            return "";
        }
        return currentUser;
    }
    
    @Override
    public PageInfo<PlatformRole> findByPage(PlatformRolePageReq platformRolePageReq)
    {
        PageHelper.startPage(platformRolePageReq.getPageNum(), platformRolePageReq.getPageSize());
        List<PlatformRole> roleList = platformRoleMapper.findByPage(platformRolePageReq);
        PageInfo<PlatformRole> pageInfo = new PageInfo<PlatformRole>(roleList);
        return pageInfo;
    }
    
    @Override
    public int edit(PlatformRoleReq platformRoleReq)
    {
        int result = 0;
        Example example = new Example(PlatformRole.class);
        Criteria criteria = example.createCriteria();
        criteria.andEqualTo("uuid", platformRoleReq.getUuid());
        List<PlatformRole> roleList = platformRoleMapper.selectByExample(example);
        if (roleList != null && roleList.size() > 0)
        {
            PlatformRole platformRole = roleList.get(0);
            platformRole.setModifier(getCurrentUser());
            platformRole.setModifyDate(new Date());
            platformRole.setRoleName(platformRoleReq.getRoleName());
            platformRole.setDescription(platformRoleReq.getDescription());
            result = platformRoleMapper.updateByExample(platformRole, example);
        }
        return result;
    }
    
    @Transactional
    @Override
    public int add(PlatformRoleReq platformRoleReq)
    {
        int result = 0;
        PlatformRole platformRole = new PlatformRole();
        BeanUtils.copyProperties(platformRoleReq, platformRole);
        platformRole.setCreateDate(new Date());
        platformRole.setCreator(getCurrentUser());
        platformRole.setVersion(version);
        platformRole.setDelFlag(false);
        result = platformRoleMapper.insert(platformRole);
        // 给角色添加初始权限，当前用户信息权限
        String roleUuid = platformRole.getUuid();
        result = platformRoleMapper.addInitPerminssion(roleUuid);
        return result;
    }
    
    @Override
    public Integer delete(String uuid)
    {
        // 删除前判断是否有用户属于本角色
        int count = platformRoleMapper.findRoleUsersCount(uuid);
        if (count == 0)
        {
            int result = 0;
            Example example = new Example(PlatformRole.class);
            Criteria criteria = example.createCriteria();
            criteria.andEqualTo("uuid", uuid);
            List<PlatformRole> roleList = platformRoleMapper.selectByExample(example);
            if (roleList != null && roleList.size() > 0)
            {
                PlatformRole platformRole = roleList.get(0);
                platformRole.setModifier(getCurrentUser());
                platformRole.setModifyDate(new Date());
                platformRole.setDelFlag(true);
                result = platformRoleMapper.updateByExample(platformRole, example);
            }
            return result;
        }
        else
        {
            return null;
        }
    }
    
    @Override
    public PlatformRole findByUuid(String uuid)
    {
        return platformRoleMapper.findByUuid(uuid);
    }
    
    @Override
    public List<PlatformRole> findRolesByUserName(String userName)
    {
        List<PlatformRole> roleList = platformRoleMapper.findRolesByUserName(userName);
        if (roleList != null && roleList.size() > 0)
        {
            return roleList;
        }
        return null;
    }
    
    @Override
    @Transactional
    public int assignedMenu(MenuAssignedReq menuAssignedReq)
    {
        // 删除角色所有权限
        platformRoleMapper.deleteMenuByRoleUuid(menuAssignedReq.getRoleUuid());
        // 分配新权限给对应角色
        int result = 0;
        String[] menusUrls = menuAssignedReq.getMenuArray();
        Map<String, Object> urlMap = new HashMap<String, Object>();
        urlMap.put("menusUrls", menusUrls);
        List<String> menuUuidList = platformMenuMapper.findMenuIdByUrl(urlMap);
        Map<String, Object> assigendMap = new HashMap<String, Object>();
        assigendMap.put("roleUuid", menuAssignedReq.getRoleUuid());
        assigendMap.put("menuUuidList", menuUuidList);
        result = platformRoleMapper.assigned(assigendMap);
        return result;
    }
    
    @Override
    public List<String> findMenuByRoleUuid(String roleUuid)
    {
        return platformRoleMapper.findMenuByRole(roleUuid);
    }
    
    @Override
    public int findCountByName(String name)
    {
        return platformRoleMapper.findCountByName(name);
    }
    
    @Override
    public boolean isSuperAdmin(String roleUuid)
    {
        Example example = new Example(PlatformRole.class);
        Criteria criteria = example.createCriteria();
        criteria.andEqualTo("uuid", roleUuid);
        List<PlatformRole> roleList = platformRoleMapper.selectByExample(example);
        PlatformRole platformRole = roleList.get(0);
        // 如果是超级管理员则返回true
        if (superAdmin.equals(platformRole.getRoleName()))
        {
            return true;
        }
        return false;
    }
    
    @Override
    public List<PlatformRole> findAll()
    {
        return platformRoleMapper.selectAll();
    }

    @Override
    public boolean isNameChanged(String name, String uuid) {
        Example example = new Example(PlatformRole.class);
        Criteria criteria = example.createCriteria();
        criteria.andEqualTo("uuid",uuid);
        List<PlatformRole> roleList = platformRoleMapper.selectByExample(example);
        if(roleList!=null && roleList.size()>0){
            PlatformRole platformRole = roleList.get(0);
            if (!name.equals(platformRole.getRoleName())){
                return true;
            }else{
                return false;
            }
        }
        return false;
    }

}
