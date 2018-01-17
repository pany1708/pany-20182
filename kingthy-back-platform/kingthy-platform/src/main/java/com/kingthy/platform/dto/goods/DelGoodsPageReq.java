package com.kingthy.platform.dto.goods;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
public class DelGoodsPageReq implements Serializable
{
    
    /**
     * serialVersionUID:TODO（用一句话描述这个变量表示什么）
     *
     * @since 1.0.0
     */
    
    private static final long serialVersionUID = 1L;
    
    private int pageSize;
    
    private int pageNum;
    
    private String searchName;
}
