package com.kingthy.platform.dto.material;

import com.kingthy.platform.entity.material.Material;
import lombok.Data;

/**
 * MaterialDto
 * <p>
 * yuanml
 * 2017/5/5
 *
 * @version 1.0.0
 */
@Data
public class MaterialDto extends Material
{

    /**
     * 查询返回面料分类名称
     */
    private String materielName;
}
