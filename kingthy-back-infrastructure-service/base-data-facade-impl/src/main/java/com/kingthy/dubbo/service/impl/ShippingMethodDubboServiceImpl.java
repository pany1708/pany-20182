package com.kingthy.dubbo.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kingthy.dto.ShippingInfoDTO;
import com.kingthy.dubbo.basedata.service.ShippingMethodDubboService;
import com.kingthy.entity.ShippingMethod;
import com.kingthy.mapper.ShippingMethodMapper;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @author zhaochen
 * @Description:
 * @DATE Created by 10:18 on 2017/8/10.
 * @Modified by:
 */
@Service(version = "1.0.0",timeout = 3000)
public class ShippingMethodDubboServiceImpl implements ShippingMethodDubboService {

    @Autowired
    private ShippingMethodMapper shippingMethodMapper;

    @Override
    public int insert(ShippingMethod shippingMethod) {
        shippingMethod.setVersion(1);
        return shippingMethodMapper.insert(shippingMethod);
    }

    @Override
    public int updateByUuid(ShippingMethod var) {
        Example example = new Example(ShippingMethod.class);
        example.createCriteria().andEqualTo("uuid", var.getUuid());
        return shippingMethodMapper.updateByExampleSelective(var, example);
    }

    @Override
    public List<ShippingMethod> selectAll() {
        return shippingMethodMapper.selectAll();
    }

    @Override
    public ShippingMethod selectByUuid(String uuid) {
        Example example = new Example(ShippingMethod.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("uuid",uuid);
        List<ShippingMethod> shippingMethodList = shippingMethodMapper.selectByExample(example);
        if (shippingMethodList!=null && shippingMethodList.size()>0){
            return shippingMethodList.get(0);
        }
        return new ShippingMethod();
    }

    @Override
    public int selectCount(ShippingMethod var) {
        return shippingMethodMapper.selectCount(var);
    }

    @Override
    public List<ShippingMethod> select(ShippingMethod var1) {
        return shippingMethodMapper.select(var1);
    }

    @Override
    public ShippingMethod selectOne(ShippingMethod var1) {
        return shippingMethodMapper.selectOne(var1);
    }

    @Override
    public PageInfo<ShippingMethod> queryPage(Integer pageNum, Integer pageSize, ShippingMethod var) {
        PageHelper.startPage(pageNum, pageSize);
        return new PageInfo<>(shippingMethodMapper.select(var));
    }

    @Override
    public List<ShippingInfoDTO> selectShippingInfoByAreaUuid(Long areaUuid) {
        return shippingMethodMapper.selectShippingInfoByAreaUuid(areaUuid);
    }
}
