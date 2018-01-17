package com.kingthy.platform.entity.opus;

import java.io.Serializable;

/**
 * 
 * OpusAccessories(作品辅料关联)
 * 
 * yuanml 2017年4月11日 下午7:43:30
 * 
 * @version 1.0.0
 *
 */
public class OpusAccessories implements Serializable
{
    private Integer id;
    
    private String opusUuid;
    
    private String accessoriesUuid;
    
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
    
    public String getAccessoriesUuid()
    {
        return accessoriesUuid;
    }
    
    public void setAccessoriesUuid(String accessoriesUuid)
    {
        this.accessoriesUuid = accessoriesUuid;
    }
}