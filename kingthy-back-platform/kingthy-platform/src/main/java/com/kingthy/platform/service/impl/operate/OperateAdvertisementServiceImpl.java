package com.kingthy.platform.service.impl.operate;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kingthy.platform.dto.operate.OperateAdvertisementReq;
import com.kingthy.platform.entity.operate.OperateAdvertisement;
import com.kingthy.platform.mapper.operate.OperateAdvertisementMapper;
import com.kingthy.platform.service.operate.OperateAdvertisementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @author chenz
 * @version 1.0.0
 * @class_name OperateAdvertisementServiceImpl
 * @description 广告接口实现类
 * @create 2017/8/9
 */
@Service(value = "operateAdvertisementService")
public class OperateAdvertisementServiceImpl implements OperateAdvertisementService{

    @Autowired
    private OperateAdvertisementMapper operateAdvertisementMapper;

    @Override
    public int insert(OperateAdvertisementReq operateAdvertisementReq) {
        OperateAdvertisement operateAdvertisement = new OperateAdvertisement();
        operateAdvertisement.setAdName(operateAdvertisementReq.getAdName());
        operateAdvertisement.setBanners(operateAdvertisementReq.getBanners());
        operateAdvertisement.setDescription(operateAdvertisementReq.getDescription());
        operateAdvertisement.setEndTime(operateAdvertisementReq.getEndTime());
        operateAdvertisement.setHref(operateAdvertisementReq.getHref());
        operateAdvertisement.setLength(operateAdvertisementReq.getLength());
        operateAdvertisement.setLocation(operateAdvertisementReq.getLocation());
        operateAdvertisement.setStartTime(operateAdvertisementReq.getStartTime());
        operateAdvertisement.setStatus(operateAdvertisementReq.getStatus().isValue());
        operateAdvertisement.setWidth(operateAdvertisementReq.getWidth());
        operateAdvertisement.setCreateDate(new Date());
        operateAdvertisement.setCreator("user");
        operateAdvertisement.setDelFlag(false);
        operateAdvertisement.setVersion(1);
        return operateAdvertisementMapper.insert(operateAdvertisement);
    }

    @Override
    public int updateByUuid(OperateAdvertisement operateAdvertisement) {
        Example example = new Example(OperateAdvertisement.class);
        Example.Criteria  criteria = example.createCriteria();
        criteria.andEqualTo("uuid",operateAdvertisement.getUuid());
        return operateAdvertisementMapper.updateByExampleSelective(operateAdvertisement,example);
    }

    @Override
    public int deleteByUuid(String uuid) {
        Example example = new Example(OperateAdvertisement.class);
        Example.Criteria  criteria = example.createCriteria();
        criteria.andEqualTo("uuid",uuid);
        OperateAdvertisement operateAdvertisement = new OperateAdvertisement();
        operateAdvertisement.setDelFlag(true);
        operateAdvertisement.setUuid(uuid);
        return operateAdvertisementMapper.updateByExampleSelective(operateAdvertisement,example);
    }

    @Override
    public OperateAdvertisement selectByUuid(String uuid) {
        Example example = new Example(OperateAdvertisement.class);
        Example.Criteria  criteria = example.createCriteria();
        criteria.andEqualTo("uuid",uuid);
        List<OperateAdvertisement> list = operateAdvertisementMapper.selectByExample(example);
        if (list !=null && list.size()>0){
            return list.get(0);
        }
        return new OperateAdvertisement();
    }

    @Override
    public PageInfo<OperateAdvertisement> queryPage(OperateAdvertisementReq operateAdvertisementReq) {
        PageHelper.startPage(operateAdvertisementReq.getPageNum(),operateAdvertisementReq.getPageSize());
        List<OperateAdvertisement> result = operateAdvertisementMapper.findByPage(operateAdvertisementReq);
        PageInfo<OperateAdvertisement> pageInfo = new PageInfo<OperateAdvertisement>(result);
        return pageInfo;
    }
}
