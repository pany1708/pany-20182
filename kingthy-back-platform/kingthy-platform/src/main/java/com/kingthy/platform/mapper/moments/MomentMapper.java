package com.kingthy.platform.mapper.moments;


import com.kingthy.platform.dto.moments.MomentDto;
import com.kingthy.platform.entity.moments.Moments;
import com.kingthy.request.QueryMomentPageReq;
import com.kingthy.platform.util.MyMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * moment(描述其作用)
 * <p>
 * 赵生辉 2017年05月18日 10:48
 *
 * @version 1.0.0
 */
public interface MomentMapper extends MyMapper<Moments>
{

    int updateLikes(@Param("uuid") String uuid, @Param("likes") Integer likes, @Param("comments") Integer comments);

    List<MomentDto> selectMoment(@Param("queryMomentPageReq") QueryMomentPageReq queryMomentPageReq);
}
