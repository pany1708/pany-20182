package com.kingthy.platform.response;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
public class WebApiResponse<T> implements Serializable
{
    /**
     * serialVersionUID:TODO（用一句话描述这个变量表示什么）
     *
     * @since 1.0.0
     */
    
    private static final long serialVersionUID = -6557898536448299915L;
    
    public static final int SUCCESS_CODE = 0;
    
    public static final int ERROR_CODE = 1;
    
    private int code;
    
    private String message;
    
    private T data;
    
    private long error;
    
    public static <T> WebApiResponse<T> success(T data)
    {
        WebApiResponse<T> response = new WebApiResponse<T>();
        response.setCode(SUCCESS_CODE);
        response.setData(data);
        return response;
    }
    
    public static <T> WebApiResponse<T> success(T data, String message)
    {
        WebApiResponse<T> response = new WebApiResponse<T>();
        response.setCode(SUCCESS_CODE);
        response.setData(data);
        response.setMessage(message);
        return response;
    }
    
    public static <T> WebApiResponse<T> error(String message)
    {
        WebApiResponse<T> response = new WebApiResponse<T>();
        response.setCode(ERROR_CODE);
        response.setMessage(message);
        return response;
    }
    
    public static <T> WebApiResponse<T> error(String message, Long error)
    {
        WebApiResponse<T> response = new WebApiResponse<T>();
        response.setCode(ERROR_CODE);
        response.setMessage(message);
        response.setError(error);
        return response;
    }
    
    public WebApiResponse()
    {
        
    }
    
    public WebApiResponse(int code, String message, T data)
    {
        super();
        this.code = code;
        this.message = message;
        this.data = data;
    }

    /**
     * api消息
     */
    public enum ResponseMsg
    {
        SUCCESS("操作成功"), FAIL("操作失败"), PARAMETER_ERROR("参数为空或参数有误"), EXCEPTION("接口发生异常");
        private String value;
        
        public String getValue()
        {
            return value;
        }
        
        public void setValue(String value)
        {
            this.value = value;
        }

        private ResponseMsg(String value)
        {
            this.value = value;
        }
    }
}
