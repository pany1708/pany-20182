package com.kingthy.dto;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;


/**
 *
 * 登录
 * @author likejie
 * @date  2017/5/8.
 */
@Data
@ToString
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "登录", description = "用户模型")
public class UserDTO implements Serializable
{
    
    private static final long serialVersionUID = -9078734818848659684L;
    
    /**
     * 手机号
     */
    @ApiModelProperty(value = "手机号", required = true)
    private String phone;
    /**
     * 密码
     */
    @ApiModelProperty(value = "密码")
    private String pwd;
    
    @ApiModelProperty(value = "客户端ID", required = true)
    private String clientId;

    @ApiModelProperty(value = "验证码")
    private String code;

    @ApiModelProperty(value = "登录方式：0：密码，：1 手机验证码")
    private Integer loginMode;

    public  enum LoginModel{

        /**密码登录*/
        PASSWORD(0),
        /**手机验证码登录*/
        PHONE_VERIFY_CODE(1);

        LoginModel(Integer value){
            this.value = value;
        }
        private Integer value;

        public Integer getValue()
        {
            return value;
        }

        public void setValue(Integer value)
        {
            this.value = value;
        }
    }
}
