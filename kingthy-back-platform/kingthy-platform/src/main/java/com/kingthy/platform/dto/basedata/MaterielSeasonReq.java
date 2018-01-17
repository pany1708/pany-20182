/**
 * 系统项目名称
 * com.kingthy.platform.dto.basedata
 * MaterielCategoryInsertReq.java
 * 
 * 2017年4月12日-下午7:51:56
 *  2017金昔科技有限公司-版权所有
 *
 */
package com.kingthy.platform.dto.basedata;

import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.NotEmpty;

import java.io.Serializable;

/**
 *
 * 新增物料分类时传入参数
 * 
 * yuanml 2017年4月12日 下午7:51:56
 * 
 * @version 1.0.0
 *
 */
@Data
@ToString
public class MaterielSeasonReq implements Serializable
{
    private static final long serialVersionUID = 1L;

    @NotEmpty(message = "分类名称不能为空")
    private String className;
    
    private Boolean status;

    private String description;

}
