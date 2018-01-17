package com.kingthy.common;

import java.util.Random;

/**
 * @author xumin
 * @Description:
 * @DATE Created by 15:27 on 2017/8/24.
 * @Modified by:
 */
public class CommonUtils
{

    public static final String updaterId = "MALL";

    public static final String officially_desinger = "10000";

    public static final String REQUEST_HEADER_PARAMETER = "Authorization";

    public static final String REIDS_ORDER_SN_KEY = "sn:order";

    public static final String UPLOAD_FILE_REDIS_KEY = "upload:file";

    public static String uuidToSharding(String uuid)
    {
        int i;
        if (Integer.MIN_VALUE != uuid.hashCode())
        {
            i = Math.abs(uuid.hashCode()) % 5;
        }
        else
        {
            i = Integer.MIN_VALUE % 5;
        }

        String sharding = null;

        switch (i)
        {
            case 0:
                sharding = CommonUtils.ShardingEnum.A.name();
                break;

            case 1:
                sharding = CommonUtils.ShardingEnum.B.name();
                break;

            case 2:
                sharding = CommonUtils.ShardingEnum.C.name();
                break;

            case 3:
                sharding = CommonUtils.ShardingEnum.D.name();
                break;
            case 4:
                sharding = CommonUtils.ShardingEnum.E.name();
                break;
        }

        return sharding;
    }

    public enum ShardingEnum
    {
        A, B, C, D, E
    }

    public static void main(String [] agrs){
        System.out.println("--------------------" + uuidToSharding("97397638320965673"));
    }

    /**
     *
     * 从[1,10] 获取一个随机数
     */
    public static int getRandomNum(){
        Random rd=new Random();
        return rd.nextInt(10)+1;
    }
    /**
     *
     * 获取一个随机数,不包含0
     */
    public static int getRandomNum(Integer count){
        Random rd=new Random();
        return rd.nextInt(count)+1;
    }
    /**
     * 获取随机字母数字组合
     * @param length 字符串长度
     * @return
     */
    public static String getRandomNumAndChar(Integer length) {
        String str = "";
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            boolean b = random.nextBoolean();
            if (b) {
                /**字符串*/
                // int choice = random.nextBoolean() ? 65 : 97; 取得65大写字母还是97小写字母
                // 取得大写字母
                str += (char) (65 + random.nextInt(26));
            } else {
                /**数字*/
                str += String.valueOf(random.nextInt(10));
            }
        }
        return str;
    }
}
