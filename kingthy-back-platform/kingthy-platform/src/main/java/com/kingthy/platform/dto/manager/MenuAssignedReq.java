package com.kingthy.platform.dto.manager;

import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * 
 *
 * MenuAssignedReq(权限分配入参封装)
 * 
 * 陈钊 2017年5月12日 上午11:43:57
 * 
 * @version 1.0.0
 *
 */
@Data
@ToString
public class MenuAssignedReq
{
    @NotEmpty(message = "角色uuid不能为空")
    private String roleUuid;
    
    private String[] menuArray;
}
