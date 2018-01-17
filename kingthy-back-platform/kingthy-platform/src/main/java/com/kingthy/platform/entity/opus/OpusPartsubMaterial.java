package com.kingthy.platform.entity.opus;

import java.io.Serializable;

/**
 *
 * OpusPartsubMaterial(部件物料关联)
 * 
 * yuanml 2017年4月11日 下午7:46:47
 * 
 * @version 1.0.0
 *
 */
public class OpusPartsubMaterial implements Serializable
{
    private Integer id;
    
    private String uuid;
    
    private String partsubUuid;
    
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
    
    public String getMaterialUuid()
    {
        return materialUuid;
    }
    
    public void setMaterialUuid(String materialUuid)
    {
        this.materialUuid = materialUuid;
    }
}