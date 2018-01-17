package com.kingthy.platform.entity.opus;

import java.io.Serializable;

/**
 *
 * OpusPartsubAccessories(部件辅料关联)
 * 
 * yuanml 2017年4月11日 下午7:45:49
 * 
 * @version 1.0.0
 *
 */
public class OpusPartsubAccessories implements Serializable
{
    private Integer id;
    
    private String uuid;
    
    private String partsubUuid;
    
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
    
    public String getUuid()
    {
        return uuid;
    }
    
    public void setUuid(String uuid)
    {
        this.uuid = uuid;
    }
    
    public String getPartsubUuid()
    {
        return partsubUuid;
    }
    
    public void setPartsubUuid(String partsubUuid)
    {
        this.partsubUuid = partsubUuid;
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