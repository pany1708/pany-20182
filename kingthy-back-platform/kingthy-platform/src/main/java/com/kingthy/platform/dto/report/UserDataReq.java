package com.kingthy.platform.dto.report;

import lombok.Data;
import lombok.ToString;

import java.util.Date;

/**
 * @author chenz
 * @version 1.0.0
 * @class_name UserDataReq
 * @description 用户数据查询入参封装类
 * @create 2017/7/19
 */
@Data
@ToString
public class UserDataReq {
    //起始时间
    private Date startDate;
    //结束时间
    private Date endDate;
    //时间段
    private TimeRage timeRage;

    public enum TimeRage{
        yesterday(1,"昨天"),lastWeek(2,"上周"),lastHalfMonth(3,"前半个月"),lastMonth(4,"上个月");
        private int value;
        private String valueName;
        private TimeRage(int value,String valueName){
            this.value = value;
            this.valueName = valueName;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public String getValueName() {
            return valueName;
        }

        public void setValueName(String valueName) {
            this.valueName = valueName;
        }
    }
}
