/**
 * 系统项目名称
 * com.kingthy.platform.entity.member
 * Integral.java
 * 
 * 2017年4月17日-下午7:54:05
 *  2017金昔科技有限公司-版权所有
 *
 */
package com.kingthy.platform.entity.member;

import com.kingthy.platform.util.BaseTableFileds;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 *
 * Integral
 * 
 * yuanml 2017年4月17日 下午7:54:05
 * 
 * @version 1.0.0
 *
 */
@ToString
@Data
@EqualsAndHashCode(callSuper = true)
public class Integral extends BaseTableFileds
{
    private static final long serialVersionUID = 1L;
    
    private String source;
    
    private Integer score;
    
    private String description;
}
