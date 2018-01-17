package com.kingthy.platform.controller.upstream;

import com.kingthy.platform.conf.UpstreamConfig;
import com.kingthy.platform.response.WebApiResponse;
import com.kingthy.platform.service.upstream.MyRestTemplate;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @author chenz
 * @version 1.0.0
 * @class_name RunTimeInfo
 * @description 策略运行时信息
 * @create 2017/6/8
 */
@RestController
@RequestMapping("/runTime")
public class RunTimeInfo {

    private static final Logger LOG = LoggerFactory.getLogger(RunTimeInfo.class);

    @Autowired
    private UpstreamConfig upstreamConfig;

    @Resource
    private MyRestTemplate myRestTemplate;

    @ApiOperation(value = "设置运行时分流信息", notes = "根据分流策略id设置运行时分流信息")
    @RequestMapping(value = "/setRunTime/{policyId}",method = RequestMethod.GET)
    public WebApiResponse<?> setRunTime(@PathVariable @ApiParam(name = "policyId", value = "策略id", required = true) int policyId){
        String result = null;
        try {
            String url = upstreamConfig.runtimeSetUrl(policyId);
            RestTemplate restTemplate = myRestTemplate.getRestTemplate();
            result = restTemplate.getForObject(url,String.class);
            return WebApiResponse.success(result);
        } catch (RestClientException e) {
            LOG.error("/runTime/setRunTime/{policyId}"+e);
            return WebApiResponse.error(WebApiResponse.ResponseMsg.EXCEPTION.getValue());
        }
    }

    @ApiOperation(value = "查找运行时分流信息", notes = "查找运行时分流信息")
    @RequestMapping(value = "/getRunTime",method = RequestMethod.GET)
    public WebApiResponse<?> getRunTime(){
        try {
            String url = upstreamConfig.runtimeGetUrl();
            RestTemplate restTemplate = myRestTemplate.getRestTemplate();
            String result = restTemplate.getForObject(url,String.class);
            return WebApiResponse.success(result);
        } catch (RestClientException e) {
            LOG.error("/runTime/getRunTime"+e);
            return WebApiResponse.error(WebApiResponse.ResponseMsg.EXCEPTION.getValue());
        }
    }
}
