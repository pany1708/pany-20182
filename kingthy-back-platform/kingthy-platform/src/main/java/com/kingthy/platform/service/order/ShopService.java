package com.kingthy.platform.service.order;

import com.github.pagehelper.PageInfo;
import com.kingthy.platform.dto.order.QueryShopPageReq;
import com.kingthy.platform.entity.order.Area;
import com.kingthy.platform.entity.order.Shop;

import java.util.Date;
import java.util.List;

/**
 * ShopService(描述其作用)
 * <p>
 * 赵生辉 2017年07月21日 9:48
 *
 * @version 1.0.0
 */
public interface ShopService
{
    int create(Shop shop);

    PageInfo<Shop> queryShopPage(QueryShopPageReq queryBaseDataReq);

    Shop queryShopInfo(String uuid);

    int update(Shop shop);

    int delete(String uuid);

}
