package com.kingthy.mapper;


import com.kingthy.dto.GoodsParameterCountDTO;
import com.kingthy.entity.DressCategory;
import com.kingthy.util.MyMapper;

import java.util.List;

/**
 * DressCategoryMapper(描述其作用)
 * @author zhaochen 2017年07月10日 10:32
 *
 * @version 1.0.0
 */
public interface DressCategoryMapper extends MyMapper<DressCategory>
{
    /**
     * 批量更新商品数量
     * @param list
     * @return
     */
    int batchUpdateGoodsNum(List<GoodsParameterCountDTO> list);

    /**
     * 查询所有裙装信息
     * @return
     */
    List<DressCategory> findAllDressCategory();

    /**
     * 根据uuid查询
     * @param uuid
     * @return
     */
    DressCategory selectByUuid(String uuid);

    /**
     * 分页查询裙装分类
     * @param dressCategory
     * @return
     */
    List<DressCategory> queryPage(DressCategory dressCategory);
}
