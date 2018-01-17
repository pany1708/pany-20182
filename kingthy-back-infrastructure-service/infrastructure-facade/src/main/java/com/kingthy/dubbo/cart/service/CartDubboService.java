package com.kingthy.dubbo.cart.service;

import com.kingthy.base.BaseService;
import com.kingthy.dto.CartDTO;
import com.kingthy.entity.Cart;
import com.kingthy.entity.CartItem;
import com.kingthy.entity.Goods;
import com.kingthy.request.EventPublishReq;
import com.kingthy.response.CartResp;

import java.util.List;

/**
 * CartService(描述其作用)
 * <p>
 * 赵生辉 2017年08月07日 11:08
 *
 * @version 1.0.0
 */
public interface CartDubboService extends BaseService<Cart>
{
    Cart add(Goods goods, int quantity, String memberUuid);

    Cart edit(CartDTO cart);

    List<CartResp> list(String memberId);

    int remove(String cartItemUuid);

    Cart getCurrentCart(String memberUuid);

    boolean contains(Cart cart, Goods goods);

    CartItem getCartItem(Cart cart, Goods goods);

    List<CartItem> getCartItemById(String cartUuid);

    int checkCartItem(String memberUuid, String cartItemUuid);

    void removeAllCart(Cart cart);

    String selectCartByMemberUuid(String memberUuid);

    Cart selectOne(Cart var1);

}
