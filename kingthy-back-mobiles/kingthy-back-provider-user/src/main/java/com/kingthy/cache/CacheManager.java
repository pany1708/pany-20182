package com.kingthy.cache;

import com.kingthy.common.KryoSerializeUtils;
import com.kingthy.dto.UpdateCacheQueueDTO;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.concurrent.TimeUnit;
/**
 * 描述：分布式缓存+本地缓存
 * 缓存获取策略：获取redis缓存。如果redis缓存不存在，获取本地缓存，并使用本地缓存值重新设置redis缓存。
 * 缓存更新策略：更新redis缓存，同时通过redis的发布/订阅功能，广播消息，通知其他服务器更新本地缓存。
 * @author likejie
 * @date 2017/12/14
 */
@Component
public class CacheManager {
    private static final Logger LOG = LoggerFactory.getLogger(CacheManager.class);

    @Autowired
    private RedisManager redisManager;

    /**
     * 获取redis缓存 [缓存时间随机生成，时间单位默认：TimeUnit.HOURS]
     *
     * @param key 缓存key
     */
    public String get(String key) {
        String data = "";
        try {
            data = redisManager.get(key);
        } catch (Exception ex) {
            LOG.error("redis缓存不可用,已从本地缓存获取数据", ex);
            return LocalCacheManager.get(key);
        }
        if (StringUtils.isNotBlank(data)) {
            return data;
        } else {
            /**redis缓存不存在时，获取本地缓存*/
            data = LocalCacheManager.get(key);
            if (StringUtils.isNotBlank(data)) {
                //更新缓存到redis
                redisManager.set(key, data, TimeUnit.HOURS);
            }
        }
        return data;
    }

    /**
     * 获取缓存
     *
     * @param key      缓存key
     * @param timeUnit 时间单位 [缓存时间随机生成，timeUnit 设置时间单位]
     */
    public String get(String key, TimeUnit timeUnit) {
        String data = "";
        try {
            data = redisManager.get(key);
        } catch (Exception ex) {
            LOG.error("redis缓存不可用,已从本地缓存获取数据", ex);
            return LocalCacheManager.get(key);
        }
        if (StringUtils.isNotBlank(data)) {
            return data;
        } else {
            /**redis缓存不存在时，获取本地缓存*/
            data = LocalCacheManager.get(key);
            if (StringUtils.isNotBlank(data)) {
                //更新缓存到redis
                redisManager.set(key, data, timeUnit);
            }
        }
        return data;
    }

    /**
     * 设置redis缓存，同时更新本地缓存
     */
    public void set(String key, String value) {

        redisManager.set(key, value);
        /**更新本地缓存**/
        updateLoaclCache(key, value);
    }

    /**
     * 设置redis缓存，同时更新本地缓存
     */
    public void set(String key, String value, TimeUnit timeUint) {
        redisManager.set(key, value, timeUint);
        /**更新本地缓存**/
        updateLoaclCache(key, value);
    }

    /**
     * 设置redis缓存，同时更新本地缓存
     */
    public void set(String key, String value, long time, TimeUnit timeUint) {
        redisManager.set(key, value, time, timeUint);
        /**更新本地缓存**/
        updateLoaclCache(key, value);
    }

    /**
     * 设置缓存，如果缓存为null，给null设置缓存失效时间。同时更新本地缓存
     */
    public void setByValue(String key, String value, TimeUnit timeUint) {

        redisManager.setByValue(key, value, timeUint);
        /**更新本地缓存**/
        updateLoaclCache(key, value);
    }

    /**
     * 设置缓存，如果缓存为null，给null设置缓存失效时间。同时更新本地缓存
     */
    public void setByValue(String key, String value, long time, TimeUnit timeUint) {

        redisManager.setByValue(key, value, time, timeUint);
        /**更新本地缓存**/
        updateLoaclCache(key, value);
    }

    /**
     * 获取缓存过期时间
     */
    public long getExpire(String key) {
        return redisManager.getExpire(key);
    }

    /**
     * 更新redis缓存
     */
    public void updateCache(UpdateCacheQueueDTO updateCacheQueueDTO) {

        if (redisManager.updateCache(updateCacheQueueDTO)) {
            //发送消息到redis，由redis监听器删除本地缓存
            redisManager.publish(LocalCacheManager.LOCAL_CHCHE_DELETE_CHANNEL, updateCacheQueueDTO.getCacheKey());
        }
    }

    /**
     * 更新本地缓存
     */
    private void updateLoaclCache(String key, String value) {
        if (StringUtils.isNotBlank(key)) {
            UpdateCacheQueueDTO updateCacheQueueDTO = new UpdateCacheQueueDTO();
            updateCacheQueueDTO.setData(value);
            updateCacheQueueDTO.setCacheKey(key);
            //通过redis 发送广播消息，通知其他服务器更新本地缓存
            redisManager.publish(LocalCacheManager.LOCAL_CHCHE_UPDATE_CHANNEL, KryoSerializeUtils.serializationObject(updateCacheQueueDTO));
        }
    }
}
