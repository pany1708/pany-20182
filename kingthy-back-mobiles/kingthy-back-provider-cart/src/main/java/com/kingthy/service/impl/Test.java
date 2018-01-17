package com.kingthy.service.impl;

import rx.Observable;

import java.util.concurrent.Future;

/**
 * @Author 潘勇
 * @Data 2017/9/11 17:26.
 */
public class Test
{
    public static void main(String[] args)
        throws Exception
    {
        String s = new CommandHelloWorld("Bob").execute();
        Future<String> s1 = new CommandHelloWorld("Bob").queue();
        Observable<String> s2 = new CommandHelloWorld("Bob").observe();
        System.out.printf("s" + s);
        System.out.printf("s1" + s1);
        System.out.printf("s2" + s2);
    }
}
