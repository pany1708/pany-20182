package com.kingthy.dubbo.basedata.service;


import com.github.pagehelper.PageInfo;
import com.kingthy.base.BaseService;
import com.kingthy.dto.AreaDto;
import com.kingthy.dto.CityInfoDTO;
import com.kingthy.entity.Area;
import com.kingthy.entity.ClassCategory;

import java.util.List;

/**
 * AreaDubboService(描述其作用)
 * @author zhaochen 2017年07月06日 14:41
 *
 * @version 1.0.0
 */
public interface AreaDubboService
{
    /**
     * 新建地区信息
     * @param area
     * @return
     */
    int createArea(Area area);

    /**
     * 查询地区信息
     * @param id
     * @return
     */
    Area queryAreaInfo(int id);

    /**
     * 分页查询地区信息
     * @param pageNum
     * @param pageSize
     * @param area
     * @return
     */
    PageInfo<Area> queryAreaInfo(int pageNum , int pageSize , Area area);

    /**
     * 根据父节点分页查询地区信息
     * @param pageNum
     * @param pageSize
     * @param parentId
     * @return
     */
    PageInfo<Area> queryParentAreaInfo(int pageNum ,int pageSize ,int parentId);

    /**
     * 查询地区信息
     * @param area
     * @return
     */
    List<Area> queryArea(Area area);

    /**
     * 更新地区信息
     * @param area
     * @return
     */
    int updateAreaInfo(Area area);

    /**
     * 删除地区信息
     * @param id
     * @return
     */
    int deleteAreaInfo(int id);

    /**
     * 全查地区信息
     * @return
     */
    List<AreaDto> queryAreaAll();

    /**
     * 根据UUID查询城市
     * @param areaUuid
     * @return
     */
    CityInfoDTO selectCityInfoByAreaId(Integer areaUuid);
}
