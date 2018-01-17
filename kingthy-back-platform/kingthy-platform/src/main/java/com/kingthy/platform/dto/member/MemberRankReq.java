/**
 * 系统项目名称
 * com.kingthy.platform.dto.member
 * MemberRankReq.java
 * 
 * 2017年4月18日-上午8:57:32
 *  2017金昔科技有限公司-版权所有
 *
 */
package com.kingthy.platform.dto.member;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.DecimalMin;
import java.math.BigDecimal;

/**
 *
 * MemberRankReq等级入参
 * 
 * yuanml 2017年4月18日 上午8:57:32
 * 
 * @version 1.0.0
 *
 */
@Data
public class MemberRankReq
{
    @DecimalMin(value = "0",message = "积分值必须大于零")
    private BigDecimal amountMin;

    @DecimalMin(value = "0",message = "积分值必须大于零")
    private BigDecimal amountMax;

    @NotEmpty(message = "等级名称不能为空")
    private String name;

    private String uuid;
}
