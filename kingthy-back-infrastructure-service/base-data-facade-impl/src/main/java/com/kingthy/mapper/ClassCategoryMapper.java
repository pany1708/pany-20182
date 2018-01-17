package com.kingthy.mapper;



import com.kingthy.dto.CategoryTreeDto;
import com.kingthy.dto.GoodsParameterCountDTO;
import com.kingthy.entity.ClassCategory;
import com.kingthy.response.ClassCategoryResp;
import com.kingthy.util.MyMapper;

import java.util.List;
import java.util.Map;


/**
 * 
 *
 * ClassCategoryMapper(品类分类映射接口)
 * 
 * @author zhaochen 2017年4月1日 下午1:52:45
 * 
 * @version 1.0.0
 *
 */
public interface ClassCategoryMapper extends MyMapper<ClassCategory>
{
    /**
     * 根据uuid更新分类信息
     * @param classCategory
     * @return int
     */
    int updateByUuid(ClassCategory classCategory);

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
     * 根据条件分页查询
     * @param classCategory
     * @return List<ClassCategory>
     */
    List<ClassCategory> findByPage(ClassCategory classCategory);

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
     * 更新商品数量
     * @return
     */
    int updateGoodsNum();

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
     * 批量更新商品数量
     * @param list
     * @return
     */
    int batchUpdateGoodsNum(List<GoodsParameterCountDTO> list);

    /**
     * 根据uuids批量查询
     * @param list
     * @return
     */
    List<ClassCategory> selectNameByUuids(List<String> list);

    /**
     * 查询品类
     * @return
     */
    List<ClassCategoryResp> queryClassCategory();

    /**
     * 根据uuid查询信息
     * @param uuid
     * @return
     */
    ClassCategory selectByUuid(String uuid);
}