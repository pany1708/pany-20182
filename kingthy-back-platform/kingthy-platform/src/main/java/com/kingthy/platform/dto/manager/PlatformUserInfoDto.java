package com.kingthy.platform.dto.manager;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class PlatformUserInfoDto
{
    private String uuid;
    
    private String userName;
    
    private String phone;
    
    private String officePhone;
    
    private String email;
    
    private String roleName;
    
    private String roleUuid;
    
}
