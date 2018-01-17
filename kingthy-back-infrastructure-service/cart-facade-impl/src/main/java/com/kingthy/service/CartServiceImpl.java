package com.kingthy.service;

import com.google.gson.Gson;
import com.kingthy.cache.RedisManager;
import com.kingthy.dubbo.service.impl.CartServiceDubboImpl;
import com.kingthy.entity.Cart;
import com.kingthy.entity.CartItem;
import com.kingthy.entity.EventProcess;
import com.kingthy.entity.Goods;
import com.kingthy.mapper.CartItemMapper;
import com.kingthy.mapper.CartMapper;
import com.kingthy.mapper.EventProcessMapper;
import com.kingthy.request.EventPublishReq;
import com.kingthy.response.CartResp;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * CartItemServiceImpl(描述其作用)
 *
 * @author 赵生辉 2017年08月17日 18:09
 *
 * @version 1.0.0
 */
@Service
public class CartServiceImpl
{
    @Autowired
    private transient CartMapper cartMapper;

    @Autowired
    private transient CartItemMapper cartItemMapper;

    @Autowired
    private EventProcessMapper eventProcessMapper;

    @Autowired
    private RedisManager redisManager;

    private static final Logger LOG = LoggerFactory.getLogger(CartServiceDubboImpl.class);

    @Transactional
    public Cart add(Goods goods, int quantity, String memberUuid)
    {
        Assert.notNull(goods);
        Assert.state(quantity > 0);

        Cart cart = getCurrentCart(memberUuid);
        if (cart == null)
        {
            cart = new Cart();
            if (StringUtils.isNotBlank(memberUuid))
            {
                cart.setMemberUuid(memberUuid);
            }
            cart.setCreateDate(new java.util.Date());
            cart.setModifyDate(new java.util.Date());
            cart.setDelFlag(false);
            cart.setVersion(0);
            Date expire = DateUtils.addSeconds(new Date(), Cart.TIMEOUT);
            cart.setExpire(expire);
            cartMapper.insert(cart);
        }
        // 判断购物车中是否包含该商品
        if (null != cart && contains(cart, goods))
        {
            // 如果包含则对该商品数量进行+1操作
            CartItem cartItem = getCartItem(cart, goods);
            cartItem.setModifyDate(new java.util.Date());
            cartItem.setQuantity(cartItem.getQuantity() + quantity);
            Example example = new Example(CartItem.class);
            example.createCriteria().andEqualTo("uuid", cartItem.getUuid()).andEqualTo("version",
                cartItem.getVersion());
            cartItem.setVersion(cartItem.getVersion() + 1);

            int flag = cartItemMapper.updateByExample(cartItem, example);
            if (flag == 0)
            {
                LOG.debug("");
            }
        }
        else
        {
            CartItem cartItem = new CartItem();
            cartItem.setQuantity(quantity);
            cartItem.setGoodsUuid(goods.getUuid());
            cartItem.setCartUuid(cart.getUuid());
            cartItem.setCreateDate(new java.util.Date());
            cartItem.setModifyDate(new java.util.Date());
            cartItem.setVersion(0);
            cartItem.setDelFlag(false);
            cartItemMapper.insert(cartItem);
        }

        return cart;
    }

    @Transactional
    public void removeAllCart(Cart cart)
    {
        if (cart.getUuid() != null)
        {
            cart.setModifyDate(new Date());
            cart.setDelFlag(true);
            cartMapper.updateByPrimaryKeySelective(cart);
            Example example = new Example(CartItem.class);
            String cartCacheKey = redisManager.generateCacheKey(CartResp.class,cart.getMemberUuid());
            redisManager.delete(cartCacheKey);
            example.createCriteria().andEqualTo("cartUuid", cart.getUuid()).andEqualTo("delFlag", false);
            List<CartItem> cartItems = cartItemMapper.selectByExample(example);
            if (!cart.getCartItems().isEmpty())
            {
                for (CartItem cartItem : cartItems)
                {
                    String cacheKey = redisManager.generateCacheKey(CartItem.class,cartItem.getUuid());
                    redisManager.delete(cacheKey);
                    cartItem.setModifyDate(new Date());
                    cartItem.setDelFlag(true);
                    cartItem.setQuantity(0);
                    cartItemMapper.updateByPrimaryKeySelective(cartItem);
                }
            }
        }
    }

