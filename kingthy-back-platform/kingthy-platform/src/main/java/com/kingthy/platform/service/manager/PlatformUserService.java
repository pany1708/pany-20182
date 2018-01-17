package com.kingthy.platform.service.manager;

import com.github.pagehelper.PageInfo;
import com.kingthy.platform.dto.manager.*;
import com.kingthy.platform.entity.manager.PlatformUser;

/**
 * 
 *
 * PlatFormUserService(运营支撑平台用户接口)
 * 
 * 陈钊 2017年5月3日 下午8:16:33
 * 
 * @version 1.0.0
 *
 */
public interface PlatformUserService
{
    /**
     * 
     * findUserByPage(分页查询用户信息)
     * 
     * @param platformUserPageReq
     * @return <b>创建人：</b>陈钊<br/>
     *         PageInfo<PlatformUserInfoDto>
     * @exception @since 1.0.0
     */
    PageInfo<PlatformUserInfoDto> findUserByPage(PlatformUserPageReq platformUserPageReq);
    
    /**
     * 
     * add(添加用户)
     * 
     * @param platformUserReq
     * @return <b>创建人：</b>陈钊<br/>
     *         int
     * @exception @since 1.0.0
     */
    int add(PlatformUserReq platformUserReq);
    
    /**
     * 
     * editBaseInfo(修改用户基本信息)
     * 
     * @param platformUserEditReq
     * @return <b>创建人：</b>陈钊<br/>
     *         int
     * @exception @since 1.0.0
     */
    int editBaseInfo(PlatformUserEditReq platformUserEditReq);
    
    /**
     * 
     * delete(删除用户)
     * 
     * @param uuid
     * @return <b>创建人：</b>陈钊<br/>
     *         int
     * @exception @since 1.0.0
     */
    int delete(String uuid);
    
    /**
     * 
     * findBaseInfoByUuid(根据uuid查询用户基本信息，包含角色uuid，角色名)
     * 
     * @param uuid
     * @return <b>创建人：</b>陈钊<br/>
     *         PlatformUserInfoDto
     * @exception @since 1.0.0
     */
    PlatformUserInfoDto findBaseInfoByUuid(String uuid);
    
    /**
     * 
     * findByUserName(根据用户名查询用户信息)
     * 
     * @param userName
     * @return <b>创建人：</b>陈钊<br/>
     *         PlatformUser
     * @exception @since 1.0.0
     */
    PlatformUser findByUserName(String userName);
    
    /**
     * 
     * findCountByName(根据用户名查询用户数量)
     * 
     * @param userName
     * @return <b>创建人：</b>陈钊<br/>
     *         int
     * @exception @since 1.0.0
     */
    int findCountByName(String userName);
    
    /**
     * 
     * resetPassword(重置用户密码)
     * 
     * @param resetPasswordReq
     * @return <b>创建人：</b>陈钊<br/>
     *         String
     * @exception @since 1.0.0
     */
    String resetPassword(ResetPasswordReq resetPasswordReq);
    
    /**
     * 
     * editPassword(修改密码)
     * 
     * @param editPasswordReq
     * @return <b>创建人：</b>陈钊<br/>
     *         int
     * @exception @since 1.0.0
     */
    int editPassword(EditPasswordReq editPasswordReq);
    
    /**
     * 
     * checkPassword(验证密码是否正确)
     * 
     * @param password
     * @param uuid
     * @return <b>创建人：</b>陈钊<br/>
     *         boolean
     * @exception @since 1.0.0
     */
    boolean checkPassword(String password, String uuid);
    
    /**
     * 
     * adminEditUser(修改用户信息，包括角色)
     * 
     * @param platformUserEditReq
     * @return <b>创建人：</b>陈钊<br/>
     *         int
     * @exception @since 1.0.0
     */
    int adminEditUser(PlatformUserEditReq platformUserEditReq);
    
    /**
     * 
     * checkInitAdmin(检查当前用户是否有权限操作系统内置用户)
     * 
     * @param uuid
     * @return <b>创建人：</b>陈钊<br/>
     *         boolean
     * @exception @since 1.0.0
     */
    boolean checkInitAdmin(String uuid);
    
    /**
     * 
     * beforeAssigned(检查角色是否更改)
     * 
     * @param platformUserEditReq
     * @return <b>创建人：</b>陈钊<br/>
     *         boolean
     * @exception @since 1.0.0
     */
    boolean beforeAssigned(PlatformUserEditReq platformUserEditReq);

    /**
     * isNameChange(检查用户名是否改变)
     * @param name
     * @param uuid
     * @return boolean
     */
    boolean isNameChange(String name,String uuid);
}
