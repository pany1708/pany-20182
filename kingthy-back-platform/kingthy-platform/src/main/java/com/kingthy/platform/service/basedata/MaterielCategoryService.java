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

import com.github.pagehelper.PageInfo;
import com.kingthy.platform.dto.basedata.MaterielCategoryDto;
import com.kingthy.platform.entity.basedata.MaterielCategory;

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
public interface MaterielCategoryService
{
    /**
     * 
     * 新增一个物料分类 (这里描述这个方法适用条件 – 可选)
     *
     * @param materielCategory
     * @return int
     * @exception @since 1.0.0
     */
    int createMaterielCategory(MaterielCategory materielCategory);
    
    /**
     * 查找目录物料分类 (这里描述这个方法适用条件 – 可选)
     *
     * @param parentId
     * @return List<Season>
     * @exception @since 1.0.0
     */
    List<MaterielCategory> findMaterielCategoryByParentId(String parentId);
    
    /**
     * 更新物料分类信息 (这里描述这个方法适用条件 – 可选)
     *
     * @param materielCategory
     * @return int
     */
    int updateMaterielCategoryByUuid(MaterielCategory materielCategory);

    /**
     * 分页查询材料分类
     * @param pageNum
     * @param pageSize
     * @param materielCategory
     * @return
     */
    PageInfo findMaterielCategory(int pageNum,int pageSize,MaterielCategory materielCategory);

    /**
     * 显示隐藏物料分类 (这里描述这个方法适用条件 – 可选)
     *
     * @param materielCategoryUuid
     * @param materielCategoryStatus
     * @return int
     */
    int updateMaterielCategoryStatusByUuid(String materielCategoryUuid, String materielCategoryStatus);
    
    /**
     * 移除物料分类 (这里描述这个方法适用条件 – 可选)
     *
     * @param materielCategoryUuid
     * @return int
     */
    int deleteMaterielCategory(String materielCategoryUuid);
    
    /**
     * 转移物料分类 (这里描述这个方法适用条件 – 可选)
     *
     * @param materielCategoryUuidOld
     * @param materielCategoryUuidNew
     * @param sourceGrade
     * @param targetGrade
     * @return int
     */
    int transferMaterielCategory(String materielCategoryUuidOld, String materielCategoryUuidNew, String sourceGrade,
        String targetGrade);
    
    /**
     * 根据uuid查询单个物料分类 (进行单条记录查询)
     *
     * @param materielCategoryUuid
     * @return MaterielCategory
     */
    MaterielCategory findMaterielCategoryByUuid(String materielCategoryUuid);
    
    /**
     * 返回封装类，方便所有节点排序显示 (这里描述这个方法适用条件 – 可选)
     *
     * @return List<MaterielCategoryDto>
     * @exception @since 1.0.0
     */
    List<MaterielCategoryDto> findAllMaterielcategoryTree();
    
    /**
     * 查询所有顶级节点物料分类 (这里描述这个方法适用条件 – 可选)
     *
     * @return List<MaterielCategory>
     * @exception @since 1.0.0
     */
    List<MaterielCategory> findAllMaterielcategoryTop();
    
    /**
     * findMaterielCategoryName(检查是否已存在相同名称) (这里描述这个方法适用条件 – 可选)
     *
     * @param materielCategoryName
     * @return Boolean
     * @exception @since 1.0.0
     */
    Boolean findMaterielCategoryName(String materielCategoryName);

    /**
     * 更新商品数量
     * @return
     */
    int updateGoodsNum();

    /**
     * 查询所有的材质分类
     * @return
     */
    List<MaterielCategory> findAllMaterielcategory();
}
