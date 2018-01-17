/**
 * 系统项目名称
 * com.kingthy.platform.dto.opus
 * OpusDto.java
 * 
 * 2017年4月6日-上午10:17:12
 *  2017金昔科技有限公司-版权所有
 *
 */
package com.kingthy.platform.dto.opus;

import com.kingthy.platform.entity.basedata.PartsCategory;
import com.kingthy.platform.entity.material.Accessories;
import com.kingthy.platform.entity.material.Material;
import com.kingthy.platform.entity.opus.Opus;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 *
 * OpusDto
 * 
 * yuanml 2017年4月6日 上午10:17:12
 * 
 * @version 1.0.0
 *
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class OpusDto extends Opus
{
    
    private static final long serialVersionUID = 1L;
    
    private List<PartsCategory> opusPartList;

    private List<Accessories> opusAccessoriesList;

    private List<Material> opusMaterialList;
}
