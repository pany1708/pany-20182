/**
 * 系统项目名称
 * com.kingthy.service.impl
 * MemberOrderServiceImpl.java
 * 
 * 2017年4月24日-上午9:44:28
 *  2017金昔科技有限公司-版权所有
 *
 */
package com.kingthy.service.impl;



import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.github.pagehelper.PageInfo;
import com.kingthy.cache.CacheManager;
import com.kingthy.cache.RedisManager;
import com.kingthy.common.KryoSerializeUtils;
import com.kingthy.constant.CacheKeysConstants;
import com.kingthy.dto.MemberOrderDTO;
import com.kingthy.dubbo.goods.service.GoodsDubboService;
import com.kingthy.dubbo.order.service.OrderItemDubboService;
import com.kingthy.entity.Goods;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.kingthy.request.MemberOrderReq;
import com.kingthy.service.MemberOrderService;

/**
 * @author 李克杰 2017年4月24日 上午9:44:28
 * @version 1.0.0
 */
@Service("memberOrderService")
public class MemberOrderServiceImpl implements MemberOrderService
{

    @Reference(version = "1.0.0")
    private GoodsDubboService goodsDubboService;

    @Reference(version = "1.0.0")
    private OrderItemDubboService orderItemDubboService;


    @Autowired
    private CacheManager cacheManager;

    private  static final String CACHE_KEY_PREFIX="page-1-";


    private  PageInfo<MemberOrderDTO> pageQueryMemberOrders(MemberOrderReq req){

        PageInfo<MemberOrderDTO> pageInfo=orderItemDubboService.pageQueryMemberOrders(req);
        List<MemberOrderDTO> list=pageInfo.getList();
        if(list.size()>0){
            List<String> productIds=new ArrayList<>();
            for(MemberOrderDTO dto:list){
                productIds.add(dto.getProductUuid());
            }
            List<Goods> goodsList= goodsDubboService.getGoodsListByIds(productIds);
            for(MemberOrderDTO dto:list){
                for(Goods goods:goodsList){
                    if(dto.getProductUuid().equals(goods.getUuid())){
                        dto.setProductImage(goods.getGoodsImage());
                    }
                }
            }
        }
        return pageInfo;
    }
    @Override
    public PageInfo<MemberOrderDTO> pageGetOrderList(MemberOrderReq req) {

        PageInfo<MemberOrderDTO> pageInfo = new PageInfo<>();
        pageInfo = pageQueryMemberOrders(req);
        //缓存第一页数据，判断是否第一页查询
        /*boolean isDefault=(req.getPageNum() == null || req.getPageNum() <= 1)&&req.getOrderStatus()==null;
        if (isDefault) {
            //从缓存获取第一屏数据
            String cacheKey = CacheKeysConstants.generateCacheKey(MemberOrderDTO.class, CACHE_KEY_PREFIX + req.getMemberUuid());
            String cacheData = cacheManager.get(cacheKey);
            if (StringUtils.isNotBlank(cacheData)) {
                pageInfo = JSON.parseObject(cacheData, new TypeReference<PageInfo<MemberOrderDTO>>() {
                }.getType());
            } else {
                //缓存第一屏数据
                //获取失效时间，如果已经失效，再查询数据库，即使为空直，如果没失效，也不会直接访问数据库
                long expire = cacheManager.getExpire(cacheKey);
                if (expire <= 0) {
                    pageInfo = pageQueryMemberOrders(req);
                    if(pageInfo.getList().size()>0) {
                        cacheData = JSON.toJSONString(pageInfo);
                    }else{
                        cacheData=null;
                    }
                    cacheManager.setByValue(cacheKey, cacheData, TimeUnit.MINUTES);
                }
            }
        } else {
            //从数据库获取数据
            pageInfo = pageQueryMemberOrders(req);
        }*/
        return pageInfo;
    }
}
