package com.kingthy.constant;

/**
 * 描述：缓存 通用key前缀
 *
 * @author likejie
 * @date 2017/12/8
 */
public interface CacheKeysConstants {

    /**手机验证码key 前缀**/
    String PHONE_VERIFY_PREFIX="verify-code:";

    /**登录用户key前缀**/
    String  LONGIN_PREFIX="login:";




    /**缓存key最大长度限制*/
    int MAXLENGTH = 20;
    /**
     * 动态生成缓存key
     * @param objectClass
     * @param key
     * @return
     */
    static String generateCacheKey(Class objectClass,String key) {

        //控制长度
        String className = objectClass.getSimpleName();
        if (className.length() > MAXLENGTH) {
            className = className.substring(0, 20);
        }
        return className +":"+ key;
    }
}
