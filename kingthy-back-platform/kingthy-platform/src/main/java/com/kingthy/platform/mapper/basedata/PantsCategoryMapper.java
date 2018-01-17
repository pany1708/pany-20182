package com.kingthy.platform.mapper.basedata;

import com.kingthy.platform.entity.basedata.PantsCategory;
import com.kingthy.platform.util.MyMapper;

/**
 * PantsCategoryMapper(描述其作用)
 * <p>
 * 赵生辉 2017年07月10日 10:04
 *
 * @version 1.0.0
 */
public interface PantsCategoryMapper extends MyMapper<PantsCategory>
{

    /**
     * 更新商品数量
     * @return
     */
    int updateGoodsNum();
}
