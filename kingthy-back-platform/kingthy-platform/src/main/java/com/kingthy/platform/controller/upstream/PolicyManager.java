package com.kingthy.platform.controller.upstream;

import com.kingthy.platform.conf.UpstreamConfig;
import com.kingthy.platform.entity.upstream.Policy;
import com.kingthy.platform.entity.upstream.PolicyDto;
import com.kingthy.platform.entity.upstream.UpstreamDto;
import com.kingthy.platform.response.WebApiResponse;
import com.kingthy.platform.service.upstream.MyRestTemplate;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.DefaultTypedTuple;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static com.alibaba.fastjson.JSON.toJSONString;

/**
 * @author chenz
 * @version 1.0.0
 * @class_name StrategyManager
 * @description 分流策略管理类
 * @create 2017/6/7
 */
@RestController
@RequestMapping("/policy")
public class PolicyManager {

    private static final Logger LOG = LoggerFactory.getLogger(PolicyManager.class);

    @Resource
    private MyRestTemplate myRestTemplate;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private UpstreamConfig upstreamConfig;

    @ApiOperation(value = "检查分流策略", notes = "检查根据uid范围进行分流的策略是否合法")
    @RequestMapping(value = "/check",method = RequestMethod.POST)
    public WebApiResponse<?> checkPolicy(@RequestBody @ApiParam(name = "strategy", value = "分流策略", required = true) Policy policy){
        ResponseEntity responseEntity = null;
        try {
            String result = toJSONString(policy);
            HttpEntity<String> httpEntity = new HttpEntity<String>(result);
            RestTemplate restTemplate = myRestTemplate.getRestTemplate();
            String url = upstreamConfig.getBaseUrl()+upstreamConfig.getPolicy_check();
            responseEntity = restTemplate.exchange(url, HttpMethod.POST,httpEntity, UpstreamDto.class);
            if (responseEntity.getBody()!=null){
                UpstreamDto upstreamDto = (UpstreamDto)responseEntity.getBody();
                return WebApiResponse.success(upstreamDto);
            }
        } catch (RestClientException e) {
            LOG.error("/policy/check"+e);
            return WebApiResponse.error(WebApiResponse.ResponseMsg.EXCEPTION.getValue());
        }
        return WebApiResponse.success(responseEntity);
    }

    @ApiOperation(value = "添加分流策略", notes = "添加根据uid范围进行分流的策略")
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public WebApiResponse<?> addPolicy(@RequestBody @ApiParam(name = "strategy", value = "分流策略", required = true) Policy policy){
        ResponseEntity responseEntity = null;
        try {
            String result = toJSONString(policy);
            HttpEntity<String> httpEntity = new HttpEntity<String>(result);
            RestTemplate restTemplate = myRestTemplate.getRestTemplate();
            String url = upstreamConfig.getBaseUrl()+upstreamConfig.getPolicy_set();
            responseEntity = restTemplate.exchange(url, HttpMethod.POST,httpEntity, UpstreamDto.class);
            if (responseEntity.getBody()!=null){
                UpstreamDto upstreamDto = (UpstreamDto)responseEntity.getBody();
                if (upstreamDto.getCode() == 200 ){
                    return WebApiResponse.success(upstreamDto,"添加成功");
                }else{
                    return WebApiResponse.success(upstreamDto,"添加失败");
                }
            }
        } catch (RestClientException e) {
            LOG.error("/policy/add"+e);
           return WebApiResponse.error(WebApiResponse.ResponseMsg.EXCEPTION.getValue());
        }
        return WebApiResponse.success(responseEntity);
    }

    @ApiOperation(value = "查询所有分流策略", notes = "查询所有策略")
    @RequestMapping(value = "/getAll",method = RequestMethod.GET)
    public WebApiResponse<?> getAllPolicy(){
        Set<String> keySet = null;
        try {
            keySet = stringRedisTemplate.keys("ab:policies:*:divdata");
            if(keySet!=null){
                //查询运行时信息
                String url = upstreamConfig.runtimeGetUrl();
                RestTemplate restTemplate = myRestTemplate.getRestTemplate();
                String runtimeInfo = restTemplate.getForObject(url,String.class);
                //查询策略信息
                List<PolicyDto> divdatalist = new ArrayList<PolicyDto>();
                for (String key:keySet){
                  Set dataSet =  stringRedisTemplate.opsForZSet().rangeWithScores(key,0,-1);
                  StringBuffer stringBuffer =new StringBuffer();
                  PolicyDto policyDto = new PolicyDto();
                    for(Object obj:dataSet){
                        DefaultTypedTuple defaultTypedTuple = (DefaultTypedTuple) obj;
                        String value = defaultTypedTuple.getValue().toString().substring(2);
                        stringBuffer.append(value+"---->"+Math.round(defaultTypedTuple.getScore())+" | ");
                    }
                    policyDto.setDescription(key+" | "+stringBuffer);
                    //如果策略被运行时信息所采用，则标记为true
                    if(runtimeInfo.contains(key)){
                        policyDto.setRunTime(true);
                    }else{
                        policyDto.setRunTime(false);
                    }
                    divdatalist.add(policyDto);
                }
                return WebApiResponse.success(divdatalist);
            }
            return WebApiResponse.error("没有数据");
        } catch (Exception e) {
            LOG.error("/policy/getAll"+e);
           return WebApiResponse.error(WebApiResponse.ResponseMsg.EXCEPTION.getValue());
        }
    }

    @ApiOperation(value = "查询分流策略", notes = "根据策略id查询策略信息")
    @RequestMapping(value = "/get/{policyId}",method = RequestMethod.GET)
    public WebApiResponse<?> getStrategy(@PathVariable @ApiParam(name = "policyId", value = "策略id", required = true) int policyId){
        String result = null;
        try {
            RestTemplate restTemplate = myRestTemplate.getRestTemplate();
            String url = upstreamConfig.policyGetUrl(policyId);
            result = restTemplate.getForObject(url,String.class);
            return WebApiResponse.success(result);
        } catch (RestClientException e) {
            LOG.error("/policy/get/{policyId}"+e);
            return WebApiResponse.error(WebApiResponse.ResponseMsg.EXCEPTION.getValue());
        }
    }

    @ApiOperation(value = "删除分流策略", notes = "根据策略id删除策略")
    @RequestMapping(value = "/del/{policyId}",method = RequestMethod.DELETE)
    public WebApiResponse<?> deleteStrategy(@PathVariable @ApiParam(name = "policyId", value = "策略id", required = true) int policyId){
        String result = null;
        try {
            RestTemplate restTemplate = myRestTemplate.getRestTemplate();
            String url = upstreamConfig.policyDelUrl(policyId);
            result = restTemplate.getForObject(url,String.class);
            return WebApiResponse.success(result);
        } catch (RestClientException e) {
            LOG.error("/policy/del/{policyId}"+e);
            return WebApiResponse.error(WebApiResponse.ResponseMsg.EXCEPTION.getValue());
        }
    }

}
