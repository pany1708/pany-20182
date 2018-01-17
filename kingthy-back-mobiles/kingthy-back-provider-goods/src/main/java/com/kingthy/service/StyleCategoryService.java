package com.kingthy.service;

import java.util.List;
import java.util.Map;

import com.kingthy.response.ClassCategoryResp;

/**
 *
 * StyleCategoryService
 * @author xxxx
 *
 */
@FunctionalInterface
public interface StyleCategoryService
{
    /**
     * queryStyleCategory
     * @return
     */
    Map<String, List<ClassCategoryResp>> queryStyleCategory();
    
}
