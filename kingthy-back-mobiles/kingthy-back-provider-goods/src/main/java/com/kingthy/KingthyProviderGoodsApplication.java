package com.kingthy;

import org.springframework.boot.SpringApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.feign.EnableFeignClients;


/**
 * 启动
 * @author xxxx
 */
@SpringCloudApplication
@EnableFeignClients
@EnableCaching
public class KingthyProviderGoodsApplication
{
    
    public static void main(String[] args)
    {

        SpringApplication.run(KingthyProviderGoodsApplication.class, args);
    }
}
