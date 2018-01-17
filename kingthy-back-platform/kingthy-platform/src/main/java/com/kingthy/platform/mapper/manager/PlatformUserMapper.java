package com.kingthy.platform.mapper.manager;

import com.kingthy.platform.dto.manager.PlatformUserEditReq;
import com.kingthy.platform.dto.manager.PlatformUserInfoDto;
import com.kingthy.platform.dto.manager.PlatformUserPageReq;
import com.kingthy.platform.dto.manager.ResetPasswordReq;
import com.kingthy.platform.entity.manager.PlatformUser;
import com.kingthy.platform.util.MyMapper;

import java.util.List;

public interface PlatformUserMapper extends MyMapper<PlatformUser>
{
    /**
     * 
     * findUserByPage(分页查询用户信息)
     * 
     * @param platformUserPageReq
     * @return <b>创建人：</b>陈钊<br/>
     *         List<PlatformUserInfoDto>
     * @exception @since 1.0.0
     */
    List<PlatformUserInfoDto> findUserByPage(PlatformUserPageReq platformUserPageReq);
    
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
     * findCountByName(根据用户名查询用户个数)
     * 
     * @param userName
     * @return <b>创建人：</b>陈钊<br/>
     *         int
     * @exception @since 1.0.0
     */
    int findCountByName(String userName);
    
    /**
     * 
     * assignedRole(给用户分配角色)
     * 
     * @param platformUserEditReq
     * @return <b>创建人：</b>陈钊<br/>
     *         int
     * @exception @since 1.0.0
     */
    int assignedRole(PlatformUserEditReq platformUserEditReq);
    
    /**
     * 
     * resetPassword(重置用户密码)
     * 
     * @param resetPasswordReq
     * @return <b>创建人：</b>陈钊<br/>
     *         int
     * @exception @since 1.0.0
     */
    int resetPassword(ResetPasswordReq resetPasswordReq);
    
    /**
     * 
     * findTotalInfoByUuid(根据用户uuid查询用户表信息)
     * 
     * @param uuid
     * @return <b>创建人：</b>陈钊<br/>
     *         PlatformUser
     * @exception @since 1.0.0
     */
    PlatformUser findTotalInfoByUuid(String uuid);
    
    /**
     * 
     * deleteUserRoleByUserId(根据用户uuid删除其所有关联角色)
     * 
     * @param uuid
     * @return <b>创建人：</b>陈钊<br/>
     *         int
     * @exception @since 1.0.0
     */
    int deleteUserRoleByUserId(String uuid);
}