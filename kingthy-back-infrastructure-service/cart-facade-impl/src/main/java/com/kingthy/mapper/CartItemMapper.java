package com.kingthy.mapper;

import com.kingthy.entity.CartItem;
import com.kingthy.util.MyMapper;

import java.util.List;

/**
 * CartItemMapper(描述其作用)
 *
 * @author 赵生辉 2017年08月07日 13:57
 *
 * @version 1.0.0
 */
public interface CartItemMapper extends MyMapper<CartItem>
{
    /**
     * 分页查找
     * @param cartItem
     * @return
     */
    List<CartItem> findByPage(CartItem cartItem);

    /**
     * 根据uuid查询购物车
     * @param cartItemUuid
     * @return
     */
    CartItem findCartItemByUuid(String cartItemUuid);

    /**
     * 根据购物车uuid查询购物车内商品
     * @param cartUuid
     * @return
     */
    List<CartItem> getCartItemByCartUuid(String cartUuid);
}
