package com.kingthy.platform.entity.manager;

import com.kingthy.platform.util.BaseTableFileds;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 
 *
 * PlatformRole(平台角色实体类)
 * 
 * 陈钊 2017年5月4日 下午4:04:18
 * 
 * @version 1.0.0
 *
 */
@ToString
@Data
@EqualsAndHashCode(callSuper = true)
public class PlatformRole extends BaseTableFileds
{
    
    private String roleName;
    
    private String description;
    
    private static final long serialVersionUID = 1L;
    
}