package com.kingthy.platform.entity.order;

import com.kingthy.platform.util.BaseTableFileds;
import lombok.Data;
import lombok.ToString;

/**
 * @AUTHORS xumin
 * @Description:
 * @DATE Created by 9:39 on 2017/7/25.
 * @Modified by:
 */
@Data
@ToString
public class AfterSaleSchedule extends BaseTableFileds {

    private String goodsUuid;

    private String memo;

    private String saleServiceUuid;

    private int status;//0:发起售后 1:-审核通过 2:厂家确认收货,待生产 3:生产中 4:已包装发货，等待收货 5:已收货 6:已评价

    private String orderSn;//订单号
}

