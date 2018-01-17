package com.kingthy.entity;

import com.kingthy.common.BaseTableFileds;
import lombok.Data;
import lombok.ToString;

import javax.persistence.Transient;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
@ToString
public class DyeingPattern  extends BaseTableFileds implements Serializable{

    /**
     * 花样编码
     *
     */
    private String code;

    /**
     * 花样名称
     */
    private String name;

    /**
     * 花样效果图
     */
    private String imageUrl;

    /**
     * 效果图长(mm)
     */
    private Integer imageLength;

    /**
     * 效果图宽(mm)
     */
    private Integer imageWidth;

    /**
     * 生产文件
     */
    private String productFile;

    /**
     * 花样分类UUID
     */
    private String type;

    /**
     * 价格
     */
    private BigDecimal price;

    /**
     * 备注说明
     */
    private String remark;

    /**
     * 状态
     */
    private String status;

    /**
     * 特殊工艺分类名称
     */
    @Transient
    private String typeName;

    /**
     * 是否改变尺寸
     */
    @Transient
    private String ifChangeSize;
    @Transient
    private String beginTime;
    @Transient
    private String endTime;

    public String getSize(){
        return imageLength+"*"+imageWidth;
    }


    /**
     * dyeing_pattern
     */
    private static final long serialVersionUID = 1L;

}