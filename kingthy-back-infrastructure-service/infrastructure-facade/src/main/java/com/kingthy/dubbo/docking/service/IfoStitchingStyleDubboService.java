package com.kingthy.dubbo.docking.service;

import com.kingthy.entity.IfoOrderStitchingInfo;

import java.util.List;

/**
 * @author xumin
 * @Description:
 * @DATE Created by 17:35 on 2017/9/20.
 * @Modified by:
 */
public interface IfoStitchingStyleDubboService
{
    int insert(IfoOrderStitchingInfo t);

    int insertList(List<IfoOrderStitchingInfo> stitchingStyleList);
}
