package com.kingthy.dubbo.order.service;

import com.kingthy.base.BaseService;
import com.kingthy.entity.AfterSaleSchedule;

import java.util.List;

/**
 * @author xumin
 * @Description:
 * @DATE Created by 18:37 on 2017/8/4.
 * @Modified by:
 */
public interface AfterSaleScheduleDubboService extends BaseService<AfterSaleSchedule> {

    int updateSelectiveByOrderSn(AfterSaleSchedule var);

    List<AfterSaleSchedule> selectScheduleByOrderSn(String orderSn);

}
