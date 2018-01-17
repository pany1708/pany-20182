package com.kingthy.dubbo.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.kingthy.dubbo.docking.service.IfoOrderDetailBomDubboService;
import com.kingthy.entity.IfoOrderDetailBom;
import com.kingthy.mapper.IfoOrderDetailBomMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author xumin
 * @Description:
 * @DATE Created by 17:22 on 2017/9/20.
 * @Modified by:
 */
@Service(version = "1.0.0", timeout = 3000)
public class IfoOrderDetailBomDubboServiceImpl implements IfoOrderDetailBomDubboService {

    @Autowired
    private IfoOrderDetailBomMapper ifoOrderDetailBomMapper;

    @Override
    public int insert(IfoOrderDetailBom t) {
        return ifoOrderDetailBomMapper.insert(t);
    }

    @Override
    public int insertList(List<IfoOrderDetailBom> bomList) {
        return ifoOrderDetailBomMapper.insertList(bomList);
    }
}
