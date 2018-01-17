package com.kingthy.platform.entity.basedata;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@Data
@ToString
public class Area implements Serializable
{
    @javax.persistence.Id
    private Integer id;

    @JsonIgnore
    private Date createDate;

    @JsonIgnore
    private Date modifyDate;
    
    @JsonIgnore
    private String creator;
    
    @JsonIgnore
    private String modifier;
    
    @JsonIgnore
    private Integer version;
    
    @JsonIgnore
    private Integer orders;
    
    private Integer grade;
    
    private String name;
    
    @JsonIgnore
    private String treePath;
    
    private Long areaParentId;
    
    private String fullName;
    
    private static final long serialVersionUID = 1L;
    

}