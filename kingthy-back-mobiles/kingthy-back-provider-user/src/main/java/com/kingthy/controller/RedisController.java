package com.kingthy.controller;

import com.kingthy.cache.RedisManager;
import com.kingthy.response.WebApiResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 描述：----------
 *
 * @author likejie
 * @date 2017/11/27
 */
@Api
@RestController
@RequestMapping("/redis")
public class RedisController {

    @Autowired
    private RedisManager redisManager;

    @ApiOperation(value = "获取缓存")
    @GetMapping("/getCache/{key}")
    public WebApiResponse getRedis(@PathVariable @ApiParam(name = "key", value = "key", required = true)String key){
        String cache = redisManager.get(key);
        return  WebApiResponse.success(cache);
    }
    @ApiOperation(value = "删除缓存")
    @GetMapping("/delCache/{key}")
    public WebApiResponse delRedis(@PathVariable @ApiParam(name = "key", value = "key", required = true)String key){
        redisManager.delete(key);
        return  WebApiResponse.success();
    }
}
