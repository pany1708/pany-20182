package com.kingthy.platform.dto.goods;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * ReviewReq
 * <p>
 * yuanml
 * 2017/5/16
 *
 * @version 1.0.0
 */
@Data
public class ReviewReq
{
    @ApiModelProperty(value = "页码")
    private Integer pageNum;
    
    @ApiModelProperty(value = "每页数量")
    private Integer pageSize;
    
    @ApiModelProperty(value = "评论内容")
    private String content;

    @ApiModelProperty(value = "商品名称")
    private String goodsName;

    @ApiModelProperty("状态")
    private String status;

    @ApiModelProperty("用户名")
    private String memberName;

    private Long beginDate;

    private Long endDate;

    @JsonIgnore
    private String reviewUuid;
}
