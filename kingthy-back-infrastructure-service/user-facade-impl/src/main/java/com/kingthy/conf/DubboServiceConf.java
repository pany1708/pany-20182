package com.kingthy.conf;

import com.alibaba.dubbo.config.ProviderConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @author 潘勇
 * @Data 2017/9/29 18:22.
 */
@Component
public class DubboServiceConf
{
    @Bean
    public ProviderConfig configDubboThread()
    {
        ProviderConfig providerConfig = new ProviderConfig();
        /**业务线程池大小，dubbo底层基于netty，所以线程池大小设置跟tomcat的无关，默认值100*/
        providerConfig.setThreads(100);

        /**io线程池大小，CPU个数+1，不建议调整此参数
        providerConfig.setIothreads(10)*/

        /** 线程池类型：
         fixed 固定大小线程池，启动时建立线程，不关闭，一直持有。(缺省)
         cached 缓存线程池，空闲一分钟自动删除，需要时重建。
         limited 可伸缩线程池，但池中的线程数只会增长不会收缩。(为避免收缩时突然来了大流量引起的性能问题)。*/
        providerConfig.setThreadpool("limited");
        /**重试次数*/
        providerConfig.setRetries(0);
        /**负载均衡策略，可选值：random,roundrobin,leastactive，分别表示：随机，轮循，最少活跃调用 */
        providerConfig.setLoadbalance("leastactive");
        /**超时时间*/
        providerConfig.setTimeout(50000);

        /**服务提供方最大可接受连接数，默认值0（表示不限制）*/
        providerConfig.setAccepts(0);
        /**提供者-每服务每方法最大并发调用数，默认值0（表示不限制）*/
        providerConfig.setExecutes(0);
        /**消费者-每服务每方法最大并发调用数，默认值0（表示不限制）*/
        providerConfig.setActives(0);
        return providerConfig;
    }
}
