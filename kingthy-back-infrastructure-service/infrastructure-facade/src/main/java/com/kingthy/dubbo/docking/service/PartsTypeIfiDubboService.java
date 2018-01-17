package com.kingthy.dubbo.docking.service;

import com.kingthy.entity.PartsTypeIfi;

import java.util.List;

/**
 * @author xumin
 * @Description: 零件类别
 * @DATE Created by 14:24 on 2017/9/13.
 * @Modified by:
 */
public interface PartsTypeIfiDubboService
{

    List<PartsTypeIfi> queryListByLimit(Integer pageNum, Integer pageSize, Integer operSt);

    int updateStatusById(Integer id, PartsTypeIfi.StatusType status);

    List<PartsTypeIfi> queryUpdateListByListCode(List<String> codes);
}
