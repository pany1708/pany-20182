package com.kingthy.dubbo.basedata.service;

import com.kingthy.base.BaseService;
import com.kingthy.dto.GoodsParameterCountDTO;
import com.kingthy.entity.DressCategory;

import java.util.List;

/**
 * DressCategory(描述其作用)
 * @author zhaochen 2017年07月10日 10:43
 *
 * @version 1.0.0
 */
public interface DressCategoryDubboService extends BaseService<DressCategory>
{
    /**
     * 删除裙装分类
     * @param uuid
     * @return
     */
    int deleteDressCategory(String uuid);

    /**
     * 批量更新商品数量
     * @param list
     * @return
     */
    int batchUpdateGoodsNum(List<GoodsParameterCountDTO> list);
}
