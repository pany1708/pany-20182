package com.kingthy.entity;

import com.kingthy.common.BaseTableFileds;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author xumin
 * @Description:
 * @DATE Created by 9:45 on 2018/1/5.
 * @Modified by:
 */
@Data
@ToString
public class AccessoriesImages extends BaseTableFileds implements Serializable
{
    private static final long serialVersionUID = 1L;

    private String accessoriesUuid;
    private String imagePath;
    private String filePath;
    private String colourSn;
    private Integer colourId;
    private String colourRgb;
    private Integer colourIdx;
    private Integer status;//状态0:禁用 1:启用
}
