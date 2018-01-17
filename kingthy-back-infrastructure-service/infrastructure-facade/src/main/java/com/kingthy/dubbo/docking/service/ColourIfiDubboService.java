package com.kingthy.dubbo.docking.service;

import com.kingthy.entity.ColourIfi;

import java.util.List;

/**
 * @author xumin
 * @Description:
 * @DATE Created by 17:46 on 2017/9/6.
 * @Modified by:
 */
public interface ColourIfiDubboService
{

    List<ColourIfi> queryListByLimit(Integer pageNum, Integer pageSize, Integer operSt);

    int updateStatusById(Integer id, ColourIfi.StatusType status);

    List<ColourIfi> queryUpdateListByListCode(List<String> codes);

}
