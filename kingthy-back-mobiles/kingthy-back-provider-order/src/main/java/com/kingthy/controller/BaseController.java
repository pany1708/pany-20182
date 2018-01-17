package com.kingthy.controller;

import com.kingthy.entity.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * @author:xumin
 * @Description:
 * @Date:11:37 2017/11/2
 */
public class BaseController
{

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    protected Member getMember(String token)
    {
        Member member = null;
        String memberInfo = stringRedisTemplate.opsForValue().get(token);

        if (org.apache.commons.lang3.StringUtils.isNotBlank(memberInfo))
        {
            member = com.kingthy.common.KryoSerializeUtils.deserializationObject(memberInfo, Member.class);
        }
        return member;
    }
}
