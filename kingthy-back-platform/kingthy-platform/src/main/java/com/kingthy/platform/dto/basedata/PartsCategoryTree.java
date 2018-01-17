package com.kingthy.platform.dto.basedata;

import com.kingthy.platform.entity.basedata.PartsCategory;

import java.util.List;

/**
 * 
 *
 * PartsCategoryTree
 * 
 * 赵生辉 2017年4月13日 下午8:20:05
 * 
 * @version 1.0.0
 *
 */
public class PartsCategoryTree extends PartsCategory
{
    
    private List<PartsCategoryTree> childrens;
    
    public List<PartsCategoryTree> getChildrens()
    {
        return childrens;
    }
    
    public void setChildrens(List<PartsCategoryTree> childrens)
    {
        this.childrens = childrens;
    }
    
}