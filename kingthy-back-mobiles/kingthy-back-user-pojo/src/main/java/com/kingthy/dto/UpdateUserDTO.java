package com.kingthy.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


/**
 *
 * 用户信息修改
 * @author likejie
 * @date  2017/5/8.
 */
@Data
@ToString
public class UpdateUserDTO implements Serializable
{
    
    private static final long serialVersionUID = 7899073777903688555L;

    /**
     * 用户uuid
     */
    @JsonIgnore
    private String memberUuid;

    @ApiModelProperty("用户手机号")
    private String phone;

    @ApiModelProperty("手机验证码")
    private String code;

    @ApiModelProperty("用户名称")
    private String userName;

    @ApiModelProperty("用户签名")
    private String userSignature;

    @ApiModelProperty("用户密码")
    private String password;

    @ApiModelProperty("用户支付密码")
    private String paymentPassword;


}
