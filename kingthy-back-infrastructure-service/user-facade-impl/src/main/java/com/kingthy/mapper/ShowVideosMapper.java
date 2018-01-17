package com.kingthy.mapper;

import com.kingthy.entity.ShowVideos;
import com.kingthy.util.MyMapper;

import java.util.List;

/**
 *
 * @author   likejie
 * @date 2017/9/11.
 */
public interface ShowVideosMapper  extends MyMapper<ShowVideos> {

    /**
     * 查询
     * 
     * @param showVideos
     * @return
     */
    List<ShowVideos> query(ShowVideos showVideos);
}
