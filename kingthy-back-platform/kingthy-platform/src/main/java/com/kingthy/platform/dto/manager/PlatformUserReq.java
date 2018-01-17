package com.kingthy.platform.dto.manager;

import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.NotEmpty;

@ToString
@Data
public class PlatformUserReq
{
    @NotEmpty(message = "用户名不能为空")
    private String userName;
    
    @NotEmpty(message = "密码不能为空")
    private String password;
    
    private String phone;
    
    private String officePhone;
    
    private String email;
    
    private String uuid;
    
    private String roleUuid;
}
