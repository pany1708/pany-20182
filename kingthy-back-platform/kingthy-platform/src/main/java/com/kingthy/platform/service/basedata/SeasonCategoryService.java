/**
 * 系统项目名称
 * com.kingthy.platform.service.basedata
 * SeasonService.java
 * 
 * 2017年3月29日-下午4:11:08
 *  2017金昔科技有限公司-版权所有
 *
 */
package com.kingthy.platform.service.basedata;

import com.kingthy.platform.dto.basedata.MaterielSeasonReq;
import com.kingthy.platform.dto.basedata.SeasonCategoryDto;
import com.kingthy.platform.entity.basedata.SeasonCategory;

import java.util.List;

/**
 *
 * SeasonService
 * 
 * yuanml 2017年3月29日 下午4:11:08
 * 
 * @version 1.0.0
 *
 */
public interface SeasonCategoryService
{
    /**
     * 
     * 新增一个季节分类 (这里描述这个方法适用条件 – 可选)
     * 
     * yuanml
     * 
     * @param materielSeasonReq
     * @return int
     */
    int createSeasonCategory(MaterielSeasonReq materielSeasonReq);
    
    /**
     * 查找目录季节分类 (这里描述这个方法适用条件 – 可选)
     * 
     * yuanml
     * 
     * @param parentId
     * @return List<Season>
     * @exception @since 1.0.0
     */
    List<SeasonCategory> findSeasonCategoryByParentId(String parentId);
    
    /**
     * 更新季节分类信息 (这里描述这个方法适用条件 – 可选)
     *
     * yuanml
     * 
     * @param materielSeasonReq
     * @return int
     */
    int updateSeasonCategoryByUuid(MaterielSeasonReq materielSeasonReq);
    
    /**
     * 显示隐藏季节分类 (这里描述这个方法适用条件 – 可选)
     *
     * yuanml
     * 
     * @param seasonCategoryUuid
     * @param seasonCategoryStatus
     * @return int
     * @exception @since 1.0.0
     */
    int updateSeasonCategoryStatusByUuid(String seasonCategoryUuid, String seasonCategoryStatus);
    
    /**
     * 移除季节分类 (这里描述这个方法适用条件 – 可选)
     *
     * yuanml
     * 
     * @param seasonCategoryUuid
     * @return int
     * @exception @since 1.0.0
     */
    int deleteSeasonCategory(String seasonCategoryUuid);
    
    /**
     * 转移季节分类 (这里描述这个方法适用条件 – 可选)
     *
     * yuanml
     * 
     * @param seasonCategoryUuidOld
     * @param seasoncategoryUuidNew
     * @param sourceGrade
     * @param targetGrade
     * @return int
     */
    int transferSeasonCategory(String seasonCategoryUuidOld, String seasoncategoryUuidNew, String sourceGrade,
        String targetGrade);
    
    /**
     * 根据uuid查询单个季节分类 (进行单条记录查询)
     *
     * yuanml
     * 
     * @param seasonCategoryUuid
     * @return SeasonCategory
     * @exception @since 1.0.0
     */
    SeasonCategory findSeasonCategoryByUuid(String seasonCategoryUuid);
    
    /**
     * 返回封装类，方便所有节点排序显示 (这里描述这个方法适用条件 – 可选)
     *
     * yuanml
     * 
     * @return List<SeasonCategoryDto>
     * @exception @since 1.0.0
     */
    List<SeasonCategoryDto> findAllSeasoncategoryTree();
    
    /**
     * 查询所有顶级季节分类(这里描述这个方法适用条件 – 可选)
     *
     * yuanml
     * 
     * @return List<SeasonCategory>
     * @exception @since 1.0.0
     */
    List<SeasonCategory> findAllSeasoncategoryTop();
    
    /**
     * findSeasonCategoryName(检查分类名称是否重复) (这里描述这个方法适用条件 – 可选)
     *
     * yuanml
     * 
     * @param seasonCategoryName
     * @return Boolean
     * @exception @since 1.0.0
     */
    Boolean findSeasonCategoryName(String seasonCategoryName);
    
}
