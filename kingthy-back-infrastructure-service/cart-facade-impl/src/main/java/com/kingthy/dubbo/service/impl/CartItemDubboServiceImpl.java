package com.kingthy.dubbo.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kingthy.dubbo.cart.service.CartItemDubboService;
import com.kingthy.entity.CartItem;
import com.kingthy.exception.BizException;
import com.kingthy.mapper.CartItemMapper;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

/**
 * CartItemServiceImpl(描述其作用)
 *
 * @author 赵生辉 2017年08月07日 11:27
 *
 * @version 1.0.0
 */
@Service(version="1.0.0", timeout = 10000)
public class CartItemDubboServiceImpl implements CartItemDubboService
{
    @Autowired
    CartItemMapper cartItemMapper;

    @Override
    public int updateCartItem(CartItem cartItem, int quantity)
    {
        if (null == cartItem)
        {
            throw new BizException("购物车业务层异常,findCartItem返回0");
        }
        else
        {
            cartItem.setQuantity(quantity);
            Example example = new Example(CartItem.class);
            example.createCriteria().andEqualTo("uuid", cartItem.getUuid());
            return cartItemMapper.updateByExampleSelective(cartItem, example);
        }

    }

    @Override
    public CartItem findCartItem(String cartItemUuid)
    {
        return cartItemMapper.findCartItemByUuid(cartItemUuid);
    }

    @Override
    public int insert(CartItem cartItem)
    {
        Date currentDate = new Date();
        cartItem.setCreateDate(currentDate);
        cartItem.setModifyDate(currentDate);
        cartItem.setVersion(0);
        cartItem.setDelFlag(false);
        return  cartItemMapper.insertSelective(cartItem);
    }

    @Override
    public int updateByUuid(CartItem cartItem)
    {
        Example example = new Example(CartItem.class);
        example.createCriteria().andEqualTo("uuid", cartItem.getUuid());
        return cartItemMapper.updateByExampleSelective(cartItem, example);
    }

    @Override
    public List<CartItem> selectAll()
    {
        return cartItemMapper.selectAll();
    }

    @Override
    public CartItem selectByUuid(String uuid)
    {
        Example example = new Example(CartItem.class);
        example.createCriteria().andEqualTo("uuid",uuid);
        List<CartItem> list = cartItemMapper.selectByExample(example);
        if (list!=null && !list.isEmpty()){
            return list.get(0);
        }
        return new CartItem();
    }

    @Override
    public int selectCount(CartItem cartItem)
    {
        return cartItemMapper.selectCount(cartItem);
    }

    @Override
    public List<CartItem> select(CartItem cart) {
        Example example = new Example(CartItem.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("cartUuid", cart.getCartUuid());
        criteria.andEqualTo("delFlag", false);
        return cartItemMapper.selectByExample(example);
    }

    @Override
    public CartItem selectOne(CartItem cartItem)
    {
        return cartItemMapper.selectOne(cartItem);
    }

    @Override
    public PageInfo<CartItem> queryPage(Integer pageNum, Integer pageSize, CartItem cartItem)
    {
        PageHelper.startPage(pageNum,pageSize);
        List<CartItem> cartItemList = cartItemMapper.select(cartItem);
        return new PageInfo<>(cartItemList);
    }

}
