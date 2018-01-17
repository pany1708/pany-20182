package com.kingthy.dubbo.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.kingthy.dubbo.docking.service.ColourIfiDubboService;
import com.kingthy.entity.ColourIfi;
import com.kingthy.mapper.ColourIfiMapper;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

/**
 * @author xumin
 * @Description:
 * @DATE Created by 18:06 on 2017/9/6.
 * @Modified by:
 */
@Service(version = "1.0.0", timeout = 3000)
public class ColourIfiDubboServiceImpl implements ColourIfiDubboService {

    @Autowired
    private ColourIfiMapper colourIfiMapper;

    @Override
    public List<ColourIfi> queryListByLimit(Integer pageNum, Integer pageSize, Integer operSt) {

        PageHelper.startPage(pageNum, pageSize);

        ColourIfi var = new ColourIfi();
        var.setOperSt(operSt);
        var.setIfStatus(ColourIfi.StatusType.init.getValue());
        return colourIfiMapper.select(var);
    }

    @Override
    public int updateStatusById(Integer id, ColourIfi.StatusType status)
    {
        ColourIfi var = new ColourIfi();
        var.setId(id);
        var.setUpdateTime(new Date());
        var.setIfStatus(status.getValue());
        return colourIfiMapper.updateByPrimaryKeySelective(var);
    }

    @Override
    public List<ColourIfi> queryUpdateListByListCode(List<String> codes) {

        Example example = new Example(ColourIfi.class);
        example.createCriteria().andIn("code", codes)
                .andEqualTo("operSt", ColourIfi.OperStType.add.getValue())
                .andEqualTo("ifStatus", ColourIfi.StatusType.init.getValue());
        return colourIfiMapper.selectByExample(example);
    }

}
