package com.kingthy.conf;

import com.kingthy.filter.HTTPBearerAuthorizeFilter;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * User:pany <br>
 * Date:2016-12-23 <br>
 * Time:16:59 <br>
 */

@Configuration
@EnableZuulProxy
public class FilterConfig
{

    @Bean
    public HTTPBearerAuthorizeFilter accessFilter()
    {
        return new HTTPBearerAuthorizeFilter();
    }
}