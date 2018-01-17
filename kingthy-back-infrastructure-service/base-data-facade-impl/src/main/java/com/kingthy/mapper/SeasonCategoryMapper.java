/**
 * 系统项目名称
 * com.kingthy.platform.mapper.basedata
 * SeasonMapper.java
 * 
 * 2017年3月29日-下午3:58:28
 *  2017金昔科技有限公司-版权所有
 *
 */
package com.kingthy.mapper;


import com.kingthy.entity.SeasonCategory;
import com.kingthy.util.MyMapper;

import java.util.List;

/**
 *
 * SeasonMapper
 * 
 * @author zhaochen 2017年3月29日 下午3:58:28
 * 
 * @version 1.0.0
 *
 */
public interface SeasonCategoryMapper extends MyMapper<SeasonCategory>
{
    /**
     * 分页查询季节信息
     * @param seasonCategory
     * @return
     */
    List<SeasonCategory> fingByPage(SeasonCategory seasonCategory);
}
