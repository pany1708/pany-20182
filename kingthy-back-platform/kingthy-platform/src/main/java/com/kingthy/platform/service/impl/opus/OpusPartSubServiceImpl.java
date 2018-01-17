/**
 * 系统项目名称
 * com.kingthy.platform.service.impl.opus
 * OpusPartSubImpl.java
 * 
 * 2017年4月7日-下午3:49:49
 *  2017金昔科技有限公司-版权所有
 *
 */
package com.kingthy.platform.service.impl.opus;

import com.kingthy.platform.dto.opus.OpusPartSubDto;
import com.kingthy.platform.mapper.opus.PartSubMapper;
import com.kingthy.platform.service.opus.OpusPartSubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 * OpusPartSubImpl
 * 
 * yuanml 2017年4月7日 下午3:49:49
 * 
 * @version 1.0.0
 *
 */
@Service("opusPartSubService")
public class OpusPartSubServiceImpl implements OpusPartSubService
{
    @Autowired
    private PartSubMapper partSubMapper;
    
    private Map<String, Object> parameterMap;
    
    @Override
    public List<OpusPartSubDto> findPartSub(List<String> partSubUuids)
    {
        return partSubMapper.selectByUuids(partSubUuids);
    }
    
    @Override
    public Map<?, ?> findPartSubMaterialAccessories(String partSubUuid, String table)
    {
        parameterMap = new ConcurrentHashMap<>();
        parameterMap.put("partSubUuid", partSubUuid);
        parameterMap.put("table", table);
        return partSubMapper.findPartSubMaterial(parameterMap);
    }
    
}
