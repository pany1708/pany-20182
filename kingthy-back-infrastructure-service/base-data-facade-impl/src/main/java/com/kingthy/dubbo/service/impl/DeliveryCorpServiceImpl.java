package com.kingthy.dubbo.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kingthy.dubbo.basedata.service.DeliveryCorpDubboService;
import com.kingthy.entity.DeliveryCorp;
import com.kingthy.entity.TagCategory;
import com.kingthy.mapper.DeliveryCorpMapper;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

/**
 * @author zhaochen 2017/8/25.
 */
@Service(version = "1.0.0",timeout = 3000)
public class DeliveryCorpServiceImpl implements DeliveryCorpDubboService {

    @Autowired
    private  DeliveryCorpMapper deliveryCorpMapper;

    @Override
    public int insert(DeliveryCorp deliveryCorp) {
        deliveryCorp.setVersion(1L);
        return deliveryCorpMapper.insertSelective(deliveryCorp);
    }

    @Override
    public int updateByUuid(DeliveryCorp deliveryCorp) {
        DeliveryCorp deliveryCorpResult = selectByUuid(deliveryCorp.getCode());
        Long currentVersion = deliveryCorpResult.getVersion();
        Date currentDate = new Date();
        deliveryCorp.setModifyDate(currentDate);
        Example example = new Example(TagCategory.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("delFlag", false);
        criteria.andEqualTo("code", deliveryCorp.getCode());
        criteria.andEqualTo("version",currentVersion);
        deliveryCorp.setVersion(currentVersion+1L);
        return deliveryCorpMapper.updateByExampleSelective(deliveryCorp, example);
    }

    @Override
    public List<DeliveryCorp> selectAll() {
        return deliveryCorpMapper.selectAllDeliveryCorp();
    }

    @Override
    public DeliveryCorp selectByUuid(String uuid) {
        DeliveryCorp cond=new DeliveryCorp();
        cond.setCode(uuid);
        return deliveryCorpMapper.selectOne(cond);
    }

    @Override
    public int selectCount(DeliveryCorp deliveryCorp) {
        return deliveryCorpMapper.selectCount(deliveryCorp);
    }

    @Override
    public List<DeliveryCorp> select(DeliveryCorp var1) {
        return deliveryCorpMapper.select(var1);
    }

    @Override
    public DeliveryCorp selectOne(DeliveryCorp var1) {
        return deliveryCorpMapper.selectOne(var1);
    }

    @Override
    public PageInfo<DeliveryCorp> queryPage(Integer pageNum, Integer pageSize, DeliveryCorp deliveryCorp) {
        PageHelper.startPage(pageNum, pageSize);
        List<DeliveryCorp> countries = deliveryCorpMapper.select(deliveryCorp);
        return new PageInfo<>(countries);
    }
}
