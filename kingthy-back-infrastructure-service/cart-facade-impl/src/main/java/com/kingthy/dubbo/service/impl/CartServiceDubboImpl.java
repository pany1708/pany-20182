package com.kingthy.dubbo.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kingthy.dto.CartDTO;
import com.kingthy.dubbo.cart.service.CartDubboService;
import com.kingthy.entity.Cart;
import com.kingthy.entity.CartItem;
import com.kingthy.entity.Goods;
import com.kingthy.mapper.CartItemMapper;
import com.kingthy.mapper.CartMapper;
import com.kingthy.response.CartResp;
import com.kingthy.service.CartServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

/**
 * opusServiceImpl(描述其作用)
 *
 * @author 赵生辉 2017年08月02日 15:35
 *
 * @version 1.0.0
 */
@Service(version="1.0.0", timeout = 10000)
public class CartServiceDubboImpl implements CartDubboService
{

    @Autowired
    private transient CartMapper cartMapper;

    @Autowired
    private transient CartItemMapper cartItemMapper;

    @Autowired
    private transient CartServiceImpl cartServiceImpl;

    /**
     * 根据token获取当前用户的购物车信息
     *
     * @return
     * @author 潘勇
     * @return Cart
     * @begin 2017-03-08 15:03
     * @since 1.0.0
     */
    @Override
    public Cart getCurrentCart(String memberUuid)
    {
        return cartMapper.selectCartItem(memberUuid);
    }

    /**
     * 添加商品以及数量到用户的购物车中
     *
     * @param goods
     * @param quantity
     * @param memberUuid
     * @return
     * @author 潘勇
     * @return Cart
     * @begin 2017-03-08 15:04
     * @since 1.0.0
     */
    @Override
    public Cart add(Goods goods, int quantity, String memberUuid)
    {
        return cartServiceImpl.add(goods,quantity,memberUuid);
    }
    @Override
    public CartItem getCartItem(Cart cart, Goods goods)
    {
        for (CartItem cartItem : getCartItemById(cart.getUuid()))
        {
            if (cartItem.getGoodsUuid().equals(goods.getUuid()))
            {
                return cartItem;
            }
        }
        return null;
    }
    @Override
    public List<CartItem> getCartItemById(String cartUuid)
    {
        return cartItemMapper.getCartItemByCartUuid(cartUuid);
    }

    @Override
    public boolean contains(Cart cart, Goods goods)
    {
        List<CartItem> cartItems = cartItemMapper.getCartItemByCartUuid(cart.getUuid());
        if (cartItems != null && cartItems.size() > 0)
        {

            for (CartItem cartItem : cartItems)
            {
                if (cartItem.getGoodsUuid().equals(goods.getUuid()))
                {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public Cart edit(CartDTO cart)
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<CartResp> list(String memberUuid)
    {
        // TODO Auto-generated method stub
        List<CartResp> cartResps = cartMapper.selectCart(memberUuid);
        if (null != cartResps && cartResps.size() > 0)
        {
            return cartResps;
        }
        else
        {
            return null;
        }
    }

    @Override
    public int remove(String cartItemUuid)
    {
        return cartMapper.deleteCartItemByUuid(cartItemUuid);
    }

    @Override
    public int checkCartItem(String memberUuid, String cartItemUuid)
    {
        return cartMapper.checkCartItem(memberUuid, cartItemUuid);
    }

    @Override
    public void removeAllCart(Cart cart)
    {
        cartServiceImpl.removeAllCart(cart);
    }

    @Override
    public String selectCartByMemberUuid(String memberUuid)
    {
        return cartMapper.selectCartByMemberUuid(memberUuid);
    }

    @Override
    public int insert(Cart cart)
    {
        Date currentDate = new Date();
        cart.setCreateDate(currentDate);
        cart.setModifyDate(currentDate);
        cart.setCreator("Creator");
        cart.setDelFlag(false);
        cart.setVersion(1);
        return cartMapper.insertSelective(cart);
    }

    @Override
    public int updateByUuid(Cart cart)
    {
        Example example = new Example(Cart.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("uuid",cart.getUuid());
        return cartMapper.updateByExample(cart,example);
    }

    @Override
    public List<Cart> selectAll()
    {
        return cartMapper.selectAll();
    }

    @Override
    public Cart selectByUuid(String uuid)
    {
        Example example = new Example(Cart.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("uuid",uuid);
        List<Cart> cartList = cartMapper.selectByExample(example);
        if(cartList == null || cartList.size() == 0)
        {
            return null;
        }
        else
        {
            return cartList.get(0);
        }
    }

    @Override
    public int selectCount(Cart cart)
    {
        return cartMapper.selectCount(cart);
    }

    @Override
    public List<Cart> select(Cart cart)
    {
        return cartMapper.select(cart);
    }

    @Override
    public Cart selectOne(Cart cart)
    {
        return cartMapper.selectOne(cart);
    }

    @Override
    public PageInfo<Cart> queryPage(Integer pageNum, Integer pageSize, Cart cart)
    {
        PageHelper.startPage(pageNum,pageSize);
        List<Cart> cartList = cartMapper.findByPage(cart);
        return new PageInfo<>(cartList);
    }

}
