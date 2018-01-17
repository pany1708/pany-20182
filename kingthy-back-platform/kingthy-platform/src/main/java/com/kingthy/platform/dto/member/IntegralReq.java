package com.kingthy.platform.dto.member;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Min;

/**
 * 
 *
 * IntegralReq(积分设置添加、修改入参)
 * 
 * 陈钊 2017年4月18日 上午10:46:27
 * 
 * @version 1.0.0
 *
 */
@ToString
@Data
public class IntegralReq
{
    @NotEmpty(message = "积分来源不能为空")
    private String source;
    
    @Min(value = 0, message = "积分必须大于等于0")
    private Integer score;
    
    @NotEmpty(message = "积分描述不能为空")
    private String description;
    
    private String uuid;
    
    @ApiModelProperty("页码")
    private Integer pageNum;
    
    @ApiModelProperty("页数")
    private Integer pageSize;
}
