package com.kingthy.dubbo.user.service;

import com.github.pagehelper.PageInfo;
import com.kingthy.base.BaseService;
import com.kingthy.dto.*;
import com.kingthy.entity.Member;
import com.kingthy.request.MemberBaseInfoReq;
import com.kingthy.request.MemberPageReq;
import com.kingthy.request.MemberUuidArrayReq;

import java.util.ArrayList;
import java.util.List;

/**
 * 会员服务接口
 * @author likejie
 * @date  2017/8/7.
 */
public interface MemberDubboService extends BaseService<Member> {

    /**
     * 根据UUID查询会员信息
     * @param uuid
     * @return
     */
    MemberDTO getMemberByUuid(String uuid);
    /**
     * 根据UUID查询会员主页
     * @param memberUuid
     * @return
     */
    MemberHomeDTO getHomeInfo(String memberUuid);
    /**
     * 根据会员名称查询会员uuid集合
     * @param name
     * @return
     */
    List<String> selectMemberUuidsByName(String name);
    /**
     * 根据会员名称查询会员列表
     * @param name
     * @return
     */
    List<Member> selectMemberListByName(String name);
    /**
     * 更改昵称
     * @param uuid
     * @param nickName
     * @return
     */
    int updateName(String uuid, String nickName);

    /**
     * 检查昵称是否存在
     * @param uuid
     * @param nickName
     * @return
     */
    int checkNameIsExist(String uuid, String nickName);

    /**
     *
     * 编辑签名
     * @param uuid
     * @param signature
     * @return
     */
    int updateSignature(String uuid, String signature);


    /**
     *
     * 账号解锁
     * @param phone
     * @return
     */
    int updateLocked(String phone);



    /**
     *
     * 检查手机号是否存在
     * @param uuid
     * @param phone
     * @return
     */
    int checkPhoneIsExist(String uuid, String phone);

    /**
     *
     * 修改手机号
     * @param uuid
     * @param phone
     * @return
     */
    int modifyPhone(String uuid, String phone);

    /**
     *
     * 修改密码
     * @param uuid
     * @param password
     * @param salt
     * @return
     */
    int modifyPassword(String uuid, String password, String salt);


    /**
     *
     * 修改支付密码
     * @param uuid
     * @param paymentPassword
     * @param paymenSalt
     * @return
     */
    int modifyPaymentPassword(String uuid, String paymentPassword, String paymenSalt);

    /**
     * 根据uuid查询用户名，头像
     *
     * @param list
     * @return List<CommentMemberDto>
     */
    ArrayList<CommentMemberDto> findMemberInfoByList(List<BuyersShowDTO> list);
    /**
     * 修改支付密码
     * @param designerUuid
     * @param memberUuid
     * @return
     */
    GoodsDTO.DesignerInfo selectDesignerInfoByDesignerUuid(String memberUuid, String designerUuid);
    /**
     * 修改支付密码
     * @param memberPageReq
     * @return
     */
    PageInfo<Member> findMemberByPage(MemberPageReq memberPageReq);
    /**
     * 修改支付密码
     * @param uuidArrayReq
     * @return
     */
    int changeIsEnableBatch(MemberUuidArrayReq uuidArrayReq);
    /**
     * 修改支付密码
     * @param uuid
     * @return
     */
    Member findByUuid(String uuid);
    /**
     *
     * 修改支付密码
     * @param uuids
     * @return
     */
    List<Member> selectMemberListByUuids(List<String> uuids);

    /**
     * 根据uuids获得用户基本信息
     * @param memberBaseInfoReq
     * @return
     */
    List<MemberBaseInfoDTO> getMembersBaseInfo(MemberBaseInfoReq memberBaseInfoReq);

    /**
     * 更新会员登录信息
     * @param dto
     * @return
     */
    int updateMemberLoginInfo(UpdateLoginInfoDTO dto);
}
