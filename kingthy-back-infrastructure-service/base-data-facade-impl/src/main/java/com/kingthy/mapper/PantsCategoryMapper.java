package com.kingthy.mapper;


import com.kingthy.dto.GoodsParameterCountDTO;
import com.kingthy.entity.PantsCategory;
import com.kingthy.util.MyMapper;

import java.util.List;

/**
 * PantsCategoryMapper(描述其作用)
 * @author zhaochen 2017年07月10日 10:04
 *
 * @version 1.0.0
 */
public interface PantsCategoryMapper extends MyMapper<PantsCategory>
{
    /**
     * 批量更新商品数量
     * @param list
     * @return
     */
    int batchUpdateGoodsNum(List<GoodsParameterCountDTO> list);

    /**
     * 分页查询裤子分类
     * @param pantsCategory
     * @return
     */
    List<PantsCategory> findByPage(PantsCategory pantsCategory);

    /**
     * 查询裤装分类uuid
     * @return
     */
    List<PantsCategory> selectAllPantsCategoryUuids();

    /**
     * 根据uuid查询裤装分类信息
     * @param uuid
     * @return
     */
    PantsCategory selectByUuid(String uuid);
}
