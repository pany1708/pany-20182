package com.kingthy.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSONObject;
import com.kingthy.cache.RedisManager;
import com.kingthy.constant.CacheKeysConstants;
import com.kingthy.dubbo.cart.service.CartItemDubboService;
import com.kingthy.dubbo.goods.service.GoodsDubboService;
import com.kingthy.entity.CartItem;
import com.kingthy.entity.Goods;
import com.kingthy.exception.bizexcapiton.CartBizException;
import com.kingthy.response.CartResp;
import com.kingthy.service.CartItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.concurrent.TimeUnit;

/**
 * @author shenghuizhao
 */
@Service("cartItemService")
public class CartItemServiceImpl implements CartItemService
{
    @Reference(version = "1.0.0")
    private  CartItemDubboService cartItemDubboService;

    @Autowired
    private RedisManager redisManager;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private CartServiceImpl cartServiceImpl;

    private static final Logger LOG= LoggerFactory.getLogger(CartItemServiceImpl.class);

    @Override
    public int updateCartItem(CartItem cartItem, int quantity,String memberUuid)
        throws CartBizException
    {
        String cartCacheKey = CacheKeysConstants.generateCacheKey(CartResp.class,memberUuid);
        redisManager.delete(cartCacheKey);
        String cacheKey = CacheKeysConstants.generateCacheKey(CartItem.class,cartItem.getUuid());
        redisManager.delete(cacheKey);
        int result = cartItemDubboService.updateCartItem( cartItem, quantity);
        cartServiceImpl.list(memberUuid);
       return result;
    }
    @Override
    public CartItem findCartItem(String cartItemUuid)
    {
        CartItem cartItem = new CartItem();
        String cacheKey = CacheKeysConstants.generateCacheKey(CartItem.class,cartItemUuid);
        String cacheData = redisManager.get(cacheKey);
        if(!StringUtils.isEmpty(cacheData))
        {
            cartItem = JSONObject.parseObject(cacheData,CartItem.class);
        }
        else
        {
            cartItem = cartItemDubboService.findCartItem(cartItemUuid);
            long expire = redisManager.getExpire(cacheKey);
            if (expire <= 0)
            {

                redisManager.set(cacheKey,JSONObject.toJSONString(cartItem),TimeUnit.DAYS);
            }
        }
        return cartItem;
    }

    @Override
    public Goods findGoodsByUuid(String goodsUuid) {

        return goodsDubboService.selectByUuid(goodsUuid);
    }
    @Reference(version = "1.0.0",check = false)
    private GoodsDubboService goodsDubboService;
}
