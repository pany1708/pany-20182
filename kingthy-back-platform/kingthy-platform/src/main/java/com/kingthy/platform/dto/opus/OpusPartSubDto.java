/**
 * 系统项目名称
 * com.kingthy.platform.dto.opus
 * OpusPartSubDto.java
 * 
 * 2017年4月18日-下午6:23:30
 *  2017金昔科技有限公司-版权所有
 *
 */
package com.kingthy.platform.dto.opus;

import com.kingthy.platform.entity.opus.OpusPartSub;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Map;

/**
 *
 * OpusPartSubDto
 * 
 * yuanml 2017年4月18日 下午6:23:30
 * 
 * @version 1.0.0
 *
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class OpusPartSubDto extends OpusPartSub
{
    
    private static final long serialVersionUID = 1L;
    
    private String partsubCategoryName;

    private Map<?, ?> opusPartSubMaterials;

    private Map<?, ?> opusPartSubAccessoriess;
}
