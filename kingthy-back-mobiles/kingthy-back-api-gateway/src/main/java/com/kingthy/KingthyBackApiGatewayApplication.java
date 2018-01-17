package com.kingthy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * 启动类
 * @author  xxxx
 */
@SpringBootApplication
@EnableZuulProxy
public class KingthyBackApiGatewayApplication
{
    
    public static void main(String[] args)
    {
        SpringApplication.run(KingthyBackApiGatewayApplication.class, args);
    }
}
