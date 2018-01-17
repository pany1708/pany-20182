package com.kingthy.dubbo.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kingthy.dubbo.basedata.service.OperateFashionCollectionDubboService;
import com.kingthy.entity.OperateFashionCollection;
import com.kingthy.mapper.OperateFashionCollectionMapper;
import com.kingthy.request.OperateFashionCollectionReq;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @author chenz
 * @version 1.0.0
 * @class_name OperateFashionCollectionServiceImpl
 * @description 潮搭接口实现类
 * @create 2017/8/25
 */
@Service(version = "1.0.0",timeout = 3000)
public class OperateFashionCollectionServiceImpl implements OperateFashionCollectionDubboService{

    @Autowired
    private OperateFashionCollectionMapper operateFashionCollectionMapper;

    @Override
    public int insert(OperateFashionCollection operateFashionCollection) {
        operateFashionCollection.setVersion(1);
        return operateFashionCollectionMapper.insert(operateFashionCollection);
    }

    @Override
    public int updateByUuid(OperateFashionCollection operateFashionCollection) {
        OperateFashionCollection operateFashionCollectionResult = selectByUuid(operateFashionCollection.getUuid());
        Integer currentVersion = operateFashionCollectionResult.getVersion();
        Example example = new Example(OperateFashionCollection.class);
        Example.Criteria  criteria = example.createCriteria();
        criteria.andEqualTo("uuid",operateFashionCollection.getUuid());
        criteria.andEqualTo("version",currentVersion);
        operateFashionCollection.setVersion(currentVersion+1);
        return operateFashionCollectionMapper.updateByExampleSelective(operateFashionCollection,example);
    }

    @Override
    public List<OperateFashionCollection> selectAll() {
        return null;
    }

    @Override
    public OperateFashionCollection selectByUuid(String uuid) {
        return operateFashionCollectionMapper.selectByUuid(uuid);
    }

    @Override
    public int selectCount(OperateFashionCollection operateFashionCollection) {
        return operateFashionCollectionMapper.selectCount(operateFashionCollection);
    }

    @Override
    public List<OperateFashionCollection> select(OperateFashionCollection var1) {
        return operateFashionCollectionMapper.select(var1);
    }

    @Override
    public OperateFashionCollection selectOne(OperateFashionCollection var1) {
        return operateFashionCollectionMapper.selectOne(var1);
    }

    @Override
    public PageInfo<OperateFashionCollection> queryPage(Integer pageNum, Integer pageSize, OperateFashionCollection operateFashionCollection) {
        return null;
    }

    @Override
    public PageInfo<OperateFashionCollection> queryPage(OperateFashionCollectionReq operateFashionCollectionReq) {
        PageHelper.startPage(operateFashionCollectionReq.getPageNum(),operateFashionCollectionReq.getPageSize());
        List<OperateFashionCollection> result = operateFashionCollectionMapper.findByPage(operateFashionCollectionReq);
        return new PageInfo<>(result);
    }
}
