package com.kingthy.platform.service.basedata;

import com.github.pagehelper.PageInfo;
import com.kingthy.platform.entity.basedata.SegmentCategory;

/**
 * SegmentCategoryService(描述其作用)
 * <p>
 * 赵生辉 2017年07月11日 10:57
 *
 * @version 1.0.0
 */
public interface SegmentCategoryService
{
    /**
     * 添加新的分段数据
     * @param segmentCategory
     * @return
     */
    int createSegmentCategory(SegmentCategory segmentCategory);

    /**
     * 分页查询分段数据
     * @param pageNum
     * @param pageSize
     * @param segmentCategory
     * @return
     */
    PageInfo<SegmentCategory> querySegmentCategory(int pageNum,int pageSize,SegmentCategory segmentCategory);

    /**
     * 查询分段详情
     * @param uuid
     * @return
     */
    SegmentCategory querySegmentCategory(String uuid);

    /**
     * 修改分段数据信息
     * @param segmentCategory
     * @return
     */
    int updateSegmentCategory(SegmentCategory segmentCategory);

    /**
     * 删除分段参数
     * @param uuid
     * @return
     */
    int deleteSegmentCategory(String uuid);

    /**
     * 更新年龄商品数量
     * @return
     */
    int updateGoodsNum();

    /**
     * 更新价格分段商品数量
     * @return
     */
    int updateGoodsNumPrice();
}
