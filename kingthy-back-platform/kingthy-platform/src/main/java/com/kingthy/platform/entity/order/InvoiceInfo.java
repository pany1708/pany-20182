package com.kingthy.platform.entity.order;

import com.kingthy.platform.util.BaseTableFileds;
import lombok.Data;
import lombok.ToString;

/**
 * @AUTHORS xumin
 * @Description:
 * @DATE Created by 17:46 on 2017/7/7.
 * @Modified by:
 */
@Data
@ToString
public class InvoiceInfo extends BaseTableFileds implements java.io.Serializable{

    private Integer invoiceType;

    private String invoiceTitle;

    private String orderSn;

    private static final long serialVersionUID = 1L;
}
