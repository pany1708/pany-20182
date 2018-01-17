package com.kingthy.dubbo.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.kingthy.common.CommonUtils;
import com.kingthy.dubbo.docking.service.IfiMaterialTypeDubboService;
import com.kingthy.entity.IfiMaterialType;
import com.kingthy.mapper.IfiMaterialTypeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

/**
 * @author xumin
 * @Description:
 * @DATE Created by 15:46 on 2017/9/22.
 * @Modified by:
 */

@Service(version = "1.0.0", timeout = 3000)
public class IfiMaterialTypeDubboServiceImpl implements IfiMaterialTypeDubboService {

    @Autowired
    private IfiMaterialTypeMapper ifiMaterialTypeMapper;

    @Override
    public List<IfiMaterialType> queryListByLimit(Integer pageNum, Integer pageSize, Integer operSt) {
        PageHelper.startPage(pageNum, pageSize);

        IfiMaterialType var = new IfiMaterialType();
        var.setOperSt(operSt);
        var.setIfStatus(IfiMaterialType.StatusType.init.getValue());
        return ifiMaterialTypeMapper.select(var);
    }

    @Override
    public int updateStatusById(Integer id, IfiMaterialType.StatusType status) {
        IfiMaterialType var = new IfiMaterialType();
        var.setId(id);
        var.setUpdateTime(new Date());
        var.setIfStatus(status.getValue());
        var.setUpdaterId(CommonUtils.updaterId);
        return ifiMaterialTypeMapper.updateByPrimaryKeySelective(var);
    }

    @Override
    public List<IfiMaterialType> queryUpdateListByListCode(List<String> codes) {
        Example example = new Example(IfiMaterialType.class);
        example.createCriteria().andIn("code", codes)
                .andEqualTo("operSt", IfiMaterialType.OperStType.add.getValue())
                .andEqualTo("ifStatus", IfiMaterialType.StatusType.init.getValue());
        return ifiMaterialTypeMapper.selectByExample(example);
    }
}
