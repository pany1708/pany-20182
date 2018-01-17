package com.kingthy.platform.mapper.basedata;

import com.kingthy.platform.dto.basedata.PartsCategoryTree;
import com.kingthy.platform.entity.basedata.PartsCategory;
import com.kingthy.platform.util.MyMapper;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Map;

/**
 * 
 *
 * PartsCategoryMapper(部件分类接口映射)
 * 
 * 赵生辉 2017年4月13日 下午8:27:42
 * 
 * @version 1.0.0
 *
 */
public interface PartsCategoryMapper extends MyMapper<PartsCategory>
{
    /**
     * 
     * selectChild(查询当前项的所有子项) (这里描述这个方法适用条件 – 可选)
     * 
     * @param uuid
     * @return 赵生辉 String
     * @exception @since 1.0.0
     */
    String selectChild(@Param(value = "uuid") String uuid);
    
    /**
     * 
     * updateStatusInExample(修改指定部件分类的状态) (这里描述这个方法适用条件 – 可选)
     * 
     * @param map
     * @return 赵生辉 int
     * @exception @since 1.0.0
     */
    int updateStatusInExample(Map<Object, Object> map);
    
    /**
     * 
     * updateGradeInExample(修改指定部件分类的级别) (这里描述这个方法适用条件 – 可选)
     * 
     * @param map
     * @return 赵生辉 int
     * @exception @since 1.0.0
     */
    int updateGradeInExample(Map<Object, Object> map);
    
    /**
     * 
     * selectPartsCategoryTree(查询所有的部件) (用于映射到PartsCategoryTree上用来分级排列数据)
     * 
     * @return 赵生辉 List<PartsCategoryTree>
     * @exception @since 1.0.0
     */
    List<PartsCategoryTree> selectPartsCategoryTree();
    
    /**
     * 
     * selectPartsCategoryTreeByExample(条件查询所有的部件) (条件查询所有的部件)
     * 
     * @param example
     * @return 赵生辉 List<PartsCategoryTree>
     * @exception @since 1.0.0
     */
    List<PartsCategoryTree> selectPartsCategoryTreeByExample(Example example);
    
}
