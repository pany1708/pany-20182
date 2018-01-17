package com.kingthy.platform.entity.message;

import com.kingthy.platform.util.BaseTableFileds;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
public class MessageInfo extends BaseTableFileds {

    private String title;

    private String content;

    private String url;

    private Date pushTime;

    private String messagePlatformUuid;

    private String membersUuid;

    private String memberName;

    private String pushTarget;

    private Boolean pushStatus;

}