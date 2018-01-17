package com.kingthy.platform.entity.opus;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 *
 * Opus(作品实体类)
 * 
 * yuanml 2017年4月11日 下午7:42:35
 * 
 * @version 1.0.0
 *
 */
@ToString
@Data
public class Opus implements Serializable
{
    private Integer id;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date createDate;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date modifyDate;

    private Integer version;

    private String opusName;

    private String remark;

    private String sn;

    private String modlePath;

    private Integer opusStatus;

    private Boolean isShow;

    private Boolean delFlag;

    private String uuid;

    private String memberUuid;

    private String memberNick;

    private String creator;

    private String modifier;

    private String opusMaterialInfo;

    private String opusPartsInfo;

    private String opusAccessoriesInfo;

    private String opusImage;
    
    private static final long serialVersionUID = 1L;
}