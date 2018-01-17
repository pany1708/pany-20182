package com.kingthy.dubbo.basedata.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.kingthy.dto.AccessoriesDto;
import com.kingthy.dto.AccessoriesFileDto;
import com.kingthy.entity.Accessories;
import com.kingthy.request.AddUpdateAccessoriesReq;
import com.kingthy.request.UpdateAccessoriesReq;

/**
 * @author zhaochen
 */
public interface AccessoriesDubboService
{
    /**
     * 
     * create(创建一个辅料) (创建一个辅料)
     * 
     * @param accessories
     * @return 赵生辉 int
     */
    int create(Accessories accessories);

    void saveAccessories(AddUpdateAccessoriesReq req);

    /**
     * 创建辅料并返回结果
     * @param accessories
     * @return
     */
    String insertReturnUuid(Accessories accessories);
    
    /**
     * 
     * findAccessories(查询一个辅料的详情) (查询一个辅料的详情)
     * 
     * @param accessoriesUuid
     * @return 赵生辉 AccessoriesDto
     */
    AccessoriesDto findAccessories(String accessoriesUuid);
    
    /**
     * 分页查询辅料表
     * 
     * @param pageNum
     * @param pageSize
     * @param accessories
     * @return
     */
    PageInfo<AccessoriesDto> findAccessoriesPage(int pageNum, int pageSize, Accessories accessories);
    
    /**
     * 删除辅料详情
     *
     * @author yuanml
     *
     * @param accessoriesUuid
     * @return
     */
    void deleteAccessories(String accessoriesUuid);
    
    /**
     * 更新辅料信息
     * @param accessories
     * @author yuanml
     *
     * @return
     */
    int updateAccessories(Accessories accessories);

    void updateAccessories(UpdateAccessoriesReq req);

    /**
     * findAccessories
     * 
     * @param list
     * @return
     */
    List<Accessories> findAccessories(List<String> list);

    /**
     * 根据编码查询
     * @param code
     * @return
     */
    Accessories selectAccessoriesByCode(String code);

    /**
     * 根据uuid查询
     * @param uuid
     * @return
     */
    Accessories selectAccessoriesByUuid(String uuid);


    /**
     * 查询条数
     * @param accessoriesName
     * @param accessoriesUuid
     * @return
     */
    int selectCountByExample(String accessoriesName, String accessoriesUuid);
    
    /**
     * 查询辅料附属文件
     * 
     * @return
     */
    List<AccessoriesFileDto> findFiles();
}
