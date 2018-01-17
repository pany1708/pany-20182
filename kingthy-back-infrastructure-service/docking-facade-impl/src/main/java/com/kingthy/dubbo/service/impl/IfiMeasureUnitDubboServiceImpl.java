package com.kingthy.dubbo.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.kingthy.common.CommonUtils;
import com.kingthy.dubbo.docking.service.IfiMeasureUnitDubboService;
import com.kingthy.entity.IfiMeasureUnit;
import com.kingthy.mapper.IfiMeasureUnitMapper;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

/**
 * @author xumin
 * @Description:
 * @DATE Created by 18:40 on 2017/9/26.
 * @Modified by:
 */
@Service(version = "1.0.0", timeout = 3000)
public class IfiMeasureUnitDubboServiceImpl implements IfiMeasureUnitDubboService
{
    @Autowired
    private IfiMeasureUnitMapper ifiMeasureUnitMapper;

    @Override
    public List<IfiMeasureUnit> queryListByLimit(Integer pageNum, Integer pageSize, Integer operSt) {

        PageHelper.startPage(pageNum, pageSize);

        IfiMeasureUnit var = new IfiMeasureUnit();
        var.setOperSt(operSt);
        var.setIfStatus(IfiMeasureUnit.StatusType.init.getValue());
        return ifiMeasureUnitMapper.select(var);

    }

    @Override
    public int updateStatusById(Integer id, IfiMeasureUnit.StatusType status) {
        IfiMeasureUnit var = new IfiMeasureUnit();
        var.setId(id);
        var.setUpdateTime(new Date());
        var.setIfStatus(status.getValue());
        var.setUpdaterId(CommonUtils.updaterId);
        return ifiMeasureUnitMapper.updateByPrimaryKeySelective(var);
    }

    @Override
    public List<IfiMeasureUnit> queryUpdateListByListCode(List<String> codes) {
        Example example = new Example(IfiMeasureUnit.class);
        example.createCriteria().andIn("code", codes)
                .andEqualTo("operSt", IfiMeasureUnit.OperStType.add.getValue())
                .andEqualTo("ifStatus", IfiMeasureUnit.StatusType.init.getValue());
        return ifiMeasureUnitMapper.selectByExample(example);
    }
}
