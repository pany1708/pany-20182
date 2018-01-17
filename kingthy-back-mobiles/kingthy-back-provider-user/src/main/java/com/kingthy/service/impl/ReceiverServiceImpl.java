package com.kingthy.service.impl;


import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.github.pagehelper.PageInfo;
import com.kingthy.cache.CacheManager;
import com.kingthy.cache.RedisManager;
import com.kingthy.constant.CacheKeysConstants;
import com.kingthy.dto.UpdateCacheQueueDTO;
import com.kingthy.dubbo.user.service.ReceiverDubboService;
import com.kingthy.request.ReceiverReq;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.kingthy.dto.MemberReceiverDTO;
import com.kingthy.entity.Receiver;
import com.kingthy.service.ReceiverService;
/**
 *
 * 会员收获地址 [业务处理实现类]
 * 
 * @author likejie 2017-4-20
 * 
 * @version 1.0.0
 *
 */
@Service("receiverService")
public class ReceiverServiceImpl implements ReceiverService
{

    //dubbo服务提供端接口
    @Reference(version = "1.0.0")
    private ReceiverDubboService receiverDubboService;
    @Autowired
    private CacheManager cacheManager;


    private  static final String CACHE_KEY_PREFIX="page-1-";
    @Override
    public Receiver getReceiverByUuid(String uuid)
    {
       return receiverDubboService.selectByUuid(uuid);
    }
    
    @Override
    public List<MemberReceiverDTO> getMemberReceiverList(String memberUuid) {

        return receiverDubboService.getMemberReceiverList(memberUuid);
    }
    @Override
    public int addReceiver(Receiver receiver)
    {
        // 设置指定地址为默认地址
        int res = receiverDubboService.insert(receiver);
        if (res > 0)
        {
            //更新缓存
            String cacheKey = CacheKeysConstants.generateCacheKey(Receiver.class, CACHE_KEY_PREFIX + receiver.getMemberUuid());
            UpdateCacheQueueDTO updateCacheQueueDTO=new UpdateCacheQueueDTO();
            updateCacheQueueDTO.setCacheKey(cacheKey);
            cacheManager.updateCache(updateCacheQueueDTO);
        }
        return res;
    }
    @Override
    public int updateReceiver(Receiver receiver)
    {
        int res= receiverDubboService.updateByUuid(receiver);
        if(res>0){
            //更新缓存
            String cacheKey = CacheKeysConstants.generateCacheKey(Receiver.class, CACHE_KEY_PREFIX+ receiver.getMemberUuid());
            UpdateCacheQueueDTO updateCacheQueueDTO=new UpdateCacheQueueDTO();
            updateCacheQueueDTO.setCacheKey(cacheKey);
            cacheManager.updateCache(updateCacheQueueDTO);
        }
        return  res;

    }
    
    @Override
    public int deleteReceiver(String uuid,String memberUuid)
    {
        int res= receiverDubboService.deleteReceiver(uuid,memberUuid);
        if(res>0){
            //更新缓存
            String cacheKey = CacheKeysConstants.generateCacheKey(Receiver.class, CACHE_KEY_PREFIX + memberUuid);
            UpdateCacheQueueDTO updateCacheQueueDTO=new UpdateCacheQueueDTO();
            updateCacheQueueDTO.setCacheKey(cacheKey);
            cacheManager.updateCache(updateCacheQueueDTO);
        }
        return 0;
    }
    @Override
    public int setDefaultReceiver(String memberUuid, String uuid)
    {

        int res = receiverDubboService.setDefaultReceiver(memberUuid, uuid);
        if (res > 0)
        {
            //更新缓存
            String cacheKey = CacheKeysConstants.generateCacheKey(Receiver.class, CACHE_KEY_PREFIX + memberUuid);
            UpdateCacheQueueDTO updateCacheQueueDTO=new UpdateCacheQueueDTO();
            updateCacheQueueDTO.setCacheKey(cacheKey);
            cacheManager.updateCache(updateCacheQueueDTO);
        }
        return res;

    }
    
    @Override
    public Receiver getDefaultReceiver(String memberUuid)
    {
        return receiverDubboService.getDefaultReceiver(memberUuid);
    }
    @Override
    public PageInfo<Receiver> pageGetReceiverList(ReceiverReq req){

        PageInfo<Receiver> pageInfo = new PageInfo<>();
        //查询条件
        Receiver cond=new Receiver();
        cond.setMemberUuid(req.getMemberUuid());
        cond.setDelFlag(false);
        if (Optional.ofNullable(req.getPageNum()).orElse(1)<=1) {
            //当pageNum<=1时，从缓存读取数据
            String cacheKey = CacheKeysConstants.generateCacheKey(Receiver.class, CACHE_KEY_PREFIX+ req.getMemberUuid());
            String cacheData = cacheManager.get(cacheKey);
            if (StringUtils.isNotBlank(cacheData)) {
                pageInfo = JSON.parseObject(cacheData, new TypeReference<PageInfo<Receiver>>() {
                }.getType());
            } else {
                //缓存第一页
                //获取失效时间，如果已经失效，再查询数据库，即使为空直，如果没失效，也不会直接访问数据库
                long expire = cacheManager.getExpire(cacheKey);
                if (expire <= 0) {
                    pageInfo= receiverDubboService.queryPage(req.getPageNum(),req.getPageSize(),cond);
                    if(pageInfo.getList().size()>0) {
                        cacheData = JSON.toJSONString(pageInfo);
                    }else{
                        cacheData=null;
                    }
                    cacheManager.setByValue(cacheKey, cacheData, TimeUnit.MINUTES);
                }
            }
        } else {
            //当pageNum>1时，查询数据库
            pageInfo= receiverDubboService.queryPage(req.getPageNum(),req.getPageSize(),cond);
        }
        return pageInfo;
    }
}
