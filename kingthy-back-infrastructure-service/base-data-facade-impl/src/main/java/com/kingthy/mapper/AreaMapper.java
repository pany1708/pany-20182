package com.kingthy.mapper;


import com.kingthy.dto.AreaDto;
import com.kingthy.dto.CityInfoDTO;
import com.kingthy.entity.Area;
import com.kingthy.util.MyMapper;

import java.util.List;

/**
 * AreaMapper(描述其作用)
 * @author zhaochen  2017年07月06日 14:39
 *
 * @version 1.0.0
 */
public interface AreaMapper extends MyMapper<Area>
{
    /**
     * 查询地区信息
     * @return
     */
    List<AreaDto> selectArea();

    /**
     * 根据uuid查询地区信息
     * @param areaUuid
     * @return
     */
    CityInfoDTO selectCityInfoByAreaId(Integer areaUuid);

    /**
     * 根据id查询地区信息
     * @param id
     * @return
     */
    Area findById(int id);

    /**
     * 分页查询地区信息
     * @param area
     * @return
     */
    List<Area> queryAreaInfoList(Area area);

    /**
     * 根据上级地区查询其子地区
     * @param area
     * @return
     */
    List<Area> queryParentAreaInfoList(Area area);
}
