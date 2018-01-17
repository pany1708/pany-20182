package com.kingthy.dubbo.docking.service;

import com.kingthy.entity.IfiMeasureUnit;

import java.util.List;

/**
 * @author xumin
 * @Description:计量单位
 * @DATE Created by 18:37 on 2017/9/26.
 * @Modified by:
 */
public interface IfiMeasureUnitDubboService {

    List<IfiMeasureUnit> queryListByLimit(Integer pageNum, Integer pageSize, Integer operSt);

    int updateStatusById(Integer id, IfiMeasureUnit.StatusType status);

    List<IfiMeasureUnit> queryUpdateListByListCode(List<String> codes);
}
