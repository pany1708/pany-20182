package com.kingthy.mapper;

import com.kingthy.dto.SourceTypeDTO;
import com.kingthy.entity.SourceCategory;
import com.kingthy.response.QuerySubCategoryResp;
import com.kingthy.util.MyMapper;

import java.util.List;

/**
 * Created by kingthy on 2018/1/4.
 */
public interface SourceCategoryMapper extends MyMapper<SourceCategory>
{

    /**
     * 分页查询
     *
     * @param sourceCategory
     * @return
     */
    List<SourceCategory> findByPage(SourceCategory sourceCategory);

    /**
     * 查询类别
     * @param type
     * @return
     */
    List<SourceTypeDTO> querySourceByType(Integer type);

    /**
     * @Description: 查询分类下的顶级子分类
     * @Date: 2018/1/5 17:14
     */
    List<QuerySubCategoryResp> querySubCategory(int categoryType);
}
