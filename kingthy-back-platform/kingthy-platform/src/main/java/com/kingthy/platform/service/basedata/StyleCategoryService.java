package com.kingthy.platform.service.basedata;

import com.github.pagehelper.PageInfo;
import com.kingthy.platform.entity.basedata.StyleCategory;

import java.util.List;

/**
 * 
 *
 * StyleCategoryService(风格分类接口)
 * 
 * 陈钊 2017年3月29日 下午1:56:11
 * 
 * @version 1.0.0
 *
 */
public interface StyleCategoryService
{
    /**
     *
     * create(创建一个新的风格) (添加新的风格)
     *
     * @param styleCategory
     * @return 赵生辉 int
     * @exception @since 1.0.0
     */
    int create(StyleCategory styleCategory);

    /**
     *
     * deleteById(删除一个风格) (逻辑删除一个风格)
     *
     * @param uuid
     * @return 赵生辉 int
     * @exception @since 1.0.0
     */
    int deleteByUuid(String uuid);

    /**
     *
     * updateSelective(选择性修改风格信息) (可以修改部分信息)
     *
     * @param styleCategory
     * @return 赵生辉 int
     * @exception @since 1.0.0
     */
    int updateSelective(StyleCategory styleCategory);

    /**
     *
     * findAllTagCategory(查询所有的风格) (用于其他模块调用风格数据)
     *
     * @param styleCategory
     * @return 赵生辉 PageInfo<Member>
     * @exception @since 1.0.0
     */
    List<StyleCategory> findAllStyleCategory(StyleCategory styleCategory);

    /**
     * findStyleCategoryInfo查询风格分类详细信息
     * @param uuid
     * @return
     */
    StyleCategory findStyleCategoryInfo(String uuid);

    /**
     *
     * findPageAllTagCategory(分页查询所有的风格) (用于首页分页展示数据信息)
     *
     * @param pageNo
     * @param pageSize
     * @param styleCategory
     * @return 赵生辉 PageInfo<Member>
     * @exception @since 1.0.0
     */
    PageInfo<StyleCategory> findAllTagCategoryPage(int pageNum, int pageSize, StyleCategory styleCategory);

    /**
     * 更新商品数量
     * @return
     */
    int updateGoodsNum();

}
