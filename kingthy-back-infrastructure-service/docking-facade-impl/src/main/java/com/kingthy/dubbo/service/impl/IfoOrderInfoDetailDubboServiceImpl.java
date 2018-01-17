package com.kingthy.dubbo.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.kingthy.dubbo.docking.service.IfoOrderInfoDetailDubboService;
import com.kingthy.entity.IfoOrderInfoDetail;
import com.kingthy.mapper.IfoOrderInfoDetailMapper;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author xumin
 * @Description:
 * @DATE Created by 17:03 on 2017/9/20.
 * @Modified by:
 */
@Service(version = "1.0.0", timeout = 3000)
public class IfoOrderInfoDetailDubboServiceImpl implements IfoOrderInfoDetailDubboService {


    @Autowired
    private IfoOrderInfoDetailMapper ifoOrderInfoDetailMapper;

    @Override
    public int insert(IfoOrderInfoDetail t) {

        return ifoOrderInfoDetailMapper.insert(t);
    }
}
