/**
 * 系统项目名称
 * com.kingthy.platform.mapper.opus
 * PartSubMapper.java
 * 
 * 2017年4月7日-下午4:18:34
 *  2017金昔科技有限公司-版权所有
 *
 */
package com.kingthy.platform.mapper.opus;

import com.kingthy.platform.dto.opus.OpusPartSubDto;
import com.kingthy.platform.entity.opus.OpusPartSub;
import com.kingthy.platform.util.MyMapper;
import tk.mybatis.mapper.common.MySqlMapper;

import java.util.List;
import java.util.Map;

/**
 *
 * PartSubMapper
 * 
 * yuanml 2017年4月7日 下午4:18:34
 * 
 * @version 1.0.0
 *
 */
public interface PartSubMapper extends MyMapper<OpusPartSub>, MySqlMapper<OpusPartSub>
{
    
    /**
     * 查询部件可选面料 (这里描述这个方法适用条件 – 可选)
     *
     * yuanml
     * 
     * @param parameterMap
     * @return Map<?, ?>
     * @exception @since 1.0.0
     */
    Map<?, ?> findPartSubMaterial(Map<String, Object> parameterMap);
    
    /**
     * selectByUuid(根据uuid集查询部件信息，返回结果带出部件关联信息) (这里描述这个方法适用条件 – 可选)
     *
     * yuanml
     * 
     * @param partSubUuids
     * @return List<OpusPartSubDto>
     * @exception @since 1.0.0
     */
    List<OpusPartSubDto> selectByUuids(List<String> partSubUuids);
    
}
