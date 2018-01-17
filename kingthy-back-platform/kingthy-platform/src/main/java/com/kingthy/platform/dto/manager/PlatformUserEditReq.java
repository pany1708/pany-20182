package com.kingthy.platform.dto.manager;

import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * 
 *
 * PlatformUserEditReq(修改用户信息入参)
 * 
 * 陈钊 2017年5月16日 下午3:07:22
 * 
 * @version 1.0.0
 *
 */
@ToString
@Data
public class PlatformUserEditReq
{
    @NotEmpty(message = "用户名不能为空")
    private String userName;
    
    private String phone;
    
    private String officePhone;
    
    private String email;
    
    private String uuid;
    
    private String roleUuid;
}
