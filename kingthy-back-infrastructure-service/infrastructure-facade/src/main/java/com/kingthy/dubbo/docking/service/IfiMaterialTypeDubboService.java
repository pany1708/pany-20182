package com.kingthy.dubbo.docking.service;

import com.kingthy.entity.IfiMaterialType;

import java.util.List;

/**
 * @author xumin
 * @Description:
 * @DATE Created by 15:45 on 2017/9/22.
 * @Modified by:
 */
public interface IfiMaterialTypeDubboService {

    List<IfiMaterialType> queryListByLimit(Integer pageNum, Integer pageSize, Integer operSt);

    int updateStatusById(Integer id, IfiMaterialType.StatusType status);

    List<IfiMaterialType> queryUpdateListByListCode(List<String> codes);
}
