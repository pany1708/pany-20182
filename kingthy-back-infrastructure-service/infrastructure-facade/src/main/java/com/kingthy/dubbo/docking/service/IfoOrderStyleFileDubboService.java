package com.kingthy.dubbo.docking.service;

import com.kingthy.entity.IfoOrderStyleFileInfo;

import java.util.List;

/**
 * @author xumin
 * @Description:
 * @DATE Created by 17:51 on 2017/9/20.
 * @Modified by:
 */
public interface IfoOrderStyleFileDubboService
{
    int insert(IfoOrderStyleFileInfo t);

    int insertList(List<IfoOrderStyleFileInfo> styleFileList);
}
