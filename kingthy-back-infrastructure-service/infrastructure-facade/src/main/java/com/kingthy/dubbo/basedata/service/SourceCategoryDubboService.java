package com.kingthy.dubbo.basedata.service;

import com.kingthy.base.BaseService;
import com.kingthy.dto.SourceTypeDTO;
import com.kingthy.entity.SourceCategory;
import com.kingthy.response.QuerySubCategoryResp;

import java.util.List;

/**
 * Created by kingthy on 2018/1/4.
 */
public interface SourceCategoryDubboService  extends BaseService<SourceCategory>
{
    /**
     * 查询类别
     * @param type
     * @return
     */
    List<SourceTypeDTO> querySourceByType(Integer type);

    /**
     * @Description:根据分类查询子分类列表
     * @Date: 2018/1/8 16:58
     */
    List<QuerySubCategoryResp> querySubCategory(int categoryType);
}
