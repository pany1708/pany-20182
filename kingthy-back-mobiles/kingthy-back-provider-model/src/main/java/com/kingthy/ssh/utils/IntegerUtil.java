package com.kingthy.ssh.utils;

/**
 * @Author: 潘勇
 * @Description: IntegerUtil工具类
 * @Date: 2017/12/7 17:58
 */
public class IntegerUtil
{

    /**
     * @Author: 潘勇
     * @Description: 如果为0, 则返回默认值
     * @Date: 2017/12/7 18:00
     */
    public static Integer defaultIfZero(Integer originalInt, Integer defaultInt)
    {
        if (0 == originalInt)
        {
            return defaultInt;
        }
        return originalInt;
    }

    /**
     * @Author: 潘勇
     * @Description: 如果为0, 则返回默认值
     * @Date: 2017/12/7 18:05
     */
    public static Integer defaultIfError(String originalStr, Integer defaultInt)
    {

        try
        {
            return Integer.parseInt(StringUtil.trimToEmpty(originalStr));
        }
        catch (Exception e)
        {
            return defaultInt;
        }
    }

    /**
     * @Author: 潘勇
     * @Description: 如果是一个不合法的整型，那么返回一个默认值
     * @Date: 2017/12/7 19:01
     */
    public static Integer defaultIfError(Integer originalStr, Integer defaultInt)
    {

        try
        {
            return Integer.valueOf(originalStr);
        }
        catch (Exception e)
        {
            return defaultInt;
        }
    }

    /**
     * @Author: 潘勇
     * @Description: 如果非正, 则返回默认值<br>
     * @Date: 2017/12/7 19:08
     */
    public static Integer defaultIfSmallerThan0(Integer originalInt, Integer defaultInt)
    {
        if (0 >= originalInt)
        {
            return defaultInt;
        }
        return originalInt;
    }

    /**
     * @Author: 潘勇
     * @Description: 将String 转化成 Integer，如果小于等于0，将抛异常
     * @Date: 2017/12/7 19:36
     */
    public static Integer exceptionIfSmallerThan0(String originalStr)
        throws Exception
    {
        try
        {
            int num = Integer.parseInt(StringUtil.trimToEmpty(originalStr));
            if (num > 0)
                return num;
            else
                throw new Exception();
        }
        catch (Exception e)
        {
            throw new Exception(originalStr + " is smaller than 0, or it is a  invalid parameter ");
        }
    }

    /**
     * @Author: 潘勇
     * @Description: 判断是否大余0
     * @Date: 2017/12/7 19:40
     */
    public static boolean isBiggerThan0(int num)
    {
        return 0 < num;
    }

    public static Integer maxIfTooBig(Integer originalInt, Integer maxInt)
    {
        if (originalInt >= maxInt)
        {
            originalInt = maxInt;
        }
        return originalInt;
    }

}
