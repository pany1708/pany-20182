package com.kingthy.dubbo.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kingthy.dubbo.basedata.service.OperateAdvertisementDubboService;
import com.kingthy.entity.OperateAdvertisement;
import com.kingthy.mapper.OperateAdvertisementMapper;
import com.kingthy.request.OperateAdvertisementReq;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @author chenz
 * @version 1.0.0
 * @class_name OperateAdvertisementServiceImpl
 * @description 广告管理类
 * @create 2017/8/25
 */
@Service(version = "1.0.0",timeout = 3000)
public class OperateAdvertisementServiceImpl implements OperateAdvertisementDubboService {

    @Autowired
    private OperateAdvertisementMapper operateAdvertisementMapper;

    @Override
    public int insert(OperateAdvertisement operateAdvertisement) {
        operateAdvertisement.setVersion(1);
        return operateAdvertisementMapper.insert(operateAdvertisement);
    }

    @Override
    public int updateByUuid(OperateAdvertisement operateAdvertisement) {
        OperateAdvertisement operateAdvertisementResult = selectByUuid(operateAdvertisement.getUuid());
        Integer currentVersion = operateAdvertisementResult.getVersion();
        Example example = new Example(OperateAdvertisement.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("uuid", operateAdvertisement.getUuid());
        criteria.andEqualTo("version",currentVersion);
        operateAdvertisement.setVersion(currentVersion+1);
        return operateAdvertisementMapper.updateByExampleSelective(operateAdvertisement, example);
    }

    @Override
    public List<OperateAdvertisement> selectAll() {
        Example example = new Example(OperateAdvertisement.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("delFlag",false);
        return operateAdvertisementMapper.selectByExample(example);
    }

    @Override
    public OperateAdvertisement selectByUuid(String uuid) {
        return operateAdvertisementMapper.selectByUuid(uuid);
    }

    @Override
    public int selectCount(OperateAdvertisement operateAdvertisement) {
        return operateAdvertisementMapper.selectCount(operateAdvertisement);
    }

    @Override
    public List<OperateAdvertisement> select(OperateAdvertisement var1) {
        return operateAdvertisementMapper.select(var1);
    }

    @Override
    public OperateAdvertisement selectOne(OperateAdvertisement var1) {
        return operateAdvertisementMapper.selectOne(var1);
    }

    @Deprecated
    @Override
    public PageInfo<OperateAdvertisement> queryPage(Integer pageNum, Integer pageSize, OperateAdvertisement operateAdvertisement) {
        return null;
    }

    @Override
    public PageInfo<OperateAdvertisement> queryPage(OperateAdvertisementReq operateAdvertisementReq) {
        PageHelper.startPage(operateAdvertisementReq.getPageNum(), operateAdvertisementReq.getPageSize());
        List<OperateAdvertisement> result = operateAdvertisementMapper.findByPage(operateAdvertisementReq);
        return new PageInfo<>(result);
    }
}
