package com.kingthy.platform.service.impl.manager;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kingthy.platform.dto.manager.*;
import com.kingthy.platform.entity.manager.PlatformMenu;
import com.kingthy.platform.entity.manager.PlatformUser;
import com.kingthy.platform.mapper.manager.PlatformMenuMapper;
import com.kingthy.platform.mapper.manager.PlatformUserMapper;
import com.kingthy.platform.service.manager.PlatformUserService;
import com.kingthy.platform.util.MD5Util;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 
 *
 * PlatFormUserImpl(支撑平台用户实现类)
 * 
 * 陈钊 2017年5月3日 下午8:18:11
 * 
 * @version 1.0.0
 *
 */
@Service("platformUserService")
public class PlatformUserServiceImpl implements PlatformUserService
{
    
    @Autowired
    private PlatformUserMapper platformUserMapper;
    
    @Autowired
    private HttpServletRequest request;
    
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    
    @Autowired
    private PlatformMenuMapper platformMenuMapper;
    
    private static final int version = 1;
    
    /**
     * 重置密码时的初始密码
     */
    private static final String basePassword = "111111";
    
    /**
     * 系统内置用户
     */
    private static final String initUser = "admin";
    
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
    public PageInfo<PlatformUserInfoDto> findUserByPage(PlatformUserPageReq platformUserPageReq)
    {
        PageHelper.startPage(platformUserPageReq.getPageNum(), platformUserPageReq.getPageSize());
        List<PlatformUserInfoDto> userList = platformUserMapper.findUserByPage(platformUserPageReq);
        PageInfo<PlatformUserInfoDto> page = new PageInfo<PlatformUserInfoDto>(userList);
        return page;
    }
    
    @Transactional
    @Override
    public int add(PlatformUserReq platformUserReq)
    {
        // 添加用户
        int result = 0;
        PlatformUser platformUser = new PlatformUser();
        BeanUtils.copyProperties(platformUserReq, platformUser);
        String salt = String.valueOf(System.currentTimeMillis());
        String password = MD5Util.MD5Encode(platformUser.getPassword() + salt);
        platformUser.setPassword(password);
        Date currentDate = new Date();
        platformUser.setCreateDate(currentDate);
        platformUser.setCreator(getCurrentUser());
        platformUser.setDelFlag(false);
        platformUser.setVersion(version);
        platformUser.setSalt(salt);
        result = platformUserMapper.insert(platformUser);
        // 分配角色
        PlatformUserEditReq platformUserEditReq = new PlatformUserEditReq();
        platformUserEditReq.setRoleUuid(platformUserReq.getRoleUuid());
        platformUserEditReq.setUuid(platformUser.getUuid());
        result = platformUserMapper.assignedRole(platformUserEditReq);
        return result;
    }
    
    @Override
    public int editBaseInfo(PlatformUserEditReq platformUserEditReq)
    {
        int result = 0;
        Example example = new Example(PlatformUser.class);
        Criteria criteria = example.createCriteria();
        criteria.andEqualTo("uuid", platformUserEditReq.getUuid());
        List<PlatformUser> userList = platformUserMapper.selectByExample(example);
        if (userList != null && userList.size() > 0)
        {
            PlatformUser platformUser = userList.get(0);
            platformUser.setEmail(platformUserEditReq.getEmail());
            platformUser.setModifier(getCurrentUser());
            platformUser.setModifyDate(new Date());
            platformUser.setOfficePhone(platformUserEditReq.getOfficePhone());
            platformUser.setPhone(platformUserEditReq.getPhone());
            platformUser.setUserName(platformUserEditReq.getUserName());
            result = platformUserMapper.updateByExample(platformUser, example);
        }
        return result;
    }
    
    @Override
    @Transactional
    public int delete(String uuid)
    {
        int result = 0;
        Example example = new Example(PlatformUser.class);
        Criteria criteria = example.createCriteria();
        criteria.andEqualTo("uuid", uuid);
        List<PlatformUser> userList = platformUserMapper.selectByExample(example);
        if (userList != null && userList.size() > 0)
        {
            // 删除用户的角色关联表信息
            platformUserMapper.deleteUserRoleByUserId(uuid);
            // 删除用户信息
            PlatformUser platformUser = userList.get(0);
            platformUser.setModifier(getCurrentUser());
            platformUser.setModifyDate(new Date());
            platformUser.setDelFlag(true);
            result = platformUserMapper.updateByExample(platformUser, example);
        }
        return result;
    }
    
    @Override
    public PlatformUserInfoDto findBaseInfoByUuid(String uuid)
    {
        return platformUserMapper.findBaseInfoByUuid(uuid);
    }
    
    @Override
    public PlatformUser findByUserName(String userName)
    {
        Example example = new Example(PlatformUser.class);
        Criteria criteria = example.createCriteria();
        criteria.andEqualTo("userName", userName);
        criteria.andEqualTo("delFlag", false);
        List<PlatformUser> userList = platformUserMapper.selectByExample(example);
        if (userList != null && userList.size() > 0)
        {
            return userList.get(0);
        }
        return null;
    }
    
    @Override
    public int findCountByName(String userName)
    {
        return platformUserMapper.findCountByName(userName);
    }
    
    @Override
    public String resetPassword(ResetPasswordReq resetPasswordReq)
    {
        int result = 0;
        String salt = String.valueOf(System.currentTimeMillis());
        String password = MD5Util.MD5Encode(basePassword + salt);
        resetPasswordReq.setPassword(password);
        resetPasswordReq.setSalt(salt);
        resetPasswordReq.setModifier(getCurrentUser());
        resetPasswordReq.setModifyDate(new Date());
        result = platformUserMapper.resetPassword(resetPasswordReq);
        if (result > 0)
        {
            return basePassword;
        }
        return null;
    }
    
