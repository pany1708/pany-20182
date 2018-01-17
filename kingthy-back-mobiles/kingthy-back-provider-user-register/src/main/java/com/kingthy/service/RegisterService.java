package com.kingthy.service;

import com.github.pagehelper.PageInfo;
import com.kingthy.dto.UserRegDTO;
import com.kingthy.entity.Member;
import com.kingthy.response.WebApiResponse;

/**
 * 登录
 * @author py
 */
public interface RegisterService
{
    /**
     * 用户注册接口 createUser(这里用一句话描述这个方法的作用) (这里描述这个方法适用条件 – 可选)
     * 
     * @param userDTO
     * @return int
     * @exception @since 1.0.0
     */
    WebApiResponse<?> createUser(UserRegDTO userDTO);
    
    /**
     * 更新用户信息 updateUserPwd(这里用一句话描述这个方法的作用) (这里描述这个方法适用条件 – 可选)
     * 
     * @param userDTO
     * @return int
     * @exception @since 1.0.0
     */
    int updateUserPwd(UserRegDTO userDTO);
    /**
     * 获取验证码
     * @param  phone
     * @return
     */
    String getVerificationCode(String phone);

}
