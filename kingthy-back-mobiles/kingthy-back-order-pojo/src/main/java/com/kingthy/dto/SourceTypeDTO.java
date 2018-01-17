package com.kingthy.dto;

import lombok.Data;
import lombok.ToString;

/**
 * @author xumin
 * @Description:
 * @DATE Created by 15:08 on 2018/1/8.
 * @Modified by:
 */
@Data
@ToString
public class SourceTypeDTO implements java.io.Serializable
{
    private String name;
    private String uuid;
    private String parentId;
}
