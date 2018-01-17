package com.kingthy.platform.util;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * @AUTHORS xumin
 * @Description:
 * @DATE Created by 11:45 on 2017/7/17.
 * @Modified by:
 */
public class DateUtil {

    public static Long putEndTime(Long endTime){

        if (null != endTime){
            Date date = new Date(endTime);

            Calendar calendar = new GregorianCalendar();
            calendar.setTime(date);
            calendar.add(Calendar.DATE, 1);
            endTime = calendar.getTimeInMillis();
        }

        return endTime;
    }
}
