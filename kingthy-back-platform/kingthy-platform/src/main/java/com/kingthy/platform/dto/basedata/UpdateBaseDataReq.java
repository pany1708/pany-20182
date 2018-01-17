package com.kingthy.platform.dto.basedata;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * BaseDataReq(描述其作用)
 * <p>
 * 赵生辉 2017年05月11日 11:17
 *
 * @version 1.0.0
 */
@Data
@ToString
public class UpdateBaseDataReq implements Serializable
{
    private static final long serialVersionUID = 1L;

    private String uuid;

    private String className;

    private String description;

    private Boolean status;

    private String grade;

    private String parentId;

}
