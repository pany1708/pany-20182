/**
 * 系统项目名称
 * com.kingthy.platform.mapper.basedata
 * MaterielMapper.java
 * 
 * 2017年3月29日-下午3:58:28
 *  2017金昔科技有限公司-版权所有
 *
 */
package com.kingthy.mapper;


import com.kingthy.dto.GoodsParameterCountDTO;
import com.kingthy.dto.MaterieDto;
import com.kingthy.entity.MaterielCategory;
import com.kingthy.util.MyMapper;

import java.util.List;
import java.util.Map;

/**
 *
 * MaterielCategoryMapper
 * 
 * @author zhaochen 2017年3月29日 下午3:58:28
 * 
 * @version 1.0.0
 *
 */
public interface MaterielCategoryMapper extends MyMapper<MaterielCategory>
{
    /**
     * 批量更新商品数量
     * @param list
     * @return
     */
    int batchUpdateGoodsNum(List<GoodsParameterCountDTO> list);

    /**
     * 根据uuid查询名称
     * @param list
     * @return
     */
    List<MaterieDto> findMaterielNameByMaterielUuid(List<String> list);

    /**
     * 分页查询
     * @param materielCategory
     * @return
     */
    List<MaterielCategory> findByPage(MaterielCategory materielCategory);

    /**
     * 查询所有物料分类的uuid
     * @return
     */
    List<MaterielCategory> selectAllUuid();

    /**
     * 根据
     * @param uuid
     * @return
     */
    MaterielCategory selectByUuid(String uuid);
}
