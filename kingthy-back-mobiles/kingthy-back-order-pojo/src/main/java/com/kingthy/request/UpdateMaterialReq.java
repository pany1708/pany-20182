package com.kingthy.request;

import com.kingthy.dto.AccessoriesDto;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * 
 *
 * CreateMaterial(添加面料参数实体bean)
 * 
 * @author 赵生辉 2017年4月17日 下午4:46:59
 * 
 * @version 1.0.0
 *
 */
@Data
@ToString
public class UpdateMaterialReq implements Serializable
{
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "业务主键")
    private String uuid;

    @ApiModelProperty(value = "面料编码")
    private String code;

    @ApiModelProperty(value = "面料名称")
    private String name;

    @ApiModelProperty(value = "面料成分")
    private String component;

    @ApiModelProperty(value = "幅宽")
    private Float length;

    @ApiModelProperty(value = "克重")
    private Float weight;

    @ApiModelProperty(value = "备注说明")
    private String remark;

    @ApiModelProperty(value = "采样供应商")
    private String supplier;

    @ApiModelProperty(value = "联系人")
    private String linkman;

    @ApiModelProperty(value = "联系电话")
    private String linktel;

    @ApiModelProperty(value = "联系手机")
    private String linkphone;

    @ApiModelProperty(value = "当前分类Uuid")
    private String categoryUuid;
    @ApiModelProperty(value = "顶级分类Uuid")
    private String sourceUuid;

    @ApiModelProperty(value = "计量单位")
    private String measurement;

    @ApiModelProperty(value = "纱支")
    private String yarnCount;

    @ApiModelProperty(value = "是否缩水")
    private Boolean isShrink;

    @ApiModelProperty(value = "价格")
    private BigDecimal price;

    @ApiModelProperty(value = "库存")
    private Integer isStock;

    @ApiModelProperty(value = "洗涤要求")
    private String washingReq;

    @ApiModelProperty(value = "效果图片")
    private String image;

  /*  @ApiModelProperty(value = "面料颜色")
    private String color;*/

    @ApiModelProperty(value = "面料颜色")
//    private List<String> color;
    private List<AccessoriesDto.ColorDTO> color;

    @ApiModelProperty(value = "面料状态")
    private Integer status;

    @ApiModelProperty(value = "供应商地址")
    private String supplierAddress;

    private List<AccessoriesDto.ImageDTO> images;
}
