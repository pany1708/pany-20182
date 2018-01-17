package com.kingthy.platform.dto.goods;

import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.NotEmpty;

import java.io.Serializable;

/**
 * 
 *
 * UuidArray(接收uuid的数组封装类)
 * 
 * 陈钊 2017年4月6日 下午3:54:56
 * 
 * @version 1.0.0
 *
 */
@Data
@ToString
public class GoodsUuidArrayReq implements Serializable
{
    
    /**
     * serialVersionUID:TODO（用一句话描述这个变量表示什么）
     *
     * @since 1.0.0
     */
    
    private static final long serialVersionUID = 1L;
    
    /**
     * 定义数组来接收uuid
     */
    @NotEmpty(message = "必须传入至少一个商品uuid")
    private String[] array;
    
    /**
     * 删除标志
     */
    private Boolean delFlag;
    
    /**
     * 操作
     */
    private GoodsUuidArrayReq.GoodsOperation goodsOperation;
    
    public enum GoodsOperation
    {
        up(1, "上架"), down(0, "下架");
        private int value;
        
        private String nameValue;
        
        private GoodsOperation(int value, String nameValue)
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
