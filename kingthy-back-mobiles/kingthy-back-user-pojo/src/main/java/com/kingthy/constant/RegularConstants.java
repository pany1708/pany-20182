package com.kingthy.constant;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 描述：正则表达式常量
 *
 * @author likejie
 * @date 2017/12/8
 */
public interface RegularConstants {

    /**手机号匹配*/
    Pattern PHONE_PATTERN = Pattern.compile("^1[3,4,5,7,8]\\d{9}$");
    /**密码匹配（8-20位字母数字特殊字符任意两种组合）*/
    /**((?=.*\d)(?=.*\D)|(?=.*[a-zA-Z])(?=.*[^a-zA-Z]))^.{8,16}$*/
    Pattern PASSWORD_PATTERN = Pattern.compile("(?!.*[\\u4E00-\\u9FA5\\s])(?!^[a-zA-Z]+$)(?!^[\\d]+$)(?!^[^a-zA-Z\\d]+$)^.{8,16}$");
    /**支付密码匹配（6-16位数字和字母）*/
    Pattern PAY_PASSWORD_PATTERN = Pattern.compile("^[a-zA-Z0-9]{6,16}$");

    /**
     * 校验
     * @param pattern
     * @param value
     * @return boolean
     */
    static boolean check(Pattern pattern, String value){
        Matcher matcher= pattern.matcher(value);
        return matcher.matches();
    }
    /**
     * 校验 是否符合手机号规则
     * @param value
     * @return boolean
     */
    static boolean isPhone(String value){
        Matcher matcher= PHONE_PATTERN.matcher(value);
        return matcher.matches();
    }
    /**
     * 校验 是否符合密码规则
     * @param value
     * @return boolean
     */
    static boolean isPassword(String value){
        Matcher matcher= PASSWORD_PATTERN.matcher(value);
        return matcher.matches();
    }
    /**
     * 校验 是否符合支付密码规则
     * @param value
     * @return boolean
     */
    static boolean isPayPassword(String value){
        Matcher matcher= PAY_PASSWORD_PATTERN.matcher(value);
        return matcher.matches();
    }
}
