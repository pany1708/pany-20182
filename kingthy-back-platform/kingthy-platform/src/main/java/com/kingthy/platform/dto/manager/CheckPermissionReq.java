package com.kingthy.platform.dto.manager;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class CheckPermissionReq
{
    private String pageName;
    
    private String uuid;
}
