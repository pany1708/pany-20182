package com.kingthy.platform.mapper.material;

import com.kingthy.platform.dto.material.AccessoriesDto;
import com.kingthy.platform.entity.material.Accessories;
import com.kingthy.platform.util.MyMapper;

import java.util.List;
import java.util.Map;

/**
 * 
 *
 * AccessoriesMapper
 * 
 * 赵生辉 2017年4月17日 下午8:31:59
 * 
 * @version 1.0.0
 *
 */
public interface AccessoriesMapper extends MyMapper<Accessories>
{

    /**
     * @desc 分页查找所有辅料
     *
     * @author yuanml
     *
     * @param parameterMap
     * @return
     */
    List<AccessoriesDto> findAllByPage(Map parameterMap);
}
