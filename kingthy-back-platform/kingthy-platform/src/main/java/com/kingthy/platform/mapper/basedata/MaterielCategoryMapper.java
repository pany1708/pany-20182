/**
 * 系统项目名称
 * com.kingthy.platform.mapper.basedata
 * MaterielMapper.java
 * 
 * 2017年3月29日-下午3:58:28
 *  2017金昔科技有限公司-版权所有
 *
 */
package com.kingthy.platform.mapper.basedata;

import com.kingthy.platform.dto.basedata.MaterielCategoryDto;
import com.kingthy.platform.entity.basedata.MaterielCategory;
import com.kingthy.platform.util.MyMapper;

import java.util.List;
import java.util.Map;

/**
 *
 * MaterielCategoryMapper
 * 
 * yuanml 2017年3月29日 下午3:58:28
 * 
 * @version 1.0.0
 *
 */
public interface MaterielCategoryMapper extends MyMapper<MaterielCategory>
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
    int updateMaterielCategoryStatus(Map<String, Object> parameterMap);
    
    /**
     * 根据上级节点更新下级节点状态 (这里描述这个方法适用条件 – 可选)
     *
     * yuanml
     * 
     * @param materielCategory
     * @return int
     * @exception @since 1.0.0
     */
    int updateMaterielCategoryStatusByParentId(MaterielCategory materielCategory);
    
    /**
     * 将所有子类下的节点上级节点改为新节点 (这里描述这个方法适用条件 – 可选)
     *
     * yuanml
     * 
     * @param parameterMap
     * @return int
     * @exception @since 1.0.0
     */
    int transferMaterielCategory(Map<String, Object> parameterMap);
    
    /**
     * 删除物料分类，修改del_flag状态 (这里描述这个方法适用条件 – 可选)
     *
     * yuanml
     * 
     * @param parameterMap
     * @return int
     * @exception @since 1.0.0
     */
    int deleteMaterielCategory(Map<String, Object> parameterMap);
    
    /**
     * 根据uuid查找所有此节点下的子节点 (这里描述这个方法适用条件 – 可选)
     *
     * yuanml
     * 
     * @param materielCategoryUuid String
     * @return
     * @exception @since 1.0.0
     */
    String getMaterielCategoryChildUuid(String materielCategoryUuid);
    
    /**
     * 批量更新uuid层级数 (这里描述这个方法适用条件 – 可选)
     *
     * yuanml
     * 
     * @param parameterMap
     * @return int
     * @exception @since 1.0.0
     */
    int updateMaterielCategoryGradeBatch(Map<String, Object> parameterMap);
    
    /**
     * 返回封装类，方便所有节点排序显示 (这里描述这个方法适用条件 – 可选)
     * 
     * yuanml
     * 
     * @return List<MaterielCategoryDto>
     * @exception @since 1.0.0
     */
    List<MaterielCategoryDto> selectAllMaterielcategoryTree();
    
    /**
     * 查询出所有顶级节点物料分类 (这里描述这个方法适用条件 – 可选)
     *
     * yuanml
     * 
     * @return List<MaterielCategory>
     * @exception @since 1.0.0
     */
    List<MaterielCategory> findAllMaterielcategoryTop();

    /**
     * 更新商品数量
     * @return
     */
    int updateGoodsNum();
    
}
