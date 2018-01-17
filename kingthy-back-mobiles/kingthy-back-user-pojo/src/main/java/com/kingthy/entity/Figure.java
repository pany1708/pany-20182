package com.kingthy.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.io.Serializable;
import java.util.Date;

/**
 * Figure
 *
 * @author zsh
 * @date 2017/6/8.
 */
@Data
@ToString
public class Figure implements Serializable
{

    @ApiModelProperty("主键")
    private Integer id;

    @ApiModelProperty("会员业务主键")
    private String memberUuid;

    @ApiModelProperty("创建时间")
    private Date createDate;

    @ApiModelProperty("修改时间")
    private Date modifyDate;

    @ApiModelProperty("版本")
    private Integer version;

    @ApiModelProperty("创建者")
    private String creator;

    @ApiModelProperty("修改者")
    private String modifier;

    @ApiModelProperty("业务主键")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "select uuid_short()")
    private String uuid;

    @ApiModelProperty("与会员的关系")
    private String familyRelation;

    @ApiModelProperty("是否删除")
    private Boolean delFlag;

    @ApiModelProperty("模型地址")
    private String modelPath;

    @ApiModelProperty("名称")
    private String figureName;

    @ApiModelProperty("模型名称")
    private String modelName;

    @ApiModelProperty("图片文件")
    private String modelImage;

    @ApiModelProperty("是否默认")
    private Boolean isDefault;

    @ApiModelProperty("盐，数据加密秘钥")
    private String salt;

    @ApiModelProperty("状态")
    private Integer status;

    private static final long serialVersionUID = 1L;

    public enum Status
    {
        /**
         * @Author:xumin
         * @Description:
         * @Date:17:23 2017/11/2
         */
        scanning(0, "扫描中"),
        /**
         * @Author:xumin
         * @Description:
         * @Date:17:23 2017/11/2
         */
        construct(1, "构建中"),
        /**
         * @Author:xumin
         * @Description:
         * @Date:17:23 2017/11/2
         */
        finish(2, "完成");

        private int value;

        private String nameValue;

        Status(int value, String nameValue)
        {
            this.value = value;
            this.nameValue = nameValue;
        }

        public int getValue()
        {
            return value;
        }

        public void setValue(int value)
        {
            this.value = value;
        }

        public String getNameValue()
        {
            return nameValue;
        }

        public void setNameValue(String nameValue)
        {
            this.nameValue = nameValue;
        }
    }

}