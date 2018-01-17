package com.kingthy.platform.entity.opus;

import java.io.Serializable;

/**
 *
 * OpusTechnics(作品工艺关联)
 * 
 * yuanml 2017年4月11日 下午7:47:44
 * 
 * @version 1.0.0
 *
 */
public class OpusTechnics implements Serializable
{
    private Integer id;
    
    private String opusUuid;
    
    private String technicsUuid;
    
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
    
    public String getTechnicsUuid()
    {
        return technicsUuid;
    }
    
    public void setTechnicsUuid(String technicsUuid)
    {
        this.technicsUuid = technicsUuid;
    }
}