    public boolean contains(Cart cart, Goods goods)
    {
        Example example = new Example(CartItem.class);
        example.createCriteria().andEqualTo("cartUuid", cart.getUuid()).andEqualTo("delFlag", false);
        List<CartItem> cartItems = cartItemMapper.selectByExample(example);
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

    /**
     * 根据token获取当前用户的购物车信息
     *
     * @return
     * @author 潘勇
     * @return Cart
     * @begin 2017-03-08 15:03
     * @since 1.0.0
     */
    public Cart getCurrentCart(String memberUuid)
    {
        Cart cart = cartMapper.selectCartItem(memberUuid);
        return cart;
    }

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

    public List<CartItem> getCartItemById(String cartUuid)
    {
        CartItem cartItem = new CartItem();
        Example example = new Example(CartItem.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("cartUuid", cartUuid);
        criteria.andEqualTo("delFlag", false);
        List<CartItem> cartItems = cartItemMapper.selectByExample(example);
        return cartItems;
    }

    @Transactional
    public void updateCartInfoByOrder(EventProcess eventProcess) {

        Gson gson = new Gson();

        EventPublishReq eventPublishReq = gson.fromJson(eventProcess.getPayload(), EventPublishReq.class);

        //查询是否有购物车
        String cartUuid = cartMapper.selectCartByMemberUuid(eventPublishReq.getMemberUuid());

        if (!org.springframework.util.StringUtils.isEmpty(cartUuid)){


            CartItem var = new CartItem();
            var.setUuid(cartUuid);
            var.setDelFlag(false);

            Map<String, Integer> mapGoods = eventPublishReq.getGoodsAndQuantityList().stream()
                    .collect(Collectors.toMap(EventPublishReq.GoodsAndQuantity::getGoodsUuid, EventPublishReq.GoodsAndQuantity::getQuantity));

            int flag = 0;

            //清理购物车
            //需要更新的购物车内的商品
            List<CartItem> updateCartItemList = new ArrayList<>();

            List<CartItem> cartItemList = cartItemMapper.select(var);

            for (CartItem a : cartItemList){

                //验证当前结算商品是否是在购物车内
                if (mapGoods.containsKey(a.getGoodsUuid())){

                    Integer quantity = mapGoods.get(a.getGoodsUuid());

                    //如果购物车的商品 数量 小于或者等于 结算的商品数量 则从购物车中删除此商品
                    if(a.getQuantity() <= quantity){
                        a.setQuantity(0);
                        a.setDelFlag(true);

                        flag ++;

                    }else{
                        a.setQuantity(a.getQuantity() - quantity);
                    }
                    a.setVersion(a.getVersion() + 1);

                    updateCartItemList.add(a);
                }
            }

            //是否需要删除购物车
            Boolean cartDelFlag = flag == cartItemList.size();

            //删除购物车
            if(cartDelFlag){
                Cart var1 = new Cart();
                var1.setUuid(cartUuid);
                var1.setDelFlag(false);
                Cart cart = cartMapper.selectOne(var1);

                if (cart != null){
                    Cart updateCart = new Cart();
                    updateCart.setModifier(eventPublishReq.getMemberUuid());
                    updateCart.setModifyDate(new Date());
                    updateCart.setDelFlag(true);
                    updateCart.setId(cart.getId());
                    removeAllCart(updateCart);
                }

            }else {
                for(CartItem cartItem: updateCartItemList)
                {
                    Example example = new Example(CartItem.class);
                    Example.Criteria criteria = example.createCriteria();
                    criteria.andEqualTo("uuid",cartItem.getUuid());
                    criteria.andEqualTo("version",cartItem.getVersion()-1);
                    cartItemMapper.updateByExample(cartItem,example);
                }
                //updateCartItemList.forEach(v -> cartItemMapper.updateByPrimaryKeySelective(v));
            }
            eventProcessMapper.delete(eventProcess);
        }
    }
}
