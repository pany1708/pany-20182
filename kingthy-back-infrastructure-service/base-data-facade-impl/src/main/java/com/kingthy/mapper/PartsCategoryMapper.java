package com.kingthy.mapper;


import com.kingthy.dto.PartsFileDto;
import com.kingthy.entity.PartsCategory;
import com.kingthy.util.MyMapper;

import java.util.List;

/**
 * 
 *
 * PartsCategoryMapper(部件分类接口映射)
 * 
 * @author zhaochen 2017年4月13日 下午8:27:42
 * 
 * @version 1.0.0
 *
 */
public interface PartsCategoryMapper extends MyMapper<PartsCategory>
{
   /**
    * 查询文件
    * @return
    */
   List<PartsFileDto> findFiles();

   /**
    * 分页查询部件信息
    * @param partsCategory
    * @return
    */
   List<PartsCategory> findByPage(PartsCategory partsCategory);

   /**
    * 查询全部部件分类信息
    * @return
    */
   List<PartsCategory> selectAllPartsCategory();

   /**
    * 根据uuid查询部件信息
    * @param uuid
    * @return
    */
   PartsCategory selectByUuid(String uuid);

   /**
    * 根据uuid批量查询部件分类信息
    * @param list
    * @return
    */
   List<PartsCategory> findPartsCategoryByUuidList(List<String> list);
}
