package com.kingthy.cache;


import com.kingthy.common.KryoSerializeUtils;
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
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * 描述：缓存操作
 * @author  likejie
 * @date 2017.11.1
 */
@Component
public class RedisManager  {

    private static final Logger LOG = LoggerFactory.getLogger(RedisManager.class);


    private static final int MAXLENGTH = 20;

    @Autowired
    private  StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    /***针对空值到缓存设置一个过期时间***/
    @Value("${cache.nullValueExpire}")
    private long nullValueExpire;

    public long getNullValueExpire() {
        return nullValueExpire;
    }
    /**
     * 生成缓存key
     * @param objectClass
     * @param key
     * @return
     */
    public String generateCacheKey(Class objectClass,String key) {

        //控制长度
        String className = objectClass.getSimpleName();
        if (className.length() > MAXLENGTH) {
            className = className.substring(0, 20);
        }
        return className +":"+ key;
    }
    /**
     *
     * 给缓存key设置一个null值，并设置失效时间，预防缓存击穿
     *
     */
    public   void setNull(String key) {

        if (StringUtils.isNotBlank(key)) {
            stringRedisTemplate.opsForValue().set(key, "",this.nullValueExpire,TimeUnit.MINUTES);
        }
    }
    public synchronized String get(String key) {

        String data;
        if (StringUtils.isBlank(key)) {
            return null;
        }
        try {
            data = stringRedisTemplate.opsForValue().get(key);
        } catch (Exception ex) {
            LOG.error("RedisManager.get error: redis缓存不可用",ex);
            return LocalCacheManager.get(key);
        }
        if (StringUtils.isNotBlank(data)) {
            return data;
        } else {
            /**redis缓存不存在时，获取本地缓存*/
            data =LocalCacheManager.get(key);
            if (StringUtils.isNotBlank(data)) {
                //更新缓存到redis
                stringRedisTemplate.opsForValue().set(key, data, getRandomExpire(), TimeUnit.DAYS);
            }
        }
        return data;
    }
    public synchronized String get(String key,TimeUnit timeUnit) {
        String data;
        if (StringUtils.isBlank(key)) {
            return null;
        }
        try {
            data = stringRedisTemplate.opsForValue().get(key);
        } catch (Exception ex) {
            LOG.error("RedisManager.get error: redis缓存不可用",ex);
            return LocalCacheManager.get(key);
        }
        if (StringUtils.isNotBlank(data)) {
            return data;
        } else {
            /**redis缓存不存在时，获取本地缓存*/
            data = LocalCacheManager.get(key);
            if (StringUtils.isNotBlank(data)) {
                //更新缓存到redis
                stringRedisTemplate.opsForValue().set(key, data, getRandomExpire(), timeUnit);
            }
        }
        return data;
    }
    public synchronized void set(String key, String value) {

        if (StringUtils.isNotBlank(key)) {
            stringRedisTemplate.opsForValue().set(key, value);
            /**更新本地缓存**/
            updateLoaclCache(key,value);
        }
    }

    public synchronized void set(String key, String value, long time) {
        if (StringUtils.isNotBlank(key)) {
            stringRedisTemplate.opsForValue().set(key, value, time);
            /**更新本地缓存**/
            updateLoaclCache(key,value);
        }

    }

    public synchronized void set(String key, String value, long time, TimeUnit timeUint) {
        if (StringUtils.isNotBlank(key)) {
            stringRedisTemplate.opsForValue().set(key, value, time, timeUint);
            /**更新本地缓存**/
            updateLoaclCache(key,value);
        }
    }
    /**
     * 设置缓存，如果缓存为null，给null设置缓存失效时间。
     *
     */
    public synchronized void setByValue(String key, String value, long time, TimeUnit timeUint) {

        if (StringUtils.isNotBlank(key)) {
            if (StringUtils.isBlank(value)) {
                //给缓存key设置一个null值，并设置失效时间，预防缓存击穿
                setNull(key);
            } else {
                stringRedisTemplate.opsForValue().set(key, value, time, timeUint);
                /**更新本地缓存**/
                updateLoaclCache(key,value);
            }
        }
    }
    public synchronized boolean hasKey(String key){
        if(StringUtils.isNotBlank(key)){
            return stringRedisTemplate.hasKey(key);
        }
        return false;
    }

    public synchronized void delete(String key) {
        if (StringUtils.isNotBlank(key)) {
            stringRedisTemplate.delete(key);
            /**删除本地缓存**/
            //localCacheManager.remove(key);
        }
    }

    public  Set<String> keys(String key) {
        if (StringUtils.isNotBlank(key)) {
            return stringRedisTemplate.keys(key);
        }
        return new HashSet<>();
    }

    public  long getExpire(String key) {
        if (StringUtils.isNotBlank(key)) {
            return stringRedisTemplate.getExpire(key);
        }
        return 0;
    }

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
    public int getRandomExpire(){
        Random rd=new Random();
        return rd.nextInt(10)+1;
    }
   /**
    *
    * 获取一个随机数，设置缓存超时时间
    */
   public int getRandomExpire(Integer count){
       Random rd=new Random();
       return rd.nextInt(count)+1;
   }
   /**
    *
    * 更新redis缓存
    */
   public void updateCache(UpdateCacheQueueDTO updateCacheQueueDTO){

       //缓存key
       String cacheKey=updateCacheQueueDTO.getCacheKey();

       if(stringRedisTemplate.hasKey(cacheKey)){
           //缓存数据
           String cacheData=get(cacheKey);
           //过期时间
           long expire = getExpire(cacheKey);
           //检查是否需要更新缓存
           Boolean isUpdate=StringUtils.isNotBlank(cacheData)||(cacheData==null&&expire>0);
           if(isUpdate){
               //发送消息到mq，让mq执行reids更新操作
               UpdateCacheQueueDTO queue=new UpdateCacheQueueDTO();
               queue.setCacheKey(cacheKey);
               rabbitTemplate.convertAndSend(CacheMqNameConstans.EXCHANGE_NAME,CacheMqNameConstans.ROUTING_KEY_NAME,updateCacheQueueDTO);
               //发送消息到redis，由redis监听器删除本地缓存
               publish(LocalCacheManager.LOCAL_CHCHE_DELETE_CHANNEL,cacheKey);
           }
       }
   }
   /**
    * 更新本地缓存
    */
   private void updateLoaclCache(String key,String value){
       UpdateCacheQueueDTO updateCacheQueueDTO=new UpdateCacheQueueDTO();
       updateCacheQueueDTO.setData(value);
       updateCacheQueueDTO.setCacheKey(key);
       //通过redis 发送广播消息，通知其他服务器更新本地缓存
       publish(LocalCacheManager.LOCAL_CHCHE_UPDATE_CHANNEL,KryoSerializeUtils.serializationObject(updateCacheQueueDTO));
   }

}
