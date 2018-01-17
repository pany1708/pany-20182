package com.kingthy.platform.entity.order;

import com.kingthy.platform.util.BaseTableFileds;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

/**
 * 
 *
 * OrderLog(订单记录实体bean)
 * 
 * 赵生辉 2017年4月13日 下午8:25:39
 * 
 * @version 1.0.0
 *
 */
@Component
@Getter
@Setter
public class OrderLog extends BaseTableFileds
{
    
    /**
     * 详细原因
     */
    private String content;
    
    /**
     * 备注
     */
    private String remark;
    
    /**
     * 操作人
     */
    private String operator;
    
    /**
     * 操作类型
     */
    private Integer type;
    
    /**
     * 订单业务id
     */
    private String orderUuid;

    /**
     * 订单号
     */
    private String orderSn;
    
}