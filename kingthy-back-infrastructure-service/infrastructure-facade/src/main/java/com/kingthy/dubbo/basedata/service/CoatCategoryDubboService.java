package com.kingthy.dubbo.basedata.service;

import com.kingthy.base.BaseService;
import com.kingthy.dto.GoodsParameterCountDTO;
import com.kingthy.entity.CoatCategory;

import java.util.List;

/**
 * CoatCategoryDubboService(描述其作用)
 * <p>
 * @author zhaochen 2017年07月05日 18:10
 *
 * @version 1.0.0
 */
public interface CoatCategoryDubboService extends BaseService<CoatCategory>
{
    /**
     * 删除
     * @param uuid
     * @return
     */
    int deleteCoatCategory(String uuid);

    /**
     * 更新商品数量
     * @param list
     * @return
     */
    int batchUpdateGoodsNum(List<GoodsParameterCountDTO> list);
}
