package com.kingthy.platform.mapper.basedata;

import com.kingthy.platform.entity.basedata.SegmentCategory;
import com.kingthy.platform.util.MyMapper;

/**
 * SegmentCategoryMapper(描述其作用)
 * <p>
 * 赵生辉 2017年07月11日 11:05
 *
 * @version 1.0.0
 */
public interface SegmentCategoryMapper extends MyMapper<SegmentCategory>
{

    /**
     * 更新年龄分段商品数量
     * @return
     */
    int updateGoodsNum();

    /**
     * 更新价格分段商品数量
     * @return
     */
    int updateGoodsNumPrice();
}
