package com.kingthy.platform.service.member;

import com.github.pagehelper.PageInfo;
import com.kingthy.platform.dto.member.MemberPageReq;
import com.kingthy.platform.dto.member.MemberUuidArrayReq;
import com.kingthy.platform.entity.member.Member;

/**
 * 
 *
 * MemberService(会员业务层接口)
 * 
 * 陈钊 2017年4月14日 下午3:49:20
 * 
 * @version 1.0.0
 *
 */
public interface MemberService
{
    /**
     * 
     * findMemberByPage(分页查询用户信息)
     * 
     * @param memberPageReq
     * @return <b>创建人：</b>陈钊<br/>
     *         PageInfo<Member>
     * @exception @since 1.0.0
     */
    PageInfo<Member> findMemberByPage(MemberPageReq memberPageReq);

    /**
     * 查找用户信息
     * @param uuid
     * @return Member
     */
    Member findByUuid(String uuid);

    /**
     * 批量更新用户状态
     * @param uuidArrayReq
     * @return int
     */
    int changeIsLockedBatch(MemberUuidArrayReq uuidArrayReq);
}
