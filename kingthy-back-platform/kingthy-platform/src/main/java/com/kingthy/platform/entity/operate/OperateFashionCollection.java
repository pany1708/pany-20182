package com.kingthy.platform.entity.operate;


import com.kingthy.platform.util.BaseTableFileds;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 潮搭实体类
 */
@Data
@ToString
@EqualsAndHashCode(callSuper = true)
public class OperateFashionCollection extends BaseTableFileds {


    private Boolean status;

    private String collectionName;

    private String banners;

    private String temperature;

    private String occasion;

    private String color;

    private String style;

    private String clothModel;

    private String clothModelPic;


}