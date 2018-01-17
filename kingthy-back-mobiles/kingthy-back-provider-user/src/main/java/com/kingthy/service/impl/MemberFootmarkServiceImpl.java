package com.kingthy.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.github.pagehelper.PageInfo;
import com.kingthy.cache.CacheManager;
import com.kingthy.cache.RedisManager;
import com.kingthy.constant.CacheKeysConstants;
import com.kingthy.dto.*;
import com.kingthy.dubbo.goods.service.GoodsDubboService;
import com.kingthy.dubbo.user.service.MemberFootmarkDubboService;
import com.kingthy.entity.Goods;
import com.kingthy.entity.MemberFootmark;
import com.kingthy.request.MemberFootmarkPageReq;
import com.kingthy.request.MemberFootmarkReq;
import com.kingthy.response.WebApiResponse;
import com.kingthy.service.MemberFootmarkService;
import com.kingthy.util.BeanMapperUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;


/**
 *
 * 我的足迹 [业务处理实现类]
 * 
 * @author likejie 2017-4-21
 * 
 * @version 1.0.0
 *
 */
@Service("memberFootmarkService")
public class MemberFootmarkServiceImpl implements MemberFootmarkService
{

    @Reference(version = "1.0.0")
    private MemberFootmarkDubboService memberFootmarkDubboService;

    @Reference(version = "1.0.0")
    private GoodsDubboService goodsDubboService;


    @Autowired
    private CacheManager cacheManager;

    private  static final String CACHE_KEY_PREFIX="page-1-";
    @Override
    public List<MemberFootmarkDTO> getMemberFootmarkList(String memberUuid)
    {
        List<MemberFootmarkDTO> mfDtoList=new ArrayList<>();
        //查询足迹
        List<MemberFootmark> list=memberFootmarkDubboService.getFootmarkByMemberUuid(memberUuid);
        List<String> productUuids=new ArrayList<>();
        for(MemberFootmark mf : list){
            productUuids.add(mf.getProductUuid());
        }
        if(!productUuids.isEmpty()) {
            //查询商品详情
            List<Goods> goodsList = goodsDubboService.getGoodsListByIds(productUuids);
            for (MemberFootmark mf : list) {
                MemberFootmarkDTO dto = new MemberFootmarkDTO();
                dto.setUuid(mf.getUuid());
                dto.setCreateDate(mf.getCreateDate());
                dto.setProductUuid(mf.getProductUuid());
                for (Goods goods : goodsList) {
                    if (goods.getUuid().equals(mf.getProductUuid())) {
                        dto.setDesinger(goods.getDesinger());
                        dto.setGoodsDetails(goods.getGoodsDetails());
                        dto.setGoodsImage(goods.getGoodsImage());
                        dto.setGoodsName(goods.getGoodsName());
                        dto.setStandardPrice(goods.getStandardPrice());
                    }
                }
                mfDtoList.add(dto);
            }
        }
        return mfDtoList;
    }


    @Override
    public PageInfo<MemberFootmarkDTO> pageGetMemberFootmarkList(MemberFootmarkPageReq req){

        PageInfo<MemberFootmarkDTO> pageInfo=new PageInfo<>();

        if (Optional.ofNullable(req.getPageNum()).orElse(1)<=1
                &&Optional.ofNullable(req.getPageSize()).orElse(0)>0) {
            //当pageNum<=1时，从缓存读取数据
            String cacheKey = CacheKeysConstants.generateCacheKey(MemberFootmarkDTO.class, CACHE_KEY_PREFIX + req.getMemberUuid());
            String cacheData = cacheManager.get(cacheKey);
            if (StringUtils.isNotBlank(cacheData)) {
                pageInfo = JSON.parseObject(cacheData, new TypeReference<PageInfo<MemberFootmarkDTO>>() {}.getType());
            }
            if(pageInfo.getList().size()<=0){
                //缓存第一页
                //获取失效时间，如果已经失效，再查询数据库，即使为空直，如果没失效，也不会直接访问数据库
                long expire = cacheManager.getExpire(cacheKey);
                if (expire <= 0) {
                    pageInfo =pageGetMemberFootmarkList(req.getMemberUuid(), req.getPageSize(), req.getPageNum());
                    if(pageInfo.getList().size()>0) {
                        cacheData = JSON.toJSONString(pageInfo);
                    }else{
                        cacheData=null;
                    }
                    cacheManager.setByValue(cacheKey, cacheData, 30, TimeUnit.MINUTES);
                }
            }
        } else {
            //当pageNum>1时，查询数据库
            pageInfo = pageGetMemberFootmarkList(req.getMemberUuid(), req.getPageSize(), req.getPageNum());
        }
        return pageInfo;

    }



