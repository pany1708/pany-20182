package com.kingthy.dubbo.docking.service;


import com.kingthy.entity.StyleTypeIfi;

import java.util.List;

/**
 * @author xumin
 * @Description:
 * @DATE Created by 11:25 on 2017/9/12.
 * @Modified by:
 */
public interface StyleTypeIfiDubboService
{

    List<StyleTypeIfi> queryListByLimit(Integer pageNum, Integer pageSize, Integer operSt);

    int updateStatusById(Integer id, StyleTypeIfi.StatusType status);

    List<StyleTypeIfi> queryUpdateListByListCode(List<String> codes);
}
