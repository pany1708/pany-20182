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
 * CreateTagCategoryReq
 * 
 * 赵生辉 2017年4月13日 下午8:18:04
 * 
 * @version 1.0.0
 *
 */
@Component
@ToString
@Getter
@Setter
public class CreateTagCategoryReq implements Serializable
{
    
    private static final long serialVersionUID = 1L;
    
    /*
     * 名称
     */
    @NotEmpty(message = "标签名不能为空")
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
