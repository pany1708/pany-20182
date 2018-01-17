package com.kingthy.platform.dto.manager;

import lombok.Data;
import lombok.ToString;

/**
 * 
 *
 * PlatformUserPageReq(支撑平台用户分页查询参数封装类)
 * 
 * 陈钊 2017年5月3日 下午8:30:13
 * 
 * @version 1.0.0
 *
 */
@Data
@ToString
public class PlatformUserPageReq
{
    
    private int pageSize;
    
    private int pageNum;
    
    private String userName;
}
