package com.kingthy.exception;

/**
 * OpusException
 * <p>
 * yuanml 2017/4/25
 *
 * @version 1.0.0
 */
public class OpusException extends BizException
{
    
    private static final String prefixException = BizExceptionType.OpusException.getValue();
    
    public static final OpusException SERVICE_CALL_RESULT =
        new OpusException("-99", "无法访问服务，该服务可能由于某种未知原因被关闭。请重启服务！");
    
    private OpusException(String code, String msgFormat, Object... args)
    {
        super(prefixException + code, msgFormat, args);
    }
}