    @Override
    public int editPassword(EditPasswordReq editPasswordReq)
    {
        ResetPasswordReq resetPasswordReq = new ResetPasswordReq();
        int result = 0;
        String salt = String.valueOf(System.currentTimeMillis());
        String password = MD5Util.MD5Encode(editPasswordReq.getNewPassword() + salt);
        resetPasswordReq.setPassword(password);
        resetPasswordReq.setSalt(salt);
        resetPasswordReq.setUuid(editPasswordReq.getUuid());
        resetPasswordReq.setModifier(getCurrentUser());
        resetPasswordReq.setModifyDate(new Date());
        result = platformUserMapper.resetPassword(resetPasswordReq);
        return result;
    }
    
    @Override
    public boolean checkPassword(String password, String uuid)
    {
        PlatformUser platformUser = platformUserMapper.findTotalInfoByUuid(uuid);
        String salt = platformUser.getSalt();
        String md5Password = MD5Util.MD5Encode(password + salt);
        if (md5Password.equals(platformUser.getPassword()))
        {
            return true;
        }
        return false;
    }
    
    @Override
    @Transactional
    public int adminEditUser(PlatformUserEditReq platformUserEditReq)
    {
        Example example = new Example(PlatformUser.class);
        Criteria criteria = example.createCriteria();
        criteria.andEqualTo("uuid", platformUserEditReq.getUuid());
        List<PlatformUser> userList = platformUserMapper.selectByExample(example);
        PlatformUser platformUser = userList.get(0);
        PlatformUserInfoDto platformUserInfoDto = platformUserMapper.findBaseInfoByUuid(platformUserEditReq.getUuid());
        String oldRoleUuid = platformUserInfoDto.getRoleUuid();
        String newRoleUuid = platformUserEditReq.getRoleUuid();
        // 角色改变
        if (!oldRoleUuid.equals(newRoleUuid))
        {
            reAssigned(platformUserEditReq);
        }
        // 编辑用户基础信息
        int result = 0;
        platformUser.setEmail(platformUserEditReq.getEmail());
        platformUser.setModifier(getCurrentUser());
        platformUser.setModifyDate(new Date());
        platformUser.setOfficePhone(platformUserEditReq.getOfficePhone());
        platformUser.setPhone(platformUserEditReq.getPhone());
        platformUser.setUserName(platformUserEditReq.getUserName());
        result = platformUserMapper.updateByExample(platformUser, example);
        return result;
    }
    
    /**
     * reAssigned(重新分配角色，并根据角色刷新缓存中的权限列表)
     * 
     * @param platformUserEditReq <b>创建人：</b>陈钊<br/>
     *            void
     * @exception @since 1.0.0
     */
    private void reAssigned(PlatformUserEditReq platformUserEditReq)
    {
        // 删除用户所对应的角色
        platformUserMapper.deleteUserRoleByUserId(platformUserEditReq.getUuid());
        // 给用户分配新的角色
        platformUserMapper.assignedRole(platformUserEditReq);
        // 刷新内存
        List<PlatformMenu> menuList = platformMenuMapper.findMenuByRoleUuid(platformUserEditReq.getRoleUuid());
        if (menuList != null)
        {
            String key = "platform:" + platformUserEditReq.getUuid();
            if (stringRedisTemplate.hasKey(key))
            {
                stringRedisTemplate.delete(key);
            }
            for (PlatformMenu platformMenu : menuList)
            {
                stringRedisTemplate.opsForSet().add(key, platformMenu.getUrl());
                stringRedisTemplate.expire(key,7, TimeUnit.DAYS);
            }
        }
    }
    
    @Override
    public boolean checkInitAdmin(String uuid)
    {
        Example example = new Example(PlatformUser.class);
        Criteria criteria = example.createCriteria();
        criteria.andEqualTo("uuid", uuid);
        List<PlatformUser> userList = platformUserMapper.selectByExample(example);
        PlatformUser platformUser = userList.get(0);
        // 如果被修改的用户为系统内置用户，则判断当前登录用户是否为内置用户，如果是本人，则通过，否则不通过
        if (initUser.equals(platformUser.getUserName()))
        {
            if (uuid.equals(getCurrentUser()))
            {
                return true;
            }
            else
            {
                return false;
            }
        }
        else
        {
            return true;
        }
    }
    
    @Override
    public boolean beforeAssigned(PlatformUserEditReq platformUserEditReq)
    {
        PlatformUserInfoDto platformUserInfoDto = platformUserMapper.findBaseInfoByUuid(platformUserEditReq.getUuid());
        String oldRoleUuid = platformUserInfoDto.getRoleUuid();
        String newRoleUuid = platformUserEditReq.getRoleUuid();
        // 角色改变则进行进一步判断
        if (!oldRoleUuid.equals(newRoleUuid))
        {
            Example example = new Example(PlatformUser.class);
            Criteria criteria = example.createCriteria();
            criteria.andEqualTo("uuid", platformUserEditReq.getUuid());
            List<PlatformUser> userList = platformUserMapper.selectByExample(example);
            PlatformUser platformUser = userList.get(0);
            // 如果被修改的用户是系统内置用户，则进行拦截
            if (initUser.equals(platformUser.getUserName()))
            {
                return true;
            }
            else
            { // 如果被修改的用户不是系统内置用户，则不拦截
                return false;
            }
            
        }
        else
        {// 角色未改变就不拦截
            return false;
        }
    }

    @Override
    public boolean isNameChange(String name, String uuid) {
        //检查用户名是否发生改变
        PlatformUserInfoDto platformUserInfoDto = platformUserMapper.findBaseInfoByUuid(uuid);
        if(!name.equals(platformUserInfoDto.getUserName())){
            return true;
        }
        return false;
    }
}
