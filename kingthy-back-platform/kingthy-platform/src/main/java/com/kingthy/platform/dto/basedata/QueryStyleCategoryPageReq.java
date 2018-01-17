package com.kingthy.platform.dto.basedata;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * QueryStyleCategoryPageReq(描述其作用)
 * <p>
 * 赵生辉 2017年07月13日 10:29
 *
 * @version 1.0.0
 */
@Data
@ToString
public class QueryStyleCategoryPageReq implements Serializable
{
    private int pageNum;

    private int pageSize;

    private String className;

    private Boolean status;

    private String description;

    private static final long serialVersionUID = 1L;
}
