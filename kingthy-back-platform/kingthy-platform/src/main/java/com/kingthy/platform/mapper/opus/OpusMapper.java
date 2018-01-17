/**
 * 系统项目名称
 * com.kingthy.platform.mapper.opus
 * OpusMapper.java
 * 
 * 2017年4月5日-下午4:01:04
 *  2017金昔科技有限公司-版权所有
 *
 */
package com.kingthy.platform.mapper.opus;

import com.kingthy.platform.dto.opus.OpusDto;
import com.kingthy.platform.dto.opus.OpusRelationsDto;
import com.kingthy.platform.entity.opus.Opus;
import com.kingthy.platform.util.MyMapper;

import java.util.List;
import java.util.Map;

/**
 *
 * OpusMapper
 * 
 * yuanml 2017年4月5日 下午4:01:04
 * 
 * @version 1.0.0
 *
 */
public interface OpusMapper extends MyMapper<Opus>
{
    
    /**
     * 查询作品标签 (这里描述这个方法适用条件 – 可选)
     *
     * yuanml
     * 
     * @param opusUuid
     * @return List<OpusRelationsDto>
     * @exception @since 1.0.0
     */
    List<OpusRelationsDto> findOpusTag(String opusUuid);
    
    /**
     * 作品列表搜索 (这里描述这个方法适用条件 – 可选)
     *
     * yuanml
     * 
     * @param opusDto
     * @return List<Opus>
     * @exception @since 1.0.0
     */
    List<OpusDto> selectByOpusDto(OpusDto opusDto);
    
    /**
     * 查询作品部件 (这里描述这个方法适用条件 – 可选)
     *
     * yuanml
     * 
     * @param opusUuid
     * @return List<String>
     * @exception @since 1.0.0
     */
    List<String> findOpusPart(String opusUuid);
    
    /**
     * 
     * 查询作品物料,面料，工艺 (这里描述这个方法适用条件 – 可选)
     *
     * yuanml
     * 
     * @param parameterMap
     * @return List<OpusRelationsDto>
     * @exception @since 1.0.0
     */
    List<OpusRelationsDto> findOpusRelations(Map<String, Object> parameterMap);
    
    /**
     * 移除作品部件，此方法只是删除中间表的记录 (这里描述这个方法适用条件 – 可选)
     *
     * yuanml
     * 
     * @param parameterMap
     * @return int
     * @exception @since 1.0.0
     */
    int deleteOpusPart(Map<String, Object> parameterMap);
    
    /**
     * 增加作品关联 (这里描述这个方法适用条件 – 可选)
     *
     * @param parameterMap
     * @return int
     * @exception @since 1.0.0
     */
    int addOpusRelations(Map<String, Object> parameterMap);
    
    /**
     * 删除作品关联(这里描述这个方法适用条件 – 可选)
     *
     * yuanml
     * 
     * @param parameterMap
     * @return int
     * @exception @since 1.0.0
     */
    int deleteOpusRelations(Map<String, Object> parameterMap);
    
    /**
     * 更新作品显示屏蔽状态 (这里描述这个方法适用条件 – 可选)
     *
     * yuanml
     * 
     * @param parameterMap
     * @return int
     * @exception @since 1.0.0
     */
    int updateOpusIsShow(Map<String, Object> parameterMap);
    
}
