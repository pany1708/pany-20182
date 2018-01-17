package com.kingthy.request;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * Created by kingthy on 2018/1/4.
 */
@Data
@ToString
public class SourceCategoryPageReq implements Serializable {

    private Integer type;

    private Integer pageNum;

    private Integer pageSize;
}
