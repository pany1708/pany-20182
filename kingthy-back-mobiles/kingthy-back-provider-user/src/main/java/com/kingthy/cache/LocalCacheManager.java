package com.kingthy.cache;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import java.util.concurrent.TimeUnit;

/**
 * 描述：本地缓存
 *
 * @author likejie
 * @date 2017/12/4
 */
public class LocalCacheManager {


    /**本地缓存更新的redis channel*/
    public  static  final  String  LOCAL_CHCHE_UPDATE_CHANNEL="local.cache.update.channel";

    /**删除本地缓存redis channel*/
    public  static  final  String  LOCAL_CHCHE_DELETE_CHANNEL="local.cache.delete.channel";

    /**本地缓存对象*/
    private   static final Cache<String, String> CACHE = CacheBuilder.newBuilder()
            .expireAfterWrite(10, TimeUnit.DAYS).build();

    public static synchronized void put(String key,String value){
        CACHE.put(key,value);
    }
    public static synchronized String get(String key){
        return CACHE.getIfPresent(key);
    }
    public static synchronized void remove(String key){
        CACHE.invalidate(key);
    }
}
