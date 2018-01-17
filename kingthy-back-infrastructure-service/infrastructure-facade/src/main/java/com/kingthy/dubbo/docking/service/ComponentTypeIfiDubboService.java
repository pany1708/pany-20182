package com.kingthy.dubbo.docking.service;

import com.kingthy.entity.ComponentTypeIfi;
import com.kingthy.entity.PartsTypeIfi;

import java.util.List;

/**
 * @author xumin
 * @Description: 部件类别
 * @DATE Created by 16:34 on 2017/9/12.
 * @Modified by:
 */
public interface ComponentTypeIfiDubboService
{

    List<ComponentTypeIfi> queryListByLimit(Integer pageNum, Integer pageSize, Integer operSt);

    int updateStatusById(Integer id, ComponentTypeIfi.StatusType status);

    List<ComponentTypeIfi> queryUpdateListByListCode(List<String> codes);
}
