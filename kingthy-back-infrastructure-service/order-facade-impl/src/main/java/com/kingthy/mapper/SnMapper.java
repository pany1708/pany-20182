package com.kingthy.mapper;

import com.kingthy.entity.Sn;
import com.kingthy.util.MyMapper;

/**
 * @author  xumin
 * @Description:
 * @DATE Created by 14:21 on 2017/8/8.
 * @Modified by:
 */
public interface SnMapper extends MyMapper<Sn> {

    int updateLastValue(Sn sn);
}
