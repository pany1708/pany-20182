package com.kingthy.platform.dto.manager;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import java.util.Date;

@Data
public class ResetPasswordReq
{
    @NotEmpty(message = "用户uuid不能为空")
    private String uuid;
    
    private String password;
    
    private String salt;
    
    private String modifier;
    
    private Date modifyDate;
}
