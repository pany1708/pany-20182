package com.kingthy.platform.dto.basedata;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * 
 *
 * EditPartsCategoryReq
 * 
 * 赵生辉 2017年4月13日 下午8:19:45
 * 
 * @version 1.0.0
 *
 */
@Component
@ToString
@Getter
@Setter
public class EditPartsCategoryReq implements Serializable
{
    
    private static final long serialVersionUID = 1L;
    
    /*
     * 业务主键
     */
    @NotEmpty(message = "业务主键不能为空")
    private String uuid;

    @ApiModelProperty
    private String className;

    private Boolean status;

    private String image;

    private String type;

    private String modelFile;
}
