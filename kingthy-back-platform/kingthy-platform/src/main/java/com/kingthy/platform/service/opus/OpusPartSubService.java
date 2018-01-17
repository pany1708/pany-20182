/**
 * 系统项目名称
 * com.kingthy.platform.service.opus
 * OpusPartSub.java
 * 
 * 2017年4月7日-下午3:49:17
 *  2017金昔科技有限公司-版权所有
 *
 */
package com.kingthy.platform.service.opus;

import com.kingthy.platform.dto.opus.OpusPartSubDto;

import java.util.List;
import java.util.Map;

/**
 *
 * OpusPartSub
 * 
 * yuanml 2017年4月7日 下午3:49:17
 * 
 * @version 1.0.0
 *
 */
public interface OpusPartSubService
{
    
    /**
     * 
     * 查询关联部件 (这里描述这个方法适用条件 – 可选)
     *
     * yuanml
     * 
     * @param partSubUuids
     * @return List<OpusPartSubDto>
     * @exception @since 1.0.0
     */
    List<OpusPartSubDto> findPartSub(List<String> partSubUuids);
    
    /**
     * 
     * 查找部件可选面料辅料 (这里描述这个方法适用条件 – 可选)
     *
     * yuanml
     * 
     * @param partSubUuid
     * @param table
     * @return Map<?,?>
     * @exception @since 1.0.0
     */
    Map<?, ?> findPartSubMaterialAccessories(String partSubUuid, String table);
    
}
