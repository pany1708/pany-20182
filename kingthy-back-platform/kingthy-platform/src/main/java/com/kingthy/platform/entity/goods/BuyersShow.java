package com.kingthy.platform.entity.goods;

import com.kingthy.platform.util.BaseTableFileds;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class BuyersShow extends BaseTableFileds
{
    
    private String orderSn;
    
    private String orderUuid;
    
    private String goodsUuid;
    
    private String memberName;
    
    private String memberUuid;
    
    private Boolean anonymousFlag;
    
    private String ip;
    
    private String content;
    
    private Integer version;
    
    private Boolean status;
    
    private static final long serialVersionUID = 1L;
    
}