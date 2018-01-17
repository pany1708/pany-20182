package com.kingthy.job;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import com.kingthy.util.HttpClientHelper;

/**
 * MaterialSynchronizeJob
 * <p>
 * 
 * @author yuanml 2017/9/15
 *
 * @version 1.0.0
 */
public class MaterialSynchronizeJob implements SimpleJob
{
    @Resource
    private StringRedisTemplate stringRedisTemplate;
    
    @Value("${url.syncUrl}")
    private String apiUrlString;
    
    @Override
    public void execute(ShardingContext shardingContext)
    {
        Map<String, Object> map = new HashMap<>(16);
        List<String> list = new ArrayList<>();
        list.add("M");
        list.add("F");
        for (String type : list)
        {
            map.put("type", type);
            String resultData = HttpClientHelper.sendPost(apiUrlString, map, "utf-8");
            JSONObject x = JSON.parseObject(resultData);
            JSONArray jsonArray = (JSONArray)x.get("data");
            for (int j = 0; j < jsonArray.size(); j++)
            {
                JSONObject jsonObject = (JSONObject)jsonArray.get(j);
                if (null != jsonObject.get("code") && null != jsonObject.get("price"))
                {
                    String key = type + ":" + jsonObject.get("code");
                    String value = jsonObject.get("price").toString();
                    stringRedisTemplate.opsForValue().set(key, value);
                }
            }
        }
    }
}
