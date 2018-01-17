package com.kingthy.platform.service.basedata;

import com.github.pagehelper.PageInfo;
import com.kingthy.platform.entity.basedata.SkirtCategory;

/**
 * SkirtCategoryService(描述其作用)
 * <p>
 * 赵生辉 2017年07月10日 10:45
 *
 * @version 1.0.0
 */
public interface SkirtCategoryService
{

    /**
     * 创建半身裙分类
     * @param skirtCategory
     * @return
     */
    int createSkirtCategory(SkirtCategory skirtCategory);

    /**
     * 分页查询半身裙分类
     * @param pageNum
     * @param pageSize
     * @param skirtCategory
     * @return
     */
    PageInfo<SkirtCategory> querySkirtCategory(int pageNum,int pageSize,SkirtCategory skirtCategory);

    /**
     * 查询半身裙分类详情
     * @param uuid
     * @return
     */
    SkirtCategory querySkirtCategoryInfo(String uuid);

    /**
     * 修改半身裙分类
     * @param skirtCategory
     * @return
     */
    int updateSkirtCategory(SkirtCategory skirtCategory);

    /**
     * 删除半身裙分类
     * @param uuid
     * @return
     */
    int deleteSkirtCategory(String uuid);

    /**
     * 更新商品数量
     * @return
     */
    int updateGoodsNum();
}
