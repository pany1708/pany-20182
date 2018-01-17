package com.kingthy.platform.mapper.basedata;

import com.kingthy.platform.dto.basedata.CategoryTreeDto;
import com.kingthy.platform.entity.basedata.ClassCategory;
import com.kingthy.platform.util.MyMapper;

import java.util.List;
import java.util.Map;

/**
 * 
 *
 * ClassCategoryMapper(品类分类映射接口)
 * 
 * 陈钊 2017年4月1日 下午1:52:45
 * 
 * @version 1.0.0
 *
 */
public interface ClassCategoryMapper extends MyMapper<ClassCategory>
{
    
    /**
     * 
     * updateParentIdBatch(批量更新节点的父节点id)
     * 
     * @param paramMap
     * @return <b>创建人：</b>陈钊<br/>
     *         int
     * @exception @since 1.0.0
     */
    int updateParentIdBatch(Map<String, Object> paramMap);
    
    /**
     * 
     * findChildUuidsByParentId(根据根节点uuid查询其所有子孙节点，包括他自己的uuid)
     * 
     * @param parentId
     * @return <b>创建人：</b>陈钊<br/>
     *         String
     * @exception @since 1.0.0
     */
    String findChildUuidsByParentId(String parentId);
    
    /**
     * 
     * updateStatusBatch(批量更新节点状态)
     * 
     * @param paramMap
     * @return <b>创建人：</b>陈钊<br/>
     *         int
     * @exception @since 1.0.0
     */
    int updateStatusBatch(Map<String, Object> paramMap);
    
    /**
     * 
     * updateGradeBatch(批量修改节点层级)
     * 
     * @param paramMap
     * @return <b>创建人：</b>陈钊<br/>
     *         int
     * @exception @since 1.0.0
     */
    int updateGradeBatch(Map<String, Object> paramMap);
    
    /**
     * 
     * updateParentId(根据uuid修改单个节点的父节点)
     * 
     * @param paramMap
     * @return <b>创建人：</b>陈钊<br/>
     *         int
     * @exception @since 1.0.0
     */
    int updateParentId(Map<String, Object> paramMap);
    
    /**
     * 
     * updateCategory(修改单个节点信息：描述、分类名等)
     * 
     * @param classCategory
     * @return <b>创建人：</b>陈钊<br/>
     *         int
     * @exception @since 1.0.0
     */
    int updateCategory(ClassCategory classCategory);
    
    /**
     * 
     * getTree(为树结构查询提供数据)
     * 
     * @return <b>创建人：</b>陈钊<br/>
     *         List<ClassCategoryTree>
     * @exception @since 1.0.0
     */
    List<CategoryTreeDto> getTree();

    /**
     * 更新商品数量
     * @return
     */
    int updateGoodsNum();
}