package com.kingthy.platform.entity.goods;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kingthy.platform.util.BaseTableFileds;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 
 *
 * Goods(商品实体类)
 * 
 * 陈钊 2017年4月11日 下午7:41:11
 * 
 * @version 1.0.0
 *
 */
@ToString
@Data
@EqualsAndHashCode(callSuper = true)
public class Goods extends BaseTableFileds
{
    
    private String goodsName;
    
    private String goodsFeature;
    
    private BigDecimal standardPrice;
    
    private String opusSn;
    
    private String desinger;
    
    private Integer returnPoint;
    
    private Integer putOnMethod;
    
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    private Date putOnTime;
    
    private Integer status;
    
    private String goodsCategoryUuid;
    
    private String goodsStyleUuid;
    
    private String goodsSeasonUuid;
    
    private String goodsDetails;
    
    private String opusUuid;
    
    private String goodsTags;
    
    private String goodsImage;
    
    private String partInfo;
    
    private String materielInfo;
    
    private String accessoriesInfo;
    
    private String cover;
    
    private static final long serialVersionUID = 1L;
    
}