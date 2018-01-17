package com.kingthy.platform.mapper.moments;

import com.kingthy.dto.MomentCommentDto;
import com.kingthy.entity.MomentComment;
import com.kingthy.platform.util.MyMapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * MomentCommentMapper(描述其作用)
 * <p>
 * 赵生辉 2017年05月18日 10:56
 *
 * @version 1.0.0
 */
public interface MomentCommentMapper extends MyMapper<MomentComment>
{
    int updateLikes(@Param("uuid") String uuid, @Param("likes") Integer likes);

    List<MomentCommentDto> selectComment(@Param("momentUuid") String momentUuid, @Param("memberUuid") String memberUuid);
}
