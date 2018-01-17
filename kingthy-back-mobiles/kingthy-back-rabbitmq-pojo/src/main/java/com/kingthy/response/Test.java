package com.kingthy.response;

import java.math.BigDecimal;

/**
 * @Author 潘勇
 * @Data 2017/9/27 11:52.
 */
public class Test
{
    public static void main(String[] args)
    {
        double d = 1;
        BigDecimal bigDecimal = BigDecimal.valueOf(d);
        System.out.printf("bigDecimal" + bigDecimal);
    }
}
