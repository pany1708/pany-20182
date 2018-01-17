package com.kingthy.dubbo.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kingthy.dubbo.basedata.service.CollocationDubboService;
import com.kingthy.entity.Collocation;
import com.kingthy.mapper.CollocationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @author zhaochen 2017/8/29.
 */
@Service(version = "1.0.0",timeout = 3000)
public class CollocationDubboServiceImpl implements CollocationDubboService {

    @Autowired
    private  CollocationMapper mapper;

    @Override
    public int insert(Collocation collocation) {
        collocation.setDelFlag(false);
        return mapper.insert(collocation);
    }

    @Override
    public int updateByUuid(Collocation collocation) {
        Collocation collocationResult = selectByUuid(collocation.getUuid());
        Integer currentVersion = collocationResult.getVersion();
        Example example=new Example(Collocation.class);
        Example.Criteria criteria=example.createCriteria();
        criteria.andEqualTo("uuid",collocation.getUuid());
        criteria.andEqualTo("version",currentVersion);
        collocation.setVersion(currentVersion+1);
        return mapper.updateByExample(collocation,example);
    }

    @Override
    public List<Collocation> selectAll() {

        return mapper.selectAll();
    }

    @Override
    public Collocation selectByUuid(String uuid) {
        Example example=new Example(Collocation.class);
        Example.Criteria criteria=example.createCriteria();
        criteria.andEqualTo("uuid",uuid);
        List<Collocation> list= mapper.selectByExample(example);
        if(list.size()>0){
            return list.get(0);
        }
        return new Collocation();
    }

    @Override
    public int selectCount(Collocation collocation) {
        collocation.setDelFlag(false);
        return mapper.selectCount(collocation);
    }

    @Override
    public List<Collocation> select(Collocation var1) {
        var1.setDelFlag(false);
        return mapper .select(var1);
    }

    @Override
    public Collocation selectOne(Collocation var1) {
        var1.setDelFlag(false);
        return mapper.selectOne(var1);
    }

    @Override
    public PageInfo<Collocation> queryPage(Integer pageNum, Integer pageSize, Collocation collocation) {

        PageHelper.startPage(pageNum,pageSize);
        List<Collocation> list=mapper.findByPage(collocation);
        return  new  PageInfo<>(list);
    }
}
