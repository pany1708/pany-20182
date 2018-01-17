package com.kingthy.conf;

import com.alibaba.dubbo.config.ProtocolConfig;
import com.alibaba.dubbo.config.ProviderConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @Author 潘勇
 * @Data 2017/9/29 18:22.
 */
@Component
public class DubboServiceConf
{
    @Bean
    public ProviderConfig configDubboThread()
    {
        ProviderConfig providerConfig = new ProviderConfig();
        ProtocolConfig protocolConfig = new ProtocolConfig();
        protocolConfig.setName("dubbo");
        protocolConfig.setServer("netty");
        protocolConfig.setSerialization("hessian2");
        providerConfig.setThreads(2000);
        providerConfig.setIothreads(100);
        providerConfig.setAccepts(10000);
        providerConfig.setThreadpool("limited");
        providerConfig.setActives(1000);
//        providerConfig.setRetries(2);
//        providerConfig.setLoadbalance("roundrobin");
        providerConfig.setTimeout(50000);
        providerConfig.setDispatcher("all");
        providerConfig.setDelay(-1);
        providerConfig.setProtocol(protocolConfig);
        return providerConfig;
    }
}
