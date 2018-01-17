package com.kingthy.dubbo.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kingthy.dubbo.order.service.AfterSaleScheduleDubboService;
import com.kingthy.entity.AfterSaleSchedule;
import com.kingthy.mapper.AfterSaleScheduleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @author xumin
 * @Description:
 * @DATE Created by 10:17 on 2017/8/7.
 * @Modified by:
 */
@Service(version = "1.0.0", timeout = 10000)
public class AfterSaleScheduleDubboServiceImpl implements AfterSaleScheduleDubboService {

    @Autowired
    private AfterSaleScheduleMapper afterSaleScheduleMapper;

    @Override
    public int insert(AfterSaleSchedule saleSchedule) {
        return afterSaleScheduleMapper.insert(saleSchedule);
    }

    @Override
    public int updateByUuid(AfterSaleSchedule var) {

        Example example = new Example(AfterSaleSchedule.class);
        example.createCriteria().andEqualTo("uuid", var.getUuid());

        return afterSaleScheduleMapper.updateByExampleSelective(var, example);
    }

    @Override
    public List<AfterSaleSchedule> selectAll() {
        return afterSaleScheduleMapper.selectAll();
    }

    @Override
    public AfterSaleSchedule selectByUuid(String uuid) {

        AfterSaleSchedule var1 = new AfterSaleSchedule();
        var1.setUuid(uuid);

        return selectOne(var1);
    }

    @Override
    public int selectCount(AfterSaleSchedule saleSchedule) {
        return afterSaleScheduleMapper.selectCount(saleSchedule);
    }

    @Override
    public List<AfterSaleSchedule> select(AfterSaleSchedule var1) {
        return afterSaleScheduleMapper.select(var1);
    }

    @Override
    public AfterSaleSchedule selectOne(AfterSaleSchedule var1) {
        return afterSaleScheduleMapper.selectOne(var1);
    }

    @Override
    public PageInfo<AfterSaleSchedule> queryPage(Integer pageNum, Integer pageSize, AfterSaleSchedule var) {

        PageHelper.startPage(pageNum, pageSize);

        return new PageInfo<>(select(var));
    }

    @Override
    public int updateSelectiveByOrderSn(AfterSaleSchedule var) {
        return afterSaleScheduleMapper.updateSelectiveByOrderSn(var);
    }

    @Override
    public List<AfterSaleSchedule> selectScheduleByOrderSn(String orderSn) {
        return afterSaleScheduleMapper.selectScheduleByOrderSn(orderSn);
    }
}
