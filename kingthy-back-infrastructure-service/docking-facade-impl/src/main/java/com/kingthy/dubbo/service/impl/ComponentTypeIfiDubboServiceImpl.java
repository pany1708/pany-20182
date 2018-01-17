package com.kingthy.dubbo.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.kingthy.dubbo.docking.service.ComponentTypeIfiDubboService;
import com.kingthy.entity.ComponentTypeIfi;
import com.kingthy.entity.PartsTypeIfi;
import com.kingthy.mapper.ComponentTypeIfiMapper;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

/**
 * @author xumin
 * @Description: 部件类别
 * @DATE Created by 16:34 on 2017/9/12.
 * @Modified by:
 */
@Service(version = "1.0.0", timeout = 3000)
public class ComponentTypeIfiDubboServiceImpl implements ComponentTypeIfiDubboService
{

    @Autowired
    private ComponentTypeIfiMapper componentTypeIfiMapper;

    @Override
    public List<ComponentTypeIfi> queryListByLimit(Integer pageNum, Integer pageSize, Integer operSt) {

        PageHelper.startPage(pageNum, pageSize);

        ComponentTypeIfi var = new ComponentTypeIfi();
        var.setOperSt(operSt);
        var.setIfStatus(ComponentTypeIfi.StatusType.init.getValue());
        return componentTypeIfiMapper.select(var);
    }

    @Override
    public int updateStatusById(Integer id, ComponentTypeIfi.StatusType status) {
        ComponentTypeIfi var = new ComponentTypeIfi();
        var.setId(id);
        var.setUpdateTime(new Date());
        var.setIfStatus(status.getValue());
        return componentTypeIfiMapper.updateByPrimaryKeySelective(var);
    }

    @Override
    public List<ComponentTypeIfi> queryUpdateListByListCode(List<String> codes) {

        Example example = new Example(ComponentTypeIfi.class);
        example.createCriteria().andIn("code", codes)
                .andEqualTo("operSt", ComponentTypeIfi.OperStType.add.getValue())
                .andEqualTo("ifStatus", ComponentTypeIfi.StatusType.init.getValue());
        return componentTypeIfiMapper.selectByExample(example);
    }
}
