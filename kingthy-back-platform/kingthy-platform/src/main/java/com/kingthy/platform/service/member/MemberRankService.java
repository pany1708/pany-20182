/**
 * 系统项目名称
 * com.kingthy.platform.service.member
 * MemberRankService.java
 * 
 * 2017年4月17日-下午8:13:05
 *  2017金昔科技有限公司-版权所有
 *
 */
package com.kingthy.platform.service.member;

import com.kingthy.platform.dto.member.MemberRankReq;
import com.kingthy.platform.entity.member.MemberRank;

import java.util.List;

/**
 *
 * MemberRankService
 * 
 * yuanml 2017年4月17日 下午8:13:05
 * 
 * @version 1.0.0
 *
 */
public interface MemberRankService
{
    
    /**
     * 查询所有等级设置 (这里描述这个方法适用条件 – 可选)
     *
     * yuanml
     * 
     * @return List<MemberRank>
     * @exception @since 1.0.0
     */
    List<MemberRank> findMemberRank();
    
    /**
     * addMemberRank(增加等级列表参数) (这里描述这个方法适用条件 – 可选)
     *
     * yuanml
     * 
     * @param memberRankReq
     * @return int
     * @exception @since 1.0.0
     */
    int addMemberRank(MemberRankReq memberRankReq);
    
    /**
     * editMemberRank(根据uuid修改等级设置) (这里描述这个方法适用条件 – 可选)
     *
     * yuanml
     * 
     * @param memberRankReq
     * @return int
     * @exception @since 1.0.0
     */
    int editMemberRank(MemberRankReq memberRankReq);
    
    /**
     * deleteMemberRank(根据uuid软删除等级数据) (这里描述这个方法适用条件 – 可选)
     *
     * yuanml
     * 
     * @param memberRankUuid
     * @return int
     * @exception @since 1.0.0
     */
    int deleteMemberRank(String memberRankUuid);
    
    /**
     * findOneMemberRank(查询单个等级列表) (这里描述这个方法适用条件 – 可选)
     *
     * yuanml
     * 
     * @param memberRankUuid
     * @return MemberRank
     * @exception @since 1.0.0
     */
    MemberRank findOneMemberRank(String memberRankUuid);
    
}
