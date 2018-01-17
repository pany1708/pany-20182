package com.kingthy.platform.dto.member;

import lombok.Data;
import lombok.ToString;

import java.util.Date;

/**
 * 
 *
 * MemberPageReq(会员分页查询参数)
 * 
 * 陈钊 2017年4月14日 下午3:56:54
 * 
 * @version 1.0.0
 *
 */
@Data
@ToString
public class MemberPageReq
{
    /**
     * 手机号
     */
    private String phone;
    /**
     * 用户名
     */
    private String userName;

    private Date startTime;

    private Date endTime;

    private int pageNum;

    private int pageSize;
}
