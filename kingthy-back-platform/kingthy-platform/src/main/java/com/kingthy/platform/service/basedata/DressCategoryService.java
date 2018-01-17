package com.kingthy.platform.service.basedata;

import com.github.pagehelper.PageInfo;
import com.kingthy.platform.entity.basedata.DressCategory;

/**
 * DressCategory(描述其作用)
 * <p>
 * 赵生辉 2017年07月10日 10:43
 *
 * @version 1.0.0
 */
public interface DressCategoryService
{

    /**
     * 创建连衣裙分类
     * @param dressCategory
     * @return
     */
    int createDressCategory(DressCategory dressCategory);

    /**
     * 分页查询连衣裙分类
     * @param pageNum
     * @param pageSize
     * @param dressCategory
     * @return
     */
    PageInfo<DressCategory> queryDressCategory(int pageNum,int pageSize,DressCategory dressCategory);

    /**
     * 查询连衣裙分类详情
     * @param uuid
     * @return
     */
    DressCategory queryDressCategoryInfo(String uuid);

    /**
     * 修改连衣裙分类
     * @param dressCategory
     * @return
     */
    int updateDressCategory(DressCategory dressCategory);

    /**
     * 删除连衣裙分类
     * @param uuid
     * @return
     */
    int deleteDressCategory(String uuid);

    /**
     * 更新商品数量
     * @return
     */
    int updateGoodsNum();
}
