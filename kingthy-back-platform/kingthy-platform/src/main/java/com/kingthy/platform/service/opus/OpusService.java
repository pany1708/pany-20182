/**
 * 系统项目名称
 * com.kingthy.platform.service.opus
 * OpusService.java
 * 
 * 2017年4月5日-下午4:03:46
 *  2017金昔科技有限公司-版权所有
 *
 */
package com.kingthy.platform.service.opus;

import com.github.pagehelper.PageInfo;
import com.kingthy.platform.dto.opus.*;
import com.kingthy.platform.entity.opus.Opus;

import java.util.List;

/**
 *
 * OpusService
 * 
 * yuanml 2017年4月5日 下午4:03:46
 * 
 * @version 1.0.0
 *
 */
public interface OpusService
{
    
    /**
     * 
     * 根据条件查询作品 (这里描述这个方法适用条件 – 可选)
     *
     * yuanml
     * 
     * @param opusReq
     * @return PageInfo<Opus>
     * @exception @since 1.0.0
     */
    PageInfo<Opus> findOpus(OpusSearchReq opusReq);
    
    /**
     * 新建作品 (这里描述这个方法适用条件 – 可选)
     *
     * yuanml
     * 
     * @param opusInsertReq
     * @return int
     * @exception @since 1.0.0
     */
    int create(OpusInsertReq opusInsertReq);
    
    /**
     * 显示/屏蔽作品 (这里描述这个方法适用条件 – 可选) yuanml
     * 
     * @param opusUuid
     * @param isShow
     * @return int
     * @exception @since 1.0.0
     */
    int isShowOpus(String opusUuid, Boolean isShow);
    
    /**
     * 显示作品详情 (这里描述这个方法适用条件 – 可选)
     *
     * yuanml
     * 
     * @param opusUuid
     * @return OpusDto
     * @exception @since 1.0.0
     */
    OpusDto findOpusOne(String opusUuid);
    
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
     * 查询作品部件 (这里描述这个方法适用条件 – 可选)
     *
     * yuanml
     * 
     * @param opusUuid
     * @return List<OpusPartSubDto>
     */
    List<OpusPartSubDto> findOpusPart(String opusUuid);
    
    /**
     * 
     * 查询作品物料,面料，工艺 (这里描述这个方法适用条件 – 可选)
     *
     * yuanml
     * 
     * @param opusUuid
     * @param tableName
     * @return List<OpusRelationsDto>
     * @exception @since 1.0.0
     */
    List<OpusRelationsDto> findOpusRelations(String opusUuid, String tableName);
    
    /**
     * 新增作品关联 (这里描述这个方法适用条件 – 可选)
     *
     * yuanml
     * 
     * @param opusRelationsReq
     * @param relationTableName 关联表名
     * @return int
     * @exception @since 1.0.0
     */
    int addOpusRelations(OpusRelationsReq opusRelationsReq, String relationTableName);
    
    /**
     * 删除作品关联 (这里描述这个方法适用条件 – 可选)
     *
     * yuanml
     * 
     * @param opusRelationsReq
     * @param relationTableName 关联表名
     * @return int
     * @exception @since 1.0.0
     */
    int deleteOpusRelations(OpusRelationsReq opusRelationsReq, String relationTableName);
    
    /**
     * 更新作品详情 (这里描述这个方法适用条件 – 可选)
     *
     * yuanml
     * 
     * @param opusInsertReq
     * @return int
     * @exception @since 1.0.0
     */
    int updateOpus(OpusInsertReq opusInsertReq);
}
