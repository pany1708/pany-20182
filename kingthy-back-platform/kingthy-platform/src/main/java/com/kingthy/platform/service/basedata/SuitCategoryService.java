package com.kingthy.platform.service.basedata;

import com.github.pagehelper.PageInfo;
import com.kingthy.platform.entity.basedata.SuitCategory;

/**
 * SuitCategoryService(描述其作用)
 * <p>
 * 赵生辉 2017年07月10日 10:46
 *
 * @version 1.0.0
 */
public interface SuitCategoryService
{
    /**
     * 创建套装分类
     * @param suitCategory
     * @return
     */
    int createSuitCategory(SuitCategory suitCategory);

    /**
     * 分页查询套装分类
     * @param pageNum
     * @param pageSize
     * @param suitCategory
     * @return
     */
    PageInfo<SuitCategory> querySuitCategory(int pageNum,int pageSize,SuitCategory suitCategory);

    /**
     * 查询套装分类详情
     * @param uuid
     * @return
     */
    SuitCategory querySuitCategoryInfo(String uuid);

    /**
     * 修改套装分类
     * @param suitCategory
     * @return
     */
    int updateSuitCategory(SuitCategory suitCategory);

    /**
     * 删除套装分类
     * @param uuid
     * @return
     */
    int deleteSuitCategory(String uuid);

    /**
     * 更新商品数量
     * @return
     */
    int updateGoodsNum();
}
