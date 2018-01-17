package com.kingthy.platform.service.order;

import com.github.pagehelper.PageInfo;
import com.kingthy.platform.entity.order.ShippingMethod;

import java.util.List;

/**
 * 
 *
 * ShippingMethodService(配送方式接口)
 * 
 * 赵生辉 2017年4月12日 下午8:27:10
 * 
 * @version 1.0.0
 *
 */
public interface ShippingMethodService
{
    /**
     * 
     * crate(创建一个购物方式) (这里描述这个方法适用条件 – 可选)
     * 
     * @param shippingMethod
     * @return 赵生辉 int
     * @exception @since 1.0.0
     */
    int crate(ShippingMethod shippingMethod);
    
    /**
     * 
     * update(修改购物方式) (这里描述这个方法适用条件 – 可选)
     * 
     * @param shippingMethod
     * @return 赵生辉 int
     * @exception @since 1.0.0
     */
    int update(ShippingMethod shippingMethod);
    
    /**
     * 
     * findAllOrderItem(这里用一句话描述这个方法的作用) (这里描述这个方法适用条件 – 可选)
     * 
     * @return 赵生辉 List<OrderItem>
     * @exception @since 1.0.0
     */
    List<ShippingMethod> findAllShippingMethod();

    /**
     * 分页查询物流数据
     * @return
     */
    PageInfo<ShippingMethod> findAllShippingMethodPage(int pageNum,int pageSize,ShippingMethod shippingMethod);

}
