package com.kingthy.exception;

public enum OrderStatus {
    ORDER_CREATE(0, "待支付"),
    ORDER_PRODUCTION(1, "生产中"),
    ORDER_NO_DELIVERY(2, "待发货"),
    ORDER_NO_SIGN(3,"待签收"),
    ORDER_NO_EVALUATE(4,"待评价"),
    ORDER_CANCEL(5, "已取消"),
    ORDER_NO_RETURN(6, "待退单"),
    ORDER_FINISH(7, "已完成");
    // 成员变量
    private int code;

    private String message;

    /**
     * 构造方法
     *
     * @param code    状态码
     * @param message 状态信息
     */
    OrderStatus(int code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * @return 返回成员变量值
     */
    public int getCode() {
        return code;
    }

    /**
     * @return 返回成员变量值
     */
    public String getMessage() {

        return message;
    }
}
