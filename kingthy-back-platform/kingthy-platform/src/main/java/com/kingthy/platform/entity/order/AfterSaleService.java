package com.kingthy.platform.entity.order;

import com.kingthy.platform.util.BaseTableFileds;
import lombok.Data;
import lombok.ToString;

import java.util.HashMap;
import java.util.Map;

/**
 * @AUTHORS xumin
 * @Description:
 * @DATE Created by 15:02 on 2017/5/16.
 * @Modified by:
 */

@Data
@ToString
public class AfterSaleService extends BaseTableFileds {

    private int applyServiceType;

    private String applyServiceName;

    private String exchangeReason;//换货原因

    private String memo;

    private String orderSn;//订单号

    private String goodsUuid;

    private String memberUuid;

    private String memberName;

    private int status;//0:发起售后 1:-审核通过 2:厂家确认收货,待生产 3:生产中 4:已包装发货，等待收货 5:已收货 6:已评价

    private String img;

    private String shippingNumber;//运单号

    private boolean auditingFlag;//是否通过审核

    private String rejectReason;//审核不通过原因

    private String refundsAddrUuid;//售后地址

    /**
     * 申请服务 0:换货 1:仅退货 2:退货退款
     */
    public enum applyType{

        exchange(0, "换货"),
        back(1, "仅退货"),
        refund(2, "退货退款");

        private int value;

        private String nameValue;

        private applyType(int value, String name)
        {
            this.value = value;
            this.nameValue = name;
        }

        public int getValue()
        {
            return value;
        }

        public void setValue(int value)
        {
            this.value = value;
        }

        public String getNameValue()
        {
            return nameValue;
        }

        public void setNameValue(String nameValue)
        {
            this.nameValue = nameValue;
        }

        public static String getNameValueByKey(int key){
            Map<Integer, String> enumMap = new HashMap();
            for (AfterSaleService.applyType status: AfterSaleService.applyType.values()) {
                enumMap.put(status.getValue(), status.getNameValue());
            }

            return enumMap.get(key);
        }

    }
}
