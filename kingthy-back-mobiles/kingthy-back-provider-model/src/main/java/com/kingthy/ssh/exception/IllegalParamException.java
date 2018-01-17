package com.kingthy.ssh.exception;

/**
 * @Author: 潘勇
 * @Description: 参数异常
 * @Date: 2017/12/5 11:30
 */
public class IllegalParamException extends Exception
{

    private static final long serialVersionUID = -1148039976867829902L;

    public IllegalParamException()
    {
        super();
    }

    public IllegalParamException(String message)
    {
        super(message);
    }

    public IllegalParamException(String message, Throwable cause)
    {
        super(message, cause);
    }

    public IllegalParamException(Throwable cause)
    {
        super(cause);
    }
}
