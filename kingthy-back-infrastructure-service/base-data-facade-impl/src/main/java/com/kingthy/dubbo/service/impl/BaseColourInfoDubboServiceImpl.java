package com.kingthy.dubbo.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.kingthy.dubbo.basedata.service.BaseColourInfoDubboService;
import com.kingthy.entity.BaseColourInfo;
import com.kingthy.mapper.BaseColourInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author zhaochen
 * @Description:
 * @DATE Created by 13:59 on 2017/9/7.
 * @Modified by:
 */
@Service(version = "1.0.0", timeout = 3000)
public class BaseColourInfoDubboServiceImpl implements BaseColourInfoDubboService
{

    @Autowired
    private BaseColourInfoMapper baseColourInfoMapper;

    @Override
    public int insert(BaseColourInfo var) {
        return baseColourInfoMapper.insert(var);
    }

    @Override
    public int insert(List<BaseColourInfo> vars) {

        if (vars == null || vars.isEmpty()){
            return 0;
        }

        Example example = new Example(BaseColourInfo.class);
        example.createCriteria().andIn("code", vars.stream().map(b -> b.getCode()).collect(Collectors.toList()));

        Set<String> stringSet = baseColourInfoMapper.selectByExample(example).stream().map(c -> c.getCode()).collect(Collectors.toSet());

        vars.forEach(v -> {
            if (stringSet.contains(v.getCode())){
                vars.remove(v);
            }
        });

        return baseColourInfoMapper.insertList(vars);
    }

    @Override
    public int updateByCode(BaseColourInfo var) {
        Example example = new Example(BaseColourInfo.class);
        example.createCriteria().andEqualTo("code", var.getCode());
        return baseColourInfoMapper.updateByExampleSelective(var, example);
    }

    @Override
    public void updates(List<BaseColourInfo> vars) {

        vars.forEach(b -> updateByCode(b));
    }

    @Override
    public int deleteByCode(BaseColourInfo var) {

        Example example = new Example(BaseColourInfo.class);
        example.createCriteria().andEqualTo("code", var.getCode()).andEqualTo("status", "1");
        return baseColourInfoMapper.deleteByExample(example);
    }

    @Override
    public void deletes(List<BaseColourInfo> vars) {

        vars.forEach(b -> deleteByCode(b));
    }

    @Override
    public int selectColourCountByCode(String code) {
        return baseColourInfoMapper.selectColourCountByCode(code);
    }

}
