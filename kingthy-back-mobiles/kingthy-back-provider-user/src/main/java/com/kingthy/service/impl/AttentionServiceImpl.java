package com.kingthy.service.impl;


import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.github.pagehelper.PageInfo;
import com.kingthy.annotation.QueryCache;
import com.kingthy.annotation.QueryCacheKey;
import com.kingthy.cache.CacheManager;
import com.kingthy.cache.RedisManager;
import com.kingthy.common.CacheNameSpace;
import com.kingthy.constant.CacheKeysConstants;
import com.kingthy.dto.*;
import com.kingthy.dubbo.user.service.AttentionDubboService;
import com.kingthy.dubbo.user.service.MemberDubboService;
import com.kingthy.entity.Attention;
import com.kingthy.entity.Member;
import com.kingthy.request.AttentionPageReq;
import com.kingthy.request.AttentionReq;
import com.kingthy.response.WebApiResponse;
import com.kingthy.service.AttentionService;
import org.apache.commons.lang.StringUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

/**
 *
 * 我的关注 [业务处理实现类]
 * @author likejie 2017-5-4
 * @version 1.0.0
 */
@Service("attentionService")
public class AttentionServiceImpl implements AttentionService
{

    private static final Logger LOGGER= LoggerFactory.getLogger(AttentionServiceImpl.class);
    //创建dubbo服务接口实例
    @Reference(version = "1.0.0")
    private AttentionDubboService attentionDubboService;

    @Reference(version = "1.0.0")
    private  MemberDubboService memberDubboService;

    @Autowired
    private CacheManager cacheManager;

    private  static final String CACHE_KEY_PREFIX="page-1-";
    @Override
    public List<AttentionMemberDTO> getAttentionList(String memberUuid)
    {
        return attentionDubboService.getAttentionMemberList(memberUuid);
    }
    @Override
    public PageInfo<AttentionMemberDTO> pageGetAttentionList(AttentionPageReq req) {
        PageInfo<AttentionMemberDTO> pageInfo = new PageInfo<>();

        if (Optional.ofNullable(req.getPageNum()).orElse(1)<=1) {
            //当pageNum<=1时，从缓存读取数据
            String cacheKey = CacheKeysConstants.generateCacheKey(AttentionMemberDTO.class, CACHE_KEY_PREFIX + req.getMemberUuid());
            String cacheData = cacheManager.get(cacheKey);
            if (StringUtils.isNotBlank(cacheData)) {
                pageInfo = JSON.parseObject(cacheData, new TypeReference<PageInfo<AttentionMemberDTO>>() {
                }.getType());
            } else {
                //缓存第一页
                //获取失效时间，如果已经失效，再查询数据库，即使为空直，如果没失效，也不会直接访问数据库
                long expire = cacheManager.getExpire(cacheKey);
                if (expire <= 0) {
                    pageInfo = attentionDubboService.pagingSelectAttentionUsers(req.getMemberUuid(), req.getPageSize(), req.getPageNum());
                    if(pageInfo.getList().size()>0) {
                        cacheData = JSON.toJSONString(pageInfo);
                    }else{
                        cacheData=null;
                    }
                    cacheManager.setByValue(cacheKey, cacheData,TimeUnit.MINUTES);
                }
            }
        } else {
            //当pageNum>1时，查询数据库
            pageInfo = attentionDubboService.pagingSelectAttentionUsers(req.getMemberUuid(), req.getPageSize(), req.getPageNum());
        }
        return pageInfo;
    }

    @Override
    public WebApiResponse addAttention(AttentionReq req)
    {
        String memberUuid=req.getMemberUuid();
        if (memberUuid.equals(req.getAttentionUuid()))
        {
            return WebApiResponse.error("不能关注自己");
        }
        Member member=memberDubboService.selectByUuid(req.getAttentionUuid());
        if(member==null){
            return WebApiResponse.error("用户不存在，不能关注");
        }
        int count = attentionDubboService.getAttentionCount(memberUuid, req.getAttentionUuid());
        if (count > 0)
        {
            return WebApiResponse.error("该用户已关注");
        }
        Date now = new Date();
        Attention attention=new Attention();
        attention.setAttentionUuid(req.getAttentionUuid());
        attention.setMemberUuid(req.getMemberUuid());
        attention.setCreateDate(now);
        attention.setModifyDate(now);
        attention.setVersion(0);
        int res= attentionDubboService.insert(attention);
        if(res==1){
            //更新缓存
            String cacheKey = CacheKeysConstants.generateCacheKey(AttentionMemberDTO.class, CACHE_KEY_PREFIX + req.getMemberUuid());
            UpdateCacheQueueDTO updateCacheQueueDTO = new UpdateCacheQueueDTO();
            updateCacheQueueDTO.setCacheKey(cacheKey);
            cacheManager.updateCache(updateCacheQueueDTO);
            return WebApiResponse.success();
        }else{
            return WebApiResponse.error(WebApiResponse.ResponseMsg.FAIL.getValue());
        }
    }
    /**
     *
     *
     * 批量删除我的关注
     */
    @Override
    public int batchDeleteAttention(String memberUuid, List<String> attentionUuids)
    {
        int res= attentionDubboService.batchDeleteAttention(memberUuid, attentionUuids);
        if(res>0){
            //更新缓存
            String cacheKey = CacheKeysConstants.generateCacheKey(AttentionMemberDTO.class, CACHE_KEY_PREFIX + memberUuid);
            UpdateCacheQueueDTO updateCacheQueueDTO = new UpdateCacheQueueDTO();
            updateCacheQueueDTO.setCacheKey(cacheKey);
            cacheManager.updateCache(updateCacheQueueDTO);
        }
        return res;
    }
    @Override
    public int getAttentionMemberCount(String memberUuid)
    {
        return attentionDubboService.getAttentionCount(memberUuid, "");
    }
    @Override
    public int getFansCount(String memberUuid)
    {
        return attentionDubboService.getAttentionCount("", memberUuid);
    }
    @Override
    public List<FansDTO> getFansLilst(String memberUuid)
    {
        return attentionDubboService.getFansLilst(memberUuid);
    }

    @Override
    public PageInfo<FansDTO> pageGetFansList(AttentionPageReq req) {
        return attentionDubboService.pagingSelectFans(req.getMemberUuid(),req.getPageSize(),req.getPageNum());
    }
}
