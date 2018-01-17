package com.kingthy.exception;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

//import com.jxshop.utils.exception.BizExceptionType;

public class OrderBizException extends BizException
{
    
    private static final long serialVersionUID = 1L;
    
    private static final Log log = LogFactory.getLog(OrderBizException.class);
    
    private static final String prefixException = BizExceptionType.BaseDataException.getValue();
    
    public static final OrderBizException ORDER_CREATE_EXCEPTION = new OrderBizException("0001", "订单创建失败");
    
    public static final OrderBizException ORDER_SELECT_EXCEPTION = new OrderBizException("0002", "查询订单失败");
    
    public OrderBizException()
    {
    }
    
    public OrderBizException(String code, String msgFormat, Object... args)
    {
        super(prefixException + code, msgFormat, args);
    }
    
    public OrderBizException(String code, String msg)
    {
        super(prefixException + code, msg);
    }
    
    /**
     * 实例化异常
     */
    public OrderBizException newInstance(String msgFormat, Object... args)
    {
        return new OrderBizException(this.code, msgFormat, args);
    }
    
    public OrderBizException print()
    {
        log.info("==>GoodsBizException, code:" + this.code + ", msg:" + this.msg);
        return new OrderBizException(this.code, this.msg);
    }
}
