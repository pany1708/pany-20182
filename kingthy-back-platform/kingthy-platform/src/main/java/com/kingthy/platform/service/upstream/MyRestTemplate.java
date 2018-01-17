package com.kingthy.platform.service.upstream;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @author chenz
 * @version 1.0.0
 * @class_name MyRestTemplate
 * @description 自定义restTemplate
 * @create 2017/6/7
 */
@Service("myRestTemplate")
public class MyRestTemplate {

    private final RestTemplate restTemplate;

    public MyRestTemplate(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public RestTemplate getRestTemplate() {
        return restTemplate;
    }
}
