package com.kingthy.platform.dto.goods;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * ReviewUpdateReq
 * <p>
 * yuanml
 * 2017/5/16
 *
 * @version 1.0.0
 */
@Data
public class ReviewUpdateReq
{
    @ApiModelProperty(value = "操作数据UUID数组")
    private String reviewUuids;
    
    @ApiModelProperty(value = "显示隐藏，true显示")
    private Boolean status;
    
    @ApiModelProperty(value = "删除标识，true删除")
    private Boolean delFlag;
}
