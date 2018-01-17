package com.kingthy.exception;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

//import com.jxshop.utils.exception.BizExceptionType;

public class OrderItemBizException extends BizException
{
    
    private static final long serialVersionUID = 1L;
    
    private static final Log log = LogFactory.getLog(OrderItemBizException.class);
    
    private static final String prefixException = BizExceptionType.BaseDataException.getValue();
    
    public static final OrderItemBizException ORDERITEM_lIST = new OrderItemBizException("0001", "查询订单明细失败");
    
    public static final OrderItemBizException ORDERITEM_CREATE_ERROR = new OrderItemBizException("0002", "创建订单明细失败");
    
    public OrderItemBizException()
    {
    }
    
    public OrderItemBizException(String code, String msgFormat, Object... args)
    {
        super(prefixException + code, msgFormat, args);
    }
    
    public OrderItemBizException(String code, String msg)
    {
        super(prefixException + code, msg);
    }
    
    /**
     * 实例化异常
     */
    public OrderItemBizException newInstance(String msgFormat, Object... args)
    {
        return new OrderItemBizException(this.code, msgFormat, args);
    }
    
    public OrderItemBizException print()
    {
        log.info("==>GoodsBizException, code:" + this.code + ", msg:" + this.msg);
        return new OrderItemBizException(this.code, this.msg);
    }
}
