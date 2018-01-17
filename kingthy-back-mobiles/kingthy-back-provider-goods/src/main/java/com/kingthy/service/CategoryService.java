package com.kingthy.service;

import java.util.List;
import java.util.Map;

import com.kingthy.response.ClassCategoryResp;


/**
 *
 * CategoryService
 * @author xxxx
 *
 */
@FunctionalInterface
public interface CategoryService
{
    /**
     * queryCategory
     * @param
     * @return
     */
    Map<String, List<ClassCategoryResp>> queryCategory();
}