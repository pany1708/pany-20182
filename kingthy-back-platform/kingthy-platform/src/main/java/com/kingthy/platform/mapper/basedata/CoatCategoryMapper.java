package com.kingthy.platform.mapper.basedata;

import com.kingthy.platform.entity.basedata.CoatCategory;
import com.kingthy.platform.util.MyMapper;

/**
 * CoatCategoryMapper(描述其作用)
 * <p>
 * 赵生辉 2017年07月05日 18:41
 *
 * @version 1.0.0
 */
public interface CoatCategoryMapper extends MyMapper<CoatCategory>
{
    /**
     * 更新商品数量
     * @return
     */
    int updateGoodsNum();
}
