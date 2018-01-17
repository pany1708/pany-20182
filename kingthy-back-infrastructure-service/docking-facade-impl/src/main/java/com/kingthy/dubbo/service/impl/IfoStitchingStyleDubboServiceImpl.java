package com.kingthy.dubbo.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.kingthy.dubbo.docking.service.IfoStitchingStyleDubboService;
import com.kingthy.entity.IfoOrderStitchingInfo;
import com.kingthy.mapper.IfoStitchingStyleMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author xumin
 * @Description:
 * @DATE Created by 17:47 on 2017/9/20.
 * @Modified by:
 */
@Service(version = "1.0.0", timeout = 3000)
public class IfoStitchingStyleDubboServiceImpl implements IfoStitchingStyleDubboService
{

    @Autowired
    private IfoStitchingStyleMapper ifoStitchingStyleMapper;

    @Override
    public int insert(IfoOrderStitchingInfo t) {

        return ifoStitchingStyleMapper.insert(t);
    }

    @Override
    public int insertList(List<IfoOrderStitchingInfo> stitchingStyleList) {
        return ifoStitchingStyleMapper.insertList(stitchingStyleList);
    }
}
