package com.kingthy.mapper;

import com.kingthy.dto.*;
import com.kingthy.entity.Member;
import com.kingthy.request.MemberBaseInfoReq;
import com.kingthy.request.MemberPageReq;
import com.kingthy.util.MyMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 会员mapper
 * @author likejie  2017/8/7.
 */
public interface MemberMapper extends MyMapper<Member> {

    /**
     * 更改昵称
     * @param uuid
     * @return
     */
    MemberDTO getMemberByUuid(String uuid);

    /**
     * 更改昵称
     * @param memberUuid
     * @return
     */
    MemberHomeDTO getHomeInfo(String memberUuid);

    /**
     * 更改昵称
     * @param uuid
     * @param name
     * @return
     */
    int updateName(@Param("uuid") String uuid, @Param("name") String name);

    /**
     * 检查昵称是否存在
     * @param uuid
     * @param name
     * @return
     */
    int checkNameIsExist(@Param("uuid") String uuid, @Param("name") String name);

    /**
     * 编辑签名
     * @param uuid
     * @param signature
     * @return
     */
    int updateSignature(@Param("uuid") String uuid, @Param("signature") String signature);

    /**
     * 编辑签名
     * @param phone
     * @return
     */
    int updateLocked(String phone);


    /**
     * 检查手机号是否存在
     * @param uuid
     * @param phone
     * @return
     */
    int checkPhoneIsExist(@Param("uuid") String uuid, @Param("phone") String phone);

    /**
     * 修改手机号
     * @param uuid
     * @param phone
     * @return
     */
    int modifyPhone(@Param("uuid") String uuid, @Param("phone") String phone);

    /**
     * 修改密码
     * @param uuid
     * @param password
     * @param salt
     * @return
     */
    int modifyPassword(@Param("uuid") String uuid, @Param("password") String password, @Param("salt") String salt);


    /**
     * 修改支付密码
     * @param uuid
     * @param paymentPassword
     * @param paymenSalt
     * @return
     */
    int modifyPaymentPassword(@Param("uuid") String uuid, @Param("paymentPassword") String paymentPassword, @Param("paymenSalt") String paymenSalt);

    /**
     * 根据uuid查询用户名，头像
     * @param list
     * @return List<CommentMemberDto>
     */
    List<CommentMemberDto> findMemberInfoByList(List<BuyersShowDTO> list);

    /**
     * 修改支付密码
     * @param memberUuid
     * @param designerUuid
     * @return
     */
    GoodsDTO.DesignerInfo selectDesignerInfoByDesignerUuid(@Param("memberUuid") String memberUuid, @Param("designerUuid") String designerUuid);

    /**
     * findMemberByPage(分页查询会员信息)
     *
     * @param memberPageReq
     * @return <b>创建人：</b>陈钊<br/>
     * List<Member>
     * @throws @since 1.0.0
     */
    List<Member> findMemberByPage(MemberPageReq memberPageReq);

    /**
     * changeIsEnableBatch(改变用户啟用状态)
     *
     * @param paramMap
     * @return <b>创建人：</b>陈钊<br/>
     * int
     * @throws @since 1.0.0
     */
    int changeIsEnableBatch(Map<String, Object> paramMap);

    /**
     * 编辑签名
     * @param userName
     * @return
     */
    List<String> selectMemberUuidsByName(@Param("userName") String userName);

    /**
     * 获取会员列表
     * @param list
     * @return
     */
    List<Member> selectMemberListByUuids(@Param("list") List<String> list);

    /**
     * 根据uuids获得用户基本信息
     * @param list
     * @return
     */
    List<MemberBaseInfoDTO> getMembersBaseInfo(@Param("list") List<String> list);
    /**
     * 更新会员登录信息
     * @param dto
     * @return
     */
    int updateMemberLoginInfo(UpdateLoginInfoDTO dto);


}
