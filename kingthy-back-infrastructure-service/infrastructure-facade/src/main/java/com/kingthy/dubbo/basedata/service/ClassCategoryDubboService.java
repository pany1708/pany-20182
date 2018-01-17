package com.kingthy.dubbo.basedata.service;


import com.kingthy.base.BaseService;
import com.kingthy.dto.CategoryTreeDto;
import com.kingthy.dto.GoodsParameterCountDTO;
import com.kingthy.entity.ClassCategory;
import com.kingthy.request.CategoryReq;
import com.kingthy.request.TransferCategoryReq;
import com.kingthy.response.ClassCategoryResp;

import java.util.List;

/**
 * 
 *
 * ClassClassCategoryService(品类分类接口)
 * 
 * @author zhaochen 2017年3月29日 下午1:56:11
 * 
 * @version 1.0.0
 *
 */
public interface ClassCategoryDubboService extends BaseService<ClassCategory>
{
    /**
     * 转移节点
     * @param transferCategoryReq
     * @return
     */
    int transfer(TransferCategoryReq transferCategoryReq);

    /**
     * 更新品类信息
     * @param categoryReq
     * @return
     */
    int update(CategoryReq categoryReq);

    /**
     * 修改状态
     * @param classCategoryUuid
     * @param status
     * @return
     */
    int editStatus(String classCategoryUuid, boolean status);

    /**
     * 查询树结构
     * @return
     */
    List<CategoryTreeDto> getTree();

    /**
     * 批量更新商品数量
     * @param list
     * @return
     */
    int batchUpdateGoodsNum(List<GoodsParameterCountDTO> list);

    /**
     * 根据uuid批量查询名称
     * @param uuids
     * @return
     */
    List<ClassCategory> selectNameByUuids(List<String> uuids);

    /**
     * 查询分类
     * @return
     */
    List<ClassCategoryResp> queryClassCategory();

    /**
     * 根据款式code更新
     * @param var
     * @return
     */
    int updateByStyleCode(ClassCategory var);

    /**
     * 根据款式code删除
     * @param var
     * @return
     */
    int delByStyleCode(ClassCategory var);
}
