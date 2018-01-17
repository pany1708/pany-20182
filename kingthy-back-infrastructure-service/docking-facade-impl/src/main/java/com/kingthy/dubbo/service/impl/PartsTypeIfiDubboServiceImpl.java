package com.kingthy.dubbo.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.kingthy.dubbo.docking.service.PartsTypeIfiDubboService;
import com.kingthy.entity.PartsTypeIfi;
import com.kingthy.mapper.PartsTypeIfiMapper;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

/**
 * @author xumin
 * @Description: 零件类别
 * @DATE Created by 14:29 on 2017/9/13.
 * @Modified by:
 */
@Service(version = "1.0.0", timeout = 3000)
public class PartsTypeIfiDubboServiceImpl implements PartsTypeIfiDubboService
{
    @Autowired
    private PartsTypeIfiMapper partsTypeIfiMapper;

    @Override
    public List<PartsTypeIfi> queryListByLimit(Integer pageNum, Integer pageSize, Integer operSt) {

        PageHelper.startPage(pageNum, pageSize);

        PartsTypeIfi var = new PartsTypeIfi();
        var.setOperSt(operSt);
        var.setIfStatus(PartsTypeIfi.StatusType.init.getValue());
        return partsTypeIfiMapper.select(var);
    }

    @Override
    public int updateStatusById(Integer id, PartsTypeIfi.StatusType status) {

        PartsTypeIfi var = new PartsTypeIfi();
        var.setId(id);
        var.setUpdateTime(new Date());
        var.setIfStatus(status.getValue());
        return partsTypeIfiMapper.updateByPrimaryKeySelective(var);
    }

    @Override
    public List<PartsTypeIfi> queryUpdateListByListCode(List<String> codes) {
        Example example = new Example(PartsTypeIfi.class);
        example.createCriteria().andIn("code", codes)
                .andEqualTo("operSt", PartsTypeIfi.OperStType.add.getValue())
                .andEqualTo("ifStatus", PartsTypeIfi.StatusType.init.getValue());
        return partsTypeIfiMapper.selectByExample(example);
    }
}
