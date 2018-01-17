package com.kingthy.platform.dto.manager;

import lombok.Data;
import lombok.ToString;

/**
 * 
 *
 * PlatformRolePageReq(支撑平台角色分页查询参数封装类)
 * 
 * 陈钊 2017年5月4日 下午7:00:09
 * 
 * @version 1.0.0
 *
 */
@Data
@ToString
public class PlatformRolePageReq
{
    private int pageSize;
    
    private int pageNum;
    
    private String roleName;
}
