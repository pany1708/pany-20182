package com.kingthy.platform.entity.goods;

import com.kingthy.platform.util.BaseTableFileds;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class BuyersShowImg extends BaseTableFileds
{
    
    private String buyersUuid;
    
    private String imgUrl;
    
    private static final long serialVersionUID = 1L;
    
}