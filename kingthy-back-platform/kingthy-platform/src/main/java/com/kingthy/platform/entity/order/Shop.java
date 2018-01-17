package com.kingthy.platform.entity.order;

import lombok.Data;
import lombok.ToString;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.io.Serializable;
import java.util.Date;
@Data
@ToString
public class Shop implements Serializable {

    private Integer id;

    private Date createDate;

    private Date modifyDate;

    private String creator;

    private String modifier;

    private Integer version;

    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "select uuid_short()")
    private String uuid;

    private Boolean delFlag;

    private String name;

    private String address;

    private String city;

    private String telephone;

    private String openTime;

    private String closeTime;

    private Boolean status;

    private String longitude;

    private String latitude;

    private String image;

    private String description;

    private static final long serialVersionUID = 1L;
}