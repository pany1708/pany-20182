package com.kingthy.dto;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * 描述：----------
 *
 * @author likejie
 * @date 2017/12/6
 */
@Data
@ToString
public class UpdateLoginInfoDTO implements Serializable {

    private String token;
    private String phone;
    private Integer loginFailureCount;
    private Boolean isLocked;
    private Date lockedDate;
    private Date lastLoginDate;
}
