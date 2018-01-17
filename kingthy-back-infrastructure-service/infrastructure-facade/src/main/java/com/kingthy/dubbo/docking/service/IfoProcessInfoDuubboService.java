package com.kingthy.dubbo.docking.service;

import com.kingthy.entity.IfoProcessInfo;

import java.util.List;

/**
 * @author xumin
 * @Description:
 * @DATE Created by 17:26 on 2017/9/20.
 * @Modified by:
 */
public interface IfoProcessInfoDuubboService
{
    int insert(IfoProcessInfo t);

    int insertList(List<IfoProcessInfo> processInfoList);
}
