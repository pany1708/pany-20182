package com.kingthy.platform.entity.order;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.persistence.Transient;
import java.io.Serializable;
import java.util.Date;

/**
 * 
 *
 * Area(地区实体bean)
 * 
 * 赵生辉 2017年4月13日 下午8:25:15
 * 
 * @version 1.0.0
 *
 */
@Component
@Getter
@Setter
public class Area implements Serializable
{
    /**
     * 表主键
     */
    private Integer id;
    
    /**
     * 创建时间
     */
    private Date createDate;
    
    /**
     * 创建人
     */
    private String creator;
    
    /**
     * 最后修改时间
     */
    private Date modifyDate;
    
    /**
     * 最后修改人
     */
    private String modifier;
    
    /**
     * 版本
     */
    @Transient
    private Integer version;
    
    /**
     * 删除标识
     */
    private Boolean delFlag;
    
    /**
     * 排序
     */
    private Integer orders;
    
    /**
     * 全名
     */
    private String fullName;
    
    /**
     * 级别
     */
    private Integer grade;
    
    /**
     * 名称
     */
    private String name;
    
    /**
     * 树路径
     */
    private String treePath;
    
    /**
     * 上级地区id
     */
    private Integer areaParentId;
    
    private static final long serialVersionUID = 1L;
    
}