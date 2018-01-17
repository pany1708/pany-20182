package com.kingthy.platform.service.basedata;

import com.github.pagehelper.PageInfo;
import com.kingthy.platform.entity.basedata.PantsCategory;

/**
 * PantsCategoryService(描述其作用)
 * <p>
 * 赵生辉 2017年07月10日 10:00
 *
 * @version 1.0.0
 */
public interface PantsCategoryService
{
    /**
     * 创建裤装分类
     * @param pantsCategory
     * @return
     */
    int createPantsCategory(PantsCategory pantsCategory);

    /**
     * 分页查询裤装分类
     * @param pageNum
     * @param pageSize
     * @param pantsCategory
     * @return
     */
    PageInfo<PantsCategory> queryPantsCategory(int pageNum,int pageSize,PantsCategory pantsCategory);

    /**
     * 查询裤装分类详情
     * @param uuid
     * @return
     */
    PantsCategory queryPantsCategoryInfo(String uuid);

    /**
     * 修改裤装分类
     * @param pantsCategory
     * @return
     */
    int updatePantsCategory(PantsCategory pantsCategory);

    /**
     * 删除裤装分类
     * @param uuid
     * @return
     */
    int deletePantsCategory(String uuid);

    /**
     * 更新商品数量
     * @return
     */
    int updateGoodsNum();
}
