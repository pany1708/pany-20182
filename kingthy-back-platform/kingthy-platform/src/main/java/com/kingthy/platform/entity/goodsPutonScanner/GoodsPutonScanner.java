package com.kingthy.platform.entity.goodsPutonScanner;

import com.kingthy.platform.util.BaseTableFileds;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Date;

/**
 * 
 *
 * GoodsPutonScanner(商品扫描表实体类)
 * 
 * 陈钊 2017年4月11日 下午7:41:25
 * 
 * @version 1.0.0
 *
 */
@ToString
@Data
@EqualsAndHashCode(callSuper = true)
public class GoodsPutonScanner extends BaseTableFileds
{
    
    /**
     * serialVersionUID:TODO（用一句话描述这个变量表示什么）
     *
     * @since 1.0.0
     */
    
    private static final long serialVersionUID = 1L;
    
    private String goodsUuid;
    
    private Date goodsPutOnTime;
    
    private Boolean status;
    
}