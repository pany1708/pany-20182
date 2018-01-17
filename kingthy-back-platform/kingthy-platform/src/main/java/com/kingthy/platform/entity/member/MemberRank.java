/**
 * 系统项目名称
 * com.kingthy.platform.entity.member
 * MemberRank.java
 * 
 * 2017年4月17日-下午7:55:22
 *  2017金昔科技有限公司-版权所有
 *
 */
package com.kingthy.platform.entity.member;

import com.kingthy.platform.util.BaseTableFileds;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.math.BigDecimal;

/**
 *
 * MemberRank
 * 
 * yuanml 2017年4月17日 下午7:55:22
 * 
 * @version 1.0.0
 *
 */
@ToString
@Data
@EqualsAndHashCode(callSuper = true)
public class MemberRank extends BaseTableFileds
{
    
    private static final long serialVersionUID = 1L;
    
    private BigDecimal amountMin;
    
    private BigDecimal amountMax;
    
    private String name;
}
