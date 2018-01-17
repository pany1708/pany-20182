package com.kingthy.mapper;

import com.kingthy.dto.EventPublishUpdateDTO;
import com.kingthy.entity.EventPublish;
import com.kingthy.util.MyMapper;

import java.util.Map;

/**
 * @author  xumin
 * @Description:
 * @DATE Created by 14:46 on 2017/8/18.
 * @Modified by:
 */
public interface EventPublishMapper extends MyMapper<EventPublish>
{
    int updateBatchEventStatus(EventPublishUpdateDTO dto);
}
