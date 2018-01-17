package com.kingthy.exception;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

//import com.jxshop.utils.exception.BizExceptionType;

public class OrderLogBizException extends BizException
{
    
    private static final long serialVersionUID = 1L;
    
    private static final Log log = LogFactory.getLog(OrderLogBizException.class);
    
    private static final String prefixException = BizExceptionType.BaseDataException.getValue();
    
    public static final OrderLogBizException ORDERLOG_LIST = new OrderLogBizException("0001", "查询订单记录失败");
    
    public static final OrderLogBizException ORDERLOG_CREATE_ERROR = new OrderLogBizException("0002", "创建订单记录失败");
    
    public OrderLogBizException()
    {
    }
    
    public OrderLogBizException(String code, String msgFormat, Object... args)
    {
        super(prefixException + code, msgFormat, args);
    }
    
    public OrderLogBizException(String code, String msg)
    {
        super(prefixException + code, msg);
    }
    
    /**
     * 实例化异常
     */
    public OrderLogBizException newInstance(String msgFormat, Object... args)
    {
        return new OrderLogBizException(this.code, msgFormat, args);
    }
    
    public OrderLogBizException print()
    {
        log.info("==>GoodsBizException, code:" + this.code + ", msg:" + this.msg);
        return new OrderLogBizException(this.code, this.msg);
    }
}
