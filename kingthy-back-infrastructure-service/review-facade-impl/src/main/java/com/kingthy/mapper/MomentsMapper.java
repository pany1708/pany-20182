package com.kingthy.mapper;

import com.kingthy.dto.MomentDto;
import com.kingthy.entity.Moments;
import com.kingthy.request.QueryMomentPageReq;
import com.kingthy.util.MyMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author
 */
public interface MomentsMapper extends MyMapper<Moments>
{
    /**
     * 更新点赞
     * @param uuid
     * @param likes
     * @param comments
     * @return
     */
    int updateLikes(@Param("uuid")String uuid, @Param("likes") Integer likes, @Param("comments")Integer comments);

    /**
     * 查询评论
     * @param queryMomentPageReq
     * @return
     */
    List<MomentDto> selectMoment(@Param("queryMomentPageReq")QueryMomentPageReq queryMomentPageReq);
    

}
