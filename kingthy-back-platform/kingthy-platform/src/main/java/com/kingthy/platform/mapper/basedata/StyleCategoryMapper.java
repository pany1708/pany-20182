package com.kingthy.platform.mapper.basedata;

import com.kingthy.platform.dto.basedata.CategoryTreeDto;
import com.kingthy.platform.entity.basedata.StyleCategory;
import com.kingthy.platform.util.MyMapper;

import java.util.List;

/**
 * 
 *
 * StyleCategoryMapper(风格分类映射接口)
 * 
 * 陈钊 2017年4月1日 下午4:49:13
 * 
 * @version 1.0.0
 *
 */
public interface StyleCategoryMapper extends MyMapper<StyleCategory>
{
    /**
     * 
     * getTree(为树结构查询提供数据)
     * 
     * @return <b>创建人：</b>陈钊<br/>
     *         List<CategoryTreeDto>
     * @exception @since 1.0.0
     */
    List<CategoryTreeDto> getTree();

    /**
     * 更新商品数量
     * @return
     */
    int updateGoodsNum();


}