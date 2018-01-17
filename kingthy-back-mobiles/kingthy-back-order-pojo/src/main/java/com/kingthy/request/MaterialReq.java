package com.kingthy.request;

import com.kingthy.entity.Material;
import com.kingthy.entity.MaterialImages;
import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * @author xumin
 * @Description:
 * @DATE Created by 13:38 on 2018/1/9.
 * @Modified by:
 */
@Data
@ToString
public class MaterialReq implements java.io.Serializable
{
    private Material material;

    private List<MaterialImages> materialImages;

}
