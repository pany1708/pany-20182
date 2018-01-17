package com.kingthy.platform.dto.manager;

import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * 
 *
 * PlatformRoleReq(角色信息入参封装类)
 * 
 * 陈钊 2017年5月5日 上午9:46:01
 * 
 * @version 1.0.0
 *
 */
@ToString
@Data
public class PlatformRoleReq
{
    @NotEmpty(message = "角色名不能为空")
    private String roleName;
    
    private String description;
    
    private String uuid;
}
