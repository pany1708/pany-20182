package com.kingthy.ssh.exception;

/**
 * @Author: 潘勇
 * @Description: ssh异常
 * @Date: 2017/12/8 11:30
 */
public class SSHException extends Exception
{

    private static final long serialVersionUID = -6213665149000064880L;

    public SSHException()
    {
        super();
    }

    public SSHException(String message)
    {
        super(message);
    }

    public SSHException(String message, Throwable cause)
    {
        super(message, cause);
    }

    public SSHException(Throwable cause)
    {
        super(cause);
    }

}
