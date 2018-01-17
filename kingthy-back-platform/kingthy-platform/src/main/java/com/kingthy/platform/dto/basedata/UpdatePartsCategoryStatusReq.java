package com.kingthy.platform.dto.basedata;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import java.io.Serializable;

/**
 * 
 *
 * UpdatePartsCategoryStatusReq
 * 
 * 赵生辉 2017年4月13日 下午8:22:31
 * 
 * @version 1.0.0
 *
 */
@Data
public class UpdatePartsCategoryStatusReq implements Serializable
{
    private static final long serialVersionUID = 1L;
    /*
     * 业务主键
     */
    @NotEmpty(message = "业务主键不能为空")
    private String uuid;
    
    /*
     * 状态
     */
    private Boolean status;
}
