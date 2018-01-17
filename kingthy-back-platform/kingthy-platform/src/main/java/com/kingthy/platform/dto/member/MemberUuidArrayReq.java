package com.kingthy.platform.dto.member;

import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 
 *
 * UuidArray(接收uuid的数组封装类)
 * 
 * 陈钊 2017年4月6日 下午3:54:56
 * 
 * @version 1.0.0
 *
 */
@Data
@ToString
public class MemberUuidArrayReq implements Serializable
{
    
    /**
     * serialVersionUID:TODO（用一句话描述这个变量表示什么）
     *
     * @since 1.0.0
     */
    
    private static final long serialVersionUID = 1L;
    
    /**
     * 定义数组来接收uuid
     */
    @NotEmpty(message = "用户uuid不能为空")
    private String[] array;
    
    /**
     * 冻结标志
     */
    @NotNull(message = "冻结状态不能为空")
    private Boolean isLocked;
    
}
