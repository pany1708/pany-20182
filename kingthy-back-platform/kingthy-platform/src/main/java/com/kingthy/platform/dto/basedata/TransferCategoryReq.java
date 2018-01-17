package com.kingthy.platform.dto.basedata;

import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.NotEmpty;

import java.io.Serializable;

/**
 * 
 *
 * TransferCategoryReq(转移分类接口入参封装类)
 * 
 * 陈钊 2017年3月31日 下午4:11:02
 * 
 * @version 1.0.0
 *
 */
@Data
@ToString
public class TransferCategoryReq implements Serializable
{
    
    /**
     * serialVersionUID:TODO（用一句话描述这个变量表示什么）
     *
     * @since 1.0.0
     */
    
    private static final long serialVersionUID = 1L;
    
    // 原父节点的uuid
    @NotEmpty(message = "原父节点主键不能为空")
    private String sourceUuid;
    
    // 新父节点的uuid
    @NotEmpty(message = "新父节点主键不能为空")
    private String targetUuid;
    
    // 原父节点的级别编号
    private int sourceGrade;
    
    // 新父节点的级别编号
    private int targetGrade;
    
}
