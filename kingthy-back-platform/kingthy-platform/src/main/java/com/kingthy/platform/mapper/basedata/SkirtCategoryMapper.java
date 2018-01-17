package com.kingthy.platform.mapper.basedata;

import com.kingthy.platform.entity.basedata.SkirtCategory;
import com.kingthy.platform.util.MyMapper;

/**
 * SkirtCategory(描述其作用)
 * <p>
 * 赵生辉 2017年07月10日 10:33
 *
 * @version 1.0.0
 */
public interface SkirtCategoryMapper extends MyMapper<SkirtCategory>
{

    /**
     * 更新商品数量
     * @return
     */
    int updateGoodsNum();
}
