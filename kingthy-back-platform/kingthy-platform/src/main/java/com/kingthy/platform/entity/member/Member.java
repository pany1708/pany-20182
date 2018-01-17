package com.kingthy.platform.entity.member;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kingthy.platform.util.BaseTableFileds;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Transient;
import java.util.Date;

@ToString
@Data
@EqualsAndHashCode(callSuper = true)
public class Member extends BaseTableFileds {


    private String userName;

    @JsonIgnore
    private String passWord;

    @JsonIgnore
    private String paymentPassword;

    @JsonIgnore
    private String paymenSalt;

    @JsonIgnore
    private String salt;

    private String nickName;

    private Integer gender;

    private String address;

    private String areaIds;

    private String areaName;

    private Date birth;

    private String email;

    private String phone;

    private String zipCode;

    private Date lockedDate;

    private Date loginDate;

    private Integer loginFailureCount;

    private String loginIp;

    private String loginPluginId;

    private Integer integral;

    private String registerIp;

    private Boolean isEnabled;

    private Boolean isLocked;

    private Boolean isDesinger;

    private String token;

    private Date lastLoginDate;

    private String headImage;

    private String barCodes;

    private String constellation;

    private String occupation;

    private String style;

    private String skinColor;

    private String face;

    private String signature;

    private String job;

}