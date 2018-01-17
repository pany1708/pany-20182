/**
 * 系统项目名称
 * com.kingthy.service
 * MemberService.java
 * 
 * 2017年4月18日-下午5:45:45
 *  2017金昔科技有限公司-版权所有
 *
 */
package com.kingthy.service;

import com.kingthy.dto.*;
import com.kingthy.request.MemberBaseInfoReq;
import com.kingthy.response.WebApiResponse;

import java.util.List;

/**
 *
 * 会员业务逻辑接口
 * 
 * @author 李克杰 2017年4月18日 下午5:45:45
 * 
 * @version 1.0.0
 *
 */
public interface MemberService
{
    /**
     * 更新会员
     * @param member
     * @return
     */
    int updateMember(MemberDTO member);
    /**
     * 获取会员信息
     * @param uuid
     * @param uuid
     * @return
     */
    MemberDTO getMemberByUuId(String uuid);
    
    /**
     * 修改昵称
     * @param uuid
     * @param nickName
     * @return
     */
    WebApiResponse<?> updateName(String uuid, String nickName);

    /**
     * 解除锁定用户
     * @param phone
     * @return
     * @throws Exception
     */
    WebApiResponse<?> unlocked(String phone) throws Exception;
    
    /**
     *
     *修改手机号
     * @param memberUuid
     * @param phone
     * @return
     */
    WebApiResponse<?>  modifyMobile(String memberUuid,String phone);


    /**
     *
     * 修改密码
     * @param uuid
     * @param password
     * @return
     */
    WebApiResponse<?> modifyPassword( String uuid,  String password);


    /**
     *
     * 修改支付密码
     * @param membereUuid
     * @param paymentPassword
     * @return
     */
    WebApiResponse<?> modifyPaymentPassword(String membereUuid,String paymentPassword);

    /**
     *
     * 验证支付密码
     * @param memberUuid
     * @param paymentPassword
     * @return
     */
    WebApiResponse<?> verifyPaymentPassword(String memberUuid, String paymentPassword);

    /**
     *
     * 验证登录密码
     * @param memberUuid
     * @param password
     * @return
     */
    WebApiResponse<?> verifyLoginPassword(String memberUuid,String password);

    /**
     * 根据uuids获得用户基本信息
     * @param memberBaseInfoReq
     * @return
     */
    List<MemberBaseInfoDTO> getMembersBaseInfo(MemberBaseInfoReq memberBaseInfoReq);
}
