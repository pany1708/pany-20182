/**
 * 系统项目名称
 * com.kingthy.platform.dto.member
 * MemberDto.java
 * 
 * 2017年4月19日-上午9:24:31
 *  2017金昔科技有限公司-版权所有
 *
 */
package com.kingthy.platform.dto.member;

import com.kingthy.platform.entity.member.Member;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 *
 * MemberDto
 * 
 * yuanml 2017年4月19日 上午9:24:31
 * 
 * @version 1.0.0
 *
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class MemberDto extends Member
{
    
    private static final long serialVersionUID = 1L;
    
    /**
     * 会员等级
     */
    private String memberRank;
}
