package com.kingthy.platform.entity.manager;

import com.kingthy.platform.util.BaseTableFileds;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString
@Data
@EqualsAndHashCode(callSuper = true)
public class PlatformMenu extends BaseTableFileds
{
    
    private String menuName;
    
    private String url;
    
    private String description;
    
    private static final long serialVersionUID = 1L;
    
}