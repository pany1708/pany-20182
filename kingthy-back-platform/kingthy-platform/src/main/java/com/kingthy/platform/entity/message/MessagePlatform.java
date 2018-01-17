package com.kingthy.platform.entity.message;

import com.kingthy.platform.util.BaseTableFileds;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
public class MessagePlatform extends BaseTableFileds {

    private String platformName;

    private String platformDesc;

    private Date openTime;

    private Integer status;

    private String queueName;

}