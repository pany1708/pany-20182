package com.kingthy.mapper;


import com.kingthy.dto.GoodsParameterCountDTO;
import com.kingthy.entity.CoatCategory;
import com.kingthy.util.MyMapper;

import java.util.List;

/**
 * CoatCategoryMapper(描述其作用)
 * @author zhaochen 2017年07月05日 18:41
 *
 * @version 1.0.0
 */
public interface CoatCategoryMapper extends MyMapper<CoatCategory>
{
    /**
     * 更新商品数量
     * @param list
     * @return
     */
    int batchUpdateGoodsNum(List<GoodsParameterCountDTO> list);

    /**
     * 分页查询外套分类
     * @param coatCategory
     * @return
     */
    List<CoatCategory> findByPage(CoatCategory coatCategory);

    /**
     * 查询全部外套
     * @return
     */
    List<CoatCategory> findCoatCategoriesAll();

    /**
     * 根据uuid查询外套信息
     * @param uuid
     * @return
     */
    CoatCategory selectByUuid(String uuid);
}