    private PageInfo<MemberFootmarkDTO> pageGetMemberFootmarkList(String memberUuid,int pageNum,int pageSize) {

        MemberFootmark cond = new MemberFootmark();
        cond.setMemberUuid(memberUuid);
        PageInfo<MemberFootmark> pageInfo = memberFootmarkDubboService.queryPage(pageNum, pageSize, cond);
        List<MemberFootmark> list = pageInfo.getList();
        List<MemberFootmarkDTO> mfDtoList = new ArrayList<>();
        if (list.size() > 0) {
            List<String> productUuids = new ArrayList<>();
            for (MemberFootmark mf : list) {
                productUuids.add(mf.getProductUuid());
            }
            //查询商品详情
            List<Goods> goodsList = goodsDubboService.getGoodsListByIds(productUuids);
            for (MemberFootmark mf : list) {
                MemberFootmarkDTO dto = new MemberFootmarkDTO();
                dto.setUuid(mf.getUuid());
                dto.setCreateDate(mf.getCreateDate());
                dto.setProductUuid(mf.getProductUuid());
                for (Goods goods : goodsList) {
                    if (goods.getUuid().equals(mf.getProductUuid())) {
                        dto.setDesinger(goods.getDesinger());
                        dto.setGoodsDetails(goods.getGoodsDetails());
                        dto.setGoodsImage(goods.getGoodsImage());
                        dto.setGoodsName(goods.getGoodsName());
                        dto.setStandardPrice(goods.getStandardPrice());
                    }
                }
                mfDtoList.add(dto);
            }
        }
        PageInfo<MemberFootmarkDTO> result = new PageInfo<>();
        result.setList(mfDtoList);
        result.setPageNum(pageInfo.getPageNum());
        result.setPageSize(pageInfo.getPageSize());
        result.setTotal(pageInfo.getTotal());
        return result;
    }
    @Override
    public WebApiResponse addMemberFootmark(MemberFootmarkReq req) {

        MemberFootmark memberFootmark = new MemberFootmark();
        BeanMapperUtil.copyProperties(req, memberFootmark);
        // 检查是否已添加该商品， 商品只能添加一次
        int count = memberFootmarkDubboService.selectCount(memberFootmark);
        if (count == 0) {
            Date nowDate = new Date();
            memberFootmark.setCreateDate(nowDate);
            memberFootmark.setModifyDate(nowDate);
            int res = memberFootmarkDubboService.insert(memberFootmark);
            if (res > 0) {
                //更新缓存
                String cacheKey = CacheKeysConstants.generateCacheKey(MemberFootmarkDTO.class, CACHE_KEY_PREFIX + req.getMemberUuid());
                UpdateCacheQueueDTO updateCacheQueueDTO = new UpdateCacheQueueDTO();
                updateCacheQueueDTO.setCacheKey(cacheKey);
                cacheManager.updateCache(updateCacheQueueDTO);

                return WebApiResponse.success();
            }else{
                return WebApiResponse.error(WebApiResponse.ResponseMsg.FAIL.getValue());
            }
        } else {
            return WebApiResponse.error("已添加到我的足迹");
        }

    }
    
    @Override
    public int batchDeleteFootmark(String memberUuid,List<String> uuids)
    {
        int res= memberFootmarkDubboService.batchDeleteFootmark(uuids);
        if(res>0) {
            //更新缓存
            String cacheKey = CacheKeysConstants.generateCacheKey(MemberFootmarkDTO.class, CACHE_KEY_PREFIX + memberUuid);
            UpdateCacheQueueDTO updateCacheQueueDTO = new UpdateCacheQueueDTO();
            updateCacheQueueDTO.setCacheKey(cacheKey);
            cacheManager.updateCache(updateCacheQueueDTO);
        }
        return res;
    }
    
    @Override
    public List<SimliarProductDTO> getSimilarProduct(String productUuid)
    {
        return null;
    }
}
