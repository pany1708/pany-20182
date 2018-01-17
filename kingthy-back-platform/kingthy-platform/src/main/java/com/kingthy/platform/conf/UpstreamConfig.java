package com.kingthy.platform.conf;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author chenz
 * @version 1.0.0
 * @class_name UpstreamConfig
 * @description 分流接口地址配置类
 * @create 2017/6/8
 */
@Component
@ConfigurationProperties(prefix = "upstream")
@Data
public class UpstreamConfig {

    //基本路径
    private String baseUrl;
    //检查策略接口
    private String policy_check;
    //设置策略接口
    private String policy_set;
    //查询策略接口
    private String policy_get;
    //删除策略接口
    private String policy_del;

    //查询运行时信息
    private String runtime_get;
    //设置运行时信息
    private String runtime_set;
    //删除运行时信息
    private String runtime_del;
    //
    private String hostname;

    /**
     *
     * @params
     * @return
     * @throw
     * 动态拼接运行时信息设置接口地址
     * @author chenz
     */
    public String runtimeSetUrl(int policyid){
        return baseUrl+runtime_set+"&policyid="+policyid+"&hostname="+hostname;
    }

    /**
     *
     * @params
     * @return
     * @throw
     * 动态拼接运行时信息查询接口地址
     * @author chenz
     */
    public String runtimeGetUrl(){
        return baseUrl+runtime_get+"&hostname="+hostname;
    }

    /**
      *
      *
      * @params
      * @return
      * @throw
      * 动态拼接查询策略信息接口地址
      * @author chenz
      */
    public String policyGetUrl(int policyid){

        return baseUrl+policy_get+"&policyid="+policyid;
    }

    /**
     *
     *
     * @params
     * @return
     * @throw
     * 动态拼接删除策略信息接口地址
     * @author chenz
     */
    public String policyDelUrl(int policyid){
        return baseUrl+policy_del+"&policyid="+policyid;
    }
}
