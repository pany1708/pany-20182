package com.kingthy.mapper;

import com.kingthy.entity.Opus;
import com.kingthy.request.OpusSearchReq;
import com.kingthy.util.MyMapper;

import java.util.List;

/**
 * OpusMapper(描述其作用)
 * <p>
 * @author 赵生辉 2017年08月03日 11:11
 *
 * @version 1.0.0
 */
public interface OpusMapper extends MyMapper<Opus>
{
    /**
     * 分页查询作品
     * @param opusReq
     * @return
     */
    List<Opus> findByPage(OpusSearchReq opusReq);

}
