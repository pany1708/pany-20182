package com.kingthy.platform.service.material;

import com.github.pagehelper.PageInfo;
import com.kingthy.platform.dto.material.MaterialDto;
import com.kingthy.platform.entity.material.Material;

import java.util.List;

public interface MaterialService
{
    /**
     * 
     * create(创建一个面料) (创建一个面料)
     * 
     * @param addUpdateMaterialReq
     * @return 赵生辉 String
     */
    int create(Material material);
    
    /**
     * 
     * findMaterial(查询一个面料的详情) (查询一个面料的详情)
     * 
     * @param materialUuid
     * @return 赵生辉 MaterialDto
     */
    MaterialDto findMaterial(String materialUuid);
    
    /**
     * 
     * findMaterialPage(分页查询面料的列表) (分页查询面料的列表)
     * 
     * @param findPage
     * @return 赵生辉 PageInfo<MaterialDto>
     */
    PageInfo<Material> findMaterialPage(int pageNum ,int pageSize ,Material material);

    /**
     * @desc 根据uuid删除面料
     *
     * @author yuanml
     *
     * @param materialUuid
     * @return
     */
    int deleteMaterial(String materialUuid);

    /**
     * @param addUpdateMaterialReq
     * @author yuanml
     *
     * @return
     */
    int updateMaterial(Material material);

    /**
     * @desc 查找颜色
     *
     * @author yuanml
     *
     * @param rgb
     * @return
     */
    List<String> selectColor(String rgb);

}