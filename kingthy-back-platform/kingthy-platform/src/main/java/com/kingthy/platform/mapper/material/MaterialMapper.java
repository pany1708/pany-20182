package com.kingthy.platform.mapper.material;

import com.kingthy.platform.dto.material.MaterialDto;
import com.kingthy.platform.entity.material.Material;
import com.kingthy.platform.util.MyMapper;

import java.util.List;
import java.util.Map;

/**
 * 
 *
 * MaterialMapper
 * 
 * 赵生辉 2017年4月17日 下午3:35:11
 * 
 * @version 1.0.0
 *
 */
public interface MaterialMapper extends MyMapper<Material>
{

    /**
     * @desc 分页查找所有面料
     *
     * @author yuanml
     *
     * @param parameterMap
     * @return
     */
    List<MaterialDto> findAllByPage(Map<String, Object> parameterMap);

    /**
     * @desc 查找颜色
     *
     * @author yuanml
     *
     * @param rgb
     * @return
     */
    List<String> selectColor(String rgb);
}
