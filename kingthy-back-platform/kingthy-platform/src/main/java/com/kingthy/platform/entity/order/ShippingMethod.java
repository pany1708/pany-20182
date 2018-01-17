package com.kingthy.platform.entity.order;

import com.kingthy.platform.util.BaseTableFileds;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * 
 *
 * ShippingMethod(购物配送方式实体bean)
 * 
 * 赵生辉 2017年4月13日 下午8:25:58
 * 
 * @version 1.0.0
 *
 */
@Component
@Getter
@Setter
public class ShippingMethod extends BaseTableFileds
{
    /**
     * 排序
     */
    private Integer orders;
    
    /**
     * 续重
     */
    private Integer continueWeight;
    
    /**
     * 默认续重价格
     */
    private BigDecimal defaultContinuePrice;
    
    /**
     * 默认首重价格
     */
    private BigDecimal defaultFirstPrice;
    
    /**
     * 描述
     */
    private String description;
    
    /**
     * 首重
     */
    private Integer firstWeight;
    
    /**
     * log图标
     */
    private String icon;
    
    /**
     * 名称
     */
    private String name;
    
    /**
     * 快递公司的业务主键
     */
    private String deliveryCorpUuid;

    /**
     * 状态
     */
    private Integer type;
    
}