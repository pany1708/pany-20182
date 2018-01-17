/**
 * 系统项目名称
 * com.kingthy.platform.mapper.basedata
 * SeasonMapper.java
 * 
 * 2017年3月29日-下午3:58:28
 *  2017金昔科技有限公司-版权所有
 *
 */
package com.kingthy.platform.mapper.basedata;

import com.kingthy.platform.dto.basedata.SeasonCategoryDto;
import com.kingthy.platform.entity.basedata.SeasonCategory;
import com.kingthy.platform.util.MyMapper;

import java.util.List;
import java.util.Map;

/**
 *
 * SeasonMapper
 * 
 * yuanml 2017年3月29日 下午3:58:28
 * 
 * @version 1.0.0
 *
 */
public interface SeasonCategoryMapper extends MyMapper<SeasonCategory>
{
    
    /**
     * 得到上级层级数 (这里描述这个方法适用条件 – 可选)
     *
     * yuanml
     * 
     * @param parentId
     * @return Integer
     * @exception @since 1.0.0
     */
    Integer getGrade(Integer parentId);
    
    /**
     * 根据uuid更新状态信息 (这里描述这个方法适用条件 – 可选)
     *
     * yuanml
     * 
     * @param parameterMap
     * @return int
     * @exception @since 1.0.0
     */
    int updateSeasonCategoryStatus(Map<String, Object> parameterMap);
    
    /**
     * 根据上级节点更新下级节点状态 (这里描述这个方法适用条件 – 可选)
     *
     * yuanml
     * 
     * @param seasonCategory
     * @return int
     * @exception @since 1.0.0
     */
    int updateSeasonCategoryStatusByParentId(SeasonCategory seasonCategory);
    
    /**
     * 将所有子类下的节点上级节点改为新节点 (这里描述这个方法适用条件 – 可选)
     *
     * yuanml
     * 
     * @param parameterMap
     * @return int
     * @exception @since 1.0.0
     */
    int transferSeasonCategory(Map<String, Object> parameterMap);
    
    /**
     * 删除季节分类，修改del_flag状态 (这里描述这个方法适用条件 – 可选)
     *
     * yuanml
     * 
     * @param parameterMap
     * @return int
     * @exception @since 1.0.0
     */
    int deleteSeasonCategory(Map<String, Object> parameterMap);
    
    /**
     * 根据uuid查找所有此节点下的子节点 (这里描述这个方法适用条件 – 可选)
     *
     * yuanml
     * 
     * @param seasonCategoryUuid String
     * @return
     * @exception @since 1.0.0
     */
    String getSeasonCategoryChildUuid(String seasonCategoryUuid);
    
    /**
     * 批量更新uuid层级数 (这里描述这个方法适用条件 – 可选)
     *
     * yuanml
     * 
     * @param parameterMap
     * @return int
     * @exception @since 1.0.0
     */
    int updateSeasonCategoryGradeBatch(Map<String, Object> parameterMap);
    
    /**
     * 返回封装类，方便所有节点排序显示 (这里描述这个方法适用条件 – 可选)
     *
     * yuanml
     * 
     * @return List<SeasonCategoryDto>
     * @exception @since 1.0.0
     */
    List<SeasonCategoryDto> selectAllSeasoncategoryTree();
    
    /**
     * 查询出所有顶级节点信息 (这里描述这个方法适用条件 – 可选)
     *
     * yuanml
     * 
     * @return List<SeasonCategory>
     * @exception @since 1.0.0
     */
    List<SeasonCategory> findAllSeasoncategoryTop();
    
}
