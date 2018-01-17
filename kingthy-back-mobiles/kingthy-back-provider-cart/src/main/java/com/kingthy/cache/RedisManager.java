package com.kingthy.cache;


import com.kingthy.common.CommonUtils;
import com.kingthy.constant.CacheMqNameConstans;
import com.kingthy.dto.UpdateCacheQueueDTO;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * 描述：分布式缓存redis
 * @author  likejie
 * @date 2017.11.1
 */
@Component
public class RedisManager  {

    private static final Logger LOG = LoggerFactory.getLogger(RedisManager.class);

    @Autowired
    private  StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    /***针对空值到缓存设置一个过期时间,默认10分钟***/
    @Value("${cache.nullValueExpire:10}")
    private long nullValueExpire;

    public long getNullValueExpire() {
        return nullValueExpire;
    }
    /**
     *
     * 给缓存key设置一个null值，并设置失效时间，预防缓存击穿
     *
     */
    public void setNull(String key) {

        if (StringUtils.isNotBlank(key)) {
            stringRedisTemplate.opsForValue().set(key, "", this.nullValueExpire, TimeUnit.MINUTES);
        }
    }


    public synchronized String get(String key) {

        if(StringUtils.isNotBlank(key)){
           return stringRedisTemplate.opsForValue().get(key);
        }
        return null;
    }
    public synchronized void set(String key, String value) {

        if (StringUtils.isNotBlank(key)) {
            stringRedisTemplate.opsForValue().set(key, value);
        }
    }

    public synchronized void set(String key, String value, TimeUnit timeUint) {
        if (StringUtils.isNotBlank(key)) {
            stringRedisTemplate.opsForValue().set(key, value, CommonUtils.getRandomNum(), timeUint);
        }
    }
    public synchronized void set(String key, String value, long time, TimeUnit timeUint) {
        if (StringUtils.isNotBlank(key)) {
            stringRedisTemplate.opsForValue().set(key, value, time, timeUint);
        }
    }
    /**
     * 设置缓存，如果缓存为null，给null设置缓存失效时间。
     * @param key
     * @param value
     * @param timeUint
     */
    public synchronized void setByValue(String key, String value,TimeUnit timeUint) {

        if (StringUtils.isNotBlank(key)) {
            if (StringUtils.isBlank(value)) {
                //给缓存key设置一个null值，并设置失效时间，预防缓存击穿
                setNull(key);
            } else {
                stringRedisTemplate.opsForValue().set(key, value, CommonUtils.getRandomNum(), timeUint);
            }
        }
    }
    /**
     * 设置缓存，如果缓存为null，给null设置缓存失效时间。
     * @param key
     * @param value
     * @param time : 过期时间，随机生成 CommonUtils.getRandomNum()
     * @param timeUint
     */
    public synchronized void setByValue(String key, String value, long time, TimeUnit timeUint) {

        if (StringUtils.isNotBlank(key)) {
            if (StringUtils.isBlank(value)) {
                //给缓存key设置一个null值，并设置失效时间，预防缓存击穿
                setNull(key);
            } else {
                stringRedisTemplate.opsForValue().set(key, value, time, timeUint);
            }
        }
    }
    public synchronized boolean hasKey(String key){
        if(StringUtils.isNotBlank(key)){
            return stringRedisTemplate.hasKey(key);
        }
        return false;
    }

    /**删除redis缓存**/
    public synchronized void delete(String key) {
        if (StringUtils.isNotBlank(key)) {
            stringRedisTemplate.delete(key);
        }
    }


    public  Set<String> keys(String key) {
        if (StringUtils.isNotBlank(key)) {
            return stringRedisTemplate.keys(key);
        }
        return new HashSet<>();
    }
    /**
     * 获取缓存过期时间
     */
    public  synchronized long getExpire(String key) {
        if (StringUtils.isNotBlank(key)) {
            return stringRedisTemplate.getExpire(key);
        }
        return 0;
    }


    /**
     * 设置redis缓存过期时间
     */
    public  synchronized Boolean expire(String key, long time, TimeUnit timeUnit) {
        if (StringUtils.isNotBlank(key)) {
            return stringRedisTemplate.expire(key, time, timeUnit);
        }
        return false;
    }
    /**
     *
     * 发布redis消息
     */
    public synchronized void publish(String channel,String msg){

        try {
            stringRedisTemplate.convertAndSend(channel, msg);
        }catch (Exception ex){
            LOG.error(ex.toString());
        }
    }
   /**
    *
    * 更新redis缓存
    */
   public synchronized boolean updateCache(UpdateCacheQueueDTO updateCacheQueueDTO) {

       //缓存key
       String cacheKey = updateCacheQueueDTO.getCacheKey();
       boolean isUpdate = false;
       if (stringRedisTemplate.hasKey(cacheKey)) {
           //缓存数据
           String cacheData = get(cacheKey);
           //过期时间
           long expire = getExpire(cacheKey);
           //检查是否需要更新缓存
           isUpdate = StringUtils.isNotBlank(cacheData) || (cacheData == null && expire > 0);
           if (isUpdate) {
               //发送消息到mq，让mq执行reids更新操作
               UpdateCacheQueueDTO queue = new UpdateCacheQueueDTO();
               queue.setCacheKey(cacheKey);
               rabbitTemplate.convertAndSend(CacheMqNameConstans.EXCHANGE_NAME, CacheMqNameConstans.ROUTING_KEY_NAME, updateCacheQueueDTO);
               isUpdate = true;
           }
       }
       return isUpdate;
   }
}
