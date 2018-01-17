package com.kingthy.dubbo.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.kingthy.dubbo.docking.service.MaterialMIfoDubboService;
import com.kingthy.entity.MaterialMIfo;
import com.kingthy.mapper.MaterialMIfoMapper;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author xumin
 * @Description:
 * @DATE Created by 13:59 on 2017/9/18.
 * @Modified by:
 */
@Service(version = "1.0.0", timeout = 3000)
public class MaterialMIfoDubboServiceImpl implements MaterialMIfoDubboService
{

    @Autowired
    private MaterialMIfoMapper materialMIfoMapper;

    @Override
    public int insert(MaterialMIfo t) {
        return materialMIfoMapper.insert(t);
    }
}
