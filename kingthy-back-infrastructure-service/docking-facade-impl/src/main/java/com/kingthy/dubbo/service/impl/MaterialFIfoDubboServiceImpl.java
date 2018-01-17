package com.kingthy.dubbo.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.kingthy.dubbo.docking.service.MaterialFIfoDubboService;
import com.kingthy.entity.MaterialFIfo;
import com.kingthy.mapper.MaterialFIfoMapper;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author xumin
 * @Description:
 * @DATE Created by 14:33 on 2017/9/18.
 * @Modified by:
 */
@Service(version = "1.0.0", timeout = 3000)
public class MaterialFIfoDubboServiceImpl implements MaterialFIfoDubboService
{

    @Autowired
    private MaterialFIfoMapper materialFIfoMapper;

    @Override
    public int insert(MaterialFIfo t) {
        return materialFIfoMapper.insert(t);
    }
}
