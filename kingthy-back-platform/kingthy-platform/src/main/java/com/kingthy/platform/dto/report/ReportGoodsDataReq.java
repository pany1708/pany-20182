package com.kingthy.platform.dto.report;

import lombok.Data;
import lombok.ToString;

/**
 * @author chenz
 * @version 1.0.0
 * @class_name ReportGoodsDataReq
 * @description 商品数据请求入参封装类
 * @create 2017/7/28
 */
@Data
@ToString
public class ReportGoodsDataReq {


    //数据类型
    private DataType dataType;

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

    public enum DataType{
        onSale(0,"上架商品总数量"),jacket(1,"上衣商品总数量"),pants(2,"裤装商品总数量"),dress(3,"群装商品总数量"),suit(4,"套装商品总数量");
        private int value;
        private String nameValue;
        private DataType(int value,String nameValue){
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
}
