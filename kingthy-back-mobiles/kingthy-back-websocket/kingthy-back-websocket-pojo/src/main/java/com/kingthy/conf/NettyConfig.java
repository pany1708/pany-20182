package com.kingthy.conf;

import lombok.Data;
import lombok.ToString;

/**
 * netty 配置
 * @author likejie
 * @date 2017/11/1
 */
@Data
@ToString
public class NettyConfig {

    /**主机地址*/
    private String host;
    /**端口号*/
    private Integer port;
    /**是否集群*/
    private Boolean isCluster=false;
    /**心跳检测时间*/
    private Integer maxHeartbeat;
}
