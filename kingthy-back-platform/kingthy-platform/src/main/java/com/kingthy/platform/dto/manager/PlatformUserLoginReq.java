package com.kingthy.platform.dto.manager;

import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.NotEmpty;

@Data
@ToString
public class PlatformUserLoginReq
{
    @NotEmpty(message = "密码不能为空")
    private String password;
    
    @NotEmpty(message = "用户名不能为空")
    private String userName;
}
