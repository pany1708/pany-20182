package com.kingthy.platform.entity.basedata;

import com.kingthy.platform.util.BaseTableFileds;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

/**
 * 
 *
 * TagCategory(标签分类实体bean)
 * 
 * 赵生辉 2017年4月13日 下午8:24:01
 * 
 * @version 1.0.0
 *
 */
@Component
@Getter
@Setter
public class TagCategory extends BaseTableFileds
{
    
    /*
     * 名称
     */
    private String className;
    
    /*
     * 状态
     */
    private Boolean status;
    
    /*
     * 描述
     */
    private String description;
    
    /*
     * 父类主键
     */
    private String parentId;
    
    /*
     * 排序
     */
    private Integer orders;
    
    /*
     * 级别
     */
    private Integer grade;
    
    /*
     * 树路径
     */
    private String treePath;
    
}