package com.kingthy.platform.dto.order;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * 
 *
 * AuditingOrderStatusReq
 * 
 * 赵生辉 2017年4月14日 下午1:45:22
 * 
 * @version 1.0.0
 *
 */
@Setter
@Getter
@Component
public class AuditingOrderStatusReq implements Serializable
{
    
    private static final long serialVersionUID = 1L;
    
    /**
     * 详细原因
     */

    private String content;
    
    /**
     * 备注
     */
    private String remark;
    
    /**
     * 操作类型
     */
    private Integer type;
    
    /**
     * 订单业务id
     */
    @NotEmpty(message = "订单业务主键不能为空")
    private String orderUuid;
}
