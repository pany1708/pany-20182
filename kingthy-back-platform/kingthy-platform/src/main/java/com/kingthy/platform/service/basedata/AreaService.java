package com.kingthy.platform.service.basedata;

import com.github.pagehelper.PageInfo;
import com.kingthy.platform.dto.basedata.AreaDto;
import com.kingthy.platform.entity.basedata.Area;

import java.util.List;

/**
 * AreaService(描述其作用)
 * <p>
 * 赵生辉 2017年07月06日 14:41
 *
 * @version 1.0.0
 */
public interface AreaService
{

    int createArea(Area area);

    Area queryAreaInfo(int id);

    PageInfo<Area> queryAreaInfo(int pageNum ,int pageSize ,Area area);

    PageInfo<Area> queryParentAreaInfo(int pageNum ,int pageSize ,int parentId);

    List<Area> queryArea(Area area);

    int updateAreaInfo(Area area);

    int deleteAreaInfo(int id);

    List<AreaDto> queryAreaAll();
}
