package com.kingthy.dubbo.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.kingthy.dubbo.docking.service.IfoOrderStyleFileDubboService;
import com.kingthy.entity.IfoOrderStyleFileInfo;
import com.kingthy.mapper.IfoOrderStyleFileMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author xumin
 * @Description:
 * @DATE Created by 17:51 on 2017/9/20.
 * @Modified by:
 */
@Service(version = "1.0.0", timeout = 3000)
public class IfoOrderStyleFileDubboServiceImpl implements IfoOrderStyleFileDubboService
{

    @Autowired
    private IfoOrderStyleFileMapper ifoOrderStyleFileMapper;

    @Override
    public int insert(IfoOrderStyleFileInfo t) {
        return ifoOrderStyleFileMapper.insert(t);
    }

    @Override
    public int insertList(List<IfoOrderStyleFileInfo> styleFileList) {
        return ifoOrderStyleFileMapper.insertList(styleFileList);
    }
}
