package com.kingthy.mapper;

import com.kingthy.dto.MaterialDto;
import com.kingthy.entity.Material;
import com.kingthy.request.QueryResourceInfoReq;
import com.kingthy.response.QueryResourceInfoResp;
import com.kingthy.util.MyMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * MaterialMapper
 *
 * @author zhaochen 2017年4月17日 下午3:35:11
 * @version 1.0.0
 */
public interface MaterialMapper extends MyMapper<Material>
{

    /**
     * 分页查找所有面料
     *
     * @param parameterMap
     * @return
     * @author yuanml
     */
    List<MaterialDto> findAllByPage(Map<String, Object> parameterMap);

    /**
     * 查找颜色
     *
     * @param rgb
     * @return
     * @author yuanml
     */
    List<String> selectColor(String rgb);

    /**
     * 分页查询面料
     *
     * @param material
     * @return
     */
    List<MaterialDto> findMaterialPage(Material material);

    /**
     * 根据uuid查询面料信息
     *
     * @param uuid
     * @return
     */
    MaterialDto findMaterialByUuid(String uuid);

    /**
     * 根据uuidlist查询面料信息
     *
     * @param list
     * @return
     */
    List<Material> findMaterialByUuidList(List<String> list);

    /**
     * 根据uuid查询面料信息
     *
     * @param uuid
     * @return
     */
    Material selectMaterialByUuid(String uuid);

    /**
     * 根据名称查询面料数量
     *
     * @param name
     * @return
     */
    int selectCountByName(String name);

    int selectCountByNameAndUuid(@Param(value = "materialName") String materialName, @Param(value = "materialUuid")String materialUuid);

    /**
     * @Author: 潘勇
     * @Description: 根据顶级分类的uuid查询对应的资源集合
     * @Date: 2018/1/9 18:35
     */
    List<QueryResourceInfoResp> queryResourceInfoBySubCategory(QueryResourceInfoReq queryResourceInfoReq);
}
