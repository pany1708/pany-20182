package com.kingthy.platform.dto.basedata;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * 
 *
 * PartsCategoryDto
 * 
 * 赵生辉 2017年4月13日 下午8:19:59
 * 
 * @version 1.0.0
 *
 */
@Component
@ToString
@Getter
@Setter
public class PartsCategoryDto implements Serializable
{
    private static final long serialVersionUID = 1L;
    
    private Integer id;
    
    /*
     * 业务主键
     */
    private String uuid;
    
    /*
     * 名称
     */
    private String name;
    
    /*
     * 状态
     */
    private Boolean status;
    
    /*
     * 父类主键
     */
    private String parentId;
    
    /*
     * 级别
     */
    private Integer grade;
}
