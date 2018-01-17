/**
 * 系统项目名称
 * com.kingthy.platform.service.basedata
 * SeasonService.java
 * 
 * 2017年3月29日-下午4:11:08
 *  2017金昔科技有限公司-版权所有
 *
 */
package com.kingthy.dubbo.basedata.service;

import com.kingthy.base.BaseService;
import com.kingthy.dto.GoodsParameterCountDTO;
import com.kingthy.dto.MaterieDto;
import com.kingthy.entity.MaterielCategory;

import java.util.List;


/**
 *
 * SeasonService
 * 
 * @author zhaochen 2017年3月29日 下午4:11:08
 * 
 * @version 1.0.0
 *
 */
public interface MaterielCategoryDubboService extends BaseService<MaterielCategory>
{
    /**
     * 删除物料
     * @param uuid
     * @return
     */
    int deleteMaterielCategory(String uuid);
    /**
     * 批量更新商品数量
     * @param list
     * @return
     */
    int batchUpdateGoodsNum(List<GoodsParameterCountDTO> list);

    /**
     * 插入并返回
     * @param materielCategory
     * @return
     */
    String insertReturnUuid(MaterielCategory materielCategory);

    /**
     * 更新
     * @param var
     * @return
     */
    int updateByCode(MaterielCategory var);

    /**
     * 删除
     * @param var
     * @return
     */
    int deleteByCode(MaterielCategory var);

    /**
     * 查询名称
     * @param materielUuidList
     * @return
     */
    List<MaterieDto> findMaterielNameByMaterielUuid(List<String> materielUuidList);
}
