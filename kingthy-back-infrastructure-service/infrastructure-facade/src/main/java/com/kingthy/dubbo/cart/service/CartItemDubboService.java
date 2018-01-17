package com.kingthy.dubbo.cart.service;

import com.kingthy.base.BaseService;
import com.kingthy.entity.CartItem;
import com.kingthy.entity.Goods;

import java.util.List;

/**
 * CartItemService(描述其作用)
 * <p>
 * 赵生辉 2017年08月07日 11:29
 *
 * @version 1.0.0
 */
public interface CartItemDubboService extends BaseService<CartItem>
{
    int updateCartItem(CartItem cartItem, int quantity);

    CartItem findCartItem(String cartItemUuid);

    List<CartItem> select(CartItem var1);
}
