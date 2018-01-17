package com.kingthy.platform.mapper.member;

import com.kingthy.platform.dto.member.MemberPageReq;
import com.kingthy.platform.entity.member.Member;
import com.kingthy.platform.util.MyMapper;

import java.util.List;
import java.util.Map;

/**
 * 
 *
 * MemberMapper(会员映射接口)
 * 
 * 陈钊 2017年4月14日 下午2:28:32
 * 
 * @version 1.0.0
 *
 */
public interface MemberMapper extends MyMapper<Member>
{
    /**
     * 
     * findMemberByPage(分页查询会员信息)
     * 
     * @param memberPageReq
     * @return <b>创建人：</b>陈钊<br/>
     *         List<Member>
     * @exception @since 1.0.0
     */
    List<Member> findMemberByPage(MemberPageReq memberPageReq);

    /**
     *
     * changeIsLocked(改变用户冻结状态)
     *
     * @param paramMap
     * @return <b>创建人：</b>陈钊<br/>
     *         int
     * @exception @since 1.0.0
     */
    int changeIsLockedBatch(Map<String, Object> paramMap);

    /**
     * findMemberRank(根据会员积分查询会员等级) (这里描述这个方法适用条件 – 可选)
     *
     * yuanml
     *
     * @param member
     * @return String
     * @exception @since 1.0.0
     */
    String findMemberRank(Member member);

    String selectMemberName(String uuid);

}