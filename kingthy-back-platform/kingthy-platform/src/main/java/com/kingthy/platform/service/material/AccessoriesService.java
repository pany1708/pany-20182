package com.kingthy.platform.service.material;

import com.github.pagehelper.PageInfo;
import com.kingthy.platform.entity.material.Accessories;

public interface AccessoriesService
{
    /**
     * 
     * create(创建一个辅料) (创建一个辅料)
     * 
     * @param accessories
     * @return 赵生辉 int
     */
    int create(Accessories accessories);
    
    /**
     * 
     * findAccessories(查询一个辅料的详情) (查询一个辅料的详情)
     * 
     * @param accessoriesUuid
     * @return 赵生辉 AccessoriesDto
     */
    Accessories findAccessories(String accessoriesUuid);

    /**
     * 分页查询辅料表
     * @param pageNum
     * @param pageSize
     * @param accessories
     * @return
     */
    PageInfo<Accessories> findAccessoriesPage(int pageNum ,int pageSize ,Accessories accessories);

    /**
     * @desc 删除辅料详情
     *
     * @author yuanml
     *
     * @param accessoriesUuid
     * @return
     */
    int deleteAccessories(String accessoriesUuid);

    /**
     * @param accessories
     * @author yuanml
     *
     * @return
     */
    int updateAccessories(Accessories accessories);
}
