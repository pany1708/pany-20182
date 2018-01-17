package com.kingthy.dubbo.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.kingthy.dubbo.docking.service.IfoProcessInfoDuubboService;
import com.kingthy.entity.IfoProcessInfo;
import com.kingthy.mapper.IfoProcessInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author xumin
 * @Description:
 * @DATE Created by 17:26 on 2017/9/20.
 * @Modified by:
 */
@Service(version = "1.0.0", timeout = 3000)
public class IfoProcessInfoDuubboServiceImpl implements IfoProcessInfoDuubboService
{

    @Autowired
    private IfoProcessInfoMapper ifoProcessInfoMapper;

    @Override
    public int insert(IfoProcessInfo t) {
        return ifoProcessInfoMapper.insert(t);
    }

    @Override
    public int insertList(List<IfoProcessInfo> processInfoList) {
        return ifoProcessInfoMapper.insertList(processInfoList);
    }
}
