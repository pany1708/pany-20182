package com.kingthy.platform.entity.opus;

import java.io.Serializable;

/**
 *
 * OpusMaterial(作品物料关联)
 * 
 * yuanml 2017年4月11日 下午7:45:17
 * 
 * @version 1.0.0
 *
 */
public class OpusMaterial implements Serializable
{
    private Integer id;
    
    private String opusUuid;
    
    private String materialUuid;
    
    private static final long serialVersionUID = 1L;
    
    public Integer getId()
    {
        return id;
    }
    
    public void setId(Integer id)
    {
        this.id = id;
    }
    
    public String getOpusUuid()
    {
        return opusUuid;
    }
    
    public void setOpusUuid(String opusUuid)
    {
        this.opusUuid = opusUuid;
    }
    
    public String getMaterialUuid()
    {
        return materialUuid;
    }
    
    public void setMaterialUuid(String materialUuid)
    {
        this.materialUuid = materialUuid;
    }
}