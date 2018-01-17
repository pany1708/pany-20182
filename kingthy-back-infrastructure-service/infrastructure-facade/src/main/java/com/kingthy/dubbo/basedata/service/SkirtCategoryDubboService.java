package com.kingthy.dubbo.basedata.service;


import com.kingthy.base.BaseService;
import com.kingthy.dto.GoodsParameterCountDTO;
import com.kingthy.entity.SkirtCategory;

import java.util.List;

/**
 * SkirtCategoryDubboService(描述其作用)
 * @author zhaochen 2017年07月10日 10:45
 *
 * @version 1.0.0
 */
public interface SkirtCategoryDubboService extends BaseService<SkirtCategory>
{
    /**
     * 删除衬衫分类
     * @param uuid
     * @return
     */
    int deleteSkirtCategory(String uuid);
}
