package com.kingthy.platform.dto.basedata;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * 
 *
 * EditTagCategoryParam
 * 
 * 赵生辉 2017年4月13日 下午8:19:53
 * 
 * @version 1.0.0
 *
 */
@Component
@ToString
@Getter
@Setter
public class EditTagCategoryParam implements Serializable
{
    
    private static final long serialVersionUID = 1L;
    
    /*
     * 业务主键
     */
    @NotEmpty(message = "标签业务主键不能为空")
    private String uuid;
    
    /*
     * 名称
     */
    @NotEmpty(message = "标签名称不能为空")
    private String className;
    
    /*
     * 状态
     */
    private Boolean status;
    
    /*
     * 描述
     */
    private String description;
    
}