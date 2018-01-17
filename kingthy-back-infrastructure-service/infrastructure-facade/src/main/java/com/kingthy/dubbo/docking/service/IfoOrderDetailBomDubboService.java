package com.kingthy.dubbo.docking.service;

import com.kingthy.entity.IfoOrderDetailBom;

import java.util.List;

/**
 * @author xumin
 * @Description:
 * @DATE Created by 17:11 on 2017/9/20.
 * @Modified by:
 */
public interface IfoOrderDetailBomDubboService
{
    int insert(IfoOrderDetailBom t);

    int insertList(List<IfoOrderDetailBom> bomList);
}
