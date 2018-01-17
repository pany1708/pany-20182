package com.kingthy.platform.entity.manager;

import com.kingthy.platform.util.BaseTableFileds;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 
 *
 * PlatformUser(支撑平台用户实体类)
 * 
 * 陈钊 2017年5月3日 下午8:29:52
 * 
 * @version 1.0.0
 *
 */
@ToString
@Data
@EqualsAndHashCode(callSuper = true)
public class PlatformUser extends BaseTableFileds
{
    
    private String userName;
    
    private String password;
    
    private String phone;
    
    private String officePhone;
    
    private String email;
    
    private String salt;
    
    private static final long serialVersionUID = 1L;
}