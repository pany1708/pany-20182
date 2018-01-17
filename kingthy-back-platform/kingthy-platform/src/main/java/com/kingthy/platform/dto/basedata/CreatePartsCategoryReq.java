package com.kingthy.platform.dto.basedata;

import groovy.transform.ToString;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * 
 * 
 * CreatePartsCategoryReq
 * 
 * 赵生辉 2017年4月13日 下午8:17:39
 * 
 * @version 1.0.0
 *
 */
@Component
@ToString
@Setter
@Getter
public class CreatePartsCategoryReq implements Serializable
{

    @ApiModelProperty("部件名称")
    private String className;

    private Boolean status;

    private String image;

    private Boolean delFlag;

    private String sn;

    private String type;

    private String modelFile;

    private static final long serialVersionUID = 1L;
    
}