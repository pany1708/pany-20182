package com.kingthy.dubbo.basedata.service;

import com.kingthy.base.BaseService;
import com.kingthy.dto.ShippingInfoDTO;
import com.kingthy.entity.ShippingMethod;

import java.util.List;

/**
 * @author zhaochen
 * @Description:
 * @DATE Created by 10:15 on 2017/8/10.
 * @Modified by:
 */
public interface ShippingMethodDubboService extends BaseService<ShippingMethod>{

    List<ShippingInfoDTO> selectShippingInfoByAreaUuid(Long areaUuid);
}
