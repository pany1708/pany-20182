package com.kingthy.exception;

/**
 *
 * BizExceptionType
 * 
 * @author pany 2016年5月4日 上午11:07:42
 * 
 * @version 1.0.0
 *  运营平台异常 1001 <br>
 *  订单模块异常 1002 <br>
 *  用户模块异常 1003 <br>
 *  支付模块异常 1004 <br>
 *  产品模块异常 1005 <br>
 *  作品模块异常 1006 <br>
 *  商品模块异常 1007 <br>
 *  购物车模块异常 1008 <br>
 */
public enum BizExceptionType
{
    
    BaseDataException("1001"),
    OrderException("1002"),
    MemberException("1003"),
    PayException("1004"),
    ProductException("1005"),
    OpusException("1006"),
    GoodsException("1007"),
    SHoppingException("1008");
    private final String value;
    
    private BizExceptionType(String value)
    {
        this.value = value;
    }
    
    public String getValue()
    {
        return value;
    }
}
