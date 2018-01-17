package com.kingthy.conf;

import lombok.Data;
import lombok.ToString;


/**
 * zookeeper 配置
 * @author  likejie 2017/11/1.
 */
@Data
@ToString
public  class ZkConfig {
    /**缓存路径*/
    private String watchPath;
    /**服务器地址*/
    private String serverAddress;
    /**--*/
    private Restry  restry;

    @Data
    @ToString
    public static class Restry{

        private  int maxRetries;
        private  int baseSleepTimeMs;
        private  int maxSleepMs;
    }
}