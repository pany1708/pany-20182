package com.kingthy.mapper;

import com.kingthy.entity.BaseColourInfo;
import com.kingthy.util.MyMapper;

/**
 *  @author zhaochen
 */
public interface BaseColourInfoMapper extends MyMapper<BaseColourInfo> {
    /**
     *  根据颜色编码查询数量
     * @param code
     * @return
     */
    int selectColourCountByCode(String code);
}