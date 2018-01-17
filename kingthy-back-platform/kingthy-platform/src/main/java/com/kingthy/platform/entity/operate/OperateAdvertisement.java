package com.kingthy.platform.entity.operate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kingthy.platform.util.BaseTableFileds;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Date;

/**
 * 广告表实体
 */
@Data
@ToString
@EqualsAndHashCode(callSuper = true)
public class OperateAdvertisement extends BaseTableFileds
{

    private Boolean status;
    
    private String adName;
    
    private String description;
    
    private String length;
    
    private String width;
    
    private String location;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date startTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date endTime;
    
    private String banners;
    
    private String href;
    
}