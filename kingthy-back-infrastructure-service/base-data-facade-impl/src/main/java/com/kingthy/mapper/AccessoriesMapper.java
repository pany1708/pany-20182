package com.kingthy.mapper;


import com.kingthy.dto.AccessoriesDto;
import com.kingthy.dto.AccessoriesFileDto;
import com.kingthy.entity.Accessories;
import com.kingthy.util.MyMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 
 *
 * AccessoriesMapper
 * 
 * @author zhaochen 2017年4月17日 下午8:31:59
 * 
 * @version 1.0.0
 *
 */
public interface AccessoriesMapper extends MyMapper<Accessories>
{

    /**
     * 分页查找所有辅料
     *
     * @author yuanml
     *
     * @param parameterMap
     * @return List<AccessoriesDto>
     */
    List<AccessoriesDto> findAllByPage(Map parameterMap);

    /**
     * 查询辅料附属文件
     * @return
     */
    List<AccessoriesFileDto> findFiles();

    /**
     * 分页查询辅料信息
     * @param accessories
     * @return
     */
    List<AccessoriesDto> findAccessoriesPage(Accessories accessories);

    /**
     * 根据uuid查询辅料信息
     * @param uuid
     * @return
     */
    AccessoriesDto findAccessoriesDtoByUuid(String uuid);

    /**
     * 根据多个uuid批量查询辅料信息
     * @param uuidList
     * @return
     */
    List<Accessories> findAccessoriesBatchByUuids(List<String> uuidList);

    /**
     * 根据物料编码查询辅料信息
     * @param code
     * @return
     */
    Accessories selectAccessoriesByCode(String code);

    /**
     * 根据uuid查询辅料信息
     * @param uuid
     * @return
     */
    Accessories selectAccessoriesByUuid(String uuid);

    /**
     * 根据名称查询面料数量
     * @param accessoriesName
     * @return
     */
    int selectCountByName(String accessoriesName);

    int selectCountByNameAndUuid(@Param(value = "accessoriesName") String accessoriesName, @Param(value = "accessoriesUuid")String accessoriesUuid);

}
