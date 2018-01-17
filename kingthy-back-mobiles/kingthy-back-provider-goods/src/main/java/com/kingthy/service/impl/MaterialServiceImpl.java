package com.kingthy.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.kingthy.dubbo.basedata.service.MaterialDubboService;
import com.kingthy.entity.Material;
import com.kingthy.service.MaterialService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author xumin
 * @Description:
 * @DATE Created by 10:34 on 2017/12/22.
 * @Modified by:
 */
@Service
public class MaterialServiceImpl implements MaterialService
{

    @Reference(version = "1.0.0")
    private MaterialDubboService materialDubboService;

    @Override
    public List<Material> listMaterial() throws Exception {
        return materialDubboService.list(0, 10, new Material());
    }
}
