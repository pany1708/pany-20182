package com.kingthy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class KingthyProviderSamplingToolsApplication
{
        public static void main(String[] args)
    {
        SpringApplication.run(KingthyProviderSamplingToolsApplication.class, args);
    }

}
