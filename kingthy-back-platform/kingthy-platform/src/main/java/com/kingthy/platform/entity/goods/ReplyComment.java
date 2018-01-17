package com.kingthy.platform.entity.goods;

import com.kingthy.platform.util.BaseTableFileds;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
public class ReplyComment extends BaseTableFileds implements Serializable {

    private String buyersUuid;

    private String content;

    private static final long serialVersionUID = 1L;

}