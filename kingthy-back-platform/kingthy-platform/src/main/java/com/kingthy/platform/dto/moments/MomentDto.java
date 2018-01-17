package com.kingthy.platform.dto.moments;

import com.kingthy.platform.entity.moments.Moments;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * MomentDto(描述其作用)
 * <p>
 * 赵生辉 2017年06月09日 14:02
 *
 * @version 1.0.0
 */
@Data
@ToString
public class MomentDto extends Moments implements Serializable
{
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(name = "是否点赞")
    private String isLike;

    @ApiModelProperty(name = "是否关注")
    private String isAttention;

    @ApiModelProperty(name = "是否可以删除")
    private String isDel;
}
