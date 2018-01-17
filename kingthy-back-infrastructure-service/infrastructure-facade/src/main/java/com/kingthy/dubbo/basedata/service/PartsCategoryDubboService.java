package com.kingthy.dubbo.basedata.service;


import com.kingthy.base.BaseService;
import com.kingthy.dto.PartsFileDto;
import com.kingthy.entity.PartsCategory;

import java.util.List;

/**
 * 
 *
 * PartsCategoryDubboService(部件分类接口)
 * 
 * @author zhaochen 2017年3月29日 下午1:56:17
 * 
 * @version 1.0.0
 *
 */
public interface PartsCategoryDubboService extends BaseService<PartsCategory>
{
    /**
     * 查询部件信息
     * @param list
     * @return
     */
    List<PartsCategory> findPartsCategory(List<String> list);

    /**
     * 新增部件
     * @param var
     * @return
     */
    int create(PartsCategory var);

    /**
     * 更新部件
     * @param var
     * @return
     */
    int updateBySn(PartsCategory var);

    /**
     * 删除部件
     * @param var
     * @return
     */
    int deleteBySn(PartsCategory var);

    /**
     * 查询所有部件的图片，渲染文件
     * @return
     */
    List<PartsFileDto> findFiles();
}
