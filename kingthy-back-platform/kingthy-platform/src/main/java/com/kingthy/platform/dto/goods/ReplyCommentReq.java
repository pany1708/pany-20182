package com.kingthy.platform.dto.goods;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @AUTHORS xumin
 * @Description:
 * @DATE Created by 14:05 on 2017/7/7.
 * @Modified by:
 */
@Data
@ToString
public class ReplyCommentReq implements Serializable {

    private String content;

    private String buyersUuid;

    private String memberUuid;
}
