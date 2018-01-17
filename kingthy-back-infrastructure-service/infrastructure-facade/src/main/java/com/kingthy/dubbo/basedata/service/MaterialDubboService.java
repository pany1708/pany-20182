package com.kingthy.dubbo.basedata.service;

import com.github.pagehelper.PageInfo;
import com.kingthy.dto.MaterialDto;
import com.kingthy.entity.Material;
import com.kingthy.request.AddUpdateMaterialReq;
import com.kingthy.request.UpdateMaterialReq;
import com.kingthy.request.QueryResourceInfoReq;
import com.kingthy.response.QueryResourceInfoResp;

import java.util.List;

/**
 * @author zhaochen
 */
public interface MaterialDubboService
{
    List<Material> list(int pageNum, int pageSize, Material var);

    /**
     * create(创建一个面料) (创建一个面料)
     *
     * @param material
     * @return 赵生辉 String
     */
    int create(Material material);

    /**
     * 插入并返回结果
     *
     * @param material
     * @return
     */
    String insertReturnUuid(Material material);

    /**
     * 保存面料
     *
     * @param req
     * @return
     */
    void saveMaterial(AddUpdateMaterialReq req);

    void updateMaterial(UpdateMaterialReq req);

    /**
     * findMaterial(查询一个面料的详情) (查询一个面料的详情)
     *
     * @param materialUuid
     * @return 赵生辉 MaterialDto
     */
    MaterialDto findMaterial(String materialUuid);

    /**
     * findMaterialPage(分页查询面料的列表) (分页查询面料的列表)
     *
     * @param pageNum
     * @param pageSize
     * @param material
     * @return PageInfo<MaterialDto>
     */
    PageInfo<MaterialDto> findMaterialPage(int pageNum, int pageSize, Material material);

    /**
     * 根据uuid删除面料
     *
     * @param materialUuid
     * @return
     * @author yuanml
     */
    void deleteMaterial(String materialUuid);

    /**
     * 更新面料
     *
     * @param material
     * @return
     * @author yuanml
     */
    int updateMaterial(Material material);

    /**
     * 查找颜色
     *
     * @param rgb
     * @return
     * @author yuanml
     */
    List<String> selectColor(String rgb);

    /**
     * findMaterial
     *
     * @param list
     * @return
     */
    List<Material> findMaterial(List<String> list);

    /**
     * 根据code查找面料
     *
     * @param code
     * @return
     */
    Material selectMaterialByCode(String code);

    /**
     * 根据uuid查询面料
     *
     * @param uuid
     * @return
     */
    Material selectMaterialByUuid(String uuid);

    /**
     * 查询条数
     *
     * @return
     */
    int selectCountByExample(String materialName, String materialUuid);

    /**
     * @Author: 潘勇
     * @Description: 根据顶级分类的UUID查询对应的资源集合
     * @Date: 2018/1/9 18:31
     */
    List<QueryResourceInfoResp> queryResourceInfoBySubCategory(QueryResourceInfoReq queryResourceInfoReq);

    /**
     * '
     *
     * @param queryResourceInfoRespList
     * @return
     * @Author: 潘勇
     */
    int updateResourceList(List<QueryResourceInfoResp> queryResourceInfoRespList);
}