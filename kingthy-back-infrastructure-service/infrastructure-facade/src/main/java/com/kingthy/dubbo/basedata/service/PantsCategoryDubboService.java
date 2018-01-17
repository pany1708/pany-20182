package com.kingthy.dubbo.basedata.service;


import com.kingthy.base.BaseService;
import com.kingthy.dto.GoodsParameterCountDTO;
import com.kingthy.entity.PantsCategory;

import java.util.List;

/**
 * PantsCategoryDubboService(描述其作用)
 * @author zhaochen 2017年07月10日 10:00
 *
 * @version 1.0.0
 */
public interface PantsCategoryDubboService extends BaseService<PantsCategory>
{
    /**
     * 删除裤子分类
     * @param uuid
     * @return
     */
    int deletePantsCategory(String uuid);
    /**
     * 批量更新商品数量
     * @param list
     * @return
     */
    int batchUpdateGoodsNum(List<GoodsParameterCountDTO> list);
}
