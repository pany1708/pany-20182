package com.kingthy.platform.dto.manager;

import lombok.Data;
import lombok.ToString;

/**
 * 
 *
 * PlatformMenuReq(平台菜单信息入参封装类)
 * 
 * 陈钊 2017年5月5日 下午4:08:02
 * 
 * @version 1.0.0
 *
 */
@ToString
@Data
public class PlatformMenuReq
{
    private String menuName;
    
    private String url;
    
    private String description;
    
    private String uuid;
}
