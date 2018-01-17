package com.kingthy.platform.dto.manager;

import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.NotEmpty;

@Data
@ToString
public class EditPasswordReq
{
    @NotEmpty(message = "原密码不能为空")
    private String oldPassword;
    
    @NotEmpty(message = "新密码不能为空")
    private String newPassword;
    
    @NotEmpty(message = "用户uuid不能为空")
    private String uuid;
}
