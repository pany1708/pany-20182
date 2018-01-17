package com.kingthy.service.impl;

import com.kingthy.entity.CartItem;
import com.netflix.hystrix.*;

/**
 * @Author 潘勇
 * @Data 2017/9/11 16:37.
 */
public class GetCartItemServiceCommand extends HystrixCommand<CartItem>
{

    public GetCartItemServiceCommand()
    {
        super(setter());
    }

    @Override
    protected CartItem run()
        throws Exception
    {
        return null;
    }

    private static Setter setter()
    {
        //服务分组
        HystrixCommandGroupKey groupKey = HystrixCommandGroupKey.Factory.asKey("cartItem");
        //服务标识
        HystrixCommandKey commandKey = HystrixCommandKey.Factory.asKey("getCartItem");
        //线程池名称
        HystrixThreadPoolKey threadPoolKey = HystrixThreadPoolKey.Factory.asKey("cartItem-pool");
        //线程池配置
        HystrixThreadPoolProperties.Setter threadPoolProperties = HystrixThreadPoolProperties.Setter()
            .withCoreSize(10)
            .withKeepAliveTimeMinutes(5)
            .withMaxQueueSize(Integer.MAX_VALUE)
            .withQueueSizeRejectionThreshold(100000);
        //命令属性配置
        HystrixCommandProperties.Setter commandProperties = HystrixCommandProperties.Setter()
            .withExecutionIsolationStrategy(HystrixCommandProperties.ExecutionIsolationStrategy.THREAD);

        return HystrixCommand.Setter.withGroupKey(groupKey)
            .andCommandKey(commandKey)
            .andThreadPoolKey(threadPoolKey)
            .andThreadPoolKey(threadPoolKey)
            .andThreadPoolPropertiesDefaults(threadPoolProperties)
            .andCommandPropertiesDefaults(commandProperties);
    }
}
