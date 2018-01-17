package com.kingthy.service;


import com.kingthy.dto.UserDTO;
import com.kingthy.entity.Member;
import com.kingthy.response.WebApiResponse;


/**
 * 登录
 * @author py
 */
public interface LoginService {
    /**
     * 登录
     *
     * @param userDTO
     * @return
     */
    WebApiResponse toLogin(UserDTO userDTO);


    /**
     * 登出
     *
     * @param token
     * @return
     */
    WebApiResponse toLogout(String token);

    /**
     * 解锁
     *
     * @param phone
     * @return
     */
    int unlockUser(String phone);

    /**
     * 查询用户
     * @param loginName
     * @return
     */
    Member findUserByLoginName(String loginName);
}
