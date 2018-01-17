package com.kingthy.platform.service.impl.operate;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kingthy.platform.dto.operate.OperateFashionCollectionReq;
import com.kingthy.platform.entity.operate.OperateFashionCollection;
import com.kingthy.platform.entity.operate.OperateSalesPromotion;
import com.kingthy.platform.mapper.operate.OperateFashionCollectionMapper;
import com.kingthy.platform.service.operate.OperateFashionCollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @author chenz
 * @version 1.0.0
 * @class_name OperateFashionCollectionServiceImpl
 * @description 潮搭接口实现类
 * @create 2017/8/9
 */
@Service(value = "operateFashionCollectionService")
public class OperateFashionCollectionServiceImpl implements OperateFashionCollectionService {

    @Autowired
    private OperateFashionCollectionMapper operateFashionCollectionMapper;

    @Override
    public int insert(OperateFashionCollectionReq operateFashionCollectionReq) {
        OperateFashionCollection operateFashionCollection = new OperateFashionCollection();
        operateFashionCollection.setBanners(operateFashionCollectionReq.getBanners());
        operateFashionCollection.setClothModel(operateFashionCollectionReq.getClothModel());
        operateFashionCollection.setClothModelPic(operateFashionCollectionReq.getClothModelPic());
        operateFashionCollection.setCollectionName(operateFashionCollectionReq.getCollectionName());
        operateFashionCollection.setColor(operateFashionCollectionReq.getColor());
        operateFashionCollection.setOccasion(operateFashionCollectionReq.getOccasion());
        operateFashionCollection.setStatus(operateFashionCollectionReq.getStatus().isValue());
        operateFashionCollection.setStyle(operateFashionCollectionReq.getStyle());
        operateFashionCollection.setTemperature(operateFashionCollectionReq.getTemperature());
        operateFashionCollection.setCreateDate(new Date());
        operateFashionCollection.setCreator("user");
        operateFashionCollection.setDelFlag(false);
        operateFashionCollection.setVersion(1);
        return operateFashionCollectionMapper.insert(operateFashionCollection);
    }

    @Override
    public int updateByUuid(OperateFashionCollection operateFashionCollection) {
        Example example = new Example(OperateFashionCollection.class);
        Example.Criteria  criteria = example.createCriteria();
        criteria.andEqualTo("uuid",operateFashionCollection.getUuid());
        return operateFashionCollectionMapper.updateByExampleSelective(operateFashionCollection,example);
    }

    @Override
    public int deleteByUuid(String uuid) {
        Example example = new Example(OperateFashionCollection.class);
        Example.Criteria  criteria = example.createCriteria();
        criteria.andEqualTo("uuid",uuid);
        OperateFashionCollection operateSalesPromotion = new OperateFashionCollection();
        operateSalesPromotion.setDelFlag(true);
        operateSalesPromotion.setUuid(uuid);
        return operateFashionCollectionMapper.updateByExampleSelective(operateSalesPromotion,example);
    }

    @Override
    public OperateFashionCollection selectByUuid(String uuid) {
        Example example = new Example(OperateSalesPromotion.class);
        Example.Criteria  criteria = example.createCriteria();
        criteria.andEqualTo("uuid",uuid);
        List<OperateFashionCollection> list = operateFashionCollectionMapper.selectByExample(example);
        if (list !=null && list.size()>0){
            return list.get(0);
        }
        return new OperateFashionCollection();
    }

    @Override
    public PageInfo<OperateFashionCollection> queryPage(OperateFashionCollectionReq operateFashionCollectionReq) {
        PageHelper.startPage(operateFashionCollectionReq.getPageNum(),operateFashionCollectionReq.getPageSize());
        List<OperateFashionCollection> result = operateFashionCollectionMapper.findByPage(operateFashionCollectionReq);
        PageInfo<OperateFashionCollection> pageInfo = new PageInfo<OperateFashionCollection>(result);
        return pageInfo;
    }
}
