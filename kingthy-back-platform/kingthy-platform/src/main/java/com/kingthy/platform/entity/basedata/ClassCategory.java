package com.kingthy.platform.entity.basedata;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kingthy.platform.util.BaseTableFileds;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 
 *
 * ClassCategory(品类实体类)
 * 
 * 陈钊 2017年4月11日 下午7:40:33
 * 
 * @version 1.0.0
 *
 */
@ToString
@Data
@EqualsAndHashCode(callSuper = true)
public class ClassCategory extends BaseTableFileds
{
    @JsonIgnore
    private Integer orders;
    
    private Integer grade;
    
    private String parentId;
    
    private String className;
    
    @JsonIgnore
    private String treePath;
    
    private Boolean status;
    
    private String description;

    private Integer goodsNum;
    
    private static final long serialVersionUID = 1L;
    
}