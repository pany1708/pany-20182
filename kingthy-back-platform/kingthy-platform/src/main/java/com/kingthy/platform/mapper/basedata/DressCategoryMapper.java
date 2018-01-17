package com.kingthy.platform.mapper.basedata;

import com.kingthy.platform.entity.basedata.DressCategory;
import com.kingthy.platform.util.MyMapper;

/**
 * DressCategoryMapper(描述其作用)
 * <p>
 * 赵生辉 2017年07月10日 10:32
 *
 * @version 1.0.0
 */
public interface DressCategoryMapper extends MyMapper<DressCategory>
{
    /**
     * 更新商品数量
     * @return
     */
    int updateGoodsNum();
}
