package com.kingthy.mapper;

import com.kingthy.dto.MomentCommentDto;
import com.kingthy.entity.MomentComment;
import com.kingthy.util.MyMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author
 */
public interface MomentCommentMapper extends MyMapper<MomentComment>
{
    /**
     * 更新点赞
     * @param uuid
     * @param likes
     * @return
     */
    int updateLikes(@Param("uuid")String uuid, @Param("likes")Integer likes);

    /**
     * 查询动态
     * @param momentUuid
     * @param memberUuid
     * @return
     */
    List<MomentCommentDto> selectComment(@Param("momentUuid")String momentUuid, @Param("memberUuid")String memberUuid);
}
