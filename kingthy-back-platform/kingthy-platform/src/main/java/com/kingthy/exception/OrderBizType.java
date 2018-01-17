package com.kingthy.exception;

public enum OrderBizType
{
    ORDER_CREATE(0, "创建订单"),
    ORDER_PAY(1, "支付订单"),
    ORDER_UPDATE(2, "修改订单"),
    ORDER_CONFIRM(3,"订单确认"),
    ORDER_APPLY_RETURN(4, "申请退单"),
    ORDER_CONFIRM_RETURN(5,"确认退单"),
    ORDER_REJECT_RETURN(6, "驳回退单"),
    ORDER_CANCEL(7, "取消订单"),
    ORDER_DELIVERY(8, "订单发货");
    // 成员变量
    private int code;
    
    private String message;
    
    /**
     * 构造方法
     *
     * @param code 状态码
     * @param message 状态信息
     */
    OrderBizType(int code, String message)
    {
        this.code = code;
        this.message = message;
    }
    
    /**
     * @return 返回成员变量值
     */
    public int getCode()
    {
        return code;
    }
    
    /**
     * @return 返回成员变量值
     */
    public String getMessage()
    {
        return message;
    }
}
