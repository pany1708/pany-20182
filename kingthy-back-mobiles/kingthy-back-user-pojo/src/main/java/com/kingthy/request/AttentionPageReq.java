package com.kingthy.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * 描述：----------
 *
 * @author likejie
 * @date 2017/12/4
 */
@Data
@ToString
public class AttentionPageReq implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonIgnore
    @ApiModelProperty("会员业务主键")
    private String memberUuid;

    @ApiModelProperty("关注对象的uuid")
    private String attentionUuid;

    private Integer pageSize;

    private Integer pageNum;
}
