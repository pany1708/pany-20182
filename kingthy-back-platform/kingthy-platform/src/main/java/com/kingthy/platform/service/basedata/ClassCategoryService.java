package com.kingthy.platform.service.basedata;

import com.kingthy.platform.dto.basedata.CategoryReq;
import com.kingthy.platform.dto.basedata.CategoryTreeDto;
import com.kingthy.platform.dto.basedata.TransferCategoryReq;
import com.kingthy.platform.entity.basedata.ClassCategory;

import java.util.List;

/**
 * 
 *
 * ClassClassCategoryService(品类分类接口)
 * 
 * 陈钊 2017年3月29日 下午1:56:11
 * 
 * @version 1.0.0
 *
 */
public interface ClassCategoryService
{
    
    /**
     * 
     * findAllTopNodes(查询所有品类的顶级节点)
     * 
     * @return 陈钊 List<ClassCategory>
     * @exception @since 1.0.0
     */
    List<ClassCategory> findAllTopNodes();

    /**
     *
     * findAllNodes(查询所有品类)
     *
     * @return 赵生辉 List<ClassCategory>
     * @exception @since 1.0.0
     */
    public List<ClassCategory> findAllNodes();
    
    /**
     * 
     * findAllChildNodes(根据节点主键查询其所有的子节点)
     * 
     * @return <b>创建人：</b>陈钊<br/>
     *         List<classCategoryUuid>
     * @exception @since 1.0.0
     */
    List<ClassCategory> findAllChildNodes(String classCategoryUuid);
    
    /**
     * 
     * addChild(添加节点)
     * 
     * @param categoryReq
     * @return <b>创建人：</b>陈钊<br/>
     *         int
     * @exception @since 1.0.0
     */
    int addNode(CategoryReq categoryReq);
    
    /**
     * 
     * delete(删除节点)
     * 
     * @param classCategoryUuid
     * @return <b>创建人：</b>陈钊<br/>
     *         int
     * @exception @since 1.0.0
     */
    int delete(String classCategoryUuid);
    
    /**
     * 
     * findAllChildNodesNum(根据节点Uuid查询其所有子节点数量)
     * 
     * @param classCategoryUuid
     * @return <b>创建人：</b>陈钊<br/>
     *         int
     * @exception @since 1.0.0
     */
    int findAllChildNodesNum(String classCategoryUuid);
    
    /**
     * 
     * transfer(转移分类)
     * 
     * @param transferCategoryReq <b>创建人：</b>陈钊<br/>
     *            void
     * @exception @since 1.0.0
     */
    int transfer(TransferCategoryReq transferCategoryReq);
    
    /**
     * 
     * findClassCategoryByUuid(根据uuid查询单个分类信息)
     * 
     * @param classCategoryUuid
     * @return <b>创建人：</b>陈钊<br/>
     *         ClassCategory
     * @exception @since 1.0.0
     */
    ClassCategory findClassCategoryByUuid(String classCategoryUuid);
    
    /**
     * 
     * edit(编辑品类)
     * 
     * @param categoryReq <b>创建人：</b>陈钊<br/>
     *            void
     * @exception @since 1.0.0
     */
    int edit(CategoryReq categoryReq);
    
    /**
     * 
     * editStatus(修改分类节点状态，以及其所有子孙类的状态)
     * 
     * @param classCategoryUuid
     * @param status
     * @return <b>创建人：</b>陈钊<br/>
     *         int
     * @exception @since 1.0.0
     */
    int editStatus(String classCategoryUuid, boolean status);
    
    /**
     * 
     * findAll(查询所有节点信息)
     * 
     * @return <b>创建人：</b>陈钊<br/>
     *         List<ClassCategory>
     * @exception @since 1.0.0
     */
    List<ClassCategory> findAll();
    
    /**
     * 
     * getTree(查询树结构)
     * 
     * @return <b>创建人：</b>陈钊<br/>
     *         List<CategoryTreeDto>
     * @exception @since 1.0.0
     */
    List<CategoryTreeDto> getTree();
    
    /**
     * findClassCategoryName(检查名称是否重复) (这里描述这个方法适用条件 – 可选)
     *
     * yuanml
     * 
     * @param classCategoryName
     * @return Boolean
     * @exception @since 1.0.0
     */
    Boolean findClassCategoryName(String classCategoryName);

    /**
     * 更新商品数量
     * @return
     */
    int updateGoodsNum();
}
