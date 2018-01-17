package com.kingthy.platform.mapper.basedata;

import com.kingthy.platform.dto.basedata.AreaDto;
import com.kingthy.platform.entity.basedata.Area;
import com.kingthy.platform.util.MyMapper;
import java.util.List;

/**
 * AreaMapper(描述其作用)
 * <p>
 * 赵生辉 2017年07月06日 14:39
 *
 * @version 1.0.0
 */
public interface AreaMapper extends MyMapper<Area>
{

    List<AreaDto> selectArea();
}
