package com.kingthy.dubbo.order.service;

import com.kingthy.base.BaseService;
import com.kingthy.entity.Sn;
import com.kingthy.response.WebApiResponse;

/**
 * @author xumin
 * @Description:
 * @DATE Created by 14:38 on 2017/8/8.
 * @Modified by:
 */
public interface SnDubboService extends BaseService<Sn> {

    int updateLastValue(Sn sn);

    WebApiResponse<String> generateSn(String type);
}
