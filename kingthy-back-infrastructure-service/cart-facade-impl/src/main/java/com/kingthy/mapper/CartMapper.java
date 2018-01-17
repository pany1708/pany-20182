package com.kingthy.mapper;

import com.kingthy.entity.Cart;
import com.kingthy.response.CartResp;
import com.kingthy.util.MyMapper;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.provider.base.BaseInsertProvider;

import java.util.List;

/**
 * OpusMapper(描述其作用)
 *
 * @author 赵生辉 2017年08月03日 11:11
 *
 * @version 1.0.0
 */
public interface CartMapper extends MyMapper<Cart>
{
    /**
     * 插入数据
     * @param record
     * @return
     */
    @Override
    @Options(useGeneratedKeys = true, keyProperty = "record.id")
    @InsertProvider(type = BaseInsertProvider.class, method = "dynamicSQL")
    int insert(Cart record);

    /**
     * 根据会员编号查询购物车信息
     *
     * @param memberUuid
     * @return
     * @author 潘勇
     * @return Cart
     * @begin 2017-04-06 13:38
     * @since 1.0.0
     */
    List<CartResp> selectCart(String memberUuid);

    /**
     * --
     * @param memberUuid
     * @param cartItemUuid
     * @return
     */
    int checkCartItem(@Param("memberUuid") String memberUuid, @Param("cartItemUuid") String cartItemUuid);

    /**
     * 根据id删除购物车
     * @param cartItemUuid
     * @return
     */
    int deleteCartItemByUuid(@Param("cartItemUuid") String cartItemUuid);

    /**
     * 根据会员id查询购物车
     * @param memberUuid
     * @return
     */
    Cart selectCartItem(@Param("memberUuid") String memberUuid);

    /**
     * 根据会员id查询购物车
     * @param memberUuid
     * @return
     */
    String selectCartByMemberUuid(String memberUuid);

    /**
     * 分页查找购物车
     * @param cart
     * @return
     */
    List<Cart> findByPage(Cart cart);

}
