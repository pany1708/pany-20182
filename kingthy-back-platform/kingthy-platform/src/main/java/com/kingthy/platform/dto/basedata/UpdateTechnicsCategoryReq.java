package com.kingthy.platform.dto.basedata;

import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.NotEmpty;

import java.io.Serializable;

/**
 * UpdateTechnicsCategoryReq(描述其作用)
 * <p>
 * 赵生辉 2017年07月28日 18:23
 *
 * @version 1.0.0
 */
@Data
@ToString
public class UpdateTechnicsCategoryReq implements Serializable
{
    private static final long serialVersionUID = 1L;

    private  String uuid;
    /*
     * 名称
     */
    @NotEmpty(message = "标签名不能为空")
    private String name;

    /*
     * 状态
     */
    private Boolean status;

    /**
     * 工艺类型
     */
    private Integer type;

    /*
     * 描述
     */
    private String description;

}
