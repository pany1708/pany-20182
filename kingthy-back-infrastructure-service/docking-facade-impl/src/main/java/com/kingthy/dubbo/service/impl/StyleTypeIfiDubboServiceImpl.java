package com.kingthy.dubbo.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.kingthy.dubbo.docking.service.StyleTypeIfiDubboService;
import com.kingthy.entity.StyleTypeIfi;
import com.kingthy.mapper.StyleTypeIfiMapper;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

/**
 * @author xumin
 * @Description:
 * @DATE Created by 11:30 on 2017/9/12.
 * @Modified by:
 */

@Service(version = "1.0.0", timeout = 3000)
public class StyleTypeIfiDubboServiceImpl implements StyleTypeIfiDubboService {

    @Autowired
    private StyleTypeIfiMapper styleTypeIfiMapper;

    @Override
    public List<StyleTypeIfi> queryListByLimit(Integer pageNum, Integer pageSize, Integer operSt)
    {
        PageHelper.startPage(pageNum, pageSize);

        StyleTypeIfi var = new StyleTypeIfi();
        var.setOperSt(operSt);
        var.setIfStatus(StyleTypeIfi.StatusType.init.getValue());
        return styleTypeIfiMapper.select(var);
    }

    @Override
    public int updateStatusById(Integer id, StyleTypeIfi.StatusType status)
    {
        StyleTypeIfi var = new StyleTypeIfi();
        var.setId(id);
        var.setUpdateTime(new Date());
        var.setIfStatus(status.getValue());
        return styleTypeIfiMapper.updateByPrimaryKeySelective(var);
    }

    @Override
    public List<StyleTypeIfi> queryUpdateListByListCode(List<String> codes) {

        Example example = new Example(StyleTypeIfi.class);
        example.createCriteria().andIn("code", codes)
                .andEqualTo("operSt", StyleTypeIfi.OperStType.add.getValue())
                .andEqualTo("ifStatus", StyleTypeIfi.StatusType.init.getValue());
        return styleTypeIfiMapper.selectByExample(example);
    }
}
