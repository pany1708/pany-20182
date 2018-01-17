package com.kingthy.platform.service.basedata;

import com.github.pagehelper.PageInfo;
import com.kingthy.platform.entity.basedata.CoatCategory;

/**
 * CoatCategoryService(描述其作用)
 * <p>
 * 赵生辉 2017年07月05日 18:10
 *
 * @version 1.0.0
 */
public interface CoatCategoryService
{
    /**
     * 创建上衣分类
     * @param coatCategory
     * @return
     */
    int createCoatCategory(CoatCategory coatCategory);

    /**
     * 分页查询上衣分类
     * @param pageNum
     * @param pageSize
     * @param coatCategory
     * @return
     */
    PageInfo<CoatCategory> queryCoatCategory(int pageNum,int pageSize,CoatCategory coatCategory);

    /**
     * 查询上衣分类详情
     * @param uuid
     * @return
     */
    CoatCategory queryCoatCategoryInfo(String uuid);

    /**
     * 修改上衣分类
     * @param coatCategory
     * @return
     */
    int updateCoatCategory(CoatCategory coatCategory);

    /**
     * 删除上衣分类
     * @param uuid
     * @return
     */
    int deleteCoatCategory(String uuid);

    /**
     * 更新商品数量
     * @return
     */
    int updateGoodsNum();
}
