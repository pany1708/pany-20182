package com.kingthy.platform.dto.message;

import lombok.Data;
import lombok.ToString;

/**
 * @AUTHORS xumin
 * @Description:
 * @DATE Created by 14:07 on 2017/7/14.
 * @Modified by:
 */
@Data
@ToString
public class MessageInfoDto implements java.io.Serializable{

    private String uuid;
    private String title;
    private String content;
    private String url;
    private String pushTime;
    private String messagePlatformUuid;//第三短信平台
    private Integer pushTarget;
    private boolean pushStatus;
}
