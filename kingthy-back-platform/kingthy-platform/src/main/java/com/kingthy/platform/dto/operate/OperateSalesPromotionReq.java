package com.kingthy.platform.dto.operate;

import lombok.Data;
import lombok.ToString;

import java.util.Date;

/**
 * @author chenz
 * @version 1.0.0
 * @class_name OperateSalesPromotionReq
 * @description 促销请求入参封装类
 * @create 2017/8/8
 */
@Data
@ToString
public class OperateSalesPromotionReq {

    private String activityName;

    private String policy;

    private Date startTime;

    private Date endTime;

    private Integer joinGoods;

    private String description;
    //活动类型
    private PolicyType policyType;

    public enum PolicyType{
        discount(0,"折扣"),fullCut(1,"满减");
        private int value;
        private String nameValue;
        private PolicyType(int value,String nameValue){
            this.value = value;
            this.nameValue = nameValue;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public String getNameValue() {
            return nameValue;
        }

        public void setNameValue(String nameValue) {
            this.nameValue = nameValue;
        }
    }

    private int pageSize;

    private int pageNum;

    //活动状态
    public enum Status{
        before(0,"未开始"),ongoing(1,"进行中"),end(2,"结束");
        private int value;
        private String nameValue;
        private Status(int value,String nameValue){
            this.value = value;
            this.nameValue = nameValue;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public String getNameValue() {
            return nameValue;
        }

        public void setNameValue(String nameValue) {
            this.nameValue = nameValue;
        }
    }

    private Status status;
}
