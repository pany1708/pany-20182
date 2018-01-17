package com.kingthy.platform.dto.manager;

import lombok.Data;
import lombok.ToString;

/**
 * 
 *
 * PlatformMenuPageReq(支撑平台菜单分页查询参数封装类)
 * 
 * 陈钊 2017年5月5日 下午3:12:41
 * 
 * @version 1.0.0
 *
 */
@Data
@ToString
public class PlatformMenuPageReq
{
    private int pageSize;
    
    private int pageNum;
    
    private String menuName;
}
