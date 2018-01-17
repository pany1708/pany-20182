/**
 * 系统项目名称
 * com.kingthy.platform.util
 * SnFeignClient.java
 *
 * 2017年4月10日-下午2:17:19
 *  2017金昔科技有限公司-版权所有
 *
 */
package com.kingthy.platform.util;

import com.kingthy.platform.response.WebApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * SnFeignClient
 *
 * yuanml 2017年4月10日 下午2:17:19
 *
 * @version 1.0.0
 *
 */
@FeignClient(name = "provider-generate-sn", fallback = SnFeignClient.HystrixClientFallback.class)
public interface SnFeignClient
{
    Logger LOGGER = LoggerFactory.getLogger(SnFeignClient.class);

    /**
     * 无法访问服务返回code
     */
    public static final int NOSERVICE = -99;

    @RequestMapping(value = "/gererateSn/getSnByType/{type}", method = RequestMethod.GET)
    WebApiResponse<String> generateSn(@PathVariable(name = "type") String type);

    @Component
    class HystrixClientFallback implements SnFeignClient
    {
        @Override
        public WebApiResponse<String> generateSn(String type)
        {
            HystrixClientFallback.LOGGER.error("异常发生，进入fallback方法，接收的参数：{}", type.toString());
            WebApiResponse<String> webApiResponse = new WebApiResponse<String>();
            webApiResponse.setCode(NOSERVICE);
            webApiResponse.setMessage("无法访问服务，该服务可能由于某种未知原因被关闭。请重启服务！");
            return webApiResponse;
        }

    }
}
