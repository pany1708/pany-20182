package com.kingthy.mapper;

import com.kingthy.entity.Shop;
import com.kingthy.util.MyMapper;

/**
 * ShopServiceMapper(描述其作用)
 * <p>
 * 赵生辉 2017年08月28日 18:40
 *
 * @version 1.0.0
 */
public interface ShopMapper extends MyMapper<Shop>
{
    /**
     *
     * 根据名称查询数量
     * @param shop
     * @return
     */
    int selectCountByName(Shop shop);

    /**
     * 根据uuid查询店铺信息
     * @param uuid
     * @return
     */
    Shop selectByUuid(String uuid);
}
