package com.kingthy.platform.entity.order.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

/**
 * 
 *
 * FindOrderPageReq
 * 
 * 赵生辉 2017年4月14日 下午4:31:28
 * 
 * @version 1.0.0
 *
 */
@Component
@Setter
@Getter
public class FindOrderPageReq
{
    /**
     * 当前页数
     */
    @NotNull(message = "当前页数不能为空")
    private Integer pageNum;
    
    /**
     * 每页展示的数量
     */
    @NotNull(message = "每页数量不能为空")
    private Integer pageSize;
    
    /**
     * 订单序列号
     */
    private String sn;
    
    /**
     * 订单状态
     */
    private Integer status;
    
    /**
     * 收货人姓名
     */
    private String consignee;
}
