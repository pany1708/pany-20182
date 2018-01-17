package com.kingthy.service;

import com.kingthy.entity.Goods;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author xumin
 * @Description:
 * @DATE Created by 17:59 on 2017/6/9.
 * @Modified by:
 */
//@FeignClient(name = "provider-goods-dubbo", fallback = GoodsService.HystrixClientFallbackImpl.class)
public interface GoodsService {
    Logger LOGGER = LoggerFactory.getLogger(GoodsService.class);

    /**
     * 根据商品UUID查询商品
     * @param
     * @return
     */
 /*
    @RequestMapping(value = "/goods/query/goodList", method = RequestMethod.POST)
    public WebApiResponse<List<GoodsOrderDTO>> selectGoodsListByGoodsIds(@RequestBody @ApiParam(value = "goodsIds", required = true) List<String> listGoodsUuid);

    @Component
    class HystrixClientFallbackImpl implements GoodsService{

        @Override
        public WebApiResponse<List<GoodsOrderDTO>> selectGoodsListByGoodsIds(List<String> listGoodsUuid) {
            LOGGER.info("异常发生，进入fallback方法，接收的参数：listGoodsUuid = {}", listGoodsUuid.toString());
            WebApiResponse<List<GoodsOrderDTO>> webApiResponse = new WebApiResponse<>();
            webApiResponse.setCode(-99);
            webApiResponse.setMessage("无法访问服务，该服务可能由于某种未知原因被关闭。请重启服务！");
            return webApiResponse;
        }
    }*/

    Goods selectGoodsByUuid(String goodsUuid) throws Exception;
}
