package com.kingthy.platform.mapper.basedata;

import com.kingthy.platform.entity.basedata.SuitCategory;
import com.kingthy.platform.util.MyMapper;

/**
 * Suit(描述其作用)
 * <p>
 * 赵生辉 2017年07月10日 10:37
 *
 * @version 1.0.0
 */
public interface SuitCategoryMapper extends MyMapper<SuitCategory>
{
    /**
     * 更新商品数量
     * @return
     */
    int updateGoodsNum();
}
