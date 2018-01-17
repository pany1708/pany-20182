package com.kingthy.mapper;

import com.kingthy.entity.AfterSaleSchedule;
import com.kingthy.util.MyMapper;

import java.util.List;

/**
 * @author  xumin
 * @Description:
 * @DATE Created by 10:19 on 2017/8/7.
 * @Modified by:
 */
public interface AfterSaleScheduleMapper extends MyMapper<AfterSaleSchedule> {

    int updateSelectiveByOrderSn(AfterSaleSchedule var);

    List<AfterSaleSchedule> selectScheduleByOrderSn(String orderSn);
}
