package com.kingthy.platform.entity.opus;

import com.kingthy.platform.util.BaseTableFileds;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 
 *
 * OpusPartSub(部件实体类)
 * 
 * yuanml 2017年4月11日 下午7:43:01
 * 
 * @version 1.0.0
 *
 */
@ToString
@Data
@EqualsAndHashCode(callSuper = true)
public class OpusPartSub extends BaseTableFileds
{
    
    private static final long serialVersionUID = 1L;
    
    private String sn;
    
    private String name;
    
    private String partsubCategoryUuid;
    
    private Integer partsubStatus;
    
    private String imagePath;
    
}