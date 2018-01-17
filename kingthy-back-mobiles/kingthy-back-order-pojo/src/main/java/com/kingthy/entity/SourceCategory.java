package com.kingthy.entity;

import com.kingthy.common.BaseTableFileds;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * Created by kingthy on 2018/1/4.
 */
@ToString
@Data
@EqualsAndHashCode(callSuper = true)
public class SourceCategory extends BaseTableFileds {

    private String fullName;

    /**
     * 分类类型 1=面料 2=辅料 3=特殊工艺 4=饰品 5=缝纫线
     */
    private Integer type;

    private String shortEnName;

    private Boolean status;

    private String description;

    private String parentId;

    private Boolean changeSize;

    private Integer grade;

    private String path;

    /**
     * 类型
     */
    public enum Type
    {
        /**
         * 面料
         */
        M(1),
        /**
         * 辅料
         */
        F(2),
        /**
         * 特殊工艺
         */
        T(3),
        /**
         * 饰品
         */
        S(4),
        /**
         * 缝纫线
         */
        FRX(5);
        private int value;

        public int Type()
        {
            return value;
        }

        public void setValue(int value)
        {
            this.value = value;
        }

        Type(int value)
        {
            this.value = value;
        }

    }
}